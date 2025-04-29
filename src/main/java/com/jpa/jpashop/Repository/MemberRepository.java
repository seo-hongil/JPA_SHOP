package com.jpa.jpashop.Repository;


import com.jpa.jpashop.Entity.Member;
import org.springframework.data.jpa.repository.JpaRepository;

public interface MemberRepository extends JpaRepository<Member, Long> {
}
