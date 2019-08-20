package Lesson6;

import static java.util.Arrays.binarySearch;

public class CheckArray {

    public static int[] arrayAfterFour(int[] arr) {
        if (binarySearch(arr, 0, arr.length, 4) == -1) {
            throw new RuntimeException("Need number 4");
        }

        int[] res = null;
        for (int i = 0; i < arr.length; i++) {
            if (arr[i] != 4) continue;
            res = new int[arr.length - i - 1];
            System.arraycopy(arr, i + 1, res, 0, res.length);
            break;
        }
        return res;
    }

    public static boolean arrayOneAndFour(int[] arr){
        return ((binarySearch(arr, 0, arr.length, 1) != -1)&&(binarySearch(arr, 0, arr.length, 4) != -1));
    }

}

