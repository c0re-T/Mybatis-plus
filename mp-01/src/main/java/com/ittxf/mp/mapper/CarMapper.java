package com.ittxf.mp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ittxf.mp.entity.Car;

// MP底层是如何让实体类和表对应起来的？
// MP底层提供了一个BαseMapper接口，该接口上有泛型信息，通过泛型信息找到对应的实体类。这样实体类和表名对应起来了
public interface CarMapper extends BaseMapper<Car> {
}
