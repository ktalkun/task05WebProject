package main;


import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.text.SimpleDateFormat;
import java.util.*;

public class Gener {

    private static final String POSTGRESQL = "postgreSql";
    private static final String MYSQL = "mySql";

    private static final String SQL_LANGUAGE = POSTGRESQL;

    private static final String POSTGRESQL_USE_COMMAND = "\\c";
    private static final String MYSQL_USE_COMMAND = "USE";

    private static final int NUMBER = 100;
    private static final int SUMMARY_SERVICE_NUMBER = 200;
    private static final int AVAILABLE_SERVICES_NUMBER = 16;
    private static final String LOGINS_PATH = "data/logins.txt";
    private static final String NAMES_MALE_PATH = "data/names_male.txt";
    private static final String PATRONOMICS_MALE_PATH = "data/patronymic_male.txt";
    private static final String SURNAMES_MALE_PATH = "data/surnames_male.txt";
    private static final String NAMES_FEMALE_PATH = "data/names_female.txt";
    private static final String PATRONOMICS_FEMALE_PATH = "data/patronymiс_female.txt";
    private static final String SURNAMES_FEMALE_PATH = "data/surnames_female.txt";
    private static final String UPLOUD_AVATARS_DIR = "resources/upload/avatars/";


    public static void main(String[] args) throws FileNotFoundException {

        String USE_COMMAND = "";

        switch (SQL_LANGUAGE) {
            case POSTGRESQL:
                USE_COMMAND = POSTGRESQL_USE_COMMAND;
                break;
            case MYSQL:
                USE_COMMAND = MYSQL_USE_COMMAND;
                break;
            default:
                USE_COMMAND = "";
                break;
        }

        Set<String> loginsSet = new HashSet<>();
        Scanner scannerLogins = new Scanner(new FileInputStream(LOGINS_PATH));
        while (scannerLogins.hasNext()) {
            loginsSet.add(scannerLogins.nextLine());
        }
        List<String> logins = new ArrayList<>(loginsSet);
        System.out.println("Number logins: " + loginsSet.size());


        List<String> namesMale = new ArrayList<>();
        Scanner scannerNamesMale = new Scanner(new FileInputStream(NAMES_MALE_PATH));
        while (scannerNamesMale.hasNext()) {
            namesMale.add(scannerNamesMale.nextLine());
        }
        System.out.println("Number male names: " + namesMale.size());

        List<String> surnamesMale = new ArrayList<>();
        Scanner scannerSurnamesMale = new Scanner(new FileInputStream(SURNAMES_MALE_PATH));
        while (scannerSurnamesMale.hasNext()) {
            surnamesMale.add(scannerSurnamesMale.nextLine());
        }
        System.out.println("Number male surnames: " + surnamesMale.size());

        List<String> patronymicsMale = new ArrayList<>();
        Scanner scannerPatronymicsMale = new Scanner(new FileInputStream(PATRONOMICS_MALE_PATH));
        while (scannerPatronymicsMale.hasNext()) {
            patronymicsMale.add(scannerPatronymicsMale.nextLine());
        }
        System.out.println("Number male patronymics: " + patronymicsMale.size());


        List<String> namesFemale = new ArrayList<>();
        Scanner scannerNamesFemale = new Scanner(new FileInputStream(NAMES_FEMALE_PATH));
        while (scannerNamesFemale.hasNext()) {
            namesFemale.add(scannerNamesFemale.nextLine());
        }
        System.out.println("Number female names: " + namesFemale.size());

        List<String> surnamesFemale = new ArrayList<>();
        Scanner scannerSurnamesFemale = new Scanner(new FileInputStream(SURNAMES_FEMALE_PATH));
        while (scannerSurnamesFemale.hasNext()) {
            surnamesFemale.add(scannerSurnamesFemale.nextLine());
        }
        System.out.println("Number female surnames: " + surnamesFemale.size());

        List<String> patronymicsFemale = new ArrayList<>();
        Scanner scannerPatronymicsFemale = new Scanner(new FileInputStream(PATRONOMICS_FEMALE_PATH));
        while (scannerPatronymicsFemale.hasNext()) {
            patronymicsFemale.add(scannerPatronymicsFemale.nextLine());
        }
        System.out.println("Number female patronymics: " + patronymicsFemale.size());

        PrintStream printStream = new PrintStream("fill_tables.sql");
        printStream.println(USE_COMMAND + " barbershop_db;");
        printStream.println("-- Fill table users");
        printStream.println("INSERT INTO users (login, password, name, surname, patronymic, email, phone, image_path, role)");
        printStream.println("VALUES");
        StringBuilder stringBuffer = new StringBuilder();
        Set<Long> phoneNumbers = new HashSet<>();

        List<Integer> customerIdentifiers = new ArrayList<>();
        List<Integer> employeeIdentifiers = new ArrayList<>();
//        first id == 2 because admin user (who initialized firstly) has id == 1
        int currentId = 2;

        BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

        for (int i = 0; i < NUMBER; i++) {
            Random random = new Random();
            int randIndex = random.nextInt(logins.size());
            String login = logins.get(randIndex);
            logins.remove(randIndex);
            String name;
            String surname;
            String patronymic;
            long phone;
            String avatarPath = UPLOUD_AVATARS_DIR;

//            Phone generating
            do {
                phone = (long) (random.nextDouble() * Math.pow(10, 9));
            } while (phoneNumbers.contains(phone));
            phoneNumbers.add(phone);

//            Name, surname and patronymic generating
            if (random.nextInt(2) == 0) {
                name = namesMale.get(random.nextInt(namesMale.size()));
                surname = surnamesMale.get(random.nextInt(surnamesMale.size()));
                patronymic = patronymicsMale.get(random.nextInt(patronymicsMale.size()));
                avatarPath += random.nextInt(60) + 1 + ".jpg";
            } else {
                name = namesFemale.get(random.nextInt(namesFemale.size()));
                surname = surnamesFemale.get(random.nextInt(surnamesFemale.size()));
                patronymic = patronymicsFemale.get(random.nextInt(patronymicsFemale.size()));
                avatarPath += random.nextInt(60) + 61 + ".jpg";
            }

//            Role generating
            int role = random.nextInt(4);
            if (role == 2) {
                employeeIdentifiers.add(currentId);
            } else if (role == 3) {
                customerIdentifiers.add(currentId);
            }

            stringBuffer
                    .append("(")

                    .append("\'")
                    .append(login)
                    .append("\'")
                    .append(", ")

                    .append("\'")
                    .append(passwordEncoder.encode(login + "1/"))
                    .append("\'")
                    .append(", ")

                    .append("\'")
                    .append(name)
                    .append("\'")
                    .append(", ")

                    .append("\'")
                    .append(surname)
                    .append("\'")
                    .append(", ")

                    .append("\'")
                    .append(patronymic)
                    .append("\'")
                    .append(", ")

                    .append("\'")
                    .append(login)
                    .append("@gmail.com")
                    .append("\'")
                    .append(", ")

                    .append(phone)
                    .append(", ")

                    .append("\'")
                    .append(avatarPath)
                    .append("\'")
                    .append(", ")

                    .append(role)

                    .append(")");
            printStream.print(stringBuffer.toString());
            if (i < NUMBER - 1) {
                printStream.println(",");
            } else {
                printStream.println(";");
            }
            stringBuffer.setLength(0);
            currentId++;
        }
        currentId = 0;

        printStream.println("\n-- Fill table employees");
        printStream.println("INSERT INTO employees (employee_id, experience, im, fb, vk, work_week)");
        printStream.println("VALUES");
        for (int i = 0; i < employeeIdentifiers.size(); i++) {
            Random randomWeekDay = new Random();
            StringBuilder week;
            week = new StringBuilder();
            for (int j = 0; j < 7; j++) {
                week.append(randomWeekDay.nextInt(2));
            }
            stringBuffer
                    .append("(")

                    .append(employeeIdentifiers.get(i))
                    .append(", ")

                    .append("\'")
                    .append(new java.sql.Date(new Date().getDate()))
                    .append("\'")
                    .append(", ")

                    .append("\'")
                    .append("https://www.instagram.com/")
                    .append(employeeIdentifiers.get(i))
                    .append("\'")
                    .append(", ")

                    .append("\'")
                    .append("https://www.facebook.com/")
                    .append(employeeIdentifiers.get(i))
                    .append("\'")
                    .append(", ")

                    .append("\'")
                    .append("https://vk.com/")
                    .append(employeeIdentifiers.get(i))
                    .append("\'")
                    .append(", ")

                    .append("\'")
                    .append(week.toString())
                    .append("\'")

                    .append(")");
            printStream.print(stringBuffer.toString());
            if (i < employeeIdentifiers.size() - 1) {
                printStream.println(",");
            } else {
                printStream.println(";");
            }
            stringBuffer.setLength(0);
        }

        printStream.println("\n-- Fill table offers");
        printStream.println(
                "INSERT INTO offers (name, description, image_path, price, period, is_main, is_show)\n" +
                        "VALUES\n" +
                        "(\'Традиционная<br/>стрижка\', \'Эта классическая традиционная услуга идеально подходит,<br/>если вы хотите, чтобы ваши волосы были подстрижены правильно.<br/>Наши парикмахеры будут рады помочь вам с этим.<br/>Мытьё волос. Стрижка машинкой. Укладка волос.\', \'resources/upload/admin/service/traditional-haircut.png\', 25, 25, true, true),\n" +
                        "(\'Бритьё<br/>бороды\', \'Бритьё бороды - одна из наших самых популярных услуг.<br/>Это необходимость для всех мужчин,<br/>которые хотят иметь бороду, которая выглядит потрясающе.<br/>Распаривание кожного покрова лица. Бритьё шаветкой.\', \'resources/upload/admin/service/beard-shave.png\', 40, 60, true, true),\n" +
                        "(\'Подравнивание<br/>усов\', \'Усы - это классический выбор мужчин,<br/>и они никогда не выйдут из моды надолго.<br/>С нами вы можете сохранить свои усы ухоженными.<br/>Подравнивание усов. Укладка.\', \'resources/upload/admin/service/mustache-trim.png\', 30, 45, true, true),\n" +
                        "(\'Подравнивание<br/>бороды\', \'Все хорошо знаю, что борода придаёт брутальности лицу.<br/>Чтобы ваша борода смотрелась аккуратно,<br/>за ней нужен уход, именно поэтому мы предлагаем данную услугу.<br/>Придание формы бороде шаветкой. Укладка бороды.\', \'resources/upload/admin/service/beard-trim.png\', 25, 30, true, true),\n" +
                        "(\'Рисунок на волосах\', \'Стрижка волос согласно трафарету.\', \'resources/upload/admin/service/hair-draw.png\', 55, 90, false, true),\n" +
                        "(\'Афрокосички\', \'Мытьё волос. Заплетание косичек.\', \'resources/upload/admin/service/afro-braids.png\', 150, 90, false, true),\n" +
                        "(\'Покраска бороды\', \'Мытьё, окрашивание волос бороды.\', \'resources/upload/admin/service/beard-paint.png\', 45, 75, false, true),\n" +
                        "(\'Пробор/Окантовка\', \'Придание формы причёске.\', \'resources/upload/admin/service/part-edge.png\', 10, 20, false, true),\n" +
                        "(\'Укладка\', \'Укладка волос.\', \'resources/upload/admin/service/styling.png\', 10, 15, false, true),\n" +
                        "(\'Тонирование бороды\', \'Мытье, окрашивание, укладка бороды.\', \'resources/upload/admin/service/beard-tint.png\', 35, 40, false, true),\n" +

                        "(\'Королевское бритьё\', \'Распаривание кожного покрова лица. Бритьё шаветкой.\', \'path/to/preview.jpg\', 40, 60, false, false),\n" +
                        "(\'Моделирование бороды\', \'Придание формы бороде шаветкой. Укладка бороды.\', \'path/to/preview.jpg\', 25, 30, false, false),\n" +
                        "(\'Стрижка бороды и усов\', \'Мытьё, стрижка бороды и усов.\', \'path/to/preview.jpg\', 25, 45, false, false),\n" +
                        "(\'Детская стрижка\', \'Мытьё волос. Стрижка машинков/ножниками. Укладка волос.\', \'path/to/preview.jpg\', 30, 50, false, false),\n" +
                        "(\'Стрижка машинкой\', \'Мытьё волос.Стрижка машинкой. Укладка волос.\', \'path/to/preview.jpg\', 25, 25, false, false),\n" +
                        "(\'Мужская стрижка\', \'Мытьё волос. Стрижка машинков/ножниками. Укладка волос.\', \'path/to/preview.jpg\', 40, 60, false, false),\n" +
                        "(\'Камуфляж седины\', \'Мытьё, окрашивание волос.\', \'path/to/preview.jpg\', 45, 75, false, false),\n" +
                        "(\'Окрашивание\', \'Мытьй, окрашивание волос.\', \'path/to/preview.jpg\', 60, 60, false, false),\n" +
                        "(\'Очищающая маска для лица\', \'Распаривание кожи лица, наложение очищающей маски.\', \'path/to/preview.jpg\', 30, 45, false, false),\n" +
                        "(\'Удаление волос воском (одна зона)\', \'Распаривание кожи выбранной зоны, удаление волос воском.\', \'path/to/preview.jpg\', 15, 15, false, false),\n" +
                        "(\'Массаж головы\', \'Массаж головы.\', \'path/to/preview.jpg\', 10, 15, false, false);"
        );

        printStream.println("\n-- Fill table reservations");
        printStream.println("INSERT INTO reservations (offer_id, customer_id, employee_id, date)");
        printStream.println("VALUES");
        for (int i = 0; i < SUMMARY_SERVICE_NUMBER; i++) {
            Random random = new Random();
            int serviceId = random.nextInt(AVAILABLE_SERVICES_NUMBER + 1) + 1;
            stringBuffer
                    .append("(")
                    .append(serviceId)
                    .append(", ")
                    .append(random.nextInt(customerIdentifiers.size() + 1) + 1)
                    .append(", ")
                    .append(random.nextInt(employeeIdentifiers.size() + 1) + 1)
                    .append(", ")
                    .append("\'")
                    .append(new SimpleDateFormat("yyyy-MM-dd HH:mm")
                            .format(new Date()))
                    .append("\'")
                    .append(")");
            printStream.print(stringBuffer.toString());
            if (i < SUMMARY_SERVICE_NUMBER - 1) {
                printStream.println(",");
            } else {
                printStream.println(";");
            }
            stringBuffer.setLength(0);
            currentId++;
        }
    }
}
