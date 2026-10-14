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
            System.out.println("6. Показать победителя и процент голосов");
            System.out.println("7. Найти кандидата по фамилии");
            System.out.println("8. Удалить кандидата по фамилии");
            System.out.println("9. Выход");
            System.out.print("Выберите пункт меню (1..9): ");

            int c;
            try {
                c = Integer.parseInt(sc.nextLine().trim());
            } catch (NumberFormatException e) {
                System.out.println("Ошибка: введите число от 1 до 9.\n");
                continue;
            }

            switch (c) {
                case 1 -> candidateService.fillcandidates();
                case 2 -> candidateService.printcandidates();
                case 3 -> candidate.checkViolations();
                case 4 -> candidate.checkRepeatElection();
                case 5 -> candidate.sortByVotes();
                case 6 -> candidate.showWinner();
                case 7 -> candidate.findBySurname();
                case 8 -> candidate.deleteCandidate();
                case 9 -> { break cycle; }
                default -> System.out.println("Нет такого пункта меню.\n");
            }
        }

        sc.close();
    }
}