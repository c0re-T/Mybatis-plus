package com.ittxf.mp.controller;

import cn.hutool.core.bean.BeanUtil;
import com.ittxf.mp.dto.CarDTO;
import com.ittxf.mp.dto.CarQueryDTO;
import com.ittxf.mp.entity.Car;
import com.ittxf.mp.service.CarService;
import com.ittxf.mp.vo.CarVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springdoc.core.annotations.ParameterObject;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
@Tag(name = "汽车信息管理",description = "通过汽车信息管理接口完成汽车信息的CRUD操作")
public class CarController {

    private final CarService carService;

    @PostMapping("/cars")
    @Operation(summary = "新增汽车", description = "添加汽车信息，汽车信息DTO中所有属性为必填项")
    @ApiResponse(responseCode = "200", description = "新增成功")
    @ApiResponse(responseCode = "400", description = "请求参数错误")
    public void saveCar(@RequestBody CarDTO carDTO) {
        // 使用HuTool工具包进行转换，将CarDTO转换为Car(把DTO对象中的属性拷贝到实体对象Car中)
        Car car = BeanUtil.copyProperties(carDTO, Car.class);
        carService.save(car);
    }

    @DeleteMapping("/cars/{id}")
    @Operation(summary = "删除汽车", description = "根据ID删除汽车信息")
    @ApiResponse(responseCode = "200", description = "删除成功")
    @ApiResponse(responseCode = "400", description = "请求参数错误")
    public void removeCar(
            @Parameter(description = "汽车主键ID",example = "1",required = true)
            @PathVariable("id") Long id) {
        carService.removeById(id);
    }

    @GetMapping("/cars/{id}")
    @Operation(summary = "根据ID查询汽车信息", description = "根据ID查询汽车详细信息")
    @ApiResponse(responseCode = "200", description = "查询成功")
    @ApiResponse(responseCode = "400", description = "请求参数错误")
    public CarVO getCar(
            @Parameter(description = "汽车主键ID",example = "1",required = true)
            @PathVariable("id") Long id) {
        // 返回的是实体类对象
        // Car car = carService.getById(id);
        CarVO carVO = carService.getOneById(id);
        // 将实体类对象转换成VO对象返回给前端
        // return BeanUtil.copyProperties(car, CarVO.class);
        return carVO;
    }

    @GetMapping("/cars")
    @Operation(summary = "根据ID集合批量查询汽车信息", description = "根据ID集合批量查询汽车详细信息")
    @ApiResponse(responseCode = "200", description = "查询成功")
    @ApiResponse(responseCode = "400", description = "请求参数错误")
    // Spring Boot 默认支持将多个同名参数或逗号分隔的参数自动绑定到 List 集合上
    // ?id=1,2,3  和  ?id=1&id=2&id=3  都支持。不支持：[1,2,3]
    public List<CarVO> getCars(
            @Parameter(description = "汽车主键ID集合",example = "1,2,3",required = true)
            @RequestParam("ids") List<Long> ids) {
        // List集合中存储的是 Car 实体对象
        List<Car> cars = carService.listByIds(ids);
        // 将Car转换为CarVO
        return BeanUtil.copyToList(cars, CarVO.class);
    }

    @PutMapping("/cars/{id}/reduction/{price}")
    @Operation(summary = "根据ID降低厂商指导价", description = "根据ID降低厂商指导价")
    @ApiResponse(responseCode = "200", description = "降低成功")
    @ApiResponse(responseCode = "400", description = "请求参数错误")
    public void reductionPrice(
            @Parameter(description = "汽车主键ID",example = "1",required = true)
            @PathVariable("id") Long id,
            @Parameter(description = "降低的厂商指导价(单位：万元)",example = "5.00",required = true)
            @PathVariable("price") BigDecimal price) {
        carService.reductionPrice(id, price);
    }

    // RESTFul接口：根据多个条件查询汽车信息(多条件查询) 不要在路径写动词，比如：/car/getCarById?id=1
    // 这是一个GET请求，数据提交时以查询参数的方式提交：name=value&name=value...
    // 不能使用POST请求，因为RESTFul中规定，查询操作必须是GET请求
    // 不是POST请求，因此接口参数上不要添加 @RequestBody注解。【这个是专门处理POST请求，并且请求体是JSON格式的字符串】
    @GetMapping("/cars/conditions")
    @Operation(summary = "根据多个条件查询汽车信息", description = "根据多个条件查询汽车信息")
    @ApiResponse(responseCode = "200", description = "查询成功")
    @ApiResponse(responseCode = "400", description = "请求参数错误")
    // @ParameterObject 是swagger注解，不添加也不影响我们程序的使用。只不过不添加的话，swaggerUI上显示的参数是一个整休
    // 如果加上这个注解，那么在swaggerUI界面上，会将测试参数拆解成多个参数
    public List<CarVO> getCarByConditions(@ParameterObject CarQueryDTO carQueryDTO) {
        return carService.queryByConditions(carQueryDTO);
    }



}
