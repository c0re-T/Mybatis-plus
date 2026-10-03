package com.ittxf.mp.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
// 生成静态方法of，便于对象的创建。
@AllArgsConstructor(staticName = "of")
public class Owner {
    private String id;
    private String name;
}
