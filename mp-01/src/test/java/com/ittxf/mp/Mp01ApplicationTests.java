package com.ittxf.mp;

import com.ittxf.mp.entity.Car;
import com.ittxf.mp.entity.Customer;
import com.ittxf.mp.mapper.CarMapper;
import com.ittxf.mp.mapper.CustomerMapper;
import lombok.RequiredArgsConstructor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class Mp01ApplicationTests {

    @Autowired
    private CarMapper carMapper;

    @Test
    void testInsert() {
        Car car = new Car();
        car.setCarNum("京A12345");
        car.setBrand("宝马");
        car.setGuidePrice(new BigDecimal(50));
        car.setProduceTime("2026-01-01");
        car.setCarType("燃油车");
        int res = carMapper.insert(car);
        System.out.println("插入条数：" + res);
    }

    @Test
    void testDelete() {
        int res = carMapper.deleteById(2094454895527706627L);
        System.out.println("删除条数：" + res);
    }

    @Test
    void testUpdate() {
        Car car = new Car();
        car.setId(2094454895527706628L);
        car.setCarNum("京A45434");
        car.setBrand("保时捷");
        car.setGuidePrice(new BigDecimal(100));
        car.setProduceTime("2026-01-01");
        car.setCarType("燃油车");
        int res = carMapper.updateById(car);
        System.out.println("更新结果：" + res);
    }

    @Test
    void testSelectById() {
        Car car = carMapper.selectById(2094454895527706628L);
        System.out.println(car);
    }

    @Test
    void testSelectAll() {
        // 这个查询不需要任何条件，因此参数是null。
        List<Car> cars = carMapper.selectList(null);
        for (Car car : cars) {
            System.out.println(car);
        }
    }

    @Autowired
    private CustomerMapper customerMapper;

    @Test
    public void testInsertCustomer() {
        Customer customer = new Customer();
        customer.setName("张三");
        customer.setIsVip(true);
        customer.setDesc("测试插入");
        customer.setCreateTime(LocalDateTime.now());
        int res = customerMapper.insert(customer);
        System.out.println("插入条数：" + res);
    }

}
