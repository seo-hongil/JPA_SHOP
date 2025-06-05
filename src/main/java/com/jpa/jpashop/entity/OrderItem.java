package com.jpa.jpashop.entity;

import com.jpa.jpashop.entity.item.Item;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class OrderItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="item_id")
    private Item item;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    private Order order;

    private int price;

    private int count;

    private OrderItem(Item item, int price, int count) {
        this.item = item;
        this.price = price;
        this.count = count;
    }

    /*주문 생성에 따른 재고 변경*/
    public static OrderItem createOrderItem(Item item, int price, int count) {
        OrderItem orderItem = new OrderItem(item, price, count);
        item.removeStock(count);
        return orderItem;
    }

    /*주문 조회 시 상품 가격 조회*/
    public int getTotalPrice() {
        return getPrice() * getCount();
    }

    /*주문 취소에 따른 재고 변경*/
    public void cancel() {
        item.addStock(count);
    }
}
