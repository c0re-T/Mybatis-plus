package com.ittxf.mp.service;

import com.baomidou.mybatisplus.spring.service.IService;
import com.ittxf.mp.entity.Car;

// IService<Car>是MP提供的service接口
// 为什么我们写的 CarService 继承 IService<Car> 接口? 我们要在controller当中面向service接口调用方法
public interface CarService extends IService<Car> {

}
