package com.pdp.pdp_crm.service.impl;

import com.pdp.pdp_crm.dto.financeincome.FinanceIncomeDTO;
import com.pdp.pdp_crm.dto.financeincome.FinanceIncomeRequestDTO;
import com.pdp.pdp_crm.dto.payment.PaymentDTO;
import com.pdp.pdp_crm.dto.payment.PaymentRequestDTO;
import com.pdp.pdp_crm.entity.Payment;
import com.pdp.pdp_crm.enums.FinanceStatus;
import com.pdp.pdp_crm.filter.PageableRequest;
import com.pdp.pdp_crm.filter.PageableRequestUtil;
import com.pdp.pdp_crm.filter.SearchCriteria;
import com.pdp.pdp_crm.filter.SearchSpecification;
import com.pdp.pdp_crm.mapper.PaymentMapper;
import com.pdp.pdp_crm.repository.PaymentRepository;
import com.pdp.pdp_crm.service.CollectionService;
import com.pdp.pdp_crm.service.FinanceIncomeService;
import com.pdp.pdp_crm.service.PaymentService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class PaymentServiceImpl implements PaymentService {

    private final PaymentRepository paymentRepository;
    private final PaymentMapper paymentMapper;
    private final CollectionService collectionServiceImpl;
    private final FinanceIncomeService financeIncomeService;

    @Override
    public Page<PaymentDTO> findAll(Long centerId, PageableRequest pageableRequest) {
        if(!CollectionUtils.isEmpty(pageableRequest.getSearch())){
            pageableRequest.getSearch().add(new SearchCriteria("collection.student.group.center.id", "=", centerId));
        }
        else{
            pageableRequest.setSearch(List.of(new SearchCriteria("collection.student.group.center.id", "=", centerId)));
        }
        return paymentRepository.findAll(
                new SearchSpecification<>(pageableRequest.getSearch()),
                PageableRequestUtil.toPageable(pageableRequest)
        ).map(paymentMapper::toDto);
    }

    @Override
    public PaymentDTO payment(Long centerId, PaymentRequestDTO dto) {
        PaymentDTO paymentDTO = paymentMapper.toDto(paymentRepository.save(Payment.builder()
                .amount(dto.getAmount())
                .paidDate(dto.getPaidDate())
                .paymentStatus(dto.getPaymentStatus())
                .description(dto.getDescription())
                .collection(collectionServiceImpl.findByIdAndCenterId(dto.getCollectionId(), centerId)
                        .orElseThrow(() -> new RuntimeException("Collection not found")))
                .build()));

        financeIncomeService.makingMoney(
                centerId,
                new FinanceIncomeRequestDTO(null, paymentDTO.getAmount(), dto.getPaidDate(), FinanceStatus.INCOME_STUDENT));

        return paymentDTO;
    }
}
