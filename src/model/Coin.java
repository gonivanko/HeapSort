package model;

public class Coin {
    String currency;
    int centsValue;
    double mass;

    public Coin(String currency, int centsValue, double mass) {
        this.currency = currency;
        this.centsValue = centsValue;
        this.mass = mass;
    }
    public String getCurrency() {
        return currency;
    }
    public int getCentsValue() {
        return centsValue;
    }
    public double getMass() {
        return mass;
    }
    public static Coin randomCoin() {
        String[] currencies = {"USD", "EUR", "GBP", "CHF", "UAH", "PLN", "CAD", "AUD", "NZD"};
        String randomCurrency = currencies[(int) (Math.random() * currencies.length)];
        int[] centsValues = {1, 2, 5, 10, 20, 50, 100, 500};
        int randomCentsValue = centsValues[(int) (Math.random() * centsValues.length)];
        double mass = Math.random() * 100;

        return new Coin(randomCurrency, randomCentsValue, mass);
    }
    @Override
    public String toString() {
        return String.format("Name: %3s, int: %2d, double: %2.2f", currency, centsValue, mass);
    }
}
