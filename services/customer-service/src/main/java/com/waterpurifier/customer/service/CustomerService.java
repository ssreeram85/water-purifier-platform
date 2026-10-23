package com.waterpurifier.customer.service;


import com.waterpurifier.customer.controller.requestDto.CustomerRequest;
import com.waterpurifier.customer.controller.responseDto.CustomerResponse;
import com.waterpurifier.customer.entity.CustomerEntity;
import com.waterpurifier.customer.exception.DuplicateResourceException;
import com.waterpurifier.customer.repository.CustomerRepository;
import com.waterpurifier.customer.service.mapper.CustomerMapper;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
@Transactional(readOnly = false)
@AllArgsConstructor
public class CustomerService {


    private final CustomerRepository customerRepository;
    private final CustomerMapper customerMapper;

    @Transactional
    public CustomerResponse registrCustomer(CustomerRequest customerRequest) {

        guardDuplicates(customerRequest.mobileNumber(), customerRequest.email(), null);
        CustomerEntity customerEntity = customerMapper.toEntity(customerRequest);
        customerEntity = customerRepository.save(customerEntity);
        return customerMapper.toResponse(customerEntity);

    }


    private void guardDuplicates(String mobileNumber, String email, UUID id) {
        if (mobileNumber != null && !mobileNumber.isBlank()) {
            String normalised = mobileNumber.replaceAll("[\\s-]", "");
            boolean taken = id == null
                    ? customerRepository.existsByMobileNumber(normalised)
                    : customerRepository.existsByMobileNumberAndIdNot(normalised, id);
            if (taken) {
                throw new DuplicateResourceException("A customer with this mobile number already exists");
            }
        }
        if (email != null && !email.isBlank()) {
            String normalised = email.trim().toLowerCase();
            boolean taken = id == null
                    ? customerRepository.existsByEmailIgnoreCase(normalised)
                    : customerRepository.existsByEmailIgnoreCaseAndIdNot(normalised, id);
            if (taken) {
                throw new DuplicateResourceException("A customer with this email already exists");
            }
        }
    }


}
