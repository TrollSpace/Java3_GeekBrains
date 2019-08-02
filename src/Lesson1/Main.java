package Lesson1;

public class Main {
    public static void main(String[] args) {
        Integer[] data = {1, 2, 3, 4};
        change(data, 2, 3);
        for (Integer i : data) {
            System.out.println(i + " ");
        }
    }


    private static <T> void change(T[] arr, int first, int second) {
        T arrObj = arr[first];
        arr[first] = arr[second];
        arr[second] = arrObj;
    }
}
