package com.jpa.jpashop.repository;

import com.jpa.jpashop.entity.Member;
import com.jpa.jpashop.entity.item.Item;
import jakarta.persistence.EntityManager;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.security.PublicKey;
import java.util.List;

@Repository
@RequiredArgsConstructor
public class ItemRepository {

    private final EntityManager em;

    public void save(Item item){
        if(item.getId() == null){

        }else{
            em.merge(item);
        }
    }

    public Item findOne(Long id){
        return em.find(Item.class,id);
    }

    public List<Item> findAll(){ return em.createQuery("select i from Item i", Item.class)
            .getResultList();}
}
