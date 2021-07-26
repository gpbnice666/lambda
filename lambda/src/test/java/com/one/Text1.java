package com.one;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collector;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class Text1 {

    @Test
    public void text01(){
        ArrayList<User> list = new ArrayList<>();
        list.add(new User(1,"ss",null,null));
        list.add(new User(13,"aa",null,null));
        list.add(new User(1,"hhh",null,null));
        list.add(new User(24,"bb",null,null));
        list.add(new User(66,"snns",null,null));
        // 根据id进行过滤 id>20的
        List<User> collect = list.stream().filter(user -> user.getId() > 20).collect(Collectors.toList());
        //输出
        collect.stream().forEach(System.out::println);
    }
    @Test
    public void text02(){
        ArrayList<Employee> list = new ArrayList<>();
        list.add(new Employee(1,"dd",18));
        list.add(new Employee(5,"dd",18));
        list.add(new Employee(9,"dd",18));
        list.add(new Employee(1,"dd",19));
        list.add(new Employee(5,"dd",16));
        list.add(new Employee(9,"dd",58));
        list.add(new Employee(1,"dd",18));
        list.add(new Employee(1,"dd",16));
        list.add(new Employee(1,"dd",22));
        list.add(new Employee(1,"dd",18));
        Stream<Employee> aa = list.stream().distinct();
        list.stream().forEach(System.out::println);
        System.out.println("***************************");
                //distinct().map(e -> e.getId());
        // 进行映射 获取这个集合中的所有的id
        List<Integer> list1 = aa.map(e->e.getId()).collect(Collectors.toList());
        list1.stream().forEach(System.out::println);
    }
    @Test
    public void text03(){
        ArrayList<Employee> list = new ArrayList<>();
        list.add(new Employee(1,"hh",18));
        list.add(new Employee(5,"dd",18));
        list.add(new Employee(9,"ss",18));
        list.add(new Employee(1,"uu",19));
        list.add(new Employee(5,"ee",16));
        List<String> collect = list.stream().map(employee -> employee.getName()).collect(Collectors.toList());
        collect.stream().forEach(System.out::println);
    }
    @Test
    public void text04(){
        ArrayList<User> list = new ArrayList<>();
        list.add(new User(1,"ss",32,null));
        list.add(new User(2,"aa",41,null));
        list.add(new User(3,"hhh",12,null));
        list.add(new User(4,"bb",18,null));
        list.add(new User(5,"snns",20,null));
        List<User> collect = list.stream().filter(user -> user.getAge() < 20).collect(Collectors.toList());
        //输出
        collect.stream().map(user -> user.getId()).forEach(System.out::println);
    }
    @Test
    public void text05(){
        ArrayList<User> list = new ArrayList<>();
        list.add(new User(1,"ss",32,null));
        list.add(new User(2,"aa",41,null));
        list.add(new User(3,"hhh",12,null));
        list.add(new User(4,"bb",18,null));
        list.add(new User(5,"snns",20,null));
        // 进行排序 获取前三条
        List<User> collect = list.stream().sorted((u1, u2) -> u2.getAge() - u1.getAge()).limit(3).collect(Collectors.toList());
        collect.stream().forEach(System.out::println);
    }
    @Test
    public void text06(){
        ArrayList<Integer> list = new ArrayList<>();
        list.add(1);
        list.add(5);
        list.add(3);
        list.add(3);
        list.add(2);
        list.add(9);
        list.add(1);
        //进行去重排序
        List<Integer> collect = list.stream().distinct().sorted().collect(Collectors.toList());
        collect.stream().forEach(System.out::println);
       
    }
}
