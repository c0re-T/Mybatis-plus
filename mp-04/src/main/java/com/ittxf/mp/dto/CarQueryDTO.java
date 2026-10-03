package com.ittxf.mp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Schema(description = "封装查询汽车信息的条件数据")
public class CarQueryDTO {
    @Schema(description = "汽车品牌，支持模糊查询", example = "特斯拉", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String brand;
    @Schema(description = "汽车类型", example = "燃油车", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private String carType;
    @Schema(description = "最小价格（包含，单位：万）", example = "10.00", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private BigDecimal minPrice;
    @Schema(description = "最大价格（包含，单位：万）", example = "20.00", requiredMode = Schema.RequiredMode.NOT_REQUIRED)
    private BigDecimal maxPrice;
}
