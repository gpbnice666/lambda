package com.bo.pojo;


import java.util.Date;

/**
 * @author gpb
 * @date 2022/4/22 11:16
 */
public class Student {
    private Integer id;
    private Integer age;
    private String name;
    private Date birthday;
    private String classNum;

    public Student(){}
    public Student(Integer id, Integer age, String name, Date birthday, String classNum) {
        this.id = id;
        this.age = age;
        this.name = name;
        this.birthday = birthday;
        this.classNum = classNum;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getClassNum() {
        return classNum;
    }

    public void setClassNum(String classNum) {
        this.classNum = classNum;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", age=" + age +
                ", name='" + name + '\'' +
                ", birthday=" + birthday +
                ", classNum='" + classNum + '\'' +
                '}';
    }
}
