package com.newland.item.web;

import com.newland.item.pojo.Category;
import com.newland.item.service.CategoryServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("category")
public class CategoryConroller {

    @Autowired
    private CategoryServiceImpl categoryService;

    /**
     * 根据父节点id查询子节点
     * @param pid
     * @return
     */
    @GetMapping("list")
    public ResponseEntity<List<Category>>queryCategoryByPid(@RequestParam("pid")Long pid){
        return ResponseEntity.ok(categoryService.queryCategoryByPid(pid));
    }
}
