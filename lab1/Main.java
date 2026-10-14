package lab1;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in, "UTF-8");

        cycle:
        while (true) {

            System.out.println("1. Ввести кандидатов");
            System.out.println("2. Распечатать кандидатов");
            System.out.println("3. Проверить нарушения");
            System.out.println("4. Проверить необходимость повторных выборов");
            System.out.println("5. Упорядочить кандидатов по голосам");
            System.out.println("6. Выход");
            System.out.print("Выберите пункт меню (1..6): ");

            int c;
            try {
                // читаем строку и парсим — это безопаснее, чем nextInt(),
                // т.к. не оставляет "мусор" в буфере при ошибке
                c = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число от 1 до 6.\n");
                continue;
            }

            switch (c) {
                case 1 -> candidate.fillcandidates();
                case 2 -> candidate.printcandidates();
                case 3 -> candidate.checkViolations();
                case 4 -> candidate.checkRepeatElection();
                case 5 -> candidate.sortByVotes();
                case 6 -> { break cycle; }
                default -> System.out.println("Нет такого пункта меню.\n");
            }
        }

        sc.close();
    }
}