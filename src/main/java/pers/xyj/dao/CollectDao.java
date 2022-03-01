package pers.xyj.dao;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import org.apache.ibatis.annotations.*;
import pers.xyj.domain.Book;
import pers.xyj.domain.Collect;

@Mapper
public interface CollectDao extends BaseMapper<Collect> {
    @Delete("DELETE FROM collect WHERE b_id = #{bId} AND u_id = #{uId}")
    int deleteCol(@Param("bId") int bId,@Param("uId") int uId);
    @Update("UPDATE book SET count = count + 1 WHERE b_id = #{bId}")
    int addCount(@Param("bId")Integer bId);
    @Update("UPDATE book SET count = count - 1 WHERE b_id = #{bId}")
    int descCount(Integer bId);
    @Select("SELECT book.* " +
            " FROM book, collect " +
            " WHERE collect.b_id = book.b_id " +
            " AND collect.u_id = #{uId}")
    IPage<Book> querByPage(IPage<Book> page,@Param("uId") Integer uId);
}
