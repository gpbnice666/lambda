package com.one;

import org.junit.Test;

import java.util.Comparator;
import java.util.TreeSet;

public class UserText {

    @Test
    public void text01(){
        //lambda表达式规则  如果有多个
       Comparator<User> con = (u1,u2) ->{
            int num=u1.getId()- u2.getId()==0?0:u1.getId()- u2.getId();
            num=num==0?u1.getName().compareTo(u2.getName()):num;
            return num;
        };
        /*
        *  比较器  0 相同 就 覆盖   负数 小于  正数 大于
        * */
       /* Comparator<User> con = (u1,u2) -> u1.getId()-u2.getId();  一个语句 就可以写下
        TreeSet<User> users = new TreeSet<User>((u1,u2) -> u1.getId()-u2.getId());*/
        TreeSet<User> users = new TreeSet<User>(con);
        users.add(new User(1,"zss",null,null));
        users.add(new User(6,"dd",null,null));
        users.add(new User(8,"ww",null,null));
        users.add(new User(18,"aa",null,null));
        users.add(new User(6,"zs",null,null));
        users.stream().forEach(u-> System.out.println(u));

    }
    @Test
    public void text02(){
        //lambda表达式规则  如果有多个
       Comparator<User> con = (u1,u2) ->{
            int num=u1.getId()- u2.getId()==0?0:u1.getId()- u2.getId();
            num=num==0?u1.getName().compareTo(u2.getName()):num;
            return num;
        };
        /*
        *  比较器  0 相同 就 覆盖   负数 小于  正数 大于
        * */
       /* Comparator<User> con = (u1,u2) -> u1.getId()-u2.getId();  一个语句 就可以写下
        TreeSet<User> users = new TreeSet<User>((u1,u2) -> u1.getId()-u2.getId());*/
        TreeSet<User> users = new TreeSet<User>(con);
        users.add(new User(1,"zss",null,null));
        users.add(new User(6,"dd",null,null));
        users.add(new User(8,"ww",null,null));
        users.add(new User(18,"aa",null,null));
        users.add(new User(6,"zs",null,null));
        users.stream().forEach(u-> System.out.println(u));

    }
}
