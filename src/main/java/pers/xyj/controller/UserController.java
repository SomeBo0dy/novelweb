package pers.xyj.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import pers.xyj.domain.User;
import pers.xyj.service.IUserService;

import java.util.HashMap;
import java.util.Map;

@Api(value = "userControllerApi",tags={"用户操作接口"})
@RestController
@CrossOrigin
@RequestMapping("/users")
public class UserController {
    @Autowired
    private IUserService userService;
    @ApiOperation(value="获取所有用户信息")
    @GetMapping
    public Map<String,Object> getAll(){
        Map<String,Object> result = new HashMap<>();
        result.put("code",200);
        result.put("message",userService.list());
        return result;
    }
    @ApiOperation(value="根据id获取用户信息")
    @GetMapping("/{id}")
    public Map<String,Object> getById(@PathVariable Integer id){
        Map<String,Object> result = new HashMap<>();
        result.put("code",200);
        result.put("message",userService.getById(id));
        return result;
    }
    @ApiOperation(value="登录用户")
    @PostMapping("login")
    public Map<String,Object> loginAccount(User user){
        return userService.loginAccount(user);
    }
    @ApiOperation(value="注册用户")
    @PostMapping("create")
    public Map<String,Object> creatAccount(User user){
        user.setAdmin(0);
        return userService.creatAccount(user);
    }
    @ApiOperation(value="更新（没啥用）")
    @PutMapping
    public Map<String,Object> update(@RequestBody User user){
        Map<String,Object> result = new HashMap<>();
        boolean flag = userService.updateById(user);
        result.put("code",flag?200:400);
        result.put("message",flag);
        return result;
    }
    @ApiOperation(value="根据id删除用户信息")
    @DeleteMapping("/{id}")
    public Map<String,Object> delete(@PathVariable Integer id){
        Map<String,Object> result = new HashMap<>();
        boolean flag = userService.removeById(id);
        result.put("code",flag?200:400);
        result.put("message",flag);
        return result;
    }
    @ApiOperation(value="登录管理员")
    @PostMapping("admin")
    public Map<String,Object> adminAccount(User user){
        return userService.adminAccount(user);
    }
}
