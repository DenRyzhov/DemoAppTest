import java.math.BigDecimal;
public class NumberToWordsConverter {
    private static final String[] units = {
            "", "один", "два", "три", "четыре", "пять", "шесть", "семь", "восемь", "девять",
            "десять", "одиннадцать", "двенадцать", "тринадцать", "четырнадцать", "пятнадцать",
            "шестнадцать", "семнадцать", "восемнадцать", "девятнадцать"
    };

    private static final String[] tens = {
            "", "", "двадцать", "тридцать", "сорок", "пятьдесят", "шестьдесят", "семьдесят",
            "восемьдесят", "девяносто"
    };

    private static final String[] hundreds = {
            "", "сто", "двести", "триста", "четыреста", "пятьсот", "шестьсот",
            "семьсот", "восемьсот", "девятьсот"
    };

    public static String convert(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0 || amount.compareTo(new BigDecimal("99999.99")) > 0) {
            throw new IllegalArgumentException("Сумма должна быть в диапазоне от 0 до 99 999.99");
        }

        StringBuilder result = new StringBuilder();

        // Разделение целой и дробной частей
        BigDecimal integerPart = amount.setScale(0, BigDecimal.ROUND_DOWN);
        BigDecimal fractionalPart = amount.subtract(integerPart).movePointRight(2).setScale(0, BigDecimal.ROUND_DOWN);

        // Получение написания целой части
        result.append(convertInteger(integerPart));

        // Добавление дробной части
        if (fractionalPart.compareTo(BigDecimal.ZERO) > 0) {
            result.append(" и ").append(fractionalPart.toBigInteger()).append("/100");
        }

        return result.toString().trim();
    }

    private static String convertInteger(BigDecimal number) {
        StringBuilder result = new StringBuilder();
        int num = number.intValue();

        if (num > 99999) {
            throw new IllegalArgumentException("Сумма превышает 99 999");
        }

        if (num >= 1000) {
            result.append(units[num / 1000]).append(" тысяча ");
            num %= 1000;
        }

        if (num >= 100) {
            result.append(hundreds[num / 100]).append(" ");
            num %= 100;
        }

        if (num >= 20) {
            result.append(tens[num / 10]).append(" ");
            num %= 10;
        }

        if (num > 0) {
            result.append(units[num]).append(" ");
        }

        return result.toString().trim();
    }

    public static void main(String[] args) {
        System.out.println("Стоимость: " + convert(new BigDecimal("12345.67")));
    }
}

