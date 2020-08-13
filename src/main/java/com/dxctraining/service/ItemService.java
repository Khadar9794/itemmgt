package com.dxctraining.service;

import com.dxctraining.dao.ItemDao;
import com.dxctraining.entities.Item;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Transactional
@Service
public class ItemService implements IItemService{

    @Autowired
private ItemDao itemDao;

    @Override
    public Item findItem(int id) {
        validateId(id);
       Item item=itemDao.findItem(id);
       return  item;
    }

    private void validateId(int id) {
       if(id<0)
       {
           System.out.println("ID can't be negative");
       }
    }

    @Override
    public Item update(Item item) {
        item=itemDao.update(item);
        return item;
    }

    @Override
    public void removeItem(int id) {
    itemDao.removeItem(id);
    }

}
