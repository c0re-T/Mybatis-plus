package com.ittxf.mp.vo;

import com.ittxf.mp.common.CarStatus;
import com.ittxf.mp.entity.Owner;
import com.ittxf.mp.entity.WeiXiu;
import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;
import java.util.List;

// CarVo是负责返回给前端页面展示的数据进行页面展示的，也需要添加swagger注解
// DTO VO都属于WEB层
@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "封装返回给前端页面展示的汽车数据")
public class CarVO {
    // VO中swagger注解编写时，requiredMode不需要指定
    @Schema(description = "主键ID", example = "1")
    private Long id;
    @Schema(description = "车牌号", example = "京A12345")
    private String carNum;
    @Schema(description = "品牌", example = "宝马")
    private String brand;
    @Schema(description = "厂商指导价（单位：万）", example = "50.00")
    private BigDecimal guidePrice;
    @Schema(description = "出厂日期", example = "2023-01-01")
    private String produceTime;
    @Schema(description = "汽车类型", example = "燃油车", allowableValues = {"燃油车", "新能源", "氢能源", "油电混合"})
    private String carType;
    @Schema(description = "汽车关联的维修记录列表")
    private List<WeiXiuVO> weixiuVOList;

    // @Schema(description = "汽车状态", example = "1", allowableValues = {"1", "2", "3", "4"})
    // private Integer status;
    @Schema(description = "汽车状态", example = "FOR_SALE", allowableValues = {"FOR_SALE", "SOLD", "IN_MAIN", "SCRAP"})
    private CarStatus status;

    @Schema(description = "汽车关联的车主信息")
    private Owner owner;
}
