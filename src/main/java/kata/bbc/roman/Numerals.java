public class Numerals implements RomanNumeralGenerator {

    private static final int SUBTRACTIVE_DIGITS = 4;

    public String generate(int number) {

        if (number < 1 || number > 3999) {
            throw new IllegalArgumentException("Valid arguments are 1 to 3999.");
        }

        SymbolSet thousands = new SymbolSet("M", "", "", 1000);
        SymbolSet hundreds = new SymbolSet("C", "D", "M", 100);
        SymbolSet tens = new SymbolSet("X", "L", "C", 10);
        SymbolSet decimals = new SymbolSet("I", "V", "X", 1);

        return convertDigit(number, thousands)
                + convertDigit(number, hundreds)
                + convertDigit(number, tens)
                + convertDigit(number, decimals);
    }

    String convertDigit(int number, SymbolSet symbolSet) {

        int digit = (number / symbolSet.divisor) % 10;
        int quotient = digit / 5;
        int remainder = digit % 5;

        String romanNumeral = addMultipleSymbols(symbolSet.midSymbol, quotient) + addMultipleSymbols(symbolSet.baseSymbol, remainder);

        romanNumeral = convertToSubtractiveNotation(romanNumeral, symbolSet, quotient);

        return romanNumeral;
    }

    String convertToSubtractiveNotation(String simpleRomanNumeral, SymbolSet symbolSet, int quotient) {
        if (!simpleRomanNumeral.contains(addMultipleSymbols(symbolSet.baseSymbol, SUBTRACTIVE_DIGITS))) {
            return simpleRomanNumeral;
        }

        String toReplace;
        String replaceBy;

        if (quotient > 0) {
            toReplace = symbolSet.midSymbol + addMultipleSymbols(symbolSet.baseSymbol, SUBTRACTIVE_DIGITS);
            replaceBy = symbolSet.baseSymbol + symbolSet.upperSymbol;

        } else {
            toReplace = addMultipleSymbols(symbolSet.baseSymbol, SUBTRACTIVE_DIGITS);
            replaceBy = symbolSet.baseSymbol + symbolSet.midSymbol;
        }

        return simpleRomanNumeral.replace(toReplace, replaceBy);

    }

    String addMultipleSymbols(String symbol, int multiple) {
        String symbols = "";
        for (int i = 0; i < multiple; i++) {
            symbols = symbols.concat(symbol);
        }
        return symbols;
    }
}

class SymbolSet {

    final String baseSymbol;
    final String midSymbol;
    final String upperSymbol;
    final int divisor;

    SymbolSet(String baseSymbol, String midSymbol, String upperSymbol, int divisor) {
        this.baseSymbol = baseSymbol;
        this.midSymbol = midSymbol;
        this.upperSymbol = upperSymbol;
        this.divisor = divisor;
    }
}