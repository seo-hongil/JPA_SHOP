package com.jpa.jpashop.entity.item;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@DiscriminatorValue("A")
@NoArgsConstructor
public class Album extends Item{

    private String artist;

    private String etc;

    public Album(String name, int price, int stock, String artist, String etc) {
        super(name, price, stock);
        this.artist = artist;
        this.etc = etc;
    }
}
