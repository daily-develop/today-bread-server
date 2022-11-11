package com.github.org.todaybread.todaybread.customer.infra.persistence;

import com.github.org.todaybread.todaybread.customer.domain.Customer;
import com.github.org.todaybread.todaybread.member.domain.Member;
import java.util.Optional;
import java.util.UUID;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerJpaRepository extends JpaRepository<Customer, UUID> {

    Optional<Customer> findByMember(Member member);
}
