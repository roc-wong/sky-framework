package org.sky.framework.test.stream;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.function.BinaryOperator;
import java.util.stream.Collector;
import java.util.stream.Collectors;

/**
 * @author roc
 * @since 2020/4/23 17:06
 */
public class Stream {

    public static void main(String[] args) {

        List<Student> students = new ArrayList<>();
        students.add(new Student(1, "roc-1"));
        students.add(new Student(2, "roc-2"));
        students.add(new Student(1, "roc-3"));

        //Exception in thread "main" java.lang.IllegalStateException: Duplicate key Student{id=1, name='roc-1'}
//        Map<Integer, Student> collect = students.stream()
//                                                .collect(Collectors.toMap(Student::getId, student -> student));
//        System.out.println(collect);

        Map<Integer, Student> collect1 = students.stream()
                                                .collect(Collectors.toMap(Student::getId, student -> student, new BinaryOperator<Student>() {
                                                    @Override
                                                    public Student apply(Student student, Student student2) {
                                                        return student2;
                                                    }
                                                }));

        System.out.println(collect1);
    }

    static class Student{
        private int id;

        private String name;

        public Student(int id, String name) {
            this.id = id;
            this.name = name;
        }

        public int getId() {
            return id;
        }

        public void setId(int id) {
            this.id = id;
        }

        @Override
        public String toString() {
            final StringBuilder sb = new StringBuilder("Student{");
            sb.append("id=")
              .append(id);
            sb.append(", name='")
              .append(name)
              .append('\'');
            sb.append('}');
            return sb.toString();
        }
    }

}
