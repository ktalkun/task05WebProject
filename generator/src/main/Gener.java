package main;


import password.HashGenerator;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.PrintStream;
import java.sql.SQLException;
import java.util.*;

public class Gener {
    private static final int MAX_NUMBER = 33366;
    private static final int NUMBER = 100;
    private static final int SERVICES_NUMBER = 16;
    private static final String LOGINS_PATH = "data/logins.txt";
    private static final String NAMES_MALE_PATH = "data/names_male.txt";
    private static final String PATRONOMICS_MALE_PATH = "data/patronymic_male.txt";
    private static final String SURNAMES_MALE_PATH = "data/surnames_male.txt";
    private static final String NAMES_FEMALE_PATH = "data/names_female.txt";
    private static final String PATRONOMICS_FEMALE_PATH = "data/patronymiс_female.txt";
    private static final String SURNAMES_FEMALE_PATH = "data/surnames_female.txt";


    public static void main(String[] args) throws FileNotFoundException {
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
        printStream.println("USE `barbershop_db`;");
        printStream.println("# Fill table `users`");
        printStream.println("INSERT INTO `users` (`login`, `password`, `name`, `surname`, `patronymic`, `email`, `phone`, `image_path`, `role`)");
        printStream.println("VALUES");
        StringBuffer stringBuffer = new StringBuffer();
        Set<Long> phoneNumbers = new HashSet<>();

        List<Integer> customerIdentifiers = new ArrayList<>();
        List<Integer> employeeIdentifiers = new ArrayList<>();
        int currentId = 1;
        for (int i = 0; i < NUMBER; i++) {
            Random random = new Random();
            int randIndex = random.nextInt(logins.size());
            String login = logins.get(randIndex);
            logins.remove(randIndex);
            String name;
            String surname;
            String patronymic;
            long phone;

            do {
                phone = (long) (random.nextDouble() * Math.pow(10, 12));
            } while (phoneNumbers.contains(phone));

            if (random.nextInt(2) == 0) {
                name = namesMale.get(random.nextInt(namesMale.size()));
                surname = surnamesMale.get(random.nextInt(surnamesMale.size()));
                patronymic = patronymicsMale.get(random.nextInt(patronymicsMale.size()));
            } else {
                name = namesFemale.get(random.nextInt(namesFemale.size()));
                surname = surnamesFemale.get(random.nextInt(surnamesFemale.size()));
                patronymic = patronymicsFemale.get(random.nextInt(patronymicsFemale.size()));
            }
            int role = random.nextInt(4);
            if (role == 2) {
                employeeIdentifiers.add(currentId);
            } else if (role == 3) {
                customerIdentifiers.add(currentId);
            }
            stringBuffer
                    .append("(")
                    .append("\"")
                    .append(login)
                    .append("\"")
                    .append(", ")
                    .append("\"")
                    .append(HashGenerator.hashPassword(login, login).get())
                    .append("\"")
                    .append(", ")
                    .append("\"")
                    .append(name)
                    .append("\"")
                    .append(", ")
                    .append("\"")
                    .append(surname)
                    .append("\"")
                    .append(", ")
                    .append("\"")
                    .append(patronymic)
                    .append("\"")
                    .append(", ")
                    .append("\"")
                    .append(login)
                    .append("@gmail.com")
                    .append("\"")
                    .append(", ")
                    .append(phone)
                    .append(", ")
                    .append("\"")
                    .append("path/smth")
                    .append("\"")
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

        printStream.println("\n# Fill table `employees`");
        printStream.println("INSERT INTO `employees` (`employee_id`, `experience`)");
        printStream.println("VALUES");
        for (int i = 0; i < employeeIdentifiers.size(); i++) {
            stringBuffer
                    .append("(")
                    .append(employeeIdentifiers.get(i))
                    .append(", ")
                    .append("\"")
                    .append(new java.sql.Date(new Date().getDate()))
                    .append("\"")
                    .append(")");
            printStream.print(stringBuffer.toString());
            if (i < employeeIdentifiers.size() - 1) {
                printStream.println(",");
            } else {
                printStream.println(";");
            }
            stringBuffer.setLength(0);
        }

        printStream.println("\n# Fill table `offers`");
        printStream.println(
                "INSERT INTO `offers` (`name`, `description`, `price`, `period`)\n" +
                        "VALUES\n" +
                        "(\"Бритьё головы\", \"Распаривание кожного покрова головы. Бритьё шаветкой.\", 30, 45),\n" +
                        "(\"Королевское бритьё\", \"Распаривание кожного покрова лица. Бритьё шаветкой.\", 40, 60),\n" +
                        "(\"Моделирование бороды\", \"Придание формы бороде шаветкой. Укладка бороды.\", 25, 30),\n" +
                        "(\"Тонирование бороды\", \"Мытье, окрашивание, укладка бороды.\", 35, 40),\n" +
                        "(\"Стрижка бороды и усов\", \"Мытьё, стрижка бороды и усов.\", 25, 45),\n" +
                        "(\"Детская стрижка\", \"Мытьё волос. Стрижка машинков/ножниками. Укладка волос.\", 30, 50),\n" +
                        "(\"Пробор/Окантовка\", \"Придание формы причёске.\", 10, 20),\n" +
                        "(\"Стрижка машинкой\", \"Мытьё волос.Стрижка машинкой. Укладка волос.\", 25, 25),\n" +
                        "(\"Укладка\", \"Укладка волос.\", 10, 15),\n" +
                        "(\"Мужская стрижка\", \"Мытьё волос. Стрижка машинков/ножниками. Укладка волос.\", 40, 60),\n" +
                        "(\"Камуфляж седины\", \"Мытьё, окрашивание волос.\", 45, 75),\n" +
                        "(\"Афрокосички\", \"Матьё волос. Заплетание косичек.\", 150, 90),\n" +
                        "(\"Рисунок на волосах\", \"Стрижка волос согласно трафарету.\", 55, 90),\n" +
                        "(\"Окрашивание\", \"Мытьй, окрашивание волос.\", 60, 60),\n" +
                        "(\"Очищающая маска для лица\", \"Распаривание кожи лица, наложение очищающей маски.\", 30, 45),\n" +
                        "(\"Удаление волос воском (одна зона)\", \"Распаривание кожи выбранной зоны, удаление волос воском.\", 15, 15),\n" +
                        "(\"Массаж головы\", \"Массаж головы.\", 10, 15);"
        );

        printStream.println("\n# Fill table `reservations`");
        printStream.println("INSERT INTO `reservations` (`offer_id`, `customer_id`, `employee_id`, `date`)");
        printStream.println("VALUES");
        for (int i = 0; i < NUMBER; i++) {
            Random random = new Random();
            int serviceId = random.nextInt(SERVICES_NUMBER + 1) + 1;
            stringBuffer
                    .append("(")
                    .append(serviceId)
                    .append(", ")
                    .append(random.nextInt(customerIdentifiers.size() + 1) + 1)
                    .append(", ")
                    .append(random.nextInt(employeeIdentifiers.size() + 1) + 1)
                    .append(", ")
                    .append("\"")
                    .append(new java.sql.Date(new Date().getDate()))
                    .append("\"")
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

    }
}
