package com.newland.item.service;

import com.newland.item.pojo.Item;
import org.springframework.stereotype.Service;

import java.util.Random;

@Service
public class ItemService {

    public Item saveItem(Item item){
        // 新增商品
        int id = new Random().nextInt(100);
        item.setId(id);
        return item;
    }
}
