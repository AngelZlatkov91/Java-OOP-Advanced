import org.junit.Assert;
import org.junit.Test;
import p04_BubbleSortTest.Bubble;

public class TestBubble {
    @Test
    public void testBubbleSort(){
        int[] numbers = {35,5 ,56, 89 , 45, 9, 100 ,99, 12,10,18};
        Bubble.sort(numbers);
        int [] expected = {5,9,10,12,18,35,45,56,89,99,100};
        Assert.assertArrayEquals(numbers,expected);
    }





}
