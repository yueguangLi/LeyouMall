package com.newland.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newland.common.enums.ExceptionEnums;
import com.newland.common.exception.LyException;
import com.newland.common.vo.PageResult;
import com.newland.item.mapper.SpuDetailMapper;
import com.newland.item.mapper.SpuMapper;
import com.newland.item.pojo.Category;
import com.newland.item.pojo.Spu;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class GoodsServiceImpl {

    @Autowired
    private SpuMapper spuMapper;

    @Autowired
    private SpuDetailMapper detailMapper;

    @Autowired
    private CategoryServiceImpl categoryService;
    @Autowired
    private BrandServiceIpml brandService;
    public PageResult<Spu> querySpuByPage(Integer page, Integer rows, Boolean saleable, String key) {
        // 分页
        PageHelper.startPage(page,rows);
        // 过滤
        Example example = new Example(Spu.class);
        //1、搜索条件过滤
        Example.Criteria criteria = example.createCriteria();
        //2、搜索字段过滤
        if(StringUtils.isNotBlank(key)){
            criteria.andLike("title","%"+key+"%");
        }
        if(saleable!=null){
            criteria.andEqualTo("saleable",saleable);
        }
        // 默认排序
        example.setOrderByClause("last_update_time DESC");
        // 查询
        List<Spu> spus = spuMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(spus)){
            throw new LyException(ExceptionEnums.GOOD_NOT_FOND);
        }
        //解析分类和品牌的名称
        LoadCategoryAndBrandName(spus);
        //解析分页的结果
        PageInfo<Spu> info = new PageInfo<>(spus);
        return new PageResult<>(info.getTotal(),spus);
    }

    /**
     * 解析相应的名字
     * @param spus
     */
    private void LoadCategoryAndBrandName(List<Spu> spus) {
        for(Spu spu:spus){
            //处理分类名称
            List<String> names = categoryService.queryById(Arrays.asList(spu.getCid1(), spu.getCid2(), spu.getCid3())).stream().map(Category::getName).collect(Collectors.toList());
            spu.setCname(StringUtils.join(names,"/"));
            //处理品牌名称
            spu.setBname(brandService.queryById(spu.getBrandId()).getName());

        }
    }
}
