package data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;
import java.util.Random;

public class DataGenerator {
    private DataGenerator() {
    }

    public static String generateCity() {
        String[] validCities = {
                "Москва", "Санкт-Петербург", "Новосибирск", "Екатеринбург",
                "Казань", "Нижний Новгород", "Челябинск", "Самара"
        };
        Random random = new Random();
        return validCities[random.nextInt(validCities.length)];
    }

    public static String generateDate(int daysFromNow) {
        LocalDate date = LocalDate.now().plusDays(daysFromNow);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return date.format(formatter);
    }

    public static String generateInvalidDate(int daysAgo) {
        LocalDate date = LocalDate.now().minusDays(daysAgo);
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd.MM.yyyy");
        return date.format(formatter);
    }

    public static String generateName() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.name().firstName() + " " + faker.name().lastName();
    }

    public static String generatePhone() {
        Faker faker = new Faker(new Locale("ru"));
        return faker.phoneNumber().phoneNumber();
    }

    public static class Registration {
        private static Faker faker;

        private Registration() {
        }

        public static UserInfo generateUser(String locale, int daysFromNow) {
            Faker faker = new Faker(new Locale(locale));

            String city = generateCity();
            String date = generateDate(daysFromNow);
            String name = generateName();
            String phone = generatePhone();

            return new UserInfo(city, date, name, phone);
        }
    }

    @Value
    public static class UserInfo {
        String city;
        String date;
        String name;
        String phone;
    }

}
