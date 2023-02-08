package cn.pxl;

import java.sql.Array;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class MyTest {
    public static void main(String[] args) {
        ArrayList<Integer> integer1 = new ArrayList<>();
        ArrayList<Integer> integer2 = new ArrayList<>();
        integer1.add(1);
        integer1.add(2);
        integer1.add(3);
        integer2.add(2);
        integer2.add(3);
        integer2.add(4);
        List<Integer> collect = integer1.stream().filter(integer2::contains).collect(Collectors.toList());
        System.out.println(collect);
    }
}
