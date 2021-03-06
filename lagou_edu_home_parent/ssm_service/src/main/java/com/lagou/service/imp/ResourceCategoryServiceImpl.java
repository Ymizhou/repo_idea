package com.lagou.service.imp;

import com.lagou.dao.ResourceCategoryMapper;
import com.lagou.domain.ResourceCategory;
import com.lagou.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class ResourceCategoryServiceImpl implements ResourceCategoryService {

    @Autowired
    private ResourceCategoryMapper resourceCategoryMapper;

    /*
        查询所有资源分类
     */
    @Override
    public List<ResourceCategory> findAllResourceCategory() {

        List<ResourceCategory> list = resourceCategoryMapper.findAllResourceCategory();
        return list;
    }

    /*
        新增资源分类
     */
    @Override
    public void saveResourceCategory(ResourceCategory resourceCategory) {

        // 补全信息
        Date date = new Date();
        resourceCategory.setCreatedTime(date);
        resourceCategory.setUpdatedTime(date);
        resourceCategory.setCreatedBy("system");
        resourceCategory.setUpdatedBy("system");

        // 调用mapper方法
        resourceCategoryMapper.saveResourceCategory(resourceCategory);

    }

    /*
        修改资源分类
     */
    @Override
    public void updateResourceCategory(ResourceCategory resourceCategory) {

        // 补全信息
        Date date = new Date();
        resourceCategory.setUpdatedTime(date);

        // 调用mapper方法
        resourceCategoryMapper.updateResourceCategory(resourceCategory);
    }

    /*
        删除资源分类
     */
    @Override
    public void deleteResourceCategory(Integer id) {

        // 调用mapper方法
        resourceCategoryMapper.deleteResourceCategory(id);

    }
}
