package com.ittxf.mp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName("t_wx")
public class WeiXiu {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("`time`")
    private String time;
    private BigDecimal cost;
    private String description;
    private Long carId;
}
