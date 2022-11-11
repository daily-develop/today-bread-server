package com.github.org.todaybread.todaybread.customer.application.service;

import com.github.org.todaybread.todaybread.customer.domain.Customer;
import com.github.org.todaybread.todaybread.customer.exception.NotFoundCustomer;
import com.github.org.todaybread.todaybread.customer.infra.persistence.CustomerRepositoryImpl;
import com.github.org.todaybread.todaybread.member.domain.Member;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class CustomerServiceImpl implements CustomerService {

    private final CustomerRepositoryImpl customerRepository;

    @Override
    @Transactional
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }

    @Override
    public Customer getByMember(Member member) {
        return customerRepository.getByMember(member).orElseThrow(NotFoundCustomer::new);
    }
}
