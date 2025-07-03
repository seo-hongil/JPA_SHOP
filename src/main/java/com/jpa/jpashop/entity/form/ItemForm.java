package com.jpa.jpashop.entity.form;

import lombok.Data;

@Data
public class ItemForm {

    private Long id;
    private String name;
    private int price;
    private int stock;

    private String author;
    private String isbn;

    private String artist;
    private String etc;

    private String director;
    private String actor;

    private String tag;


}
