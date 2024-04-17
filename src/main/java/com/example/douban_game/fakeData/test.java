package com.example.douban_game.fakeData;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Scanner;

public class test {
    public static void change(ArrayList list,int i,int j){    //交换值方法
        int a = (int) list.get(i);
        int b = (int) list.get(j);
        list.set(i,b);
        list.set(j,a);
    }
    public static void main(String[] args) {
        ArrayList<Integer> list = new ArrayList<Integer>();
//        现往list里面存值
        list.add(91);list.add(62);list.add(3);list.add(84);list.add(35);list.add(10);list.add(97);list.add(28);list.add(49);
//        手动输入，先输入length，再输入element
//        Scanner scanner = new Scanner(System.in);
//        int longs =scanner.nextInt("输入长度");
//        for (int i = 0; i <longs ; i++) {
//            list.add(scanner.nextInt());
//        }
//        中间键
        int len = list.size()/2;      //中间键
        int i = 0;                //i的初始值
        int j = list.size()-1;       //j初始值
        while(i!=j){           //开始算法
            if(list.get(i)>=list.get(len) && list.get(j)>=list.get(len)){    //判断，如果左边元素满足大于等于中间值，右边不满足，则右边j往前移动
                j-=1;
                if (i==j){  //如果i和j重合，则对将合位置的数据和中间值比较
                    if(i<len && list.get(i) >list.get(len)){
                        change(list,i,len);

                    }
                    if(j>len && list.get(i) <list.get(len)){
                        change(list,i,len);

                    }
                }
                continue;
            }
            if(list.get(i)<list.get(len) && list.get(j)<list.get(len)){  //判断，如果右边边元素满足小于于中间值，左边不满足，则右边i往后移动
                i+=1;
                if (i==j){
                    if(i<len && list.get(i) >list.get(len)){  //如果i和j重合，则对将合位置的数据和中间值比较
                        change(list,i,len);

                    }
                    if(j>len && list.get(i) <list.get(len)){
                        change(list,i,len);

                    }
                }
                continue;
            }
            if(list.get(i)>=list.get(len) && list.get(j)<list.get(len)){  //i，j条件都满足
                change(list,i,j); //交换位置
                for (int k = 0; k < len-1; k++) {    //左边进行排序
                    if(list.get(k)>=list.get(k+1)){
                        change(list,k,k+1);
                    }
                }
                for (int k = len+1; k < list.size()-1; k++) {      //右边进行排序
                    if(list.get(k)>=list.get(k+1)){
                        change(list,k,k+1);
                    }
                }
                i+=1;
                j-=1;
                if (i==j){
                    if(i<len && list.get(i) >list.get(len)){    //如果i和j重合，则对将合位置的数据和中间值比较
                        change(list,i,len);

                    }
                    if(j>len && list.get(i) <list.get(len)){
                        change(list,i,len);
                    }
                }
                continue;

            }

            System.out.println("\n");
            i+=1;
            j-=1;
            if (i==j){      //如果i和j重合，则对将合位置的数据和中间值比较
                if(i<len && list.get(i) >list.get(len)){
                    change(list,i,len);
                }
                if(j>len && list.get(i) <list.get(len)){
                    change(list,i,len);
                }
            }
        }
//分为两个列表存储，方便展示
        ArrayList<Integer> result1 = new ArrayList<Integer>();
        ArrayList<Integer> result2 = new ArrayList<Integer>();
        if(i>len){  //如果最后的相同的位置在中间值的右边 则左边会包含中间值
            for (int k = 0; k <= i; k++) {
                result1.add(list.get(k));
            }
            for (int k = i+1; k < list.size(); k++) {
                result2.add(list.get(k));
            }
        }
        if(i<len){  //如果最后的相同的位置在中间值的左边 则右边会包含中间值
            for (int k = 0; k < i; k++) {
                result1.add(list.get(k));
            }
            for (int k = i; k < list.size(); k++) {
                result2.add(list.get(k));
            }
        }
        else{   //默认情况，论文中没写
            for (int k = 0; k < i; k++) {
                result1.add(list.get(k));
            }
            for (int k = i; k < list.size(); k++) {
                result2.add(list.get(k));
            }
        }



        for (int k = 0; k < result1.size(); k++) {
            System.out.print(result1.get(k)+" ");
        }
        System.out.println();
        for (int k = 0; k < result2.size(); k++) {
            System.out.print(result2.get(k)+" ");
        }
    }
}
