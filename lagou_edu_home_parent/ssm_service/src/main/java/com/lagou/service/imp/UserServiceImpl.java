package com.lagou.service.imp;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.lagou.dao.UserMapper;
import com.lagou.domain.*;
import com.lagou.service.UserService;
import com.lagou.utils.Md5;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserMapper userMapper;

    /*
        用户分页以及多条件查询
     */
    @Override
    public PageInfo findAllUserByPage(UserVO userVO) {

        PageHelper.startPage(userVO.getCurrentPage(),userVO.getPageSize());

        List<User> list = userMapper.findAllUserByPage(userVO);

        PageInfo<User> pageInfo = new PageInfo<>(list);

        return pageInfo;
    }

    /*
        用户登录(根据用户名查询具体的用户信息)
     */
    @Override
    public User login(User user) throws Exception {

        User user1 = userMapper.login(user);

        if (user1 != null && Md5.verify(user.getPassword(),"lagou",user1.getPassword())){
            return user1;
        }else {
            return null;
        }

    }

    /*
        分配角色（回显）
     */
    @Override
    public List<Role> findUserRelationRoleById(Integer id) {

        List<Role> list = userMapper.findUserRelationRoleById(id);

        return list;
    }

    /*
        用户关联角色
     */
    @Override
    public void UserContextRole(UserVO userVO) {

        // 1.根据用户id清空中间表关联关系
        userMapper.deleteUserContextRole(userVO.getUserId());

        // 2.重新建立中间表关联关系
        List<Integer> roleIdList = userVO.getRoleIdList();

        for (Integer roleId : roleIdList) {
            // 封装数据
            User_Role_relation user_role_relation = new User_Role_relation();
            user_role_relation.setUserId(userVO.getUserId());
            user_role_relation.setRoleId(roleId);
            Date date = new Date();
            user_role_relation.setCreatedTime(date);
            user_role_relation.setUpdatedTime(date);
            user_role_relation.setCreatedBy("system");
            user_role_relation.setUpdatedby("system");

            userMapper.userContextRole(user_role_relation);
        }

    }

    /*
        获取用户权限进行菜单动态展示
     */
    @Override
    public ResponseResult getUserPermissions(Integer userId) {

        // 1.获取当前用户拥有的角色
        List<Role> roleList = userMapper.findUserRelationRoleById(userId);

        // 2.获取角色id保存到list集合中
        ArrayList<Object> roleIds = new ArrayList<>();

        for (Role role : roleList) {
            roleIds.add(role.getId());
        }

        // 3.根据角色id查询父菜单
        List<Menu> parentMenu = userMapper.findParentMenuByRoleId(roleIds);

        // 4.查询封装父菜单关联的子菜单
        for (Menu menu : parentMenu) {
            List<Menu> subMenu = userMapper.findSubMenuByPid(menu.getId());
            menu.setSubMenuList(subMenu);
        }

        // 5.获取资源信息
        List<Resource> resourceList = userMapper.findResourceByRoleId(roleIds);

        // 6.封装数据并返回
        HashMap<String, Object> map = new HashMap<>();

        map.put("menuList",parentMenu);
        map.put("resourceList",resourceList);

        return new ResponseResult(true,200,"获取用户权限信息成功",map);
    }


}
