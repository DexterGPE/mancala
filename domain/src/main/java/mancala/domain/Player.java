package mancala.domain;

public class Player {

    private final Player opponent;
    private boolean isMyTurn;
    private String name;

    public Player() {
        this.name = "";
        this.isMyTurn = true;
        this.opponent = new Player(false, this, "");
    }

    public Player(String namePlayer1, String namePlayer2) {
        this.name = namePlayer1;
        this.isMyTurn = true;
        this.opponent = new Player(false, this, namePlayer2);
    }

    public Player(boolean isMyTurn, Player opponent, String namePlayer2) {
        this.name = namePlayer2;
        this.isMyTurn = isMyTurn;
        this.opponent = opponent;
    }

    public String getName() {
        return this.name;
    }

    public boolean getIsMyTurn() {
        return isMyTurn;
    }

    private void setIsMyTurn(boolean isMyTurn) {
        this.isMyTurn = isMyTurn;
    }

    public Player getOpponent() {
        return opponent;
    }

    void switchTurn() {
        if (this.getIsMyTurn()) {
            this.setIsMyTurn(false);
            this.getOpponent().setIsMyTurn(true);
        } else {
            this.setIsMyTurn(true);
            this.getOpponent().setIsMyTurn(false);
        }
    }
}
