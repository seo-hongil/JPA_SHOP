package com.jpa.jpashop.service;

import com.jpa.jpashop.repository.MemberRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@RequiredArgsConstructor
public class MemberService {

    private final MemberRepository memberRepository;
}
