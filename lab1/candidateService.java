package lab1;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.Arrays;
import java.util.Scanner;

public class candidateService {
    private static final DateTimeFormatter DATE_FORMAT =
            DateTimeFormatter.ofPattern("dd.MM.uuuu");
    private static candidate[] candidates;
    public static void printcandidates() {
        System.out.println("\nРезультат выборов:");
        for (int i = 0; i < candidates.length; i++) {
            System.out.println(candidates[i]);
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
    }

