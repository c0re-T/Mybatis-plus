package com.ittxf.mp.mapper;

import com.ittxf.mp.entity.Car;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;

public interface CarMapper extends BaseMapper<Car> {

    void reductionPrice(@Param("id") Long id, @Param("price") BigDecimal price);
}




