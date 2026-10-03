package com.ittxf.mp.service.Impl;

import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import com.ittxf.mp.entity.Car;
import com.ittxf.mp.mapper.CarMapper;
import com.ittxf.mp.service.CarService;
import org.springframework.stereotype.Service;

// 为什么要继承ServiceImpl<CarMapper, Car>?
// 因为ServiceImpl类已经帮我们实现了ICarService接口中的所有方法，我们直接继承即可
@Service
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements CarService {

}
