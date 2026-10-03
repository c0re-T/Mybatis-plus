package com.ittxf.mp.dto;

import com.ittxf.mp.common.CarStatus;
import com.ittxf.mp.entity.Owner;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

// 接收前端提交的数据，需要使用swagger注解
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "封装前端提交的汽车数据")
public class CarDTO {
    // 提交方对应的swagger注解需要提供 requiredMode 属性
    @Schema(description = "车牌号", example = "京A12345", requiredMode = Schema.RequiredMode.REQUIRED)
    private String carNum;
    @Schema(description = "品牌", example = "宝马", requiredMode = Schema.RequiredMode.REQUIRED)
    private String brand;
    @Schema(description = "厂商指导价（单位：万）", example = "50.00", requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal guidePrice;
    @Schema(description = "出厂日期", example = "2023-01-01", requiredMode = Schema.RequiredMode.REQUIRED)
    private String produceTime;
    @Schema(description = "汽车类型", example = "燃油车", requiredMode = Schema.RequiredMode.REQUIRED)
    private String carType;
    // @Schema(description = "汽车状态", example = "1", allowableValues = {"1", "2", "3", "4"},requiredMode = Schema.RequiredMode.REQUIRED)
    // private Integer status;

    // 更换为枚举类型增强可读性
    @Schema(description = "汽车状态", example = "在售", allowableValues = {"在售", "已售", "维修中", "报废"},requiredMode = Schema.RequiredMode.REQUIRED)
    private CarStatus status;

    @Schema(description = "汽车关联的车主信息")
    private Owner owner;
}
