package com.ittxf.mp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Data
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_customer")
public class Customer {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    // 属性名和字段名不一致，对应不上，要添加这个注解。
    @TableField("username")
    private String name;

    // 如果MP的版本低于：3.4.0。当属性名是isXxx，并且类型也是布尔类型。需要添加 @TableField。
    // @TableField("is_vip")
    private Boolean isVip; // Boolean类型插入数据库时:true-->1 | false --> 0

    // 属性名正好是数据库中的关键字，需要添加这个注解。
    @TableField("`desc`")
    private String desc;

    // 实体类中的某个属性在数据库表中不存在，而要添加这个注解。
    @TableField(exist = false)
    private LocalDateTime createTime;
}
