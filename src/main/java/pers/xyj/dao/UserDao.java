package pers.xyj.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pers.xyj.domain.User;

import java.util.List;

@Mapper
public interface UserDao extends BaseMapper<User> {

    @Insert("INSERT INTO user ( u_name, u_password, admin) " +
            " VALUES ( #{uName}, #{uPassword} , #{admin})")
    int insertUser(@Param("uName") String uname, @Param("uPassword") String upassword, @Param("admin") Integer admin);


    @Select("SELECT u_name, u_password FROM user WHERE u_name = #{uName} AND admin = 0")
    List<User> selectUserByName(@Param("uName") String uname);
    @Select("SELECT u_name, u_password FROM user WHERE u_name = #{uName} AND admin = 1")
    List<User> selectAdminByName(String uName);
}
