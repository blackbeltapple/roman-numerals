package kata.bbc.roman;

public enum NumeralSymbol {

    UNITS("I", "V", "X", 1), TENS("X", "L", "C", 10), HUNDREDS("C", "D", "M", 100), THOUSANDS("M", "", "", 1000);

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
