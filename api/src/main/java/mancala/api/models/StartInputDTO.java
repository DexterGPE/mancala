package mancala.api.models;

public class StartInputDTO {

    private String player1;
    private String player2;
    private boolean revenge;

    public String getPlayer1() {
        return player1;
    }

    public void setPlayer1(String player1) {
        this.player1 = player1;
    }

    public String getPlayer2() {
        return player2;
    }

    public void setPlayer2(String player2) {
        this.player2 = player2;
    }

    public boolean getRevenge() {
        return revenge;
    }

    public void setRevenge(boolean revenge) {
        this.revenge = revenge;
    }
}
