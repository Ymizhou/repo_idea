package com.lagou.controller;

import com.lagou.dao.ResourceCategoryMapper;
import com.lagou.domain.ResourceCategory;
import com.lagou.domain.ResponseResult;
import com.lagou.service.ResourceCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/ResourceCategory")
public class ResourceCategoryController {

    @Autowired
    private ResourceCategoryService resourceCategoryService;

    /*
        查询所有资源分类
     */
    @RequestMapping("/findAllResourceCategory")
    public ResponseResult findAllResourceCategory() {

        // 调用service方法
        List<ResourceCategory> list = resourceCategoryService.findAllResourceCategory();

        // 封装数据并返回
        ResponseResult responseResult = new ResponseResult(true, 200, "查询所有资源分类成功", list);

        return responseResult;

    }

    /*
        添加& 修改资源分类
     */
    @RequestMapping("/saveOrUpdateResourceCategory")
    public ResponseResult saveOrUpdateResourceCategory(@RequestBody ResourceCategory resourceCategory){

        // 判断为添加操作还是修改操作
        if (resourceCategory.getId() == null) {
            // 新增操作

            // 调用service方法
            resourceCategoryService.saveResourceCategory(resourceCategory);

            // 封装数据并返回
            ResponseResult responseResult = new ResponseResult(true, 200, "添加资源分类成功", null);
            return responseResult;
        }else {
            // 修改操作

            // 调用service方法
            resourceCategoryService.updateResourceCategory(resourceCategory);

            // 封装数据并返回
            ResponseResult responseResult = new ResponseResult(true, 200, "修改资源分类成功", null);
            return responseResult;
        }

    }

    /*
        删除资源分类
     */
    @RequestMapping("/deleteResourceCategory")
    public ResponseResult deleteResourceCategory(Integer id){

        // 调用service方法
        resourceCategoryService.deleteResourceCategory(id);

        // 封装数据并返回
        ResponseResult responseResult = new ResponseResult(true, 200, "删除资源分类成功", null);
        return responseResult;
    }

}
