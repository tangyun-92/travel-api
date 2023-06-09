package com.tang.travel.service.scenery.impl;

import com.tang.travel.model.dao.SceneryCategoryMapper;
import com.tang.travel.model.pojo.SceneryCategory;
import com.tang.travel.service.scenery.SceneryCategoryService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class SceneryCategoryServiceImpl implements SceneryCategoryService {

    @Resource
    private SceneryCategoryMapper sceneryCategoryMapper;

    @Override
    public List<SceneryCategory> list() {
        return sceneryCategoryMapper.selectByExample(null);
    }
}
