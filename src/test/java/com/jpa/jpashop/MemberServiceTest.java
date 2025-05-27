package com.jpa.jpashop;

import com.jpa.jpashop.entity.Address;
import com.jpa.jpashop.entity.Member;
import com.jpa.jpashop.repository.MemberRepository;
import com.jpa.jpashop.service.MemberService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
@Transactional
public class MemberServiceTest {
    @Autowired
    MemberService memberService;

    @Autowired
    MemberRepository memberRepository;

    @Test
    public void join_test(){
        //given
        Member member = new Member("seo", new Address("서울", "강변북로","123"));

        //when
        memberService.join(member);

        //then
        assertThat(member).isEqualTo(memberRepository.findById(member.getId()).get(0));
    }

    @Test
    public void member_check(){
        //given
        Member member = new Member("seo", new Address("서울", "강변북로", "123"));
        Member member2 = new Member("seo", new Address("서울", "강변북로", "123"));

        //when
        memberService.join(member);

        //then
        assertThrows(IllegalStateException.class, () -> memberService.join(member2));
    }
}
