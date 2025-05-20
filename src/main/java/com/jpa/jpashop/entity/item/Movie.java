package com.jpa.jpashop.entity.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@DiscriminatorValue("M")
@NoArgsConstructor
public class Movie extends Item{

    private String director;

    private String actor;

    public Movie(String name, int price, int stock, String director, String actor) {
        super(name, price, stock);
        this.director = director;
        this.actor = actor;
    }
}
