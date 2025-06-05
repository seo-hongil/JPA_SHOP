package com.jpa.jpashop.entity;

import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "orders")
@NoArgsConstructor
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    @JoinColumn(name = "delviery_id")
    private Delivery delivery;

    private LocalDateTime orderDate;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    private int price;

    private Order(Member member, Delivery delivery, LocalDateTime orderDate, OrderStatus status, int totalPrice) {
        this.member = member;
        this.delivery = delivery;
        this.orderDate = orderDate;
        this.status = status;
        this.price = totalPrice;
    }

    /*주문 생성*/
    public static Order createOrder(Member member, Delivery delivery, List<OrderItem> orderItems, int price) {
        Order order = new Order(member, delivery, LocalDateTime.now(), OrderStatus.ORDER, price);

        for (OrderItem orderItem : orderItems) {
            order.getOrderItems().add(orderItem);
        }

        return order;
    }

    /*주문 가격 조회*/
    public int getTotalPrice() {
        int totalPrice = 0;
        for (OrderItem orderItem : orderItems) {
            totalPrice += orderItem.getPrice();
        }
        return totalPrice;
    }
    /*주문 취소*/
    public void cancel() {
        if (delivery.getStatus() == DeliveryStatus.COMP) {
            throw new IllegalStateException("배송완료된 상품은 취소가 불가능합니다.");
        }

        for (OrderItem orderItem : orderItems) {
            orderItem.cancel();
        }

        status = OrderStatus.CANCEL;
    }
}
