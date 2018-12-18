package com.newland.item.web;

import com.newland.common.enums.ExceptionEnums;
import com.newland.common.exception.LyException;
import com.newland.item.pojo.Item;
import com.newland.item.service.ItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("item")
public class ItemController {

    @Autowired
    private ItemService itemService;

    @PostMapping
    public ResponseEntity<Item> saveIte(Item item){
        // 校验价格
        if(item.getPrice()==null){
         throw new LyException(ExceptionEnums.PRICE_CANNOT_BE_NULL);
        }
        return ResponseEntity.status(HttpStatus.CREATED).body(itemService.saveItem(item));
    }
}
