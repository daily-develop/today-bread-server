package com.github.org.todaybread.todaybread.member.application.service;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.graphql.tester.AutoConfigureGraphQlTester;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
@AutoConfigureGraphQlTester
public class MemberServiceImplTest {

    @Autowired
    private MemberServiceImpl memberService;

    @Test
    @DisplayName("멤버를 찾아올 수 있어요.")
    public void getMember() {

    }
}
