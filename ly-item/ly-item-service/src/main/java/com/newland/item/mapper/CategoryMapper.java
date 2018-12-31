package com.newland.item.mapper;

import com.newland.item.pojo.Category;
import tk.mybatis.mapper.additional.idlist.IdListMapper;
import tk.mybatis.mapper.common.Mapper;

public interface CategoryMapper extends Mapper<Category> , IdListMapper<Category,Long> {
}
