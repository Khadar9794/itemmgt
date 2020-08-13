package com.dxctraining.dao;

import com.dxctraining.entities.Item;
import com.dxctraining.exceptions.ItemNotFoundException;

import javax.persistence.Entity;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.stereotype.Repository;

@Repository
public class ItemImplementation implements ItemDao {
@PersistenceContext
private EntityManager entityManager;

    @Override
    public Item findItem(int id) {
        Item item=entityManager.find(Item.class,id);
        if(item==null)
        {
            throw new ItemNotFoundException("item not found");

        }
        return item;
    }

    @Override
    public Item update(Item item) {
        item=entityManager.merge(item);
        return item;
    }

    @Override
    public void removeItem(int id)
    {
        Item item=entityManager.find(Item.class,id);
     entityManager.remove(id);

    }

}
