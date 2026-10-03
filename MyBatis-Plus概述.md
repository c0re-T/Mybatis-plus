# MyBatis-Plus概述
<img src="https://cdn.nlark.com/yuque/0/2025/jpeg/21376908/1757681056420-39d1bc52-55fe-4f3b-8183-b4d7ba79b166.jpeg" width="4308" title="" crop="0,0,1,1" id="FzRaC" class="ne-image" style="font-size: 16px">

MyBatis-Plus不是用来替代MyBatis的，是对MyBatis的一种增强。注意：只做增强不做改变。

MyBatis-Plus为简化开发而生，为提高效率而生。

在MyBatis-Plus 中只需要简单的配置，或者不用配置（约定大于配置），即可完成单表的CRUD操作。

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744265316113-707379cf-54a5-45d1-a6d6-2584301e3754.png" width="722" title="" crop="0,0,1,1" id="WOoDY" class="ne-image" style="font-size: 16px">

MyBatis-Plus文档官网地址：[https://baomidou.com/](https://baomidou.com/)

MyBatis-Plus 由中国的开发者 **baomidou**（团队 ID）创建并维护。

MyBatis-Plus 在 MyBatis 基础上增强功能的**国产工具**，**并非官方插件**，但已被 MyBatis 官方文档收录推荐。

MyBatis-Plus 提供完善的中文文档，对国内开发者更友好。

MyBatis-Plus 支持达梦、人大金仓等国产数据库。

MyBatis-Plus 项目托管在中国的代码平台 [**Gitee**](https://gitee.com/baomidou/mybatis-plus) 上（GitHub 也有镜像仓库）。



<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744266940480-846e32c2-4f48-4fdb-9cdc-62e6036e9627.png" width="1107" title="" crop="0,0,1,1" id="uf024cab5" class="ne-image" style="font-size: 16px">

# 第一个MyBatis-Plus
<img src="https://cdn.nlark.com/yuque/0/2025/jpeg/21376908/1757681056420-39d1bc52-55fe-4f3b-8183-b4d7ba79b166.jpeg" width="4308" title="" crop="0,0,1,1" id="ci7Ma" class="ne-image" style="font-size: 16px">

提示：MyBatis-Plus是基于SpringBoot框架的。



第一步：引入 `MyBatis-Plus`依赖（注意：以下引入的是适合于`Spring Boot 3`的依赖）

```xml
<dependency>
  <groupId>com.baomidou</groupId>
  <artifactId>mybatis-plus-spring-boot3-starter</artifactId>
  <version>3.5.11</version>
</dependency>
```



第二步：`Mapper`接口继承`BaseMapper`

```java
package com.jkweilai.mp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jkweilai.mp.model.Car;

public interface CarMapper extends BaseMapper<Car> {
}
```



具体步骤如下：

第一步：依赖

```xml
<dependencies>
  <!--spring boot核心启动器-->
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter</artifactId>
  </dependency>
  <!--MyBatis-Plus的Spring Boot 3的启动器-->
  <dependency>
    <groupId>com.baomidou</groupId>
    <artifactId>mybatis-plus-spring-boot3-starter</artifactId>
    <version>3.5.11</version>
  </dependency>
  <!--mysql驱动-->
  <dependency>
    <groupId>com.mysql</groupId>
    <artifactId>mysql-connector-j</artifactId>
    <scope>runtime</scope>
  </dependency>
  <!--lombok依赖-->
  <dependency>
    <groupId>org.projectlombok</groupId>
    <artifactId>lombok</artifactId>
    <optional>true</optional>
  </dependency>
  <!--Spring Boot 3测试启动器-->
  <dependency>
    <groupId>org.springframework.boot</groupId>
    <artifactId>spring-boot-starter-test</artifactId>
    <scope>test</scope>
  </dependency>
</dependencies>
```



第二步：编写实体类

```java
package com.jkweilai.mp.model;

import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_car")
public class Car {
    private Long id;
    private String carNum;
    private String brand;
    private Double guidePrice;
    private String produceTime;
    private String carType;
}
```



第三步：编写yml配置

```yaml
# 数据源
spring:
  datasource:
    type: com.zaxxer.hikari.HikariDataSource
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/mybatis
    username: root
    password: 123456
```



第四步：编写Mapper接口直接继承`BaseMapper`

```java
package com.jkweilai.mp.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jkweilai.mp.model.Car;

public interface CarMapper extends BaseMapper<Car> {
}
```

`**<font style="color:#DF2A3F;">BaseMapper</font>**`**<font style="color:#DF2A3F;">已经将CRUD相关的方法全部实现了，该类中有大量的 insert、delete、update、select 等方法。</font>**



第五步：Spring Boot主入口程序添加 Mapper扫描

```java
package com.jkweilai.mp;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@MapperScan(basePackages = {"com.jkweilai.mp.mapper"})
@SpringBootApplication
public class Mp01Application {

	public static void main(String[] args) {
		SpringApplication.run(Mp01Application.class, args);
	}

}
```



第六步：编写测试程序

```java
package com.jkweilai.mp;

import com.jkweilai.mp.mapper.CarMapper;
import com.jkweilai.mp.model.Car;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

@SpringBootTest
class Mp01ApplicationTests {

	@Autowired
	private CarMapper carMapper;

	@Test
	void testInsert(){
		Car car = new Car();
		car.setCarNum("999");
		car.setBrand("小米su7");
		car.setGuidePrice(30.00);
		car.setProduceTime("2025-10-11");
		car.setCarType("电车");
		int count = carMapper.insert(car);
		System.out.println("插入" + count + "条记录");
	}

	@Test
	void testDeleteById(){
		int count = carMapper.deleteById(7L);
		System.out.println("删除了" + count + "条记录");
	}

	@Test
	void testUpdateById(){
		Car car = new Car();
		car.setId(8L);
		car.setBrand("小米utl");
		int count = carMapper.updateById(car);
		System.out.println("更新了" + count + "条记录");
	}

	@Test
	void testSelectById(){
		Car car = carMapper.selectById(8L);
		System.out.println(car);
	}

	@Test
	void testSelectAll(){
		List<Car> cars = carMapper.selectList(null);
		cars.forEach(System.out::println);
	}

}
```

# MyBatis-Plus常用注解
<img src="https://cdn.nlark.com/yuque/0/2025/jpeg/21376908/1757681056420-39d1bc52-55fe-4f3b-8183-b4d7ba79b166.jpeg" width="4308" title="" crop="0,0,1,1" id="FLp8z" class="ne-image" style="font-size: 16px">

## 实体类和表是如何对应的
在第一个`MyBatis-Plus`程序中可以看到，没有编写`Mapper xml`文件，仅仅只编写了`CarMapper`继承`BaseMapper`。那`MyBatis-Plus`底层是怎么让`实体类`和`表`对应起来的呢？原理如下：

`MyBatis-Plus`通过以下代码找到实体类`Car`：

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744271336190-8eb901cc-9ca3-41ef-bd20-61086ffdb16d.png" width="556" title="" crop="0,0,1,1" id="ucd433b2d" class="ne-image" style="font-size: 16px">

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744271376349-0b925a4a-98d3-4dac-b8a4-a54b4c4f9675.png" width="347" title="" crop="0,0,1,1" id="u499e0ae2" class="ne-image" style="font-size: 16px">

**然后通过反射机制获取**`**Car**`**类的类名以及字段名，遵循**`**<font style="color:#DF2A3F;">约定大于配置</font>**`**的方式完成了**`**实体类**`**与**`**表**`**的对应，约定如下：**

+ **类名驼峰转下划线作为表名，例如：类名**`**UserInfo**`**，对应的表名**`**user_info**`
+ **自动将名字为**`**id**`**的字段作为主键**
+ **属性名驼峰转下划线作为字段名，例如：属性名**`**carType**`**，对应的字段名**`**car_type**`

**如果不符合约定就需要自己通过注解的方式来指定表名和字段名。**

## 常用注解
+ @TableName：指定表名的
+ @TableId：指定主键字段信息
+ @TableField：指定普通字段信息

这几个注解是比较常用的，还有一些其他注解，如果需要可以查一下官方文档：

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744275406202-6c783a7a-71fe-48fd-b645-0485781c7d93.png" width="1563" title="" crop="0,0,1,1" id="u077fef2a" class="ne-image" style="font-size: 16px">

### @TableName
当`实体类名`和`表名`不符合mp的约定，此时就可以使用该注解来解决了，例如实体类名为`Car`，但是表名为`t_car`，则需要使用该注解：

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744272072569-06b3b3c3-f08e-4244-962f-fd586c41d2d6.png" width="328" title="" crop="0,0,1,1" id="u1df7034b" class="ne-image" style="font-size: 16px">

### @TableId
**用该注解的原因：**

+ **默认约定**：<font style="color:rgb(53, 56, 65);">该注解用于标记实体类中的主键字段。如果你的主键字段名为 id，你可以省略这个注解。</font>**<font style="color:rgb(53, 56, 65);">（数据库表的主键字段名是 id，实体类的属性名恰好也是 id，该注解可以省略）</font>**
+ **官方建议**：只要你属性名不是 `id`，最好都使用上 `@TableId`注解（**即使属性名和字段名能对应上，写上起码可读性强，而且还可以指定主键生成策略**）。



**使用方法：**

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744272770437-2b50b6b3-7f38-49dd-b0d0-b96dce60684e.png" width="446" title="" crop="0,0,1,1" id="u599fecde" class="ne-image" style="font-size: 16px">

+ value 属性用来指定表的主键名
+ type 属性用来指定主键的生成策略，如果数据库表中主键值是`auto_increment`，则采用`type = IdType.AUTO`。



**主键生成策略：**

+ IdType.AUTO：数据库自增，这种方式需要在 MySQL 建表时指定 `auto_increment`，**不指定会失败**。
+ IdType.ASSIGN_ID：雪花算法生成分布式ID（**默认策略**），主键的类型可以是 Long、Integer、String。这是mp自己提供的，底层调用了`IdentifierGenerator`接口的`nextId()`方法，实现类是`DefaultIdentifierGenerator`，雪花算法实现的（**<font style="color:rgb(15, 17, 21);">雪花算法是一种分布式ID生成算法，通过组合</font>****<font style="color:#DF2A3F;">时间戳、机器ID和序列号</font>****<font style="color:rgb(15, 17, 21);">生成全局唯一、趋势递增的64位ID。占 8 个字节，</font>****<font style="color:#DF2A3F;">时钟回拨</font>****<font style="color:rgb(15, 17, 21);">或</font>****<font style="color:#DF2A3F;">机器 ID 全局不唯一</font>****<font style="color:rgb(15, 17, 21);">可能会导致 id 重复，但概率较低</font>**）。

**<font style="color:#DF2A3F;">需要注意的是：当你使用这种方式时，即使主键是 </font>**`**<font style="color:#DF2A3F;">auto_increment</font>**`**<font style="color:#DF2A3F;">，仍然会采用雪花算法生成 ID。</font>**

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744273213286-e4f3cf01-59ec-46e6-a9ab-2019f8e5e8ec.png" width="488" title="" crop="0,0,1,1" id="u94e15b31" class="ne-image" style="font-size: 16px">

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744273262895-d9e37364-59fe-4caf-b0cc-5a0262cbc1a8.png" width="736" title="" crop="0,0,1,1" id="u87fd527e" class="ne-image" style="font-size: 16px">

+ IdType.INPUT：需要程序员手动赋值。
+ IdType.ASSIGN_ID：自动分配 UUID，主键只能是 String 类型。默认实现为 IdentifierGenerator 的 nextUUID 方法
+ IdType.NONE：无特定生成策略，如果全局配置中有 IdType 相关的配置，则会跟随全局配置。



### @TableField
```sql
drop table if exists t_customer;
create table t_customer(
  id bigint primary key auto_increment,
  username varchar(255),
  is_vip varchar(255),
  `desc` varchar(255)
);
```



**什么情况下需要使用该注解：**

+ `属性名`和`字段名`不一致。
+ `属性名`以`is`开头，并且是布尔类型。（**<font style="color:#DF2A3F;">>=3.4.0版本的MyBatis-Plus已经对is开头的属性名进行了处理。无需添加这个注解。</font>**）
+ `属性名`与数据库中的关键字冲突了。
+ `属性名`不是数据库表中的字段。

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744274649300-1aa69362-cc41-4b52-b173-bbd5dafd9fcb.png" width="596" title="" crop="0,0,1,1" id="u22f7c694" class="ne-image" style="font-size: 16px">

# MyBatis-Plus常用配置
<img src="https://cdn.nlark.com/yuque/0/2025/jpeg/21376908/1757681056420-39d1bc52-55fe-4f3b-8183-b4d7ba79b166.jpeg" width="4308" title="" crop="0,0,1,1" id="C14bb" class="ne-image" style="font-size: 16px">

mp的配置可以参考官方文档：

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744276728852-f1b08204-42fb-4254-bb5b-a50765a97028.png" width="1584" title="" crop="0,0,1,1" id="u4e9c9a33" class="ne-image" style="font-size: 16px">



常用配置如下：

```yaml
mybatis-plus:
  
  configuration:
    map-underscore-to-camel-case: true # 是否开启驼峰和下划线的映射（默认值是true）
    cache-enabled: false # 是否启用二级缓存（默认值是true）
    log-impl: org.apache.ibatis.logging.stdout.StdOutImpl # 打印SQL日志（默认不开启）
    
  type-aliases-package: com.jkweilai.mp.model # 别名包（默认不设置）
  
  mapper-locations: "classpath*:/mapper/**/*.xml" # classpath后面第一个星号代表扫描所有jar包中的classpath。默认值就是它。
  
  global-config: # 全局配置
    db-config:
      id-type: auto # id类型（默认值是：ASSIGN_ID）
      update-strategy: not_null # 更新策略：不为空时更新（默认是not_null）
```

提示：大部分的配置都是有默认配置的。



**大家可能会有疑问**：mp不是不用写mapper xml文件吗？为什么还需要`mapper-locations`配置呢？

这是因为mp做单表CRUD的时候不用提供 mapper xml文件，如果自己需要定制SQL，或者多表操作的时候就需要手动编写`mapper xml`文件了。



**小细节**：`classpath:`和`classpath*:`的区别？

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744277884879-dd57ca99-9679-4034-9f68-67baa03e9aea.png" width="656" title="" crop="0,0,1,1" id="u369d77f1" class="ne-image" style="font-size: 16px">

classpath*: 的星号表示跨模块/跨 JAR 加载资源，确保不会遗漏分散在不同位置的 XML 文件。但是要注意，这种方式效率较低，不要滥用。

# MyBatis-Plus条件构造器
<img src="https://cdn.nlark.com/yuque/0/2025/jpeg/21376908/1757681056420-39d1bc52-55fe-4f3b-8183-b4d7ba79b166.jpeg" width="4308" title="" crop="0,0,1,1" id="zMpab" class="ne-image" style="font-size: 16px">

## 初识条件构造器
mp默认生成的CRUD的SQL语句，都是基于主键id的，例如：deleteById、updateById、selectById等。

在实际的开发中 SQL 的 where 条件应该是多样化的，如何构造一个复杂条件的 SQL 呢？mp 提供了条件构造器`Wrapper`来解决这个问题。



来自官方的一段描述：

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744286634938-7d863454-cc00-4452-80ab-fa2320e779bf.png" width="924" title="" crop="0,0,1,1" id="u825e8b6e" class="ne-image" style="font-size: 16px">



以下是条件构造器`Wrapper`的继承结构图：

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744287684456-1fc1448b-0e13-46f7-b4a4-0789213abc40.png" width="863" title="" crop="0,0,1,1" id="u13d61d24" class="ne-image" style="font-size: 16px">

## QueryWrapper的使用
**<font style="color:#DF2A3F;">注意：MP 中提供的</font>**`**<font style="color:#DF2A3F;">QueryWrapper</font>**`**<font style="color:#DF2A3F;"> 与 </font>**`**<font style="color:#DF2A3F;">LambdaQueryWrapper</font>**`**<font style="color:#DF2A3F;">适合于单表查询，如果多表连接查询建议大家使用 MyBatis 原生配置文件。当然，MP 也可以实现多表连接查询，只是代码会变的很难维护、很难阅读。官方最佳实践是：采用 MP 做单表查询。</font>**

### 案例1
查询出汽车品牌中带有 '宝' 的，厂商指导价大于20万的，要求查询 id、car_num、brand、guide_price 字段。

如果写SQL语句应该这样写：

```sql
select id,car_num,brand,guide_price from t_car where brand like '%宝%' and guide_price > 20;
```

如果用条件构造器的话，Java代码应该怎么写呢？

```java
@Test
void testQueryWrapper(){
    // 1. 创建条件构造器
    QueryWrapper<Car> wrapper = new QueryWrapper<>();
    // 2. 链式追加条件
    wrapper.select("id","car_num","brand","guide_price")
            .like("brand", "宝")
            .gt("guide_price", 20.0);
    // 3. 查询
    List<Car> cars = carMapper.selectList(wrapper);
    cars.forEach(System.out::println);
}
```



另外，以上编写的代码中 `"id","car_num","brand","guide_price"` 等都属于硬编码，如果表的列名修改了，Java代码也是需要修改的。另外双引号里的字符串即使写错了，编译器也不会报错，因此mp给出了`Lambda`方式来解决这个问题，例如`LambdaQueryWrapper`，代码如下：

```java
@Test
void testLambdaQueryWrapper(){
    // 1.创建条件构造器
    LambdaQueryWrapper<Car> wrapper = new LambdaQueryWrapper<>();
    // 2.链式追加条件
    wrapper.select(Car::getId,Car::getCarNum,Car::getBrand,Car::getGuidePrice)
            .like(Car::getBrand, "宝")
            .gt(Car::getGuidePrice, 20.0);
    // 3.查询
    List<Car> cars = carMapper.selectList(wrapper);
    cars.forEach(System.out::println);
}
```



`**Car::getId**`**是如何转换成 **`**"id"**`**的呢？感兴趣的同学可以研究一下以下代码：**

```java
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;

// 普通类
class User {
    private Long id;

    public Long getId() {
        return id;
    }
}

// 函数式接口
interface MyFunc extends java.io.Serializable {
    Object apply(User user);
}

// 测试程序（以下代码是Lambda表达式相关的反射机制代码）
public class Test {
    public static void main(String[] args) throws Exception {
        // 方法引用
        MyFunc func = User::getId;
        // 获取SerializedLambda
        // 当Lambda表达式实现的函数式接口继承了Serializable，在Lambda表达式对应的类中会生成一个writeReplace方法。
        Method m = func.getClass().getDeclaredMethod("writeReplace");
        m.setAccessible(true);
        // 调用writeReplace方法会返回SerializedLambda对象，SerializedLambda 对象封装了 Lambda 表达式的“元数据”和“捕获的参数值”
        SerializedLambda lambda = (SerializedLambda) m.invoke(func);
        // 提取字段名
        String methodName = lambda.getImplMethodName(); // "getId"
        String fieldName = methodName.substring(3, 4).toLowerCase() + methodName.substring(4); // "id"
        System.out.println("字段名: " + fieldName);
    }
}
```

### 案例2
将`宝马535`的厂商指导价修改为`10.0`万。

对应的SQL语句写法：

```sql
update t_car set guide_price = 10.0 where brand = '宝马535';
```

如果用条件构造器的话Java代码该怎么写呢？

```java
@Test
void testQueryWrapper2(){
    // 1.准备数据
    Car car = new Car();
    car.setGuidePrice(10.0);
    // 2.创建条件构造器，并链式追加条件
    QueryWrapper<Car> wrapper = new QueryWrapper<Car>()
            .eq("brand", "宝马535");
    // 3.更新
    carMapper.update(car, wrapper);
}
```

使用`LambdaQueryWrapper`改造：

```java
@Test
void testLambdaQueryWrapper2(){
    // 1.准备数据
    Car car = new Car();
    car.setGuidePrice(10.0);
    // 2.创建条件构造器，并链式追加条件
    LambdaQueryWrapper<Car> wrapper = new LambdaQueryWrapper<Car>()
            .eq(Car::getBrand, "宝马535");
    // 3.更新
    carMapper.update(car, wrapper);
}
```

## UpdateWrapper的使用
当`update`语句中`set`后面的语句比较特殊的话，就需要使用`UpdateWrapper`了。

例如：要求所有的`宝马535`涨价`5`万。

SQL语句应该这样写：

```sql
update t_car set guide_price = guide_price + 5 where brand = '宝马535';
```

这个时候就需要使用`UpdateWrapper`了，Java代码如下：

```java
@Test
void testUpdateWrapper(){
    // 创建条件构造器
    UpdateWrapper<Car> wrapper = new UpdateWrapper<>();
    // 设置set语句并链式追加条件
    wrapper.setSql("guide_price = guide_price + 5")
            .eq("brand", "宝马535");
    // 更新
    carMapper.update(wrapper);
}
```

如果使用`LambdaUpdateWrapper`，代码应该这样写：

```java
@Test
void testLambdaUpdateWrapper(){
    // 创建条件构造器
    LambdaUpdateWrapper<Car> wrapper = new LambdaUpdateWrapper<>();
    // 设置set语句并链式追加条件
    wrapper.setSql("guide_price = guide_price + 5")
            .eq(Car::getBrand, "宝马535");
    // 更新
    carMapper.update(wrapper);
}
```

## 条件构造器用法总结
条件构造器用法：

1. `QueryWrapper`和`LambdaQueryWrapper`用来构建`select`、`delete`、`update`的`where`条件。
2. `UpdateWrapper`和`LambdaUpdateWrapper`一般只有在`set语句`比较特殊的情况下才会使用。
3. 尽量使用`LambdaQueryWrapper`和`LambdaUpdateWrapper`，避免硬编码。

# 自定义SQL
<img src="https://cdn.nlark.com/yuque/0/2025/jpeg/21376908/1757681056420-39d1bc52-55fe-4f3b-8183-b4d7ba79b166.jpeg" width="4308" title="" crop="0,0,1,1" id="KH1xu" class="ne-image" style="font-size: 16px">

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744292844260-8f50ee88-77dc-48e5-a8a0-80f21c72074d.png" width="646" title="" crop="0,0,1,1" id="ue31c2549" class="ne-image" style="font-size: 16px">

在Java程序中直接编写SQL这是不建议的，怎么解决这个问题？自定义SQL可以解决。



什么是自定义SQL？在Java程序中继续使用`Wrapper`进行条件的封装，将SQL语句编写到`Mapper xml`文件中，将`Wrapper`传递给`Mapper xml`，然后进行SQL语句的拼装。

第一步：在java程序中只进行`Wrapper`条件的构造

```java
@Test
void testCustomSql(){
    // 创建条件构造器
    UpdateWrapper<Car> wrapper = new UpdateWrapper<>();
    // 设置set语句并链式追加条件
    wrapper.eq("brand", "宝马535");
    // 更新
    carMapper.updateByCustomSql(wrapper, 5.0);
}
```



第二步：在Mapper接口中自定义方法

```java
public interface CarMapper extends BaseMapper<Car> {
    void updateByCustomSql(@Param("ew") UpdateWrapper<Car> wrapper, @Param("amount") Double amount);
}
```

注意：`@Param("ew")`中的`ew`是固定写法。当然也可以采用mp内置的常量来代替`"ew"`：<font style="color:#080808;background-color:#ffffff;">Constants.WRAPPER</font>



第三步：编写`Mapper xml`的配置

```xml
<mapper namespace="com.jkweilai.mp.mapper.CarMapper">
    <update id="updateByCustomSql">
        update t_car set guide_price = guide_price + #{amount} ${ew.customSqlSegment}
    </update>
</mapper>
```

注意：`${ew.customSqlSegment}`是固定写法。



# IService接口
<img src="https://cdn.nlark.com/yuque/0/2025/jpeg/21376908/1757681056420-39d1bc52-55fe-4f3b-8183-b4d7ba79b166.jpeg" width="4308" title="" crop="0,0,1,1" id="RQ6Ge" class="ne-image" style="font-size: 16px">

mp不仅提供了持久层的代码，还提供了service层的代码。

## 自己写的service层代码
```java
package com.jkweilai.mp.service;

import com.jkweilai.mp.model.Car;

import java.util.List;

public interface CarService{
    // 增
    int save(Car car);
    // 删
    int removeById(Long id);
    // 改
    int updateById(Car car);
    // 查一个
    Car getById(Long id);
    // 查所有
    List<Car> list();
}
```

```java
package com.jkweilai.mp.service.impl;

import com.jkweilai.mp.mapper.CarMapper;
import com.jkweilai.mp.model.Car;
import com.jkweilai.mp.service.CarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CarServiceImpl implements CarService {
    @Autowired
    private CarMapper carMapper;

    @Override
    public int save(Car car) {
        return carMapper.insert(car);
    }

    @Override
    public int removeById(Long id) {
        return carMapper.deleteById(id);
    }

    @Override
    public int updateById(Car car) {
        return carMapper.updateById(car);
    }

    @Override
    public Car getById(Long id) {
        return carMapper.selectById(id);
    }

    @Override
    public List<Car> list() {
        return carMapper.selectList(null);
    }
}
```

可见，在没有复杂业务的前提下，代码几乎也是固定的，就是在service中注入`mapper`，然后调用`mapper`相关的方法。因此这些代码mp也是可以自动生成的。

## 使用mp提供的IService接口
两步即可实现：

第一步：编写`CarService接口`继承`IService<Car>`接口

```java
package com.jkweilai.mp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jkweilai.mp.model.Car;

public interface CarService extends IService<Car> {
}

```

提示：`IService接口`是mp提供的，为什么我们的接口要继承这个接口，因为在`Controller`中要面向接口调用service的方法，而这些方法都在`IService接口`中，如果需要额外的业务方法，可以在自己的接口中额外添加扩展方法。



第二步：编写`CarServiceImpl实现类`继承`ServiceImpl<CarMapper,Car>`类并实现`CarService接口`

```java
package com.jkweilai.mp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jkweilai.mp.mapper.CarMapper;
import com.jkweilai.mp.model.Car;
import com.jkweilai.mp.service.CarService;
import org.springframework.stereotype.Service;

@Service
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements CarService {
}

```

提示：为什么实现了`CarService接口`还要继承 `ServiceImpl<CarMapper, Car>`？这是因为在`ServiceImpl<CarMapper, Car>`里面mp给了默认的实现，如果不继承它，则需要将`IService接口`中所有的方法自己全部实现一遍。



**<font style="color:#DF2A3F;">IService接口的继承结构如下：</font>**

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744336178236-0441b8f3-ca31-40a4-a9cd-a0438e01f2c1.png" width="550" title="" crop="0,0,1,1" id="VAV50" class="ne-image" style="font-size: 16px">

## IService接口常用方法
### 负责新增的方法
<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744336381917-1e255d47-67f0-4af9-9598-e857ff051848.png" width="465" title="" crop="0,0,1,1" id="u0422ddb9" class="ne-image" style="font-size: 16px">

+ save(T) 保存
+ saveBatch(Collection<T>) 批量保存
+ saveOrUpdate(T) 保存或修改（保存时根据id判断，如果没有则保存，如果有则更新）

### 负责删除的方法
<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744336400274-077afd2b-f307-408b-bfec-3f2d943cc5bc.png" width="444" title="" crop="0,0,1,1" id="uf47464a7" class="ne-image" style="font-size: 16px">

+ removeById(Serializable) 根据主键删除
+ removeByIds(Collection<?>) 根据多个主键删除多条记录，底层用 `in(id1, id2, id3)`
+ removeBatchByIds(Collection<?>) 根据多个主键删除多条记录，底层会启动JDBC的批处理操作（调用JDBC的addBatch方法来批量删除，大数量时效率较高。）

### 负责修改的方法
<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744336479166-0bbbbbe3-e8e7-4387-9f87-c9fe5afb7958.png" width="452" title="" crop="0,0,1,1" id="u9045ce42" class="ne-image" style="font-size: 16px">

+ updateById(T) 根据id更新
+ updateBatchById(Collection<T>) 根据id批量更新

### 负责查询的方法
<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744337340527-47aec78b-c34e-48a1-8d7f-6019bd8bed09.png" width="260" title="" crop="0,0,1,1" id="u6c82ac35" class="ne-image" style="font-size: 16px">

+ 这几个方法都是查一个。



<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744337480761-c157a80d-fb26-4635-8694-0cbb7a5413b8.png" width="366" title="" crop="0,0,1,1" id="u53178914" class="ne-image" style="font-size: 16px">

+ 这几个方法都是查多个



<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744336558292-010dea5b-18ee-4439-96c2-212c6e831b98.png" width="258" title="" crop="0,0,1,1" id="u8dfb8967" class="ne-image" style="font-size: 16px">

+ 这几个方法是查数量



<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744336698246-3fe6be3f-b343-413f-b8e9-e2c7b5e3c447.png" width="215" title="" crop="0,0,1,1" id="u55ad9b7e" class="ne-image" style="font-size: 16px">

+ 这几个方法负责分页查询



<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744336716976-4717df5e-140e-47ed-ab88-a4cc0c57d46a.png" width="375" title="" crop="0,0,1,1" id="ua98fb993" class="ne-image" style="font-size: 16px">

+ 复杂条件的查询和更新建议使用这几个方法。
+ **<font style="color:#DF2A3F;">提示：如果是通过主键查询或更新建议使用之前的方法，如果是复杂条件的查询或更新使用这几个方法更方便。</font>**



## 基于mp的IService开发业务接口
### 业务概述
实现五个接口，如下：

| 编号 | 接口                 | 请求方式 | 请求路径                         | 请求参数         | 返回值     |
| ---- | -------------------- | -------- | -------------------------------- | ---------------- | ---------- |
| 1    | 新增汽车             | POST     | /api/cars                        | 汽车 DTO         | 无         |
| 2    | 删除汽车             | DELETE   | /api/cars/{id}                   | 汽车id           | 无         |
| 3    | 根据id查询汽车       | GET      | /api/cars/{id}                   | 汽车id           | 汽车VO     |
| 4    | 根据id批量查询       | GET      | /api/cars                        | 汽车id集合       | 汽车VO集合 |
| 5    | 根据id降低厂商指导价 | PUT      | /api/cars/{id}/reduction/{price} | 汽车id，降价额度 | 无         |


实现技术：**SpringBoot + MyBatis-Plus + Swagger + Hutool + Lombok**

### 搭建环境
1. 创建SpringBoot项目
2. 创建SpringBoot项目过程中引入依赖

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744351469957-b3f6df83-b790-4407-abbb-ee3da3d69240.png" width="207" title="" crop="0,0,1,1" id="uf6cc7783" class="ne-image" style="font-size: 16px">



3. SpringBoot项目创建完成后，`application.properties`修改为`application.yml`
4. 引入依赖：MyBatis-Plus、Swagger、Hutool

```xml
<!--swagger依赖-->
<dependency>
  <groupId>com.github.xiaoymin</groupId>
  <artifactId>knife4j-openapi3-jakarta-spring-boot-starter</artifactId>
  <version>4.5.0</version>
</dependency>
<!--MyBatis-Plus依赖-->
<dependency>
  <groupId>com.baomidou</groupId>
  <artifactId>mybatis-plus-spring-boot3-starter</artifactId>
  <version>3.5.11</version>
</dependency>
<!-- hutool依赖，一个java工具库 -->
<dependency>
  <groupId>cn.hutool</groupId>
  <artifactId>hutool-all</artifactId>
  <version>5.8.37</version>
</dependency>
```

5. 编写`application.yml`配置文件

```yaml
spring:
  datasource:
    driver-class-name: com.mysql.cj.jdbc.Driver
    url: jdbc:mysql://localhost:3306/mp
    username: root
    password: 123456

  mvc:
    pathmatch:
      matching-strategy: ant_path_matcher

knife4j:
  enable: true
  group:
    default:
      group-name: default
      api-rule: package
      api-rule-resources:
        - com.jkweilai.mp.controller
```

6. 提供 Swagger 的配置类

```java
package com.jkweilai.mp.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class Knife4jConfig {

    @Bean
    public OpenAPI customOpenAPI() {
        return new OpenAPI()
                .info(new Info()
                        .title("汽车信息管理接口文档")
                        .description("汽车信息管理接口文档")
                        .version("1.0.0")
                        .contact(new Contact()
                                // 姓名和邮箱都是虚拟示例值
                                .name("张三")
                                .email("zhangsan@example.com")
                                .url("http://localhost:8080")));
    }
}
```



### MyBatis-Plus相关代码
编写po：Car类

```java
package com.jkweilai.mp.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

// 数据库实体类一般不需要出现在API文档中。因此数据库实体类不需要提供Swagger注解。
@Data
@TableName("t_car")
public class Car {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "car_num")
    private String carNum;

    @TableField(value = "brand")
    private String brand;

    @TableField(value = "guide_price")
    private BigDecimal guidePrice;

    @TableField(value = "produce_time")
    private String produceTime;

    @TableField(value = "car_type")
    private String carType;
}

```



dao 的编写：CarDao 接口

```java
package com.jkweilai.mp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jkweilai.mp.entity.Car;

import java.math.BigDecimal;

public interface CarDao extends BaseMapper<Car> {
}

```

**<font style="color:#DF2A3F;">在SpringBoot入口程序上添加</font>**`**<font style="color:#DF2A3F;">@MapperScan</font>**`**<font style="color:#DF2A3F;">扫描</font>**

<font style="color:#080808;background-color:#ffffff;">@MapperScan("com.jkweilai.mp.dao")</font>



service接口的编写：CarService

```java
package com.jkweilai.mp.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jkweilai.mp.entity.Car;

import java.math.BigDecimal;

public interface CarService extends IService<Car> {
}

```



service实现类的编写：CarServiceImpl

```java
package com.jkweilai.mp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jkweilai.mp.dao.CarDao;
import com.jkweilai.mp.entity.Car;
import com.jkweilai.mp.service.CarService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service("carService")
public class CarServiceImpl extends ServiceImpl<CarDao, Car> implements CarService {
}

```

### 编写dto
```java
package com.jkweilai.mp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

// DTO接收前端系统提交的数据。
// 1.不需要id属性，因为id是数据库自动生成的。
// 2.需要requiredMode
// 3.需要allowableValues
@Data
@Schema(description = "汽车信息")
public class CarDTO {
    @Schema(description = "车牌号", example = "京A88888", requiredMode = Schema.RequiredMode.REQUIRED)
    private String carNum;

    @Schema(description = "品牌", example = "宝马535Li", requiredMode = Schema.RequiredMode.REQUIRED)
    private String brand;

    @Schema(description = "厂商指导价（单位：万）", example = "50.00", multipleOf = 0.01, requiredMode = Schema.RequiredMode.REQUIRED)
    private BigDecimal guidePrice;

    @Schema(description = "出厂日期", example = "1970-01-01", requiredMode = Schema.RequiredMode.REQUIRED)
    private String produceTime;

    @Schema(description = "汽车类型", example = "燃油车", allowableValues = {"燃油车", "新能源", "氢能源"}, requiredMode = Schema.RequiredMode.REQUIRED)
    private String carType;
}

```

### 编写vo
```java
package com.jkweilai.mp.vo;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

// VO是负责响应的
// 1.需要提供id属性
// 2.不需要requiredMode
// 3.不需要allowableValues
@Data
@Schema(description = "汽车信息实体")
public class CarVO {
    @Schema(description = "主键", example = "1")
    private Long id;

    @Schema(description = "车牌号", example = "京A88888")
    private String carNum;

    @Schema(description = "品牌", example = "宝马535Li")
    private String brand;

    @Schema(description = "厂商指导价（单位：万）", example = "50.00")
    private BigDecimal guidePrice;

    @Schema(description = "出厂日期", example = "1970-01-01")
    private String produceTime;

    @Schema(description = "汽车类型", example = "燃油车")
    private String carType;
}

```

### 实现简单的业务接口
编写`CarController`

```java
package com.jkweilai.mp.controller;

import cn.hutool.core.bean.BeanUtil;
import com.jkweilai.mp.dto.CarDTO;
import com.jkweilai.mp.entity.Car;
import com.jkweilai.mp.service.CarService;
import com.jkweilai.mp.vo.CarVO;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api")
@RequiredArgsConstructor
// @Tag 的作用是把该 Controller 中所有接口，在文档中归类到同一个分组下，并给这个分组起一个易读的名称和说明。
@Tag(name = "汽车信息管理", description = "对汽车信息进行增删改查")
public class CarController {

    private final CarService carService;

    @PostMapping("/cars")
    // @Operation 用于描述一个接口（API）的功能，summary 写简短名称，description 写详细说明，让调用方快速理解接口的用途和约束。
    @Operation(summary = "新增汽车", description = "新增汽车信息，所有字段为必填项")
    // @ApiResponse 用于在文档中说明接口可能返回的各种 HTTP 状态码及其业务含义，告诉调用方"什么情况返回什么状态码"。和controller方法的返回值无关。
    @ApiResponse(responseCode = "200", description = "新增成功")
    @ApiResponse(responseCode = "400", description = "请求参数错误")
    public void add(@RequestBody
                    //@Parameter 用于在文档中描述 Controller 方法中某个参数（如 @RequestParam、@PathVariable）的用途和约束，让调用方知道该参数的含义
                    //由于 CarDTO 中的字段已经用@Schema标注了，所以这里的@Parameter注解建议省略。
                    @Parameter(description = "汽车信息请求体", required = true)
                    CarDTO carDTO) {
        // 将CarDTO转换为Car
        Car car = BeanUtil.copyProperties(carDTO, Car.class);
        // 调用service保存汽车
        carService.save(car);
    }

    @DeleteMapping("/cars/{id}")
    @Operation(summary = "删除汽车信息", description = "根据id删除汽车信息")
    @ApiResponse(responseCode = "200", description = "删除成功")
    @ApiResponse(responseCode = "400", description = "请求参数错误")
    public void removeById(@PathVariable @Parameter(description = "汽车主键id", example = "1", required = true) Long id) {
        carService.removeById(id);
    }

    @GetMapping("/cars/{id}")
    @Operation(summary = "查询汽车信息", description = "根据汽车id查询汽车信息")
    @ApiResponse(responseCode = "200", description = "查询成功")
    @ApiResponse(responseCode = "400", description = "请求参数错误")
    public CarVO getCarById(@PathVariable @Parameter(description = "汽车主键id", example = "1", required = true) Long id) {
        // 返回实体
        Car car = carService.getById(id);
        // 实体转换为VO
        CarVO carVO = BeanUtil.copyProperties(car, CarVO.class);
        return carVO;
    }

    @GetMapping("/cars")
    @Operation(summary = "根据id批量查询", description = "根据多个id查询多个汽车信息")
    @ApiResponse(responseCode = "200", description = "查询成功")
    @ApiResponse(responseCode = "400", description = "请求参数错误")
    // Spring Boot 默认支持将多个同名参数或逗号分隔的参数自动绑定到 List 集合上
    // ?id=1,2,3  和  ?id=1&id=2&id=3  都支持。不支持：[1,2,3]
    public List<CarVO> getCars(@RequestParam("id") @Parameter(description = "汽车ID列表，多个用逗号分隔", example = "1,2,3", required = true) List<Long> ids) {
        List<Car> carList = carService.listByIds(ids);
        return BeanUtil.copyToList(carList, CarVO.class);
    }

}

```

### 实现复杂的业务接口
在controller中添加方法：

```java
@PutMapping("/cars/{id}/reduction/{price}")
@Operation(summary = "汽车降价", description = "汽车的价格进行降价操作")
@ApiResponse(responseCode = "200", description = "降价成功")
@ApiResponse(responseCode = "400", description = "请求参数错误")
public void reduction(@PathVariable @Parameter(description = "汽车id", example = "1", required = true) Long id,
                      @PathVariable @Parameter(description = "降价额度（单位：万）", example = "1.00", required = true) BigDecimal price) {
    carService.reduction(id, price);
}
```

CarService接口添加方法：

```java
public interface CarService extends IService<Car> {
    void reduction(Long id, BigDecimal price);
}
```

CarServiceImpl实现方法：

```java
package com.jkweilai.mp.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jkweilai.mp.dao.CarDao;
import com.jkweilai.mp.entity.Car;
import com.jkweilai.mp.service.CarService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

@Service("carService")
public class CarServiceImpl extends ServiceImpl<CarDao, Car> implements CarService {

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void reduction(Long id, BigDecimal price) {
        // 1. 根据id查询汽车信息，如果不存在抛异常
        Car car = getById(id);
        if(car == null){
            throw new RuntimeException("您操作降价的汽车不存在，汽车id=" + id);
        }
        // 2. 判断降价额度大于当前价格，不能降价，因为价格不能小于0
        if(price.compareTo(car.getGuidePrice()) > 0){
            throw new RuntimeException("降价后的价格不能小于0");
        }
        // 3. 降价
        // baseMapper 是 ServiceImpl中内置的一个引用。指向Dao对象。
        baseMapper.reduction(id, price);
    }

}

```

CarDao 添加方法：

```java
package com.jkweilai.mp.dao;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jkweilai.mp.entity.Car;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;

import java.math.BigDecimal;

public interface CarDao extends BaseMapper<Car> {
    @Update("update t_car set guide_price = guide_price - #{price} where id = #{id}")
    void reduction(@Param("id") Long id, @Param("price") BigDecimal price);
}

```



**最后**，启动项目，打开浏览器，输入`Swagger`地址：http://localhost:8080/doc.html 来进行接口的测试：

<img src="https://cdn.nlark.com/yuque/0/2026/png/21376908/1784459807923-6f778b91-e948-42ac-9285-6972b17c7431.png" width="1497.6" title="" crop="0,0,1,1" id="ud22b3f9e" class="ne-image">



### IService的lambdaQuery()方法
适合复杂查询。

实现一个功能：实现多条件查询。查询条件包括：

+ 可以根据汽车品牌模糊查询
+ 可以根据价格区间查询
+ 可以根据汽车类型查询

实际查询时，不知道用户提供了哪些条件。如果使用原生的`mybatis`实现的话`SQL`应该是这样写：

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744362927181-94c01deb-74e3-4cda-8688-95cc0cf44182.png" width="593" title="" crop="0,0,1,1" id="u44451229" class="ne-image" style="font-size: 16px">



在mp中应该怎么做呢？可以使用我们之前学过的`Wrapper`，也可以使用`IService`中提供的`lambdaQuery()`方法。下面演示`lambdaQuery()`的用法：

提供`CarQuery`对象来封装查询条件：

```java
package com.jkweilai.mp.dto;

import io.swagger.v3.oas.annotations.media.Schema;
import lombok.Data;

import java.math.BigDecimal;

@Data
@Schema(description = "查询汽车的条件")
public class CarQuery {
    @Schema(description = "根据汽车品牌模糊查询", example = "宝马")
    private String brand;
    @Schema(description = "根据价格区间查询，只要大于等于这个价格的查出来（单位：万）", example = "10.00")
    private BigDecimal minPrice;
    @Schema(description = "根据价格区间查询，只要小于等于这个价格的查出来（单位：万）", example = "20.00")
    private BigDecimal maxPrice;
    @Schema(description = "根据汽车类型查询", example = "燃油车", allowableValues = {"燃油车", "新能源", "氢能源"})
    private String carType;
}

```



在`CarController`中提供`queryByMultiCondition`方法：

**<font style="color:#DF2A3F;">查询操作如果参数不多，RESTful 接口应设计为 get 请求，请求在请求行上提交，以下代码中 </font>**`**<font style="color:#DF2A3F;">@ParameterObject</font>**`**<font style="color:#DF2A3F;">是 swagger 的注解，和 SpringMVC 无关。使用这个注解，swagger 在生成文档的时候，会将 </font>**`**<font style="color:#DF2A3F;">CarQuery</font>**`**<font style="color:#DF2A3F;">对象的属性拆开在文档中显示。</font>**

```java
@GetMapping("/cars/conditions")
@Operation(summary = "多条件查询汽车", description = "根据多条件查询汽车信息")
@ApiResponse(responseCode = "200", description = "查询成功")
@ApiResponse(responseCode = "400", description = "请求参数错误")
// 加上 @ParameterObject 后，Swagger 会把 CarQuery 的字段展开成独立的 Query 参数。
// @ParameterObject是swagger中的注解，有了它，swagger的doc中接口测试时，参数能够正确显示。
// 没有它不影响我们项目的执行，只是为了在swagger中测试方便。
public List<CarVO> queryByMultiCondition(@ParameterObject CarQuery carQuery) {
    List<Car> carList = carService.queryByMultiCondition(carQuery);
    return BeanUtil.copyToList(carList, CarVO.class);
}
```



在`CarServiceImpl`中实现`queryByMultiCondition`方法：使用`lambdaQuery()`方法

```java
@Override
public List<Car> queryByMultiCondition(CarQuery carQuery) {

    String brand = carQuery.getBrand();
    String carType = carQuery.getCarType();
    BigDecimal maxPrice = carQuery.getMaxPrice();
    BigDecimal minPrice = carQuery.getMinPrice();
    // 这里没有再调用 lambdaQuery().select()这样的方法，如果不调用就是将实体类中的@TableField标注的字段都返回。
    // 如果你要返回指定字段，那么就需要手动调用select()方法。
    return lambdaQuery()
            .like(StringUtils.isNotBlank(brand), Car::getBrand, brand)
            .eq(StringUtils.isNotBlank(carType), Car::getCarType, carType)
            .ge(minPrice != null, Car::getGuidePrice, minPrice)
            .le(maxPrice != null, Car::getGuidePrice, maxPrice)
            .list();
}
```



### IService的lambdaUpdate()方法
适合复杂更新语句。

需求：针对提供id的车辆进行降价操作，如果降价后的价格低于10万，则将汽车类型修改为低端车。

直接在`CarServiceImpl`<font style="color:#080808;background-color:#ffffff;">的</font>`<font style="color:#080808;background-color:#ffffff;">reduction()</font>`<font style="color:#080808;background-color:#ffffff;">方法基础上做修改：</font>

```java
@Override
@Transactional(rollbackFor = Exception.class)
public void reduction(Long id, BigDecimal price) {
    // 1. 根据id查询汽车信息，如果不存在抛异常
    Car car = getById(id);
    if(car == null){
        throw new RuntimeException("您操作降价的汽车不存在，汽车id=" + id);
    }
    // 2. 判断降价额度大于当前价格，不能降价，因为价格不能小于0
    if(price.compareTo(car.getGuidePrice()) > 0){
        throw new RuntimeException("降价后的价格不能小于0");
    }
    // 3. 降价
    // baseMapper 是 ServiceImpl中内置的一个引用。指向Dao对象。
    //baseMapper.reduction(id, price);

    // 降价后的价格
    BigDecimal currentPrice = car.getGuidePrice().subtract(price);
    lambdaUpdate()
            .set(Car::getGuidePrice, currentPrice)
            .set(currentPrice.compareTo(new BigDecimal(10)) < 0, Car::getCarType, "低端车")
            .eq(Car::getId, id)
            .eq(Car::getGuidePrice, car.getGuidePrice()) // 乐观锁机制，更新时看看有没有被别人更新。
            .update();
}
```

**<font style="color:#DF2A3F;">注意：事务 + 乐观锁 来保证原子化操作！！！</font>**

### IService的批量新增
向`t_car`表中插入1万条车辆信息



1. 第一种方式：不使用批处理操作并记录耗时

```java
@Test
void testSave() {
    long begin = System.currentTimeMillis();
    for (long i = 1; i <= 10000; i++) {
        // 每循环一次创建一个Car对象
        Car car = new Car();
        car.setId(i);
        car.setCarNum("CarNum" + i);
        car.setBrand("宝马" + i);
        car.setGuidePrice(30.0);
        car.setProduceTime("2000-10-11");
        car.setCarType("燃油车");
        // 保存Car
        carService.save(car);
    }
    long end = System.currentTimeMillis();
    System.out.println("耗时" + (end - begin) / 1000 + "秒");
}
```

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744372402863-22556ef5-fc53-4a2c-9643-ab3f0815c489.png" width="163" title="" crop="0,0,1,1" id="udbcf2268" class="ne-image" style="font-size: 16px">



2. 第二种方式：使用批处理操作并记录耗时

```java
@Test
void testSaveBatch() {
    long begin = System.currentTimeMillis();
    List<Car> cars = new ArrayList<>();
    for (long i = 1; i <= 10000; i++) {
        // 每循环一次创建一个Car对象
        Car car = new Car();
        car.setId(i);
        car.setCarNum("CarNum" + i);
        car.setBrand("宝马" + i);
        car.setGuidePrice(30.0);
        car.setProduceTime("2000-10-11");
        car.setCarType("燃油车");
        cars.add(car);
        // 每100个保存一次
        if(i % 100 == 0){
            carService.saveBatch(cars);
            cars.clear();
        }
    }
    long end = System.currentTimeMillis();
    System.out.println("耗时" + (end - begin) / 1000 + "秒");
}
```

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744372598941-ca856d4c-382d-43b3-a80b-260b97e93c49.png" width="135" title="" crop="0,0,1,1" id="uc6370e5e" class="ne-image" style="font-size: 16px">

效率得到提升，原理是：每100条`insert`语句打包一次批量发给数据。



3. 在`application.yml`中的`url`后面添加`rewriteBatchedStatements=true`并记录耗时

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744373051588-13dd3192-2f5a-4d43-8068-8354900bb890.png" width="161" title="" crop="0,0,1,1" id="ubfb75374" class="ne-image" style="font-size: 16px">

它的作用是将这种写法

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744372873640-bc99c91c-2142-4809-8936-a9ebb3d0c8ca.png" width="325" title="" crop="0,0,1,1" id="uad8149b3" class="ne-image" style="font-size: 16px">

转换成这种写法

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744372883742-2335e86b-2836-4905-8ac2-83c5f3259683.png" width="434" title="" crop="0,0,1,1" id="ub923bf3e" class="ne-image" style="font-size: 16px">

这是mysql驱动实现的，不是mp的功能。

# 代码生成器/逆向工程
<img src="https://cdn.nlark.com/yuque/0/2025/jpeg/21376908/1757681056420-39d1bc52-55fe-4f3b-8183-b4d7ba79b166.jpeg" width="4308" title="" crop="0,0,1,1" id="Lj8uR" class="ne-image" style="font-size: 16px">



`MyBatis-Plus`官方为我们推荐了两种方式：

+ 第一种方式：通过自己编写代码来生成代码（**可以参考官方的代码来实现**）
+ 第二种方式：`baomidou` 专门为`IDEA`开发了`MyBatis X`插件。



首先，要安装插件`MyBatis X`

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744375663041-742ea8a1-077f-4524-a555-1b025acc0d69.png" width="1311" title="" crop="0,0,1,1" id="uf2451ff2" class="ne-image" style="font-size: 16px">



安装插件后，在表上右键，会出现`MyBatisX-Generator`，如下图：

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744376534949-7a74a587-4cbc-4792-9fab-d963f232f0d3.png" width="362" title="" crop="0,0,1,1" id="ucdd70335" class="ne-image" style="font-size: 16px">



点击它：

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744377495608-9a1cae16-8a4b-4a2e-b8a7-3e26305337d0.png" width="1006" title="" crop="0,0,1,1" id="uaca33782" class="ne-image" style="font-size: 16px">

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744377544316-427d9327-819a-4065-81d3-9d63b4c236ec.png" width="968" title="" crop="0,0,1,1" id="u250650ae" class="ne-image" style="font-size: 16px">

就这样，代码就轻松的生成了。

# 静态工具类Db
<img src="https://cdn.nlark.com/yuque/0/2025/jpeg/21376908/1757681056420-39d1bc52-55fe-4f3b-8183-b4d7ba79b166.jpeg" width="4308" title="" crop="0,0,1,1" id="H6txc" class="ne-image" style="font-size: 16px">

静态工具类`Db`的功能和`IService接口`功能一样。

## Db中的方法
1. 增

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744379838552-3d1b6a62-78f0-4e95-9ced-153ac50927dd.png" width="398" title="" crop="0,0,1,1" id="ua4c3cca3" class="ne-image" style="font-size: 16px">

2. 删

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744379879613-a97aaa06-9577-4605-9531-d9f95324f0df.png" width="508" title="" crop="0,0,1,1" id="u22982222" class="ne-image" style="font-size: 16px">

3. 改

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744379969894-38d8d299-c939-4217-a70f-301e22528f36.png" width="333" title="" crop="0,0,1,1" id="u91a7bb7c" class="ne-image" style="font-size: 16px">

4. 查

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744380054930-af582d90-e0d4-488d-823d-05dbc74807bd.png" width="332" title="" crop="0,0,1,1" id="u91c6fd64" class="ne-image" style="font-size: 16px">

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744380130810-be45dac1-a3b0-4034-9dd9-0ae0f7c2b68a.png" width="413" title="" crop="0,0,1,1" id="ub550de5a" class="ne-image" style="font-size: 16px">

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744380155936-0916ecd0-e116-40fe-9a19-c3a00d70599d.png" width="370" title="" crop="0,0,1,1" id="u6499aaac" class="ne-image" style="font-size: 16px">

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744380170477-43b7351a-a986-447a-bc8f-c2e726a8aebd.png" width="219" title="" crop="0,0,1,1" id="uf75204bd" class="ne-image" style="font-size: 16px">

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744380221861-049ba810-073c-4b2c-895d-837b79fca7e3.png" width="420" title="" crop="0,0,1,1" id="ubad46c88" class="ne-image" style="font-size: 16px">



可以看到以上的方法基本上都是`IService`接口中的方法。

**<font style="color:#DF2A3F;">静态工具类 Db 中的方法一般比 IService 接口中的方法多一个</font>**`**<font style="color:#DF2A3F;">Class</font>**`**<font style="color:#DF2A3F;">参数。这是因为 IService 接口可以通过泛型来指定类型，而静态工具类 Db 是无法指定泛型的。</font>**

## 什么情况下需要Db
既然方法一样，为什么还要再提供一个静态工具类Db呢？

现在我们已经有一张表`t_car`，假设我们还有一张表来保存汽车的维修记录`t_wx`，这两张表的结构分别如下：

汽车表：

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744418354574-6d424729-fe5c-4064-86f9-623c3360c7d7.png" width="314" title="" crop="0,0,1,1" id="uc53f7cdb" class="ne-image" style="font-size: 16px">

维修记录表：

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744418614986-6917d5d1-fee4-4597-b3b5-53a5251a4c56.png" width="374" title="" crop="0,0,1,1" id="u7666c744" class="ne-image" style="font-size: 16px">

```sql
drop table if exists t_wx;
create table t_wx(
  id bigint primary key auto_increment,
  `time` char(10),
  cost decimal,
  description varchar(255),
  car_id bigint
);
insert into t_wx(`time`,cost,description,car_id) values('2025-10-11',300,'更换火花塞',1);
insert into t_wx(`time`,cost,description,car_id) values('2025-10-12',400,'小保养',1);
insert into t_wx(`time`,cost,description,car_id) values('2025-10-13',500,'大保养',1);
select * from t_wx;
```

可以看到汽车和维修记录的关系是：**一对多**。一个汽车有多条维修记录。

假设我们现在有这样一个需求：给定汽车的id，查询汽车的同时，再将汽车关联的维修记录也查出来。

我们有两张表，那应该是两个Service，分别是：CarService、WeiXiuService，要实现上面的需求，代码应该会是这样的结构：

```java
public class CarServiceImpl extends ServiceImpl<CarMapper, Car> implements CarService{
    @Autowired
    private WeiXiuService weiXiuService;

    // 根据汽车id查询汽车信息，并且携带汽车关联的维修记录
    public Car queryCarAndWeiXiuById(Long id){
        // 这里需要调用 CarService 的方法，也需要调用 WeiXiuService 的方法
    }
}
```

假设我们还有另一个需求：给定维修的id，查询维修记录的同时，再将关联的汽车信息查出来。代码应该是这样的结构：

```java
public class WeiXiuServiceImpl extends ServiceImpl<WeiXiuMapper, WeiXiu> implements WeiXiuService{
    @Autowired
    private CarService carService;

    // 根据维修id查询维修记录，并且携带关联的汽车信息。
    public WeiXiu queryWeiXiuAndCarById(Long id){
        // 这里需要调用 WeiXiuService 的方法，也需要调用 CarService 的方法
    }
}
```

如果代码这样写的话，很容易形成循环依赖。因为CarService中需要注入WeiXiuService，而WeiXiuService中需要注入CarService。怎么避免循环依赖呢？**<font style="color:#DF2A3F;">可以使用Db静态工具类。</font>**这样的话 CarService 中就不需要注入 WeiXiuService，而WeiXiuService中也不再注入CarService。

## Db的使用
接下来我们就使用Db来实现这样的需求：给定汽车的id，查询汽车的同时，再将汽车关联的维修记录也查出来。

### 编写`WeiXiu`这个实体类
```java
package com.jkweilai.carmgtsys.model.po;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName("t_wx")
public class WeiXiu {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    @TableField("`time`")
    private String time;
    private Double cost;
    private String description;
    private Long carId;
}
```

### 编写`WeiXiuMapper`
```java
package com.jkweilai.carmgtsys.mapper;

import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.jkweilai.carmgtsys.model.po.WeiXiu;

public interface WeiXiuMapper extends BaseMapper<WeiXiu> {
}
```

### 编写`WeiXiuVO`
```java
package com.jkweilai.carmgtsys.model.vo;

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
```

### `CarVO`中添加`List<WeiXiuVO>`
<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744427707462-cc4ff4a5-17ba-4ff9-8f03-7e0aa1106ed5.png" width="771" title="" crop="0,0,1,1" id="u11a873e0" class="ne-image" style="font-size: 16px">

### `CarController`中添加业务接口
```java
@Operation(summary = "查询车辆信息以及该车辆的维修记录",
        description = "根据车辆id查询汽车信息，并且将该车辆的维修记录全部查询出来",
        parameters = @Parameter(name = "id", description = "车辆id"))
@GetMapping("queryById/{id}")
public CarVO queryCarAndWeiXiuById(@PathVariable("id") Long id){
    return carService.queryCarAndWeiXiuById(id);
}
```

### `CarServiceImpl`编写业务方法
```java
@Override
public CarVO queryCarAndWeiXiuById(Long id) {
    // 1. 根据id查找车辆信息
    Car car = getById(id);
    // 2. 校验车辆信息是否存在
    if(car == null){
        throw new RuntimeException("车辆信息异常！");
    }
    // 3. 车辆信息存在的情况下继续查询维修记录
    List<WeiXiu> weiXiuList = Db.lambdaQuery(WeiXiu.class)
            .eq(WeiXiu::getCarId, car.getId())
            .list();
    // 4. Car转CarVO
    CarVO carVO = BeanUtil.copyProperties(car, CarVO.class);
    carVO.setWeiXiuList(BeanUtil.copyToList(weiXiuList, WeiXiuVO.class));
    return carVO;
}
```



测试：

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744427830630-96ed7d4d-6954-4c84-a339-1d8088034bea.png" width="571" title="" crop="0,0,1,1" id="uada41765" class="ne-image" style="font-size: 16px">



**<font style="color:#DF2A3F;">课后练习：给定多个车辆的id，查询这些车辆的信息以及每个车辆关联的维修记录信息。</font>**

# 逻辑删除
<img src="https://cdn.nlark.com/yuque/0/2025/jpeg/21376908/1757681056420-39d1bc52-55fe-4f3b-8183-b4d7ba79b166.jpeg" width="4308" title="" crop="0,0,1,1" id="vmh0X" class="ne-image" style="font-size: 16px">

逻辑删除指的不是真正的删除数据，执行逻辑删除时，表中的数据仍然存在，只是这条记录被标记为`已删除`。怎么标记的？可以添加一个标记字段，例如：deleted，当deleted=1表示已删除，deleted=0表示未删除。



什么时候使用逻辑删除？数据比较重要的情况下才会考虑使用逻辑删除。实际开发中不太推荐使用逻辑删除，因为逻辑删除会导致数据量越来越大，影响查询性能。如果数据比较重要，删除时可以考虑将数据迁移到其他表（做备份）。



当我们使用逻辑删除时，删除操作应该是一个update语句，例如：

```sql
update t_wx set deleted = 1 where id = ?;
```

当我们使用逻辑删除后，所有的查询语句也会受到影响，查询语句应该这样写：

```sql
select * from t_wx where deleted = 0;
```



那既然是这样 MyBatis-Plus 为我们自动生成的 SQL 语句还能用吗？当然可以用。因为mp也考虑到这一点了，你可以在`application.yml`配置文件中进行逻辑删除策略的配置，这样当删除和查询数据时，mp会自动按照逻辑删除的SQL语句生成。怎么配置？

```yaml
mybatis-plus:
  global-config:
    db-config:
      logic-delete-field: deleted # 标记逻辑删除的字段
      logic-delete-value: 1 # 已删除
      logic-not-delete-value: 0 # 未删除
```



给`t_wx`表添加一个`deleted`字段，编写代码测试一下：

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744429819330-692287d2-c849-4e7b-9f71-d8c52c23d9f9.png" width="568" title="" crop="0,0,1,1" id="udcb4dcda" class="ne-image" style="font-size: 16px">



WeiXiu这个实体类上也要添加一个属性：

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744430204264-68306fc7-939a-4d96-bfdf-133b43e0ac2c.png" width="512" title="" crop="0,0,1,1" id="ud8d03040" class="ne-image" style="font-size: 16px">



测试代码：

```java
package com.jkweilai.carmgtsys.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.jkweilai.carmgtsys.model.po.WeiXiu;

public interface WeiXiuService extends IService<WeiXiu> {
}
```

```java
package com.jkweilai.carmgtsys.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.jkweilai.carmgtsys.mapper.WeiXiuMapper;
import com.jkweilai.carmgtsys.model.po.WeiXiu;
import com.jkweilai.carmgtsys.service.WeiXiuService;

@Service
public class WeiXiuServiceImpl extends ServiceImpl<WeiXiuMapper, WeiXiu> implements WeiXiuService {
}
```

```java
@Autowired
private WeiXiuService weiXiuService;

@Test
public void logicDelete(){
    // 根据id删除数据
    weiXiuService.removeById(2L);
    // 根据id查询数据
    WeiXiu weiXiu = weiXiuService.getById(2L);
    System.out.println(weiXiu);
}
```



执行结果：

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744430279990-59421d5a-b2ad-4ce0-8753-0f03a9cf3918.png" width="1029" title="" crop="0,0,1,1" id="u2ab9e092" class="ne-image" style="font-size: 16px">



数据库数据没有真正删除：

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744430312146-03b05c15-36b4-46d3-ad99-699d95fc2c83.png" width="548" title="" crop="0,0,1,1" id="u0e25cce2" class="ne-image" style="font-size: 16px">



**注意：保存数据时，可以不用指定 deleted 字段的值，设计数据库表的时候，将 deleted 字段设置默认值为 0 即可。**

# 枚举处理器
<img src="https://cdn.nlark.com/yuque/0/2025/jpeg/21376908/1757681056420-39d1bc52-55fe-4f3b-8183-b4d7ba79b166.jpeg" width="4308" title="" crop="0,0,1,1" id="g4iuE" class="ne-image" style="font-size: 16px">

## 使用枚举增强可读性
假设汽车有一个状态属性，状态包括：在售(1)、已售(2)、维修中(3)、报废(4)，在数据库表中对应的字段为`status`，数据库中字段的类型为`int`，如下：

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744439091743-ff706e19-8360-4a35-849b-8d7000ee6454.png" width="493" title="" crop="0,0,1,1" id="ud3624dde" class="ne-image" style="font-size: 16px">

这时候，在po上也应该添加一个新的属性，如下：

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744439193534-8417f125-a0b0-4ff1-8b6c-a49bcf4cc037.png" width="495" title="" crop="0,0,1,1" id="u3a8ebd8d" class="ne-image" style="font-size: 16px">

`status`定义为Integer类型不是特别好的设计，因为这样出现在程序中的是数字`1,2,3,4`，**可读性较差**。

有的时候**对于po的属性**来说定义为枚举类型比数字类型来说可读性更好一些，例如定义这样一个枚举类型来表示汽车状态：

```java
package com.jkweilai.carmgtsys.enums;

import lombok.Getter;

@Getter
public enum CarStatus {
    
    FOR_SALE(1, "在售"),
    SOLD(2, "已售"),
    IN_MAIN(3, "维修中"),
    SCRAP(4, "报废");
    
    private int value;
    private String desc;

    CarStatus(int value, String desc) {
        this.value = value;
        this.desc = desc;
    }
}
```

po中的属性使用枚举类型：

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744439732134-c6e12dac-1274-4027-b48c-243b7a65708c.png" width="538" title="" crop="0,0,1,1" id="uc25e002e" class="ne-image" style="font-size: 16px">

这样的话，我们在编写java程序时，可读性会很好。但是新的问题出现了：数据库中存储的是`1,2,3,4`这样的数字，Java程序中是枚举类型的值，它们之间怎么进行映射转换呢？

不用担心，MyBatis-Plus已经帮我们解决了，它提供了这样一个类：`MybatisEnumTypeHandler`，这个类可以帮助我们完成`Java枚举类型的值`与`数据库中的int值`之间的转换，我们只需要在程序中做以下两步：

第一步：使用`@EnumValue`标注在Java枚举类型中哪个属性的值存储到数据库中。

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744440422424-e65aa05d-5b93-4f99-8c0f-5ea821995b38.png" width="408" title="" crop="0,0,1,1" id="ud99faf99" class="ne-image" style="font-size: 16px">

第二步：在`application.yml`文件中指定我们使用的是哪个转换器，我们使用MP提供的`MybatisEnumTypeHandler`即可。

```yaml
mybatis-plus:
  configuration:
    default-enum-type-handler: com.baomidou.mybatisplus.core.handlers.MybatisEnumTypeHandler
```



## 测试保存功能
`CarDTO`代码添加属性，如下：

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744441847827-44668c5a-d5cd-4738-96f8-67fea673ac2d.png" width="673" title="" crop="0,0,1,1" id="u7c2541e3" class="ne-image" style="font-size: 16px">

其他位置不需要修改，直接测试我们之前编写的保存接口：

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744442036230-36af4263-bbdb-48b1-8922-daf7c4fa9784.png" width="446" title="" crop="0,0,1,1" id="ud5cd8407" class="ne-image" style="font-size: 16px">

我们来看一下数据库表中插入的状态值是不是`2`：

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744442152960-767bb71f-0497-45db-8ba3-b705a298e57d.png" width="735" title="" crop="0,0,1,1" id="ubfe9e741" class="ne-image" style="font-size: 16px">

## 测试查询功能
需求：查询所有 **在售 **的车辆信息。



第一步：CarVO中添加属性

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744442862124-6a655706-6b27-491d-a2fc-9037a77fb8c2.png" width="585" title="" crop="0,0,1,1" id="u0d57bea8" class="ne-image" style="font-size: 16px">



第二步：在CarController中添加方法

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744442894426-b0d92395-50ca-49f3-953e-259bc5eaf78c.png" width="721" title="" crop="0,0,1,1" id="ub462265f" class="ne-image" style="font-size: 16px">



第三步：编写CarServiceImpl类中的query

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744442949283-577f344c-faac-4f5c-8da9-7207bef3dfe2.png" width="810" title="" crop="0,0,1,1" id="u71dc1077" class="ne-image" style="font-size: 16px">



测试结果：

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744443075911-9a80edfd-bc54-42ef-8000-2a3c055118b9.png" width="466" title="" crop="0,0,1,1" id="u840d2062" class="ne-image" style="font-size: 16px">



如果展示的时候，希望展示结果是`在售`，而不是`FOR_SALE`，可以使用`@JsonValue`注解标注枚举类型中`desc`属性：

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744443210615-9a3ec3cf-754e-4d93-be07-6e91c045ba23.png" width="500" title="" crop="0,0,1,1" id="u5098a384" class="ne-image" style="font-size: 16px">

这个注解不是mp的。属于`Jackson库`的注解。

再来看结果：

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1744443329927-170a622c-392f-4bf7-b012-7b670a481277.png" width="414" title="" crop="0,0,1,1" id="uc24bfb0b" class="ne-image" style="font-size: 16px">

**<font style="color:#DF2A3F;">重点注意事项</font>****：当添加了 **`**@JsonValue**`**注解之后，前端系统提交 JSON 给 DTO 对象时，JSON 字符串中的 **`**status**`**需要使用中文：**`**"status":"在售"**`**，这样才能解决新增时的报错问题。**

# json 处理器
<img src="https://cdn.nlark.com/yuque/0/2025/jpeg/21376908/1757681056420-39d1bc52-55fe-4f3b-8183-b4d7ba79b166.jpeg" width="4308" title="" crop="0,0,1,1" id="C5cwe" class="ne-image">

## 为什么需要 json 处理器
假设汽车表中有一个字段是 json 类型，存储了车主的信息。如下：

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1765424229312-d7cd17b2-8134-45b7-b2f7-70109e129a9b.png" width="945.6" title="" crop="0,0,1,1" id="u77a0f9a7" class="ne-image">

```json
{"id": "909890989898767676", "name": "张三"}
{"id": "909890989898767677", "name": "李四"}
{"id": "909890989898767678", "name": "王五"}
```

**在实体类中应该定义为  什么类型的属性来接收这个 json 数据呢？**

1. 定义为 String 直接接收 json 格式的字符串。
2. 定义一个 json 字符串对应的实体类。

显然第二种方式会比较好。因为在 java 程序中操作对象比操作 json 字符串更方便。但默认情况下，数据库表中 json 格式的字符串是不会自动转换成 java 对象的。这个时候就需要 MP 为我们提供的 json 处理器了。

## MP 提供的 json 处理器
<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1765424546642-67352e0f-e202-454e-aa56-20e3d46d29b0.png" width="479.2" title="" crop="0,0,1,1" id="u7949dbe4" class="ne-image">

MP 给我们提供了很多 json 处理器，springboot 默认集成的是 jackson，因此我们这里选择 `JacksonTypeHandler`会比较方便，不需要引入额外的 json 处理库。

## 使用 json 处理器
### 第一步：编写 json 对应的实体
```java
package com.jkweilai.mp.entity;

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
```

### 第二步：在字段上使用 `@TableField`注解
在 Car 实体类上添加字段，并使用 `@TableField`注解进行标注，**<font style="color:#DF2A3F;">指定 json 类型处理器</font>**，另外在实体类上**<font style="color:#DF2A3F;">开启自动结果映射</font>**：

```java
@Data
// 需要开启自动结果映射
@TableName(value = "t_car", autoResultMap = true)
public class Car {
    @TableId(value = "id", type = IdType.AUTO)
    private Long id;
    private String carNum;
    private String brand;
    private BigDecimal guidePrice;
    private String produceTime;
    private String carType;
    private CarStatus status;
    // 添加json类型处理器
    @TableField(typeHandler = JacksonTypeHandler.class)
    private Owner owner;
}
```



记得 VO 类也要修改一下：`CarVO`类上添加一个字段

```java
@Schema(description = "车主信息")
private Owner owner;
```

### 第三步：测试查询
直接通过 Swagger UI 测试之前的接口：通过 id 查询汽车信息的接口。

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1765425952884-d4409c4b-a80c-429b-bcdf-93fe25f00764.png" width="338.4" title="" crop="0,0,1,1" id="u90f78992" class="ne-image">

### 第四步：测试插入
编写单元测试，插入数据，看看能不能正常插入 json 字符串：

```java
@Resource
private CarService carService;

@Test
public void testSave(){
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
```

测试结果：

<img src="https://cdn.nlark.com/yuque/0/2025/png/21376908/1765426213984-aa09478f-f210-49fe-b757-c01f65d932b9.png" width="948" title="" crop="0,0,1,1" id="u53ee3ce8" class="ne-image">

# MP 分页插件的使用
<img src="https://cdn.nlark.com/yuque/0/2025/jpeg/21376908/1757681056420-39d1bc52-55fe-4f3b-8183-b4d7ba79b166.jpeg" width="4308" title="" crop="0,0,1,1" id="Ll0in" class="ne-image">

<font style="color:rgb(15, 17, 21);">MyBatis-Plus的分页插件能自动将Page对象参数转换为数据库分页SQL，无需手写LIMIT语句。</font>

## <font style="color:rgb(15, 17, 21);">第一步：引入额外的依赖</font>
使用分页插件需要引入以下的依赖。

```xml
<dependency>
  <groupId>com.baomidou</groupId>
  <artifactId>mybatis-plus-jsqlparser</artifactId>
  <version>3.5.11</version>
</dependency>
```

## 第二步：通过配置类添加插件
要使用 MP 提供的分页插件，第一步先配置插件，编写以下的配置类：

```java
package com.jkweilai.mp.config;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class MyBatisConfig {
    
    // MyBatis-Plus 的插件机制底层基于 MyBatis 的拦截器（Interceptor） 实现
    @Bean
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        // 创建 MybatisPlusInterceptor 拦截器链，用于集中管理 Mybatis-Plus 的各种功能拦截器
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();

        // 创建分页拦截器实例，指定数据库类型为 MySQL
        // DbType.MYSQL 会自动生成适合 MySQL 的分页 SQL（使用 LIMIT 语句）
        PaginationInnerInterceptor pageInterceptor = new PaginationInnerInterceptor(DbType.MYSQL);

        // 设置单次分页查询的最大记录数限制，防止恶意大数量查询（如 pageSize=10000）
        // 当 pageSize 参数值超过 500 时，会自动调整为 500
        // 这可以有效防止内存溢出和数据库性能问题
        pageInterceptor.setMaxLimit(500L);

        // 将分页拦截器添加到拦截器链中
        // 拦截器会按照添加顺序执行，分页拦截器通常放在最后
        interceptor.addInnerInterceptor(pageInterceptor);

        // 返回配置好的拦截器实例，Spring 会将其注册到 Mybatis 的插件链中
        return interceptor;
    }
}

```

## 编写分页查询的代码
```java
@Test
void testPage(){
    // 创建分页对象（指定pageNo和pageSize）
    int pageNo = 1;
    int pageSize = 2;
    Page<Car> page = Page.of(pageNo, pageSize);
    // 指定排序规则，以下表示：先按照guide_price升序，如果guide_price相同则按照id降序
    page.addOrder(OrderItem.asc("guide_price"));
    page.addOrder(OrderItem.desc("id"));
    // 指定查询条件，执行查询
    //QueryWrapper<Car> queryWrapper = new QueryWrapper<>();
    //carService.page(page, queryWrapper);
    carService.page(page);
    // 获取总记录条数
    long total = page.getTotal();
    System.out.println("总记录条数：" + total);
    // 获取总页数
    long pages = page.getPages();
    System.out.println("总页数：" + pages);
    // 获取数据
    List<Car> records = page.getRecords();
    records.forEach(System.out::println);
}
```



到此：MP 掌握这些，完全可以应付日常开发。