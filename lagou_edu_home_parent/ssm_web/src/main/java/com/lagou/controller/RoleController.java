package com.lagou.controller;

import com.lagou.domain.Menu;
import com.lagou.domain.ResponseResult;
import com.lagou.domain.Role;
import com.lagou.domain.RoleMenuVO;
import com.lagou.service.MenuService;
import com.lagou.service.RoleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/role")
public class RoleController {

    @Autowired
    private RoleService roleService;

    @Autowired
    private MenuService menuService;

    /*
        查询所有角色(条件)
     */
    @RequestMapping("/findAllRole")
    public ResponseResult findAllRole(@RequestBody Role role){

        List<Role> list = roleService.findAllRole(role);

        ResponseResult responseResult = new ResponseResult(true, 200, "查询所有角色成功", list);

        return responseResult;

    }

    /*
        查询所有的父子菜单信息 （分配菜单的第一个接口）
     */
    @RequestMapping("/findAllMenu")
    public ResponseResult findSubMenuListByPid() {

        List<Menu> subMenuListByPid = menuService.findSubMenuListByPid(-1);

        // 按格式响应数据
        HashMap<String, Object> map = new HashMap<>();
        map.put("parentMenuList",subMenuListByPid);

        ResponseResult responseResult = new ResponseResult(true, 200, "查询所有的父子菜单信息成功", map);

        return responseResult;

    }

    /*
        根据角色id查询对应的菜单信息id
     */
    @RequestMapping("/findMenuByRoleId")
    public ResponseResult findMenuByRoleId(Integer roleId) {

        List<Integer> list = roleService.findMenuByRoleId(roleId);

        ResponseResult responseResult = new ResponseResult(true, 200, "根据角色id查询对应的菜单信息id成功", list);

        return responseResult;

    }

    /*
        为角色分配菜单
     */
    @RequestMapping("/RoleContextMenu")
    public ResponseResult RoleContextMenu(@RequestBody RoleMenuVO roleMenuVO) {

        roleService.roleContextMenu(roleMenuVO);

        ResponseResult responseResult = new ResponseResult(true, 200, "为角色分配菜单", null);

        return responseResult;
    }

    /*
        删除角色
     */
    @RequestMapping("/deleteRole")
    public ResponseResult deleteRole(Integer id) {

        roleService.deleteRole(id);

        ResponseResult responseResult = new ResponseResult(true, 200, "删除角色成功", null);

        return responseResult;

    }


}
