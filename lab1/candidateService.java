package lab1;

public class candidateService {
    private static candidate[] candidates;
    public static void printcandidates() {
    System.out.println("\nРезультат выборов:");
    for (int i = 0; i < candidates.length; i++) {
        System.out.println(candidates[i]);
    }
}
}
