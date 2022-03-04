package pers.xyj.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.*;
import pers.xyj.domain.Book;

import java.util.List;

@Mapper
public interface BookDao extends BaseMapper<Book> {
    @Select("SELECT * " +
            " FROM book " +
            " LIMIT #{cur}, #{size}")
    List<Book> getByPage(@Param("cur") int currentPage,@Param("size") int pageSize);
    @Select("SELECT book.* " +
            " FROM book " +
            " WHERE book.b_name LIKE \"%\"#{name}\"%\" AND book.pass = 1" +
            " LIMIT #{cur}, #{size}")
    List<Book> search(@Param("name") String name,@Param("cur") int currentPage, @Param("size") int pageSize);
    @Select("SELECT * " +
            " FROM book " +
            " WHERE book.pass = 1" +
            " ORDER BY count DESC" +
            " LIMIT 0, 10")
    List<Book> getHotList();
    @Update("UPDATE book SET pass = 1 WHERE b_id = #{bId}")
    int passBook(@Param("bId")Integer bId);
    @Delete("DELETE FROM collect" +
            " WHERE b_id = #{bId}")
    void deleteCol(@Param("bId")Integer id);
}
