package Lesson1;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Main {
    public static void main(String[] args) {

        //First task
        Integer[] data = {1, 2, 3, 4};
        change(data, 2, 3);
        for (Integer i : data) {
            System.out.print(i + " ");
        }
        System.out.println();

        System.out.println(toArrayList(data));



    }

    //Second task
    private static <T> List<T> toArrayList(T[] inArr){
        return Arrays.asList(inArr);
    }


    private static <T> void change(T[] arr, int first, int second) {
        T arrObj = arr[first];
        arr[first] = arr[second];
        arr[second] = arrObj;
    }
}
