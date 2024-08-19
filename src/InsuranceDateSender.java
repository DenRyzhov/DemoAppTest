import java.sql.Date;
import java.util.Calendar;
public class InsuranceDateSender {
    public static Date getNextSendDate(Date requestDate) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(requestDate);

        // Получаем текущий месяц и год
        int month = calendar.get(Calendar.MONTH);
        int year = calendar.get(Calendar.YEAR);

        // Определяем следующие потенциальные даты отправки
        Date nextSendDate = getEarliestSendDate(calendar);

        // Проверяем, является ли следующая дата отправки рабочей
        nextSendDate = getVacCheck(nextSendDate);

        return nextSendDate;
    }

    private static Date getEarliestSendDate(Calendar calendar) {
        int day = calendar.get(Calendar.DAY_OF_MONTH);

        // Если текущая дата уже после 20 числа, переходим в следующий месяц
        if (day > 20) {
            calendar.add(Calendar.MONTH, 1);
            calendar.set(Calendar.DAY_OF_MONTH, 1);
        } else if (day > 10) {
            calendar.set(Calendar.DAY_OF_MONTH, 20);
        } else if (day > 1) {
            calendar.set(Calendar.DAY_OF_MONTH, 10);
        } else {
            calendar.set(Calendar.DAY_OF_MONTH, 1);
        }

        // Устанавливаем время отправки
        calendar.set(Calendar.HOUR_OF_DAY, 18);
        calendar.set(Calendar.MINUTE, 0);
        calendar.set(Calendar.SECOND, 0);

        return new Date(calendar.getTimeInMillis());
    }

    // Метод для проверки рабочего дня
    public static Date getVacCheck(Date modDate) {
        // Ваша реализация проверки рабочего дня
        // Например:
        // if (!isWorkingDay(modDate)) {
        //     return findPreviousWorkingDay(modDate);
        // }
        // Возвращение проверенной даты
        return modDate;  // временная заглушка
    }

    // Добавьте здесь вашу реализацию isWorkingDay и findPreviousWorkingDay

    public static void main(String[] args) {
        Date requestDate = new Date(System.currentTimeMillis());
        Date nextSendDate = getNextSendDate(requestDate);
        System.out.println("Следующая дата отправки: " + nextSendDate);
    }
}
