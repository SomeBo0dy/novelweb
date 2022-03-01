package pers.xyj.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
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
            " WHERE book.b_name LIKE \"%\"#{name}\"%\"" +
            " LIMIT #{cur}, #{size}")
    List<Book> search(@Param("name") String name,@Param("cur") int currentPage, @Param("size") int pageSize);
    @Select("SELECT * " +
            " FROM book " +
            " ORDER BY count DESC" +
            " LIMIT 0, 10")
    List<Book> getHotList();
}
