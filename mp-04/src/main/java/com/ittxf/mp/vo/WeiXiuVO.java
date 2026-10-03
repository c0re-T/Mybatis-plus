package com.ittxf.mp.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

@Data
@Schema(name = "WeiXiuVO", description = "维修记录的视图对象")
public class WeiXiuVO {
    @Schema(description = "维修记录id")
    private Long id;
    @Schema(description = "维修时间")
    private String time;
    @Schema(description = "维修费用")
    private Double cost;
    @Schema(description = "问题描述")
    private String description;
}