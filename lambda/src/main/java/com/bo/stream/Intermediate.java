package com.bo.stream;



import com.bo.pojo.Student;

import java.util.*;
import java.util.concurrent.ConcurrentMap;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author gpb
 * @date 2022/4/22 10:41
 */
public class Intermediate {
    private static List<Student> students;
    private static List<Student> studentList = new ArrayList<>();

    public static void main(String[] args) {
//        List<Integer> nums = Arrays.asList(1, 2, 3, 3, 5);
//        List<Integer> collect = nums.stream().map(n -> n * n).collect(Collectors.toList());
//        System.out.println(collect.toString());
        // 集合类型转换
        Set<Student> studentSet = students.stream().collect(Collectors.toSet());
        // toMap(key,value,如果key重复了处理的方法)
        // toMap() 注意key必须是唯一的,如果不是唯一的会报错而不是想普通map那样覆盖
        Map<Integer, String> studentMap = students.stream().collect(Collectors.toMap(Student::getId, Student::getName, (k1, k2) -> k1 + "," + k2));
        System.out.println("--------------set---------------");
        System.out.println("studentSet:" + studentSet);
        System.out.println("--------------map---------------");
        System.out.println("studentMap:" + studentMap);

        System.out.println("--------------groupingBy---------");
        // 分组
        Map<String, List<Student>> studentGroupingByMap = students.stream().collect(Collectors.groupingBy(Student::getClassNum));
        System.out.println("studentGroupingByMap:" + studentGroupingByMap);

        // 字符拼接
        System.out.println("--------------joining----------");
        String studentsJoiningName = students.stream().map(Student::getName).collect(Collectors.joining(",", "[", "]"));
        System.out.println("studentsJoiningName:" + studentsJoiningName);

        // age>18 && classNum 分组
        System.out.println("--------------studentAgeGroupClassNum----------");
        Map<String, List<Student>> studentAgeGroupClassNum = students.stream().filter(student -> student.getAge() > 18).collect(Collectors.groupingBy(Student::getClassNum));
        System.out.println("studentAgeGroupClassNum:" + studentAgeGroupClassNum);

        // 是否大于18岁分组 true 大于 false 小于
        // students.stream().flatMap(Collection::stream)
        //    .collect(Collectors.groupingByConcurrent(s -> s.getAge() > 16));
//        Stream<Student> stream = students.stream();
//        Stream<List<Student>> students = Stream.of(Intermediate.students);
        System.out.println("-----是否大于18岁分组 true 大于 false 小于-----");
        ConcurrentMap<Boolean, List<Student>> is18StudentMap = Stream.of(Intermediate.students).flatMap(Collection::stream).collect(Collectors.groupingByConcurrent(student -> student.getAge() > 18));
        System.out.println("is18StudentMap:" + is18StudentMap);

        // filter 过滤的作用
        // 获取 age>18
        System.out.println("-----过滤年龄大于18岁-----");
        List<Student> studentFilterAge18 = students.stream().filter(student -> student.getAge() > 18).collect(Collectors.toList());
        System.out.println("studentFilterAge18:" + studentFilterAge18);

        // map(映射的作用) 接收流中元素,并且将其映射成为新元素,例如从student对象中取name属性
        System.out.println("-----将student中的 name+age 映射出来-----");
        String studentMapNameAndAge = students.stream().map(student -> student.getName()+":"+student.getAge()).collect(Collectors.joining(",","[","]"));
        System.out.println("studentMapNameAndAge:" + studentMapNameAndAge);

        // flatMap 将所有流中的元拼成一起连接的流 流中流 flatMap(s->s.stream())
        System.out.println("----studentFlatList----");
        List<Student> studentFlatList = Stream.of(students, studentList).flatMap(e -> e.stream().filter(student -> student.getAge() > 18)).collect(Collectors.toList());
        System.out.println("studentFlatList:" + studentFlatList);

        // peek 获取流中元素,操作流中元素,与foreach不同的是 它不会截断流 可继续操作流
        System.out.println("------peek 操作流中的元素----");
        List<Student> studentPeekList = students.stream().peek(student -> {
            if (student.getAge() > 18) {
                student.setName(student.getName() + ",成年了");
            } else {
                student.setName(student.getName() + ",未成年了");

            }
        }).collect(Collectors.toList());
        System.out.println("studentPeekList : " + studentPeekList);

        // distinct() 通过流所生成元素的equals和hashCode去重
        System.out.println("------distinct去重作用-------");
        List<Integer> studentDistinctList = students.stream().map(Student::getId).distinct().collect(Collectors.toList());
        System.out.println("studentDistinctList: "+ studentDistinctList);

        // limit(long val) 截取流,取流中前 val个元素
        System.out.println("-----limit(long val) 截取多 val 个元素");
        List<Student> studentLimitList = students.stream().limit(5).collect(Collectors.toList());
        System.out.println("studentLimitList :" + studentLimitList);

        // sorted(Comparator ) 按照比较器规则排序 产生一个新的流
        // sorted() 按照自然排序 产生一个流
        System.out.println("---- sorted排序");
        List<Student> studentSorted = students.stream().sorted(new Comparator<Student>() {
            @Override
            public int compare(Student o1, Student o2) {
                return o1.getAge().compareTo(o2.getAge());
            }
        }).limit(2).collect(Collectors.toList());
        System.out.println("studentSorted : " + studentSorted);

        // 匹配是否符合  allMatch()都符合 anyMatch任一元素符合 noneMatch 都不符合
        System.out.println("-----匹配是否符合");
        boolean allMatch = students.stream().allMatch(student -> student.getAge() > 18);
        System.out.println("是不是都符合定义的规则(allMatch)：" + allMatch);
        // 只要有一个元素符合规则就满足
        boolean anyMatch = students.stream().anyMatch(student -> student.getAge() > 18);
        System.out.println("是不是任一元素符合定义的规则(anyMatch)：" + anyMatch);
        // 集合中的全部元素不满足规则才为true
        boolean noneMatch = students.stream().noneMatch(student -> student.getAge() > 18);
        System.out.println("是不是都不符合定义的规则(noneMatch)：" + noneMatch);

        // 寻找元素 findFirst() 返回第一个元素 findAny() 返回当前流中的任意个元素
        Optional<Student> studentFindFirst = students.stream().findFirst();
        if(studentFindFirst.isPresent()){
            System.out.println("-----studentFindFirst返回第一个元素");
            Student student = studentFindFirst.get();
            System.out.println(" studentFindAny : "+student);
        }
        // 在流中任意获取一个元素
        // Optional<Student> studentFindAny = students.stream().findAny()
        // parallel 转为并发流
        Optional<Student> studentFindAny = students.stream().parallel().findAny();
        if(studentFindAny.isPresent()){
            Student student = studentFindAny.get();
            System.out.println("studentFindAny : " + student );
        }

        // count 返回流中元素的总个数 == student.size()
        System.out.println("------count-----");
        long count = students.stream().count();
        System.out.println("count:" + count);

        // 返回最大值 max
        System.out.println("----- max() 返回最大值");
        //Optional<Student> maxStudent = students.stream().max(Comparator.comparing(Student::getAge));
        Student maxStudent = students.stream().max(Comparator.comparing(Student::getAge)).get();
        System.out.println("maxStudent:" + maxStudent);

        // 返回最小值 min
        System.out.println("----- min() 返回最大值");
        //Optional<Student> minStudent = students.stream().min(Comparator.comparing(Student::getAge));
        Student minStudent = students.stream().min(Comparator.comparing(Student::getAge)).get();
        System.out.println("minStudent: " + minStudent);

        // reduce 最终操作的意思 reduce(起始值(参考值),操作)
        // 流中的元素 与这个参考值  做比较对比参考
        System.out.println("------ reduce 最终操作的意思");
//        Optional<Student> reduce = students.stream().filter(student -> student.getAge() > 18).reduce((a, b) -> {
//            a.setName(a.getName() + "我是：" + a.getClassNum());
//            return a;
//        });
        Student reduceStudent = students.stream().filter(student -> student.getAge() > 18).reduce((a, b) -> {
            // a.setName(a.getName() + "我是：" + a.getClassNum());
            a.setName(a.getName() + " , " + b.getName());

            return a;
        }).get();
        System.out.println("reduceStudent" + reduceStudent);

        // 字符串拼接
        String concat = Stream.of("A", "B", "C", "D").reduce("起始值(参考值)", String::concat);
        String concatVar = Stream.of("A", "B", "C", "D").reduce(String::concat).get();
        System.out.println("reduce字符串拼接concat：" + concat);
        System.out.println("reduce字符串拼接concatVar：" + concatVar);

        //  获取最小值  参考值 Double.MAX_VALUE
        double minDouble = Stream.of(-9.0, -0.09, -12.9, 100.0, 23.0).reduce(Double.MAX_VALUE, Double::min);
        double minDoubleVar = Stream.of(-9.0, -0.09, -12.9, 100.0, 23.0).reduce( Double::min).get();
        Double minDoubleReduce = Stream.of(-9.0, -0.09, -12.9, 100.0, 23.0).reduce((a, b) -> {
            return a < b ? a : b;
        }).get();
        System.out.println("最小值minDouble：" + minDouble);
        System.out.println("最小值minDoubleVar：" + minDoubleVar);
        System.out.println("最小值minDoubleReduce：" + minDoubleReduce);
        // 求和 sum 要有起始值 0
        Integer sumReduceVar = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9).reduce(0, Integer::sum);
        System.out.println("求和sumReduceVar: " + sumReduceVar);
        // 求和 无起始值
        Integer sumReduce = Stream.of(1, 2, 3, 4, 5, 6, 7, 8, 9).reduce(Integer::sum).get();
        System.out.println("求和sumReduce: " + sumReduce);

