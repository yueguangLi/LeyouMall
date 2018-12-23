package com.newland.item.service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.newland.common.enums.ExceptionEnums;
import com.newland.common.exception.LyException;
import com.newland.common.vo.PageResult;
import com.newland.item.mapper.BrandMapper;
import com.newland.item.pojo.Brand;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import tk.mybatis.mapper.entity.Example;

import java.util.List;

@Service
public class BrandServiceIpml {

    @Autowired
    private BrandMapper brandMapper;

    public PageResult<Brand> queryByBrandPage(Integer page, Integer rows, String sortBy, boolean desc, String key) {
        // 分页
        PageHelper.startPage(page, rows);
        // 过滤
        Example example = new Example(Brand.class);
        if (StringUtils.isNotBlank(key)) {
            example.createCriteria().orLike("name", "%" + key + "%").orEqualTo("letter", key.toUpperCase());
        }
        // 排序
        if (StringUtils.isNotBlank(key)) {
            String clause = sortBy+(desc?" DESC":" ASC");
            example.setOrderByClause(clause);
        }
        // 查询

        List<Brand> list = brandMapper.selectByExample(example);
        if(CollectionUtils.isEmpty(list)){
            throw new LyException(ExceptionEnums.BRAND_NOT_FOUND);
        }
        // 解析分页结果
        PageInfo<Brand> brandPageInfo = new PageInfo<>(list);
        return new PageResult<Brand>(brandPageInfo.getTotal(),list);
    }

    /**
     * 保存信息
     * @param brand
     * @param cids
     */
    @Transactional
    public void saveBrand(Brand brand, List<Long> cids) {
        //新增
        brand.setId(null);
        int count = brandMapper.insert(brand);
        if(count!=1){
            throw  new LyException(ExceptionEnums.BRAND_SAVE_ERROR);
        }
        // 新增中间表
        for (Long cid : cids) {
            count = brandMapper.insertCategoryBrand(cid, brand.getId());
            if(count!=1){
                throw  new LyException(ExceptionEnums.BRAND_BRAND_SAVE_ERROR);
            }
        }
    }
}
