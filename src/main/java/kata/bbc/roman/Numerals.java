package kata.bbc.roman;

public class Numerals implements RomanNumeralGenerator {

    private static final int SUBTRACTIVE_DIGITS = 4;

    public String generate(int number) {

        if (number < 1 || number > 3999) {
            throw new IllegalArgumentException("Valid arguments are 1 to 3999.");
        }

        return generateFromDigit(number, NumeralSymbol.THOUSANDS) +
                generateFromDigit(number, NumeralSymbol.HUNDREDS) +
                generateFromDigit(number, NumeralSymbol.TENS) +
                generateFromDigit(number, NumeralSymbol.UNITS);
    }

    public String generateFromDigit(int number, NumeralSymbol symbolSet) {

        int digit = (number / symbolSet.getDivisor()) % 10;
        int quotient = digit / 5;
        int remainder = digit % 5;

        String romanNumeral = concatMultipleSymbols(symbolSet.getMidSymbol(), quotient) +
                concatMultipleSymbols(symbolSet.getBaseSymbol(), remainder);

        return convertToSubtractiveNotation(romanNumeral, symbolSet, quotient);

    }

    public String convertToSubtractiveNotation(String simpleRomanNumeral, NumeralSymbol symbolSet, int quotient) {
        if (!simpleRomanNumeral.contains(concatMultipleSymbols(symbolSet.getBaseSymbol(), SUBTRACTIVE_DIGITS))) {
            return simpleRomanNumeral;
        }

        String toReplace;
        String replaceBy;

        if (quotient > 0) {
            toReplace = symbolSet.getMidSymbol() + concatMultipleSymbols(symbolSet.getBaseSymbol(), SUBTRACTIVE_DIGITS);
            replaceBy = symbolSet.getBaseSymbol() + symbolSet.getUpperSymbol();

        } else {
            toReplace = concatMultipleSymbols(symbolSet.getBaseSymbol(), SUBTRACTIVE_DIGITS);
            replaceBy = symbolSet.getBaseSymbol() + symbolSet.getMidSymbol();
        }

        return simpleRomanNumeral.replace(toReplace, replaceBy);

    }

    public String concatMultipleSymbols(String symbol, int multiple) {
        String symbols = "";
        for (int i = 0; i < multiple; i++) {
            symbols = symbols.concat(symbol);
        }
        return symbols;
    }
}

enum NumeralSymbol {

    UNITS("I", "V", "X", 1),
    TENS("X", "L", "C", 10),
    HUNDREDS("C", "D", "M", 100),
    THOUSANDS("M", "", "", 1000);

    private final String baseSymbol;
    private final String midSymbol;
    private final String upperSymbol;
    private final int divisor;

    public String getBaseSymbol() {
        return baseSymbol;
    }

    public String getMidSymbol() {
        return midSymbol;
    }

    public String getUpperSymbol() {
        return upperSymbol;
    }

    public int getDivisor() {
        return this.divisor;
    }

    NumeralSymbol(String baseSymbol, String midSymbol, String upperSymbol, int divisor) {
        this.baseSymbol = baseSymbol;
        this.midSymbol = midSymbol;
        this.upperSymbol = upperSymbol;
        this.divisor = divisor;
    }
}