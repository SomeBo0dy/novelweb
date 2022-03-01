package pers.xyj.domain;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("collect")
public class Collect {
    private Integer uId;
    private Integer bId;
}
