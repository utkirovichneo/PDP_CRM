package com.pdp.pdp_crm;

import com.pdp.pdp_crm.entity.Address;
import com.pdp.pdp_crm.entity.Center;
import com.pdp.pdp_crm.exception.NotFoundException;
import com.pdp.pdp_crm.repository.AddressRepository;
import com.pdp.pdp_crm.repository.CenterRepository;
import com.pdp.pdp_crm.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class PdpCrmApplicationTests {

    @Autowired
    private UserRepository userRepository;
    @Autowired
    private CenterRepository centerRepository;
    @Autowired
    private AddressRepository addressRepository;

    @Test
    void test(){
        addressRepository.save(new Address());
    }

    @Test
    void contextLoads() {
        centerRepository.save(Center.builder()
                .name("PDP")
                .user(userRepository.findById(2L).orElseThrow(()-> new RuntimeException("User not found")))
                .email("pdp@mail.ru")
                .legalName("PDP academy")
                .address(addressRepository.findById(3L).orElseThrow(()-> new RuntimeException("Address not found")))
                .description("Description")
                .phone("+998912345678")
                .build());
    }

}
