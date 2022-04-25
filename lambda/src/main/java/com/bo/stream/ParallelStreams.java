package com.bo.stream;

import java.util.Arrays;

/**
 * 并发流
 * stream有串行和并行两种,串行Stream上的操作是在一个线程中依次完成,
 * 并行Stream则是在多个线程上完成执行的
 * @author gpb
 * @date 2022/4/24 16:55
 */
public class ParallelStreams {

    public static void main(String[] args) {
        System.out.println("===========================");
        System.out.println("Using sequential stream");
        System.out.println("===========================");
        int[] array={1,2,3,4,5,6,7,8,9,10};
        Arrays.stream(array).forEach(arr->{
            System.out.println(arr + " 工作线程为：" + Thread.currentThread().getName());
        });

        System.out.println("===========================");
        System.out.println("Using parallel stream");
        System.out.println("===========================");
        Arrays.stream(array).parallel().forEach(arr->{
            System.out.println(arr + " 工作线程为：" + Thread.currentThread().getName());
        });

    }

}
