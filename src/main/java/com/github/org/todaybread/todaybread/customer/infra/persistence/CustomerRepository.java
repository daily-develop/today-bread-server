package com.github.org.todaybread.todaybread.customer.infra.persistence;

import com.github.org.todaybread.todaybread.customer.domain.Customer;
import com.github.org.todaybread.todaybread.member.domain.Member;
import java.util.Optional;

public interface CustomerRepository {

    Optional<Customer> getByMember(Member member);

    Customer save(Customer customer);
}
