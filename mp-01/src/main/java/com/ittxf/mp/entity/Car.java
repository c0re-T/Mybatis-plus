package com.ittxf.mp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
// MP的注解：指定表名和实体类的映射关系
// 如果实体类的属性名和表的字段名完全一致，可以省略@TableName和@TableId注解
// 符合驼峰命名规则，可以省略@TableId和@TableName注解
// 数据库表名和实体类的名字无法自动转换（下划线转驼峰），无法自动对应，需要使用 @TableName("指定表名")
@TableName("t_car")
public class Car {
    // 当实体类中有id属性的话，id属性自动会和数据库表中的主键字段映射，不需要使用@TableId注解，除非设置生成id的策略
    // @TableId 的核心作用：标记主键。
    // 官方建议：只要你属性名不是 id，最好都使用上@TableId注解（即使属性名和字段名能对应上，写上起码可读性强，而且还可以指定主键生成策略）
    // 关于主键的生成策略：
    // 1 IdType.AUTO：自增（数据库字段需要设置自增）
    // 2 IdType.ASSIGN_ID：自动分配 ID，雪花算法（默认）适用于 Long、Integer、String 类型的主键，默认使用雪花算法通过 IdentifierGenerator 的 nextId 实现。
    //      当没有指定主键生成策略时，即使数据库表中的主键上有 auto_increment，它也只是走 雪花算法 生成ID
    // 3 IdType.ASSIGN_UUID：自动分配 UUID 适用于 String 类型的主键，通过 IdentifierGenerator 的 nextUUID 实现。
    //      使用这种主键生成策略的时候，实体类的属性类型需要是String
    // 4 IdType.INPUT：手动输入，适用于所有类型
    // 5 IdType.NONE：无特定生成策略，如果全局配置中有 IdType 相关的配置，则会跟随全局配置【全局的默认配置：ASSIGN_ID 雪花算法】
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    // @TableId(value = "id", type = IdType.ASSIGN_UUID)
    // private String id;
    // 属性名和字段名也是通过下划线转驼峰的方式进行自动映射
    private String carNum;
    private String brand;
    private BigDecimal guidePrice;
    private String produceTime;
    private String carType;
}
