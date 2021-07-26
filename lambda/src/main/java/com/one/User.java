package com.one;


import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor  //生成有参构造
public class User {
    private Integer id;
    private String name;
    private Integer age;
    private String sex;
}
