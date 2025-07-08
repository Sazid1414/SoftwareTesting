package math;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.*;

public class ArithmeticOperationsTest {

    private ArithmeticOperations operations;
    private static final double DELTA = 1e-10;

    @After
    public void tearDown() throws Exception {
    }

    @Before
    public void setUp() {
        operations = new ArithmeticOperations();
    }

    // Tests for divide method
    @Test
    public void testDivideNormalCase() {
        assertEquals(2.0, operations.divide(10.0, 5.0), DELTA);
        assertEquals(0.5, operations.divide(1.0, 2.0), DELTA);
    }

    @Test
    public void testDivideNegativeNumbers() {
        assertEquals(-2.0, operations.divide(-10.0, 5.0), DELTA);
        assertEquals(-2.0, operations.divide(10.0, -5.0), DELTA);
        assertEquals(2.0, operations.divide(-10.0, -5.0), DELTA);
    }

    @Test(expected = ArithmeticException.class)
    public void testDivideByZero() {
        operations.divide(10.0, 0.0);
    }

    @Test
    public void testDivideByZeroExceptionMessage() {
        try {
            operations.divide(10.0, 0.0);
            fail("Expected ArithmeticException for division by zero");
        } catch (ArithmeticException e) {
            assertEquals("Cannot divide with zero", e.getMessage());
        }
    }

    @Test
    public void testDivideZeroByNonZero() {
        assertEquals(0.0, operations.divide(0.0, 5.0), DELTA);
    }

    @Test
    public void testDivideLargeNumbers() {
        assertEquals(2.0, operations.divide(Double.MAX_VALUE, Double.MAX_VALUE / 2.0), DELTA);
    }

    @Test
    public void testDivideSmallNumbers() {
        assertEquals(1.0, operations.divide(Double.MIN_VALUE, Double.MIN_VALUE), DELTA);
    }

    // Tests for multiply method
    @Test
    public void testMultiplyNormalCase() {
        assertEquals(50, operations.multiply(10, 5));
        assertEquals(0, operations.multiply(10, 0));
        assertEquals(0, operations.multiply(0, 10));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMultiplyNegativeFirstArgument() {
        operations.multiply(-1, 5);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMultiplyNegativeSecondArgument() {
        operations.multiply(5, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMultiplyBothNegativeArguments() {
        operations.multiply(-5, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMultiplyOverflow() {
        operations.multiply(Integer.MAX_VALUE, 2);
    }

    @Test
    public void testMultiplyLargeNumbersNoOverflow() {
        assertEquals(Integer.MAX_VALUE - 1, operations.multiply(Integer.MAX_VALUE - 1, 1));
        assertEquals(1000000, operations.multiply(1000, 1000));
    }

    @Test
    public void testMultiplyEdgeCases() {
        assertEquals(Integer.MAX_VALUE, operations.multiply(Integer.MAX_VALUE, 1));
        assertEquals(0, operations.multiply(0, 0));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMultiplyZeroWithNegative() {
        // This test targets the surviving mutation in line 41: y < 0 condition
        // when x = 0, the mutant would skip the negative check and return 0
        // but our code should throw IllegalArgumentException before checking for y == 0
        operations.multiply(0, -1);
    }

    @Test
    public void testMultiplyMaxBoundary() {
        int maxDiv2 = Integer.MAX_VALUE / 2;
        assertEquals(maxDiv2 * 2, operations.multiply(maxDiv2, 2));
    }

    // Additional tests to improve mutation coverage

    @Test(expected = IllegalArgumentException.class)
    public void testMultiplyZeroNegative() {
        // Test y < 0 boundary when y is negative and x is 0
        // This targets the surviving mutation where condition order matters
        operations.multiply(0, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMultiplyPositiveXNegativeY() {
        // This specifically targets the y < 0 part of the condition
        // If the mutant removes y < 0 check, this should fail
        operations.multiply(1, -1);
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMultiplyYNegativeSpecific() {
        // Additional test to specifically target y < 0 mutation
        operations.multiply(5, -3);
    }

    @Test
    public void testMultiplyEdgeBoundaryValues() {
        // Test exact boundary for overflow condition: x <= Integer.MAX_VALUE / y
        int maxValue = Integer.MAX_VALUE;
        int divResult = maxValue / 2;
        
        // This should work (boundary case)
        assertEquals(divResult * 2, operations.multiply(divResult, 2));
        
        // Test when y = 1 (special case for division)
        assertEquals(maxValue, operations.multiply(maxValue, 1));
    }

    @Test(expected = IllegalArgumentException.class)
    public void testMultiplyOverflowBoundary() {
        // Test the exact overflow boundary: x > Integer.MAX_VALUE / y
        // Use values that will definitely overflow
        operations.multiply(Integer.MAX_VALUE / 2 + 1, 3);
    }
}