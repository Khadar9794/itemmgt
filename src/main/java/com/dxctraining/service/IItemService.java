package com.dxctraining.service;

import com.dxctraining.entities.Item;

public interface IItemService {
    public Item findItem(int id);
    Item update(Item item);
    void removeItem(int id);
}
