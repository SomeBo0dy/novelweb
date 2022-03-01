package pers.xyj.service;


import com.baomidou.mybatisplus.extension.service.IService;
import pers.xyj.domain.User;

import java.util.List;
import java.util.Map;

public interface IUserService extends IService<User> {
    Map<String,Object> loginAccount(User user);
    Map<String, Object> creatAccount (User user);

    Map<String, Object> adminAccount(User user);
}
