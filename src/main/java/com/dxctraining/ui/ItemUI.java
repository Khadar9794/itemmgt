package com.dxctraining.ui;

import com.dxctraining.entities.Item;
import com.dxctraining.service.IItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;

@Component

public class ItemUI {
    @Autowired
private IItemService itemService;


    @PostConstruct
    public void runApp()
    {
     try
     {
      Item item1=new Item(1,"pencil");
         Item item2=new Item(2,"book");
         Item item3=new Item(3,"soda");
   int id1=item1.getId();
   Item fetcheditem=itemService.findItem(id1);
System.out.println("The item is"+fetcheditem.getId()+"  "+"The name is"+fetcheditem.getName());


     }
     catch (Throwable e)
     {
         e.printStackTrace();
     }

    }

}
