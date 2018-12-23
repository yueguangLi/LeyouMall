package com.newland.item.service;

import com.newland.common.enums.ExceptionEnums;
import com.newland.common.exception.LyException;
import com.newland.item.mapper.CategoryMapper;
import com.newland.item.pojo.Category;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.Collections;
import java.util.List;

@Service
public class CategoryServiceImpl {

    @Autowired
    private CategoryMapper categoryMapper;

    public List<Category> queryCategoryByPid(Long pid) {
        // 查询结果
        Category t = new Category();
        t.setParentId(pid);
        List<Category> list = categoryMapper.select(t);
        if(CollectionUtils.isEmpty(list)){
            throw new LyException(ExceptionEnums.CATEGOTY_NOT_FOUND);
        }
        return list;
    }
}
