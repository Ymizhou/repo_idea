package com.lagou.dao;

import com.lagou.domain.*;

import java.util.List;

public interface RoleMapper {

    /*
        查询所有角色&条件进行查询
     */
    public List<Role> findAllRole(Role role);

    /*
        根据角色id查询对应的菜单信息id
     */
    public List<Integer> findMenuByRoleId(Integer roleId);

    /*
        根据roleId清空与菜单中间表关联关系
     */
    public void deleteRoleContextMenu(Integer rid);

    /*
        为角色分配菜单信息
     */
    public void roleContextMenu(Role_menu_relation role_menu_relation);

    /*
        删除角色
     */
    public void deleteRole(Integer roleId);

    /*
        根据roleId查询当前角色拥有的资源信息
     */
    public List<Resource> findResourceByRoleId(Integer roleId);

    /*
        根据roleId查询当前角色拥有的资源分类信息
     */
    public List<ResourceCategory>  findResourceCategoryByRoleId(Integer roleId);

    /*
        根据roleId清空与资源中间表关联关系
     */
    public void deleteRoleContextResource(Integer roleId);

    /*
        为角色分配资源信息
     */
    public void roleContextResource(RoleResourceRelation roleResourceRelation);

}
