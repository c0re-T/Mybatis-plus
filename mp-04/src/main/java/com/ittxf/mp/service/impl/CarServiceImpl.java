package com.ittxf.mp.service.impl;

import cn.hutool.core.bean.BeanUtil;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.baomidou.mybatisplus.spring.service.impl.ServiceImpl;
import com.ittxf.mp.dto.CarQueryDTO;
import com.ittxf.mp.entity.Car;
import com.ittxf.mp.entity.WeiXiu;
import com.ittxf.mp.service.CarService;
import com.ittxf.mp.mapper.CarMapper;
import com.ittxf.mp.vo.CarVO;
import com.ittxf.mp.vo.WeiXiuVO;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.List;

@Service
// @RequiredArgsConstructor
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements CarService{

    // 在MP中，service类中不需要手动注入Mapper。
    // ServiceImpl类已经自动注入了，它的变量名叫做：baseMapper
    // baseMapper可以直接使用
    // private final CarMapper carMapper;

    @Override
    @Transactional(rollbackFor = Exception.class) // 事务的控制
    public void reductionPrice(Long id, BigDecimal price) {
        // 1. 根据id查询车辆信息
        Car car = this.getById(id);
        if (car == null) {
            throw new RuntimeException("您降价的汽车信息不存在，汽车ID=" + id);
        }

        // 2. 降价有限制，降价之后的价格不能小于0
        if (price.compareTo(car.getGuidePrice()) > 0) {
            throw new RuntimeException("降价额度已超过汽车当前的价格，当前价格："+ car.getGuidePrice() +"，要降价的额度是："+ price);
        }

        // 3. 汽车信息存在，降价额度合理，执行降价操作
        // 调用Mapper层的reductionPrice方法执行降价操作
        // baseMapper就是Mapper层的代理对象
        // baseMapper.reductionPrice(id, price);

        // 需求更加复杂了：降价后，如果价格低于10万，将汽车类型修改为低端车
        // 获取获取降价之后的价格
        BigDecimal currentPrice = car.getGuidePrice().subtract(price);
        // 仅修改价格: update t_car set guide_price = ? where id = ?
        // 既修改价挤又修改汽车类型:update t_car set guide_price = ?, car_type='低端车' where id = ?
        // update t_car set guide_price = ?, car_type='低端车' where id = ? and guide_price = 最开始的价格;
        /*
        * 第一步：查询阶段（Java 程序执行 select）
        * 第二步：线程 A 优先执行 update（发送一条完整 SQL 给数据库）
        *   这条 SQL在数据库内部是原子的：where 判断 和 set 修改，数据库一次性做完，中间不会被打断。
        *   执行后：数据库 id=1 的`guide_price`变成 15，返回受影响行数 `rows = 1` →A 更新成功。
        * 第三步：之后线程 B 执行自己的 update，同样一条完整 SQL 发给数据库
        *   数据库现在 `guide_price=15`，`where id=1 and guide_price=20` **条件不匹配**。
        *   所以线程 B 的更新操作**不会**改变数据库中的数据。
        * */
        boolean success = lambdaUpdate()
                .set(Car::getGuidePrice, currentPrice)
                // set子句也是可以动态的，根据条件进行动态设置
                .set(currentPrice.compareTo(new BigDecimal("10.00")) <= 0, Car::getCarType, "低端车")
                .eq(Car::getId, id)
                .eq(Car::getGuidePrice, car.getGuidePrice()) // 市务 + 乐观锁 机制解决并发问题
                .update();
        if (!success) {
            throw new RuntimeException("降价失败，原因：发生了并发降价操作，请稍后再试");
        }

    }

    @Override
    public List<CarVO> queryByConditions(CarQueryDTO carQueryDTO) {
        // 先获取所有查询条件
        String brand = carQueryDTO.getBrand();
        String carType = carQueryDTO.getCarType();
        BigDecimal minPrice = carQueryDTO.getMinPrice();
        BigDecimal maxPrice = carQueryDTO.getMaxPrice();

        /*return lambdaQuery() // IService里面自动实现的
                // .select() // 不写select说明就是select *
                .like(StringUtils.isNotBlank(brand), Car::getBrand, brand)
                .eq(StringUtils.isNotBlank(carType), Car::getCarType, carType)
                .ge(minPrice != null, Car::getGuidePrice, minPrice)
                .le(maxPrice != null, Car::getGuidePrice, maxPrice)
                .list();*/

        /*return Db.lambdaQuery(Car.class)
                .like(StringUtils.isNotBlank(brand), Car::getBrand, brand)
                .eq(StringUtils.isNotBlank(carType), Car::getCarType, carType)
                .ge(minPrice != null, Car::getGuidePrice, minPrice)
                .le(maxPrice != null, Car::getGuidePrice, maxPrice)
                .list();*/
        // 查询汽车信息，同时汽车关联的维修记录也查询出来
        List<Car> list = lambdaQuery()
                .like(StringUtils.isNotBlank(brand), Car::getBrand, brand)
                .eq(StringUtils.isNotBlank(carType), Car::getCarType, carType)
                .ge(minPrice != null, Car::getGuidePrice, minPrice)
                .le(maxPrice != null, Car::getGuidePrice, maxPrice)
                .list();
        List<CarVO> carVOS = BeanUtil.copyToList(list, CarVO.class);
        for (CarVO carVO : carVOS) {
            // 通过汽车的id获取对应的维修记录
            List<WeiXiu> weiXiuList = Db.lambdaQuery(WeiXiu.class)
                    .eq(WeiXiu::getCarId, carVO.getId())
                    .list();
            List<WeiXiuVO> weiXiuVOS = BeanUtil.copyToList(weiXiuList, WeiXiuVO.class);
            carVO.setWeixiuVOList(weiXiuVOS);
        }
        return carVOS;
    }

    @Override
    public CarVO getOneById(Long id) {
        Car car = lambdaQuery()
                .eq(Car::getId, id)
                .one();
        CarVO carVO = BeanUtil.copyProperties(car, CarVO.class);
        if (carVO != null) {
            List<WeiXiu> list = Db.lambdaQuery(WeiXiu.class)
                    .eq(WeiXiu::getCarId, carVO.getId())
                    .list();
            List<WeiXiuVO> weiXiuVOS = BeanUtil.copyToList(list, WeiXiuVO.class);
            carVO.setWeixiuVOList(weiXiuVOS);
        }
        return carVO;
    }
}




