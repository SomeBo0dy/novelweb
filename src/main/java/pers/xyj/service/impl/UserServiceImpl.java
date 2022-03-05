package pers.xyj.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pers.xyj.dao.UserDao;
import pers.xyj.domain.User;
import pers.xyj.service.IUserService;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class UserServiceImpl extends ServiceImpl<UserDao, User> implements IUserService {
    @Autowired
    private UserDao userDao;

    public Map<String, Object> creatAccount (User user){
        Map<String, Object> resultMap = new HashMap<>();
        QueryWrapper<User> wrapper = new QueryWrapper<>();
        wrapper.eq("u_name", user.getUName());
        Integer count = userDao.selectCount(wrapper);
        if (count > 0){
            resultMap.put("code",400);
            resultMap.put("message","注册失败，该用户已存在");
            return resultMap;
        }
        int result = userDao.insertUser(user.getUName(),user.getUPassword(), user.getAdmin());
        if (result > 0){
            resultMap.put("code",200);
            resultMap.put("message","注册成功");
        }else{
            resultMap.put("code",400);
            resultMap.put("message","注册失败");
        }
        return resultMap;
    }

    @Override
    public Map<String, Object> adminAccount(User user) {
        Map<String,Object> resultMap = new HashMap<>();
        List<User> adminList = userDao.selectAdminByName(user.getUName());
        if (adminList == null || adminList.isEmpty()){
            resultMap.put("code",400);
            resultMap.put("message","该账户不存在");
            return resultMap;
        }
        if (adminList.size() > 1){
            resultMap.put("code",400);
            resultMap.put("message","账号异常");
            return resultMap;
        }
        User u = adminList.get(0);
        if (!u.getUPassword().equals(user.getUPassword())){
            resultMap.put("code",400);
            resultMap.put("message","用户名或密码错误");
            return resultMap;
        }
        resultMap.put("code",200);
        resultMap.put("message","登陆成功");
        resultMap.put("id",u.getUId());
        return resultMap;
    }

    public Map<String,Object> loginAccount(User user){
        Map<String,Object> resultMap = new HashMap<>();
        List<User> userList = userDao.selectUserByName(user.getUName());
        if (userList == null || userList.isEmpty()){
            resultMap.put("code",400);
            resultMap.put("message","该账户不存在");
            return resultMap;
        }
        if (userList.size() > 1){
            resultMap.put("code",400);
            resultMap.put("message","账号异常，请联系管理员");
            return resultMap;
        }
        User u = userList.get(0);
        if (!u.getUPassword().equals(user.getUPassword())){
            resultMap.put("code",400);
            resultMap.put("message","用户名或密码错误");
            return resultMap;
        }
        resultMap.put("code",200);
        resultMap.put("message","登陆成功");
        resultMap.put("id",u.getUId());
        return resultMap;
    }
}
