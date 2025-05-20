package com.jpa.jpashop.repository;


import com.jpa.jpashop.entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
