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

    @Test
    public void testNormalOnlyOneAndFour() {
        int[] arr = {1, 1, 1, 4, 4, 4, 1, 1};
        Assert.assertTrue(CheckArray.arrayOneAndFour(arr));
    }

    @Test
    public void testAnyDataNormal() {
        int[] arr = {1, 2, 3, 4, 5, 4, 0, 1};
        Assert.assertTrue(CheckArray.arrayOneAndFour(arr));
    }

    @Test
    public void testWrongData() {
        int[] arr = { 2, 3, 6, 5, 2, 0, 9};
        Assert.assertFalse(CheckArray.arrayOneAndFour(arr));
    }

    @Test
    public void testEmptyArray() {
        int[] arr = {};
        Assert.assertFalse(CheckArray.arrayOneAndFour(arr));
    }


}
