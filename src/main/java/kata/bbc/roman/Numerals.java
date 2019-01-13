package kata.bbc.roman;

public class Numerals implements RomanNumeralGenerator {

    private static final int SUBTRACTIVE_DIGITS = 4;

    public static void main(String[] args) {
        Numerals numerals = new Numerals();
        int input = Integer.parseInt(args[0]);
        System.out.println(String.format("The roman numeral for %d is '%s'", input, numerals.generate(input)));
    }

    public String generate(int number) {

        if (number < 1 || number > 3999) {
            throw new IllegalArgumentException("Valid arguments are 1 to 3999.");
        }

        return generateFromDigit(number, NumeralSymbol.THOUSANDS) +
                generateFromDigit(number, NumeralSymbol.HUNDREDS) +
                generateFromDigit(number, NumeralSymbol.TENS) +
                generateFromDigit(number, NumeralSymbol.UNITS);
    }

    public static String generateFromDigit(int number, NumeralSymbol symbolSet) {

        int digit = (number / symbolSet.getDivisor()) % 10;
        int quotient = digit / 5;
        int remainder = digit % 5;

        String romanNumeral = concatMultipleSymbols(symbolSet.getMidSymbol(), quotient) +
                concatMultipleSymbols(symbolSet.getBaseSymbol(), remainder);

        return convertToSubtractiveNotation(romanNumeral, symbolSet, quotient);
    }

    public static String convertToSubtractiveNotation(String simpleRomanNumeral, NumeralSymbol symbolSet, int quotient) {
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

    public static String concatMultipleSymbols(String symbol, int multiple) {
        String symbols = "";
        for (int i = 0; i < multiple; i++) {
            symbols = symbols.concat(symbol);
        }
        return symbols;
    }
}

