package com.newland.item.service;

import com.newland.common.enums.ExceptionEnums;
import com.newland.common.exception.LyException;
import com.newland.item.mapper.SpecGroupMapper;
import com.newland.item.mapper.SpecParamMapper;
import com.newland.item.pojo.SpecGroup;
import com.newland.item.pojo.SpecParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;

import java.util.List;

@Service
public class SpecficationService {

    @Autowired
    private SpecGroupMapper groupMapper;

    @Autowired
    private SpecParamMapper paramMapper;

    public List<SpecGroup> queryGroupByCid(Long cid) {
        //查询条件
        SpecGroup specGroup = new SpecGroup();
        specGroup.setCid(cid);
        List<SpecGroup> select = groupMapper.select(specGroup);
        if(CollectionUtils.isEmpty(select)){
            throw  new LyException(ExceptionEnums.SPEC_GROUP_NOT_FOND);
        }
        return select;
    }

    public List<SpecParam> queryParamByGid(Long gid) {

        SpecParam specParam = new SpecParam();
        specParam.setGroupId(gid);
        List<SpecParam> select = paramMapper.select(specParam);
        if(CollectionUtils.isEmpty(select)){
            throw  new LyException(ExceptionEnums.SPEC_PARAM_NOT_FOND);
        }
        return select;
    }
}
