package lab1;
import java.util.Scanner;
public class Main {
    public static void main(String[] args) {

        cycle:
        while (true) {

            System.out.println("1. Ввести кандидаов");
            System.out.println("2. Распечатать кандидатов");
            System.out.println("3. Проверить нарушения");
            System.out.println("4. Проверить необходимость повторных выборов");
            System.out.println("5. Упорядочить кандидатов по голосам");
            System.out.println("6. Выход");
            System.out.println("Выберите пункт меню (1..6)");
            System.out.println("Строка");

            int c = (new Scanner(System.in)).nextInt();

            switch (c) {

                case 1:
                    candidate.fillcandidates();
                    break;
                case 2:
                    candidate.printcandidates();
                    break;
                case 3:
                    candidate.checkViolations();
                    break;
                case 4:
                    candidate.checkRepeatElection();
                    break;
                case 5:
                    candidate.sortByVotes();
                    break;
                default:
                    break cycle;
            }
        }
    }
}