        // 字符过滤 大写 拼接
        String str = Stream.of("a", "B", "c", "D", "e", "F").
                filter(s -> s.compareTo("Z") > 0).
                map(String::toUpperCase).
                reduce(String::concat).get();
        System.out.println("字符过滤 大写 拼接str: " + str);


    }

    static {
        students = new ArrayList<Student>() {{
            add(new Student() {{
                setId(1);
                setAge(18);
                setName("小明1");
                setBirthday(new Date());
                setClassNum("1班");
            }});
            add(new Student() {{
                setId(2);
                setAge(23);
                setName("小明2");
                setBirthday(new Date());
                setClassNum("2班");
            }});
            add(new Student() {{
                setId(3);
                setAge(13);
                setName("小明3");
                setBirthday(new Date());
                setClassNum("3班");
            }});
            add(new Student() {{
                setId(4);
                setAge(14);
                setName("小明4");
                setBirthday(new Date());
                setClassNum("4班");
            }});
            add(new Student() {{
                setId(5);
                setAge(23);
                setName("小明5");
                setBirthday(new Date());
                setClassNum("5班");
            }});
            add(new Student() {{
                setId(5);
                setAge(25);
                setName("小明6");
                setBirthday(new Date());
                setClassNum("1班");
            }});
            add(new Student() {{
                setId(7);
                setAge(12);
                setName("小明7");
                setBirthday(new Date());
                setClassNum("2班");
            }});
            add(new Student() {{
                setId(8);
                setAge(21);
                setName("小明8");
                setBirthday(new Date());
                setClassNum("3班");
            }});
            add(new Student() {{
                setId(9);
                setAge(17);
                setName("小明9");
                setBirthday(new Date());
                setClassNum("4班");
            }});
            add(new Student() {{
                setId(10);
                setAge(16);
                setName("小明10");
                setBirthday(new Date());
                setClassNum("5班");
            }});
        }};
        studentList.addAll(students);
    }
}
