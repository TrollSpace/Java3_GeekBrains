package Lesson6;

import junit.framework.TestCase;
import org.junit.Assert;
import org.junit.Test;

public class CheckArrayTest extends TestCase {

    @Test
    public void testNormalData() {
        int[] arr = {1, 2, 3, 34, 34, 5, 231, 4, 321, 12, 1};
        int[] expect = {321, 12, 1};
        Assert.assertArrayEquals(expect, CheckArray.arrayAfterFour(arr));
    }

    @Test
    public void testExpectNull() {
        int[] arr = {1, 2, 3, 34, 34, 5, 231, 4};
        int[] expect = {};
        Assert.assertArrayEquals(expect, CheckArray.arrayAfterFour(arr));
    }

    @Test(expected = RuntimeException.class)
    public void testExcept() {
        int[] arr = {1, 2, 3, 5, 231, 321, 12, 1};
        Assert.assertArrayEquals(null, CheckArray.arrayAfterFour(arr));
    }
}
