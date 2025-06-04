package com.jpa.jpashop.entity.item;

import com.jpa.jpashop.entity.Category;
import com.jpa.jpashop.exception.NotEnoughStockException;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "dtype")
@NoArgsConstructor
public abstract class Item {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "item_id")
    private Long id;

    protected String name;

    protected int price;

    protected int stock;

    @ManyToMany(mappedBy = "items")
    private List<Category> categories = new ArrayList();

    public Item(String name, int price, int stock) {
        this.name = name;
        this.price = price;
        this.stock = stock;
    }

    public void addStock(int quantity){
        stock += quantity;
    }

    public void removeStock(int quantity){
        int restStock = stock - quantity;

        if(restStock < 0){
            throw new NotEnoughStockException("재고가 부족합니다.");
        }
        this.stock = restStock;
    }
}
