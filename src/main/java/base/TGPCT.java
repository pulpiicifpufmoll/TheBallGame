package base;

public class TGPCT {

    // Attributes:
    private final TheGameController gameController;

    // Constructor:
    public TGPCT() {
        this.gameController = new TheGameController(600, 600);
    }

    // Getter:
    public TheGameController getLc() {
        return gameController;
    }

    // Main:
    public static void main(String[] args) {
        TGPCT game = new TGPCT();
    }
}