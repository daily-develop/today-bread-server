package com.github.org.todaybread.todaybread.customer.application.service;

import com.github.org.todaybread.todaybread.customer.domain.Customer;
import com.github.org.todaybread.todaybread.member.domain.Member;

public interface CustomerService {

    Customer save(Customer customer);

    Customer getByMember(Member member);
}
