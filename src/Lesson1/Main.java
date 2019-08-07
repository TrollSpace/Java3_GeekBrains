package Lesson1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        List list = new ArrayList(3);
        list.add(new Integer(100));
        list.add(new Integer(200));
        list.add(new Integer(300));
        list.add(new Integer(400));
        list.add(new Integer(400));
        System.out.println("Size:" + list.size());

        //First task
        Integer[] data = {1, 2, 3, 4};
        change(data, 2, 3);
        for (Integer i : data) {
            System.out.print(i + " ");
        }
        System.out.println();

        System.out.println(toArrayList(data));

        //Third task. check

        Apple apple1 = new Apple();
        Apple apple2 = new Apple();
        Apple apple3 = new Apple();

        Orange org1 = new Orange();
        Orange org2 = new Orange();
        Orange org3 = new Orange();

        Box<Apple> appleBox = new Box<>();
        Box<Orange> orangeBox = new Box<>();

        appleBox.putFruitToBox(apple1);
        appleBox.putFruitToBox(apple2);
        appleBox.putFruitToBox(apple3);

        orangeBox.putFruitToBox(org1);
        orangeBox.putFruitToBox(org2);
        orangeBox.putFruitToBox(org3);

        System.out.println("Orange box weight: " + orangeBox.getWeight());
        System.out.println("Apple box weight: " + appleBox.getWeight());

        Box<Apple> newAppleBox = new Box<>();
        newAppleBox.putFruitToBox(apple1);
        appleBox.replaceFruits(newAppleBox);
        System.out.println(appleBox.getWeight() + " " + newAppleBox.getWeight());
    }

    //Second task
    private static <T> List<T> toArrayList(T[] inArr) {
        return Arrays.asList(inArr);
    }


    private static <T> void change(T[] arr, int first, int second) {
        if (arr.length != 0) {
            T arrObj = arr[first];
            arr[first] = arr[second];
            arr[second] = arrObj;
        }
    }
}
