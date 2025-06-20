package com.jpa.jpashop;

import com.jpa.jpashop.entity.Address;
import com.jpa.jpashop.entity.Member;
import com.jpa.jpashop.entity.Order;
import com.jpa.jpashop.entity.OrderStatus;
import com.jpa.jpashop.entity.item.Album;
import com.jpa.jpashop.entity.item.Book;
import com.jpa.jpashop.exception.NotEnoughStockException;
import com.jpa.jpashop.repository.OrderRepository;
import com.jpa.jpashop.service.OrderService;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
class OrderServiceTest {

    @Autowired
    EntityManager em;

    @Autowired
    OrderService orderService;

    @Autowired
    OrderRepository orderRepository;

    @Test
    public void 상품주문() {
        //given
        Member member = new Member("kim", new Address("서울", "도산대로", "12345"));
        Book book = new Book("jpaBook", 10000, 10, "han", "12345");

        em.persist(member);
        em.persist(book);

        //when
        Long orderId = orderService.order(member.getId(),book.getId(),2);

        //then
        Order getOrder = orderRepository.findById(orderId).orElseGet(null);
        assertThat(OrderStatus.ORDER).isEqualTo(getOrder.getStatus());
        assertThat(1).isEqualTo(getOrder.getOrderItems().size());
        assertThat(20000).isEqualTo(getOrder.getTotalPrice());
        assertThat(8).isEqualTo(book.getStock());
    }

    @Test
    public void 상품주문_재고수량초과() {
        //given
        Member member = new Member("kim", new Address("서울", "도산대로", "12345"));
        Book book = new Book("jpaBook", 10000, 10, "han", "12345");

        em.persist(member);
        em.persist(book);

        //when
        //then
        assertThrows(NotEnoughStockException.class, () -> orderService.order(member.getId(), book.getId(),11));
    }

    @Test
    public void 주문취소() {
        //given
        Member member = new Member("kim", new Address("서울", "도산대로", "12345"));
        Album album = new Album("jpaAlbum", 20000, 20, "yeong", "cool");

        em.persist(member);
        em.persist(album);

        Long orderId = orderService.order(member.getId(), album.getId(),3);

        //when
        orderService.cancelOrder(orderId);

        //then
        Order getOrder = orderRepository.findById(orderId).orElseGet(null);
        assertThat(OrderStatus.CANCEL).isEqualTo(getOrder.getStatus());
        assertThat(album.getStock()).isEqualTo(20);
    }

}