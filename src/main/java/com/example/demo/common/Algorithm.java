package com.example.demo.common;

import java.util.LinkedList;
import java.util.List;
import java.util.Scanner;

/**
 * @ClassName: Algorithm
 * @Description: 数组排列
 * @author 纪淮永
 * @date 2020年3月9日 下午1:23:00
 * 
 */
public class Algorithm {

    /** 
    * @Title: main 
    * @Description: 方法执行入口
    * @param args
    * @throws 
    * @author 纪淮永
    */ 
    @SuppressWarnings("resource")
    public static void main(String[] args) {
        Scanner scn = new Scanner(System.in);
        System.out.print("请输入正是n:");
        int n = scn.nextInt();
        Scanner sck = new Scanner(System.in);
        System.out.print("请输入需要获取到的排列数K:");
        int k = sck.nextInt();
        if (n < 1 || n >= 1024) {
            System.out.print("n的取值范围是： 1 <= n < 1024 ");
        }
        if (k < 1 || k > 64) {
            System.out.print("k的取值范围是： 1<=k<=64 ");
        }
        List<Integer> list = new LinkedList<>();
        List<String> newList = new LinkedList<>();
        for (int i = 1; i <= n; ++i) {
            list.add(i);
        }
        getAllCompose("", list, newList);
        int length = newList.size();
        k = k-1;
        if (length < k) {
            k = k % length;
        }
        StringBuilder compose = new StringBuilder();
        for (String num : newList.get(k).split("")) {
            compose.append(num).append(" ");
        }
        System.out.println(compose.toString());
    }

    /** 
    * @Title: getAllCompose 
    * @Description: 获取全部数据排列
    * @param compose
    * @param list
    * @param newList
    * @throws 
    * @author 纪淮永
    */ 
    public static void getAllCompose(String compose, List<Integer> list, List<String> newList) {
        if (list.size() == 0) {
            newList.add(compose);
        } else {
            for (int i = 0; i < list.size(); ++i) {
                List<Integer> temp = new LinkedList<>(list);
                Integer remove = temp.remove(i);
                getAllCompose(compose + remove, temp, newList);
                temp = null;
            }
        }
    }
}
