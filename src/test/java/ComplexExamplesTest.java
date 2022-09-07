import org.testng.Assert;
import org.testng.annotations.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ComplexExamplesTest {

    @Test
    public void testGetPairOfNumberByTargetWithOriginalArray() {
        int[] originalArray = new int[]{3, 4, 2, 7};
        int target = 10;
        List<Integer> rightPair = new ArrayList<>(Arrays.asList(3, 7));
        Assert.assertEquals(rightPair, ComplexExamples.getPairOfNumberByTarget(originalArray, target));
    }

    @Test
    public void testGetPairOfNumberByTargetWithSeveralPairInArray() {
        int[] originalArray = new int[]{1, 2, 6, 10, 3, 4, 2, 7};
        int target = 10;
        List<Integer> rightPair = new ArrayList<>(Arrays.asList(6, 4));
        Assert.assertEquals(rightPair, ComplexExamples.getPairOfNumberByTarget(originalArray, target));
    }

    @Test
    public void testGetPairOfNumberByTargetWhenNoPairInArray() {
        int[] originalArray = new int[]{1, 2, 3, 10, 4, 4, 2, 17};
        int target = 17;
        List<Integer> rightPair = new ArrayList<>();
        Assert.assertEquals(rightPair, ComplexExamples.getPairOfNumberByTarget(originalArray, target));
    }

    @Test
    public void testFuzzySearchWithLongStrings() {
        String targetWord = "ahbgdcahbgdc";
        String sourceWord = "zxlllyyoozxzllxzayyhzxzyyooxzbgxzxdczllooxzxlllyyoozxzllxzayyhzxzyyooxzbgxzxdczlloox";
        Assert.assertEquals(true, ComplexExamples.fuzzySearch(targetWord, sourceWord));
    }

    @Test
    public void testFuzzySearchWithUpperCaseAndDifferenceSymbols() {
        String targetWord = "$AHbgDc--ahbgdc&";
        String sourceWord = "Zxlll$yYoozxzllxzAayyHzxzyyooxzbgxzxDc--zllooxzxlllyyoozxzllxzayyhzxzyyooxzbgxzxdczlloox&";
        Assert.assertEquals(true, ComplexExamples.fuzzySearch(targetWord, sourceWord));
    }
}
