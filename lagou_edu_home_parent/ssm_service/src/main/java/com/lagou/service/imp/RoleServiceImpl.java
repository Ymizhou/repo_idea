package com.lagou.service.imp;

import com.lagou.dao.RoleMapper;
import com.lagou.domain.*;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class RoleServiceImpl implements RoleService {

    @Autowired
    private RoleMapper roleMapper;

    @Override
    public List<Role> findAllRole(Role role) {

        List<Role> list = roleMapper.findAllRole(role);

        return list;
    }

    /*
        根据角色id查询对应的菜单信息id
     */
    public List<Integer> findMenuByRoleId(Integer roleId){

        List<Integer> list = roleMapper.findMenuByRoleId(roleId);

        return list;

    }

    /*
        为角色分配菜单
     */
    public void roleContextMenu(RoleMenuVO roleMenuVO){

        // 1.清空中间表的关联关系
        roleMapper.deleteRoleContextMenu(roleMenuVO.getRoleId());

        // 2.为角色分配菜单
        Role_menu_relation role_menu_relation = new Role_menu_relation();
        Date date = new Date();
        List<Integer> menuIdList = roleMenuVO.getMenuIdList();

        for (Integer integer : menuIdList) {

            // 封装数据
            role_menu_relation.setMenuId(integer);
            role_menu_relation.setRoleId(roleMenuVO.getRoleId());
            role_menu_relation.setCreatedTime(date);
            role_menu_relation.setUpdatedTime(date);
            role_menu_relation.setCreatedBy("system");
            role_menu_relation.setUpdatedby("system");

            // 调用mapper方法
            roleMapper.roleContextMenu(role_menu_relation);

        }

    }

    /*
        删除角色
     */
    public void deleteRole(Integer roleId){

        // 根据roleId清空中间表关联关系
        roleMapper.deleteRoleContextMenu(roleId);
        // 根据roleId删除role
        roleMapper.deleteRole(roleId);

    }

    /*
        获取当前角色拥有的资源信息
     */
    @Override
    public List<ResourceCategory> findResourceListByRoleId(Integer roleId) {

        // 调用Mapper方法
        List<Resource> resourceList = roleMapper.findResourceByRoleId(roleId);
        List<ResourceCategory> resourceCategoryList = roleMapper.findResourceCategoryByRoleId(roleId);

        ArrayList<Resource> list = new ArrayList<>();
        // 遍历封装并返回
        for (ResourceCategory resourceCategory : resourceCategoryList) {
            for (Resource resource : resourceList) {
                if (resourceCategory.getId().equals(resource.getCategoryId())){
                    list.add(resource);
                }
            }
            resourceCategory.setResourceList(list);
            list=null;
        }

        return resourceCategoryList;
    }

    /*
        为角色分配资源
     */
    @Override
    public void roleContextResource(RoleResourceVo roleResourceVo) {

        // 清空与资源中间表关联关系
        roleMapper.deleteRoleContextResource(roleResourceVo.getRoleId());

        // 为角色分配资源信息
        RoleResourceRelation roleResourceRelation = new RoleResourceRelation();
        Date date = new Date();
        List<Integer> resourceIdList = roleResourceVo.getResourceIdList();

        for (Integer integer : resourceIdList) {

            // 补全信息
            roleResourceRelation.setRoleId(roleResourceVo.getRoleId());
            roleResourceRelation.setResourceId(integer);
            roleResourceRelation.setCreateTime(date);
            roleResourceRelation.setUpdateTime(date);
            roleResourceRelation.setCreateBy("system");
            roleResourceRelation.setUpdateBy("system");

            // 调用mapper方法
            roleMapper.roleContextResource(roleResourceRelation);
        }

    }



}
