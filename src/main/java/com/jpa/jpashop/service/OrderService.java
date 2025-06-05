package com.jpa.jpashop.service;

import com.jpa.jpashop.entity.*;
import com.jpa.jpashop.entity.item.Item;
import com.jpa.jpashop.repository.ItemRepository;
import com.jpa.jpashop.repository.MemberRepository;
import com.jpa.jpashop.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class OrderService {

    private final OrderRepository orderRepository;
    private final MemberRepository memberRepository;
    private final ItemRepository itemRepository;

    @Transactional
    public Long order(Long memberId, Long itemId, int count) {

        // 엔티티 조회
        Member member = memberRepository.findOne(memberId);
        Item item = itemRepository.findOne(itemId);

        //배송정보 생성
        Delivery delivery = new Delivery(member.getAddress(), DeliveryStatus.READY);

        //주문상품 생성
        List<OrderItem> orderItems = List.of(
                OrderItem.createOrderItem(item, item.getPrice(), count)
        );

        //주문 생성
        Order order = Order.createOrder(member, delivery, orderItems, item.getPrice());

        //주문 저장
        orderRepository.save(order);

        return order.getId();
    }

    @Transactional
    public void cancelOrder(Long orderId) {
        //주문 엔티티 조회
        Order order = orderRepository.findOne(orderId);

        //주문 취소
        order.cancel();
    }
}
