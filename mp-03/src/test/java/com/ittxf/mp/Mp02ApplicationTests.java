package com.ittxf.mp;


import com.ittxf.mp.entity.Car;
import com.ittxf.mp.service.CarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;
import java.util.Optional;

@SpringBootTest
class Mp02ApplicationTests {

    @Autowired
    private CarService carService;

    @Test
    void test1() {
        // Optional<Car> optById = carService.getOptById(2094454895527706628L);
        // System.out.println(optById.get());
        // System.out.println(optById);

        // Car car = carService.getById(2094454895527706628L);
        // System.out.println(car);

        // MP的service提供了哪些方法
        // 增
        // carService.saveXxx();
        // 删
        // carService.removeXxx();
        // 改
        // carService.updateXxx();
        // 查询
        // 查一个
        // carService.getXxx();
        // carService.getOneXxx();
        // 查多个
        // carService.listXxx();

        // 需要定制SQL条件的话，Service也内置了相关的方法，我们不需要再手动mew Wrapper，直接调用方法就行。
        // 底层会自动使用 LambdaQueryWrapper
        // lambdaUpdate同理
        Car car = carService.lambdaQuery()
                .eq(Car::getId, 2094454895527706628L)
                .one();
        System.out.println(car);

    }
}
