package com.ittxf.mp.mapper;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.baomidou.mybatisplus.core.toolkit.Constants;
import com.ittxf.mp.entity.Car;
import org.apache.ibatis.annotations.Param;

import java.math.BigDecimal;
import java.util.List;

public interface CarMapper extends BaseMapper<Car> {

    List<Car> selectByCondition(@Param("brand") String brand,
                                @Param("guidePrice") BigDecimal guidePrice);

    // int updateGuidePrice(@Param("ew") LambdaQueryWrapper<Car> carLambdaQueryWrapper,
    //                      @Param("guidePrice") BigDecimal guidePrice);
    int updateGuidePrice(@Param(Constants.WRAPPER) LambdaQueryWrapper<Car> carLambdaQueryWrapper,
                         @Param("guidePrice") BigDecimal guidePrice);

    int updateByBrand(@Param("brand") String brand, @Param("guidePrice") BigDecimal bigDecimal);
}
