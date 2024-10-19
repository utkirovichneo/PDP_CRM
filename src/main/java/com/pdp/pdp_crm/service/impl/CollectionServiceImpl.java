package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.dto.collection.CollectionDTO;
import com.pdp.pdp_crm.entity.Collection;
import com.pdp.pdp_crm.entity.Group;
import com.pdp.pdp_crm.entity.Student;
import com.pdp.pdp_crm.enums.CollectionStatus;
import com.pdp.pdp_crm.enums.GroupStatus;
import com.pdp.pdp_crm.mapper.CollectionMapper;
import com.pdp.pdp_crm.repository.CollectionRepository;
import com.pdp.pdp_crm.repository.GroupRepository;
import com.pdp.pdp_crm.service.CollectionService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.*;

@Service
@RequiredArgsConstructor
public class CollectionServiceImpl implements CollectionService {

    private final CollectionRepository collectionRepository;
    private final GroupServiceImpl groupServiceImpl;
    private final GroupRepository groupRepository;
    private final InvoiceServiceImpl invoiceServiceImpl;
    private final CollectionMapper collectionMapper;


    @Autowired
    @Lazy
    public void setCollectionServiceImpl(StudentServiceImpl studentServiceImpl){
        this.studentServiceImpl = studentServiceImpl;
    }
    private StudentServiceImpl studentServiceImpl;

    @Override
    public List<Long> createCollection(Long centerId, Long groupId) {

        var list = new ArrayList<Long>();

        var group = groupServiceImpl.findById(centerId, groupId)
                .orElseThrow(() -> new RuntimeException("Group not found"));

        List<Student> students = studentServiceImpl.findAllStudent(centerId, groupId);

        for (Student student : students) {

            Collection collection = Collection.builder()
                    .amount(group.getCourse().getPrice())
                    .student(student)
                    .status(CollectionStatus.NEW)
                    .build();

            list.add(collectionRepository.save(collection).getId());
        }
        return list;
    }

    @Override
    public Optional<Collection> findById(Long collectionId) {
        return collectionRepository.findById(collectionId);
    }

    @Override
    @Scheduled(cron = "0 0 */3 * * *")
    public Boolean schedulingCollection() {

        List<Group> groups = groupServiceImpl.findAll();

        for (Group group : groups) {
            var course = group.getCourse();

            if(Objects.equals(group.getCurrentCountOfStage(), course.getCountOfLessons())){
                if(Objects.equals(group.getCurrentStage(), course.getDuration())){
                    group.setStatus(GroupStatus.INACTIVE);
                        groupRepository.save(group);
                }
                else{
                    group.setCurrentCountOfStage(1L);
                    group.setCurrentStage(group.getCurrentStage() + 1L);
                    groupRepository.save(group);

                    List<Long> collectionIds = createCollection(group.getCenter().getId(), group.getId());

                    for (Long collectionId : collectionIds) {
                        invoiceServiceImpl.createInvoice(group.getCenter().getId(), collectionId);
                    }

                    groupServiceImpl.start(group.getCenter().getId(), group.getId());

                }
            }
        }

        return Boolean.TRUE;
    }

    @Override
    public CollectionDTO getCollection(Long centerId, Long studentId) {

        List<Collection> collections = collectionRepository.findAllByStudentId(studentId);

        for (Collection collection : collections) {
            if(collection.getStudent().getGroup().getCenter().getId().equals(centerId)){
                if (!collection.getStatus().equals(CollectionStatus.COMPLETED)) {
                    return collectionMapper.toDto(collection);
                }
            }
        }
        return null;
    }

    @Override
    public Optional<Collection> findByIdAndCenterId(Long id, Long centerId) {
        Optional<Collection> collection = collectionRepository.findById(id);
        if (collection.orElseThrow(() -> new RuntimeException("Collection not found")).getStudent().getGroup().getCenter().getId() == centerId) {
            return collection;
        }
        return Optional.empty();
    }
}
