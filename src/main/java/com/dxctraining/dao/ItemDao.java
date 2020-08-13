package com.dxctraining.dao;

import com.dxctraining.entities.Item;

public interface ItemDao {
public Item findItem(int id);
Item update(Item item);
void removeItem(int id);
}
