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
        return String.format("Кандидат: %s \t дата рождения: %s \t голосов: %d", surname, birthday, votes);
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
        if (votes<=0) System.out.println("Недопустимое значение !");
        else
            this.votes = votes;
    }
    public static void fillcandidates() {
        Scanner sc = new Scanner(System.in, "UTF-8");
        System.out.print("Введите количество кандидатов: ");
        int n = sc.nextInt();
        sc.nextLine();

        int oldLength = candidates.length;
        candidates = Arrays.copyOf(candidates, oldLength + n);

        for (int i = oldLength; i < candidates.length; i++) {
            System.out.println("Кандидат " + (i + 1) + " =>");
            String surname;
            while (true) {
                System.out.print("Фамилия: ");
                surname = sc.nextLine();
                if (surname.isEmpty()) {
                    System.out.println("Недопустимое значение!");
                } else {
                    break;
                }
            }
            String birthday;

            while (true) {
                System.out.print("Дата рождения (ДД.ММ.ГГГГ): ");
                birthday = sc.nextLine();

                try {
                    LocalDate date = LocalDate.parse(birthday, DATE_FORMAT);
                    birthday = date.format(DATE_FORMAT);
                    break;
                } catch (DateTimeParseException e) {
                    System.out.println("Недопустимая дата!");
                    System.out.println("Введите дату в формате ДД.ММ.ГГГГ");
                }
            }
            int votes;
            while (true) {
                System.out.print("Количество голосов: ");
                try {
                    votes = Integer.parseInt(sc.nextLine());
                    if (votes < 0) {
                        System.out.println("Недопустимое значение!");
                    } else {
                        break;
                    }
                } catch (NumberFormatException e) {
                    System.out.println("Недопустимое значение!");
                }
            }
            candidates[i] = new candidate(surname, birthday, votes);
        }
    }
    public static void printcandidates() {

        System.out.println("\nРезультат выборов:");
        for(int i=0; i<candidates.length; i++) {
            System.out.println(candidates[i]);
        }
    }
    //вычисляет одну третью от общего еол-ва избирателей, проверяет набрал ли хотя бы один кандидадт больше этого количества голосов
    public static void checkViolations() {
        int sumVotes = 0;
        for (int i = 0; i < candidates.length; i++) {
            sumVotes += candidates[i].getVotes();
        }
        System.out.println("\nПроверка нарушений:");
        System.out.println("Всего голосов за кандидатов: " + sumVotes);
        System.out.println("Общее количество избирателей: " + totalVoters);

        if (sumVotes > totalVoters)
            System.out.println("НАРУШЕНИЕ ОБНАРУЖЕНО!");
        else
            System.out.println("Нарушений не обнаружено.");
    }
    public static void checkRepeatElection() {

        boolean winnerExists = false;
        double limit = totalVoters / 3.0;
        for (int i = 0; i < candidates.length; i++){
            if (candidates[i].getVotes() > limit) {
                winnerExists = true;
                break;
            }
    }
        System.out.println("\nПроверка необходимости повторных выборов:");
        if (winnerExists)
            System.out.println("Повторные выборы НЕ требуются.");
        else
                System.out.println("Требуется проведение повторных выборов.");
}
    public static void sortByVotes() {

        for (int i = 0; i < candidates.length - 1; i++) {

            for (int j = 0; j < candidates.length - 1 - i; j++) {

                if (candidates[j].getVotes() <
                        candidates[j + 1].getVotes()) {

                    candidate temp = candidates[j];
                    candidates[j] = candidates[j + 1];
                    candidates[j + 1] = temp;
                }
            }
        }
        System.out.println("\nКандидаты, упорядоченные по убыванию голосов:");
        for (int i = 0; i < candidates.length; i++) {
            System.out.println(candidates[i]);
        }
    }
}