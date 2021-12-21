package com.lagou.service.imp;

import com.lagou.dao.RoleMapper;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVO;
import com.lagou.domain.Role_menu_relation;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

}
