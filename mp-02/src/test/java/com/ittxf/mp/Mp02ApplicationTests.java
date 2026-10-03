package com.ittxf.mp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.LambdaUpdateWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.ittxf.mp.entity.Car;
import com.ittxf.mp.mapper.CarMapper;
import org.apache.ibatis.annotations.Mapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.List;

@SpringBootTest
class Mp02ApplicationTests {

    @Autowired
    private CarMapper carMapper;

    // 这种方式是通过代码来生成SQL
    @Test
    void test1() {
        // 查询出汽车品牌中带有 '宝' 的，厂商指导价大于20万的，要求查询 id、car_num、brand、guide_price 字段。
        // 1. 创建条件构造器对象
        QueryWrapper<Car> carQueryWrapper = new QueryWrapper<>();
        // 2. 采用链式调用的方式构建查询条件
        carQueryWrapper.select("id", "car_num", "brand", "guide_price") // 字段名是硬编码，扩展性差，维护成本高
                // .like("brand", "宝") // 底层会自动生成 brand like %宝%
                .likeRight("brand", "宝")
                // .gt("guide_price", new BigDecimal("20")); // 底层会自动生成 guide_price > 20
                // .or() 如果是or的关系要调用or()方法。
                .ge("guide_price", new BigDecimal("20.00")); // 链式调用添加的条件都是并且的关系"AND"
        // 3. 执行查询语句
        List<Car> cars = carMapper.selectList(carQueryWrapper);
        cars.forEach(System.out::println); // 打印结果
    }

    // 这种是手动编写的SQL
    @Test
    public void test2() {
        List<Car> cars = carMapper.selectByCondition("宝", new BigDecimal("20.00"));
        cars.forEach(System.out::println);
    }

    // 使用LambdaQueryWrapper来代替QueryWrapper，目的是：字段名不再硬编码
    @Test
    public void test3() {
        // 创建条件构造器对象
        LambdaQueryWrapper<Car> lambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 采用链式调用的方式构建查询条件
        // Car::getId 底层最终会通过反射机制转换为 "id"
        lambdaQueryWrapper.select(Car::getId, Car::getCarNum, Car::getBrand, Car::getGuidePrice)
                .likeRight(Car::getBrand, "宝")
                .ge(Car::getGuidePrice, new BigDecimal("20.00"));
        // 执行查询语句
        List<Car> cars = carMapper.selectList(lambdaQueryWrapper);
        cars.forEach(System.out::println);
    }

    @Test
    public void test4() {
        // 提示：QueryWrapper 严格来说，并不是专门为SELECT准备的，它实际上是给所有where准备的
        // 只要语句中有where就可以使用QueryWrapper 来构建条件。
        // 1. 创建条件构造器对象
        // QueryWrapper<Car> carQueryWrapper = new QueryWrapper<>();
        LambdaQueryWrapper<Car> carLambdaQueryWrapper = new LambdaQueryWrapper<>();

        // 2. 构造条件
        // carQueryWrapper.eq("brand", "宝马");
        carLambdaQueryWrapper.eq(Car::getBrand, "宝马");

        // 3. 执行更新操作
        Car car = new Car();
        car.setGuidePrice(new BigDecimal("35.00"));
        // int update = carMapper.update(car, carQueryWrapper);
        int update = carMapper.update(car, carLambdaQueryWrapper);
        System.out.println("更新结果：" + update);
    }

    @Test
    void test5() {
        // 更新语句的话，更加专业的是使用：UpdateWrapper 系列
        // 1. 创建一个update语句专用的条件构造器对象
        // UpdateWrapper<Car> carUpdateWrapper = new UpdateWrapper<>();
        LambdaUpdateWrapper<Car> carLambdaUpdateWrapper = new LambdaUpdateWrapper<>();

        // 2. 构建update条件语句【setSql的作用：设置update语句的set子句】
        // carUpdateWrapper.eq("brand", "宝马") // update t_car set guide_price = 10 where brand = '宝马'
        //         .setSql("guide_price = 10");
        carLambdaUpdateWrapper.setSql("guide_price = 20")
                .eq(Car::getBrand, "宝马");

        // 3. 执行更新操作
        // int update = carMapper.update(carUpdateWrapper);
        int update = carMapper.update(carLambdaUpdateWrapper);
        System.out.println("更新结果：" + update);
    }

    @Test
    void test6() {
        // 什么情况下只能使用 updateWrapper？需要定制set子句时。【无法使用QueryWrapper完成】
        // 1.创建UpdateWrapper对象
        LambdaUpdateWrapper<Car> carLambdaUpdateWrapper = new LambdaUpdateWrapper<>();

        // 2.构建update条件语句
        carLambdaUpdateWrapper.setSql("guide_price = guide_price + 5")
                .eq(Car::getBrand, "宝马");

        // 3.执行更新操作
        int update = carMapper.update(carLambdaUpdateWrapper);
        System.out.println("更新结果：" + update);
    }

    // 基于 test6() 进行的改造
    // 在 test6() 当中编写了set子句，很显然，set子句是SQL片段，不太适合写死到java程序中
    // test7() 在MP当中如何自定义SQL?
    @Test
    void test7() {
        // 1. 创建条件构造器对象
        LambdaQueryWrapper<Car> carLambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 2. 构建条件
        carLambdaQueryWrapper.eq(Car::getBrand,"宝马");
        // 3. 执行更新
        int res = carMapper.updateGuidePrice(carLambdaQueryWrapper, new BigDecimal("5.00"));
        System.out.println("更新记录条数：" + res);
    }

    @Test
    void test8() { //原生mybatis
        int res = carMapper.updateByBrand("宝马", new BigDecimal("5.00"));
        System.out.println("更新记录条数：" + res);
    }

}
