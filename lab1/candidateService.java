package lab1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;

public class candidateService {
    private static int totalVoters;
    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("dd.MM.uuuu");
    private static candidate[] candidates;
    public static void printcandidates() {
        System.out.println("\nРезультат выборов:");
        for (int i = 0; i < candidates.length; i++) {
            System.out.println(candidates[i]);
        }
    }
    public static void sortByVotes() {
        for (int i = 0; i < candidates.length - 1; i++) {
            for (int j = 0; j < candidates.length - 1 - i; j++) {
                if (candidates[j].getVotes() < candidates[j + 1].getVotes()) {
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
    public static void showWinner() {

        if (candidates.length == 0) {
            System.out.println("\nСписок кандидатов пуст.");
            return;
        }

        int sumVotes = 0;
        for (int i = 0; i < candidates.length; i++) {
            sumVotes += candidates[i].getVotes();
        }

        if (sumVotes == 0) {
            System.out.println("\nНи один кандидат не набрал голосов.");
            return;
        }

        candidate winner = candidates[0];
        for (int i = 1; i < candidates.length; i++) {
            if (candidates[i].getVotes() > winner.getVotes()) {
                winner = candidates[i];
            }
        }

        int winnerCount = 0;
        for (int i = 0; i < candidates.length; i++) {
            if (candidates[i].getVotes() == winner.getVotes()) {
                winnerCount++;
            }
        }

        System.out.println("\nРезультаты голосования:");
        System.out.printf("Явка: %d из %d избирателей (%.2f%%)%n",
                sumVotes, totalVoters,
                sumVotes * 100.0 / totalVoters);
        System.out.println("-".repeat(60));

        for (int i = 0; i < candidates.length; i++) {
            double percent = candidates[i].getVotes() * 100.0 / sumVotes;
            System.out.printf("%-12s — %4d голосов (%.2f%%)%n",
                    candidates[i].getSurname(),
                    candidates[i].getVotes(),
                    percent);
        }

        System.out.println("-".repeat(60));

        if (winnerCount > 1) {
            System.out.println("НИЧЬЯ: несколько кандидатов набрали одинаковое "
                    + "максимальное количество голосов (" + winner.getVotes() + ").");
            System.out.println("Требуется второй тур голосования.");
        } else {
            System.out.printf("ПОБЕДИТЕЛЬ: %s (%d голосов, %.2f%% от числа проголосовавших)%n",
                    winner.getSurname(),
                    winner.getVotes(),
                    winner.getVotes() * 100.0 / sumVotes);
        }
    }

    public static void fillcandidates() {
            Scanner sc = new Scanner(System.in, "UTF-8");
            System.out.print("Введите количество кандидатов: ");

            int n;
            try {
                n = Integer.parseInt(sc.nextLine().trim());
                if (n <= 0) {
                    System.out.println("Недопустимое значение!");
                    return;
                }
            } catch (NumberFormatException e) {
                System.out.println("Недопустимое значение!");
                return;
            }

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
        for (int i = 0; i < candidates.length; i++) {
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
    }


