package lab1;

import java.util.Arrays;
import java.util.Scanner;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;

public class candidate {
    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("dd.MM.uuuu");
    private String surname;
    private String birthday;
    private int votes;
    private static candidate[] candidates;
    private static int totalVoters;

    static {
        candidates = new candidate[] {
                new candidate("Иванов", "15.03.1980", 250),
                new candidate("Петров", "21.07.1975", 180),
                new candidate("Сидоров", "10.11.1985", 320),
                new candidate("Смирнов", "05.01.1978", 150)
        };
        totalVoters = 1000;
    }

    public candidate(String surname, String birthday, int votes) {
        this.surname = surname;
        this.birthday = birthday;
        this.votes = votes;
    }

    public candidate() {
        Scanner sc = new Scanner(System.in, "UTF-8");
        System.out.print("Фамилия: ");
        this.setSurname(sc.nextLine());
        System.out.print("Дата рождения: ");
        this.setBirthday(sc.nextLine());
        System.out.print("Количество голосов: ");
        this.setVotes(sc.nextInt());
    }

    public String toString() {
        return String.format("Кандидат: %s \t дата рождения: %s \t голосов: %d",
                surname, birthday, votes);
    }

    public String getSurname() {
        return this.surname;
    }

    public void setSurname(String surname) {
        if (surname == null || surname.isEmpty())
            System.out.println("Недопустимое значение !");
        else this.surname = surname;
    }

    public String getBirthday() {
        return birthday;
    }

    public void setBirthday(String birthday) {
        if (birthday == null || birthday.isEmpty()) {
            System.out.println("Недопустимое значение!");
            return;
        }
        try {
            LocalDate.parse(birthday, DATE_FORMAT);
            this.birthday = birthday;
        } catch (DateTimeParseException e) {
            System.out.println("Дата должна быть в формате ДД.ММ.ГГГГ!");
        }
    }

    public int getVotes() {
        return votes;
    }

    public void setVotes(int votes) {
        if (votes <= 0) System.out.println("Недопустимое значение !");
        else this.votes = votes;
    }


    // определяет победителя, считает явку и проценты

    // поиск кандидата по фамилии


    // удаление кандидата по фамилии
    public static void deleteCandidate() {
        if (candidates.length == 0) {
            System.out.println("\nСписок кандидатов пуст.");
            return;
        }

        Scanner sc = new Scanner(System.in, "UTF-8");
        System.out.print("Введите фамилию кандидата для удаления: ");
        String target = sc.nextLine().trim();

        if (target.isEmpty()) {
            System.out.println("Фамилия не введена.");
            return;
        }

        int index = -1;
        for (int i = 0; i < candidates.length; i++) {
            if (candidates[i].getSurname().equalsIgnoreCase(target)) {
                index = i;
                break;
            }
        }

        if (index == -1) {
            System.out.println("Кандидат с фамилией \"" + target + "\" не найден.");
            return;
        }

        System.out.println("Найден кандидат:");
        System.out.println(candidates[index]);
        System.out.print("Удалить его? (y/n): ");
        String answer = sc.nextLine().trim();

        if (!answer.equalsIgnoreCase("y")) {
            System.out.println("Удаление отменено.");
            return;
        }

        for (int i = index; i < candidates.length - 1; i++) {
            candidates[i] = candidates[i + 1];
        }
        candidates = Arrays.copyOf(candidates, candidates.length - 1);

        System.out.println("Кандидат \"" + target + "\" удалён.");
    }
}