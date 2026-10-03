package com.ittxf.mp.common;

import com.baomidou.mybatisplus.annotation.EnumValue;
import com.fasterxml.jackson.annotation.JsonValue;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public enum CarStatus {
    FOR_SALE(1, "在售"),
    SOLD(2, "已售"),
    IN_MAIN(3, "维修中"),
    SCRAP(4, "报废");

    // 通过这个注解 告诉枚举转换器，枚举对象中的value属性的值存储到数据库表的status字段上1
    @EnumValue
    private int value;

    // 注意：和MP无关，是jackson中提供的一个注解
    // 这个注解的作用是枚举对象在转换成json字符串的时候起作用
    @JsonValue
    private String desc;
}
