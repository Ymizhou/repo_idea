package com.lagou.service;

import com.lagou.domain.ResourceCategory;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVO;
import com.lagou.domain.RoleResourceVo;

import java.util.List;

public interface RoleService {

    /*
        查询所有角色
     */
    public List<Role> findAllRole(Role role);

    /*
        根据角色id查询对应的菜单信息id
     */
    public List<Integer> findMenuByRoleId(Integer roleId);

    /*
        为角色分配菜单
     */
    public void roleContextMenu(RoleMenuVO roleMenuVO);

    /*
        删除角色
     */
    public void deleteRole(Integer roleId);

    /*
        获取当前角色拥有的资源信息
     */
    public List<ResourceCategory> findResourceListByRoleId(Integer roleId);

    /*
        为角色分配资源
     */
    public void roleContextResource(RoleResourceVo roleResourceVo);
}
