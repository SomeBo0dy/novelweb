package pers.xyj.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;


@Data
@TableName("user")
public class User {
    @TableId(value = "u_id", type = IdType.AUTO)
    private Integer uId;//不用传
    private String uName;
    private String uPassword;
    private Integer admin;

    public User() {
        admin = 0;//用户
    }
}
