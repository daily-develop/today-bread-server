package com.github.org.todaybread.todaybread.customer.infra.persistence;

import com.github.org.todaybread.todaybread.customer.domain.Customer;
import com.github.org.todaybread.todaybread.member.domain.Member;
import java.util.Optional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

@Repository
@RequiredArgsConstructor
public class CustomerRepositoryImpl implements CustomerRepository {

    private final CustomerJpaRepository customerRepository;

    @Override
    public Optional<Customer> getByMember(Member member) {
        return customerRepository.findByMember(member);
    }

    @Override
    public Customer save(Customer customer) {
        return customerRepository.save(customer);
    }
}
