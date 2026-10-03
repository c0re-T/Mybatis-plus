package com.ittxf.mp;

import java.io.Serializable;
import java.lang.invoke.SerializedLambda;
import java.lang.reflect.Method;

public class ReflectTest {
    public static void main(String[] args) throws Exception {
        // 匿名内部类 调用 apply 方法。
        // MyFunc 接口继承了 Serializable，所以底层生成的类也实现了Serializable接口
        // java中任何一个类实现了Serializable接口，那么底层都会有一个writeReplace方法
        /*MyFunc myFunc = new MyFunc() { // 底层做了两件事：1.定义了一个没有名字的类。2.创建了对象。
            @Override
            public Object apply(User user) {
                return user.getId();
            }
        };*/

        // 修改为lambda表达式
        // MyFunc myFunc = user -> user.getId();

        // 使用方法引用来调用 apply 方法
        MyFunc myFunc = User::getId; // 只是一个语法糖，底层仍然是方法引用

        // 获取SerializedLambda
        // 当Lambda表达式实现的函数式接口继承了Serializable，在Lambda表达式对应的类中会生成一个writeReplace方法。
        Method m = myFunc.getClass().getDeclaredMethod("writeReplace");
        m.setAccessible(true);
        // 调用writeReplace方法会返回SerializedLambda对象，SerializedLambda 对象封装了 Lambda 表达式的“元数据”和“捕获的参数值”
        SerializedLambda lambda = (SerializedLambda) m.invoke(myFunc);
        // 提取字段名
        String methodName = lambda.getImplMethodName(); // "getId"
        System.out.println("方法名: " + methodName);
        String fieldName = methodName.substring(3, 4).toLowerCase() + methodName.substring(4); // "id"
        System.out.println("字段名: " + fieldName);

        /*User user = new User();
        user.setId(100L);
        Object apply = myFunc.apply(user);
        System.out.println(apply);*/

    }
}

// 普通类
class User {
    private Long id;

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}

// 函数式接口
interface MyFunc extends Serializable {
    Object apply(User user);
}
