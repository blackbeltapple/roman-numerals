import kata.bbc.roman.NumeralSymbol;
import kata.bbc.roman.Numerals;
import org.junit.Test;
import org.junit.experimental.runners.Enclosed;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;
import org.junit.runners.Parameterized.Parameters;

import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

@RunWith(Enclosed.class)
public class NumeralsTest {

    @RunWith(Parameterized.class)
    public static class ParameterizedTests {

        @Parameters(name = "{index}: testGenerate({0} returns \"{1}\"")
        public static Collection<Object[]> data() {
            return Arrays.asList(new Object[][] {
                    //The first item in the array is the input, and second is the expected outcome.
                    { 1, "I" },
                    { 2, "II" },
                    { 3, "III" },
                    { 4, "IV" },
                    { 5, "V" },
                    { 6, "VI" },
                    { 7, "VII" },
                    { 8, "VIII" },
                    { 9, "IX" },
                    { 10, "X" },
                    { 11, "XI" },
                    { 12, "XII" },
                    { 13, "XIII" },
                    { 14, "XIV" },
                    { 15, "XV" },
                    { 16, "XVI" },
                    { 17, "XVII" },
                    { 18, "XVIII" },
                    { 19, "XIX" },
                    { 20, "XX" },
                    { 24, "XXIV" },
                    { 29, "XXIX" },
                    { 30, "XXX" },
                    { 40, "XL" },
                    { 44, "XLIV" },
                    { 49, "XLIX" },
                    { 50, "L" },
                    { 60, "LX" },
                    { 70, "LXX" },
                    { 80, "LXXX" },
                    { 90, "XC" },
                    { 99, "XCIX" },
                    { 100, "C" },
                    { 400, "CD" },
                    { 500, "D" },
                    { 900, "CM" },
                    { 1000, "M" },
                    { 1776, "MDCCLXXVI" },
                    { 1954, "MCMLIV" },
                    { 1990, "MCMXC" },
                    { 2000, "MM" },
                    { 2014, "MMXIV" },
                    { 3000, "MMM" },
                    { 3999, "MMMCMXCIX" }
            });
        }

        private int input;
        private String expected;

        public ParameterizedTests(int input, String expected) {
            this.input = input;
            this.expected = expected;
        }

        @Test
        public void testGenerate() {
            Numerals numerals = new Numerals();
            assertThat(numerals.generate(input), is(equalTo(expected)));
        }

    }

    public static class IndividualTests {

        @Test(expected = IllegalArgumentException.class)
        public void testArgumentLessThan1ThrowsException() {
            Numerals numerals = new Numerals();
            numerals.generate(0);
        }

        @Test(expected = IllegalArgumentException.class)
        public void testArgumentGreaterThan3999ThrowsException() {
            Numerals numerals = new Numerals();
            numerals.generate(4000);
        }

        @Test
        public void testAddSymbols() {
            assertThat(Numerals.concatMultipleSymbols("X", 4), is(equalTo("XXXX")));
        }

        @Test
        public void testAddSymbolsForZeroQuantity() {
            assertThat(Numerals.concatMultipleSymbols("X", 0), is(equalTo("")));
        }

        @Test
        public void testConvertToSubtractiveNotation() {
            assertThat(Numerals.convertToSubtractiveNotation("VIIII", NumeralSymbol.UNITS, 1), is(equalTo("IX")));
            assertThat(Numerals.convertToSubtractiveNotation("IIII", NumeralSymbol.UNITS, 0), is(equalTo("IV")));
        }

        @Test
        public void testGenerateFromDigitFour() {
            assertThat(Numerals.generateFromDigit(4, NumeralSymbol.UNITS), is(equalTo("IV")));
            assertThat(Numerals.generateFromDigit(40, NumeralSymbol.TENS), is(equalTo("XL")));
            assertThat(Numerals.generateFromDigit(400, NumeralSymbol.HUNDREDS), is(equalTo("CD")));

        }
    }
}

