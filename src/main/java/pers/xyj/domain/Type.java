package pers.xyj.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("type")
public class Type {
    @TableId(value = "t_id", type = IdType.AUTO)
    private Integer tId;//不用传
    private String tName;
}
