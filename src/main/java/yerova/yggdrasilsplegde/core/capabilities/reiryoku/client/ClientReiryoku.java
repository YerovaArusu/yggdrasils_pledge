package yerova.yggdrasilsplegde.core.capabilities.reiryoku.client;


public class ClientReiryoku {

    private static int reiryoku;
    private static int maxReiryoku;

    public static void set(int reiryoku, int maxReiryoku) {
        ClientReiryoku.reiryoku = reiryoku;
        ClientReiryoku.maxReiryoku = maxReiryoku;
    }

    public static int getReiryoku() {return reiryoku;}
    public static int getMaxReiryoku() {return maxReiryoku;}
}
