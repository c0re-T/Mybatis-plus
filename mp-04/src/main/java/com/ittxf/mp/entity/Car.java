package com.ittxf.mp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.math.BigDecimal;

import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import com.ittxf.mp.common.CarStatus;
import io.swagger.v3.core.util.Json;
import lombok.Data;

// 这是持久层的实体类，不需要使用swagger注解
@TableName(value ="t_car",autoResultMap = true) // 需要开启自动映射，json处理器才能生效
@Data
public class Car {
    @TableId(type = IdType.AUTO)
    private Long id;
    private String carNum;
    private String brand;
    private BigDecimal guidePrice;
    private String produceTime;
    private String carType;
    // 逻辑删除时，多了一个删除的标记字段，这个属性要在实体类中加上
    // VO和DTO中不需要添加
    private Integer deleted; // 逻辑删除字段

    // 字段是汽车对象的状态 (1 2 3 4)
    // 在售(1)、已售(2)、维修中(3)、报废(4)
    // private Integer status;
    private CarStatus status;

    // 添加json类型处理器
    @TableField(typeHandler = JacksonTypeHandler.class)
    private Owner owner;
}