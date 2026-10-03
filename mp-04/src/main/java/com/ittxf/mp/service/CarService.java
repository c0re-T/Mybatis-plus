package com.ittxf.mp.service;

import com.ittxf.mp.dto.CarQueryDTO;
import com.ittxf.mp.entity.Car;
import com.baomidou.mybatisplus.spring.service.IService;
import com.ittxf.mp.vo.CarVO;

import java.math.BigDecimal;
import java.util.List;


public interface CarService extends IService<Car> {

    void reductionPrice(Long id, BigDecimal price);

    List<CarVO> queryByConditions(CarQueryDTO carQueryDTO);

    CarVO getOneById(Long id);
}
