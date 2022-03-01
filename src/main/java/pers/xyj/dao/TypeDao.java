package pers.xyj.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import pers.xyj.domain.Book;
import pers.xyj.domain.Type;

import java.util.List;

@Mapper
public interface TypeDao extends BaseMapper<Type> {
    @Select("SELECT book.*" +
            " FROM book, type " +
            " WHERE book.t_id = type.t_id" +
            " AND type.t_id = #{tId}")
    List<Book> getListById(@Param("tId") Integer id);
    @Select("SELECT COUNT(*) " +
            " FROM book, type " +
            " WHERE book.t_id = type.t_id" +
            " AND type.t_id = #{tId}")
    int getTypePageNum(@Param("tId") Integer id);
    @Select("SELECT book.* " +
            " FROM book, type " +
            " WHERE book.t_id = type.t_id" +
            " AND type.t_id = #{tId}" +
            " LIMIT #{cur}, #{size}")
    List<Book> getPageById(@Param("tId") Integer id,@Param("cur") int cur,@Param("size") Integer pageSize);
}
