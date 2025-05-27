package com.jpa.jpashop;

import com.jpa.jpashop.entity.Member;
import com.jpa.jpashop.repository.MemberRepository;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;


import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE) // H2 자동 설정
public class H2DatabaseTest {

    @Autowired
    private MemberRepository memberRepository;

    @Test
    public void h2Connect_test() {
       /* // given
        Member member = new Member();
        member.setName("홍길동");

        // when
        memberRepository.save(member);

        // then
        List<Member> users = memberRepository.findAll();
        assertThat(users).isNotEmpty();
        assertThat(users.get(0).getName()).isEqualTo("홍길동");*/
    }
}