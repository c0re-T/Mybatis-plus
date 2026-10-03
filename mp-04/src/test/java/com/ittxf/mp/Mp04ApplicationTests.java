package com.ittxf.mp;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.ittxf.mp.common.CarStatus;
import com.ittxf.mp.entity.Car;
import com.ittxf.mp.entity.Owner;
import com.ittxf.mp.service.CarService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class Mp04ApplicationTests {

    @Autowired
    private CarService carService;

    @Test
    void testSave() {
        long begin = System.currentTimeMillis();
        for (long i = 5; i <= 1000; i++) {
            // 每循环一次创建一个Car对象
            Car car = new Car();
            car.setId(i);
            car.setCarNum("CarNum" + i);
            car.setBrand("宝马" + i);
            car.setGuidePrice(new BigDecimal(30.0));
            car.setProduceTime("2000-10-11");
            car.setCarType("燃油车");
            // 保存Car
            carService.save(car);
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时" + (end - begin) / 1000 + "秒");
    }

    @Test
    void testSaveBatch() {
        long begin = System.currentTimeMillis();
        List<Car> cars = new ArrayList<>();
        for (long i = 1; i <= 1000; i++) {
            // 每循环一次创建一个Car对象
            Car car = new Car();
            car.setId(i);
            car.setCarNum("CarNum" + i);
            car.setBrand("宝马" + i);
            car.setGuidePrice(new BigDecimal(30.0));
            car.setProduceTime("2000-10-11");
            car.setCarType("燃油车");
            cars.add(car);
            // 每100个保存一次
            if(i % 100 == 0){
                // url后面没有添加rewriteBatchedStatements=true的时候：把100条insert SQL打包发送给数据库。
                // url后面添加rewriteBatchedStatements=true的时候：把1条insert SQL打包发送给数据库。
                // saveBatch这是MP给提供的
                carService.saveBatch(cars);
                cars.clear();
            }
        }
        long end = System.currentTimeMillis();
        System.out.println("耗时" + (end - begin) / 1000 + "秒");
    }

    @Test
    public void testSaveCarJson(){
        Car car = new Car();
        car.setCarNum("津A90909");
        car.setCarType("新能源");
        car.setBrand("BYD666");
        car.setProduceTime("2025-10-11");
        car.setStatus(CarStatus.FOR_SALE);
        car.setGuidePrice(BigDecimal.valueOf(20));

        // 车辆关联车主
        car.setOwner(Owner.of("890989098989876787", "赵六"));
        carService.save(car);
    }

    @Test
    void testQueryPage(){
        // 设置每页显示的记录条数
        int pageSize = 5;

        // 设置当前页码
        int pageNo = 2;

        // 创建一个Page对象
        Page<Car> carPage = new Page<>(pageNo,pageSize);

        // 添加排序顺序
        carPage.addOrder(OrderItem.desc("guide_price")); // 先按照指导价进行降序排列
        carPage.addOrder(OrderItem.asc("produce_time")); // 如果指导价相同，则按照出厂日期升序排列

        // 添加查询条件
        LambdaQueryWrapper<Car> carLambdaQueryWrapper = new LambdaQueryWrapper<>();
        // 设置各种查询条件...
        LambdaQueryWrapper<Car> newQueryWrapper = carLambdaQueryWrapper.eq(Car::getCarType, "燃油车");

        carService.page(carPage, newQueryWrapper);

        // 查询之后的所有数据都自动被封装到Page对象中了，我们直接从page对象中获取数据即可。
        // 获取总页数
        long pages = carPage.getPages();
        // 获取总记录条数
        long total = carPage.getTotal();
        // 获取相关数据
        List<Car> records = carPage.getRecords();
        System.out.println("总页数：" + pages);
        System.out.println("总记录条数：" + total);
        for (Car record : records) {
            System.out.println(record);
        }
    }

}
