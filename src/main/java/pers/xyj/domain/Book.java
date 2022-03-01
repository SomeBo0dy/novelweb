package pers.xyj.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

//lombok
@Data
@TableName("book")
public class Book {
    @TableId(value = "b_id", type = IdType.AUTO)
    private Integer bId;//不用传
    private String bName;
    private String bDesc;
    private String author;
    private String imgPath;//不用传
    private String textPath;//不用传
    //文件key都用novel
    private String tId;
    private Integer count;//不用传
    public Book() {count=0;}

}
