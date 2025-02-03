package mancala.domain;

public class Mancala implements IMancala {
    private final String namePlayer1;
    private final String namePlayer2;
    private final NormalBowl startingBowl;


    public Mancala(String namePlayer1, String namePlayer2) {
        this.namePlayer1 = namePlayer1;
        this.namePlayer2 = namePlayer2;
        startingBowl = new NormalBowl(namePlayer1, namePlayer2);
    }

    public Mancala(String gameState, String namePlayer1, String namePlayer2) {
        this.namePlayer1 = namePlayer1;
        this.namePlayer2 = namePlayer2;
        startingBowl = new NormalBowl(gameState, namePlayer1, namePlayer2);
    }

    @Override
    public String getNameOfPlayerOne() {
        return this.namePlayer1;
    }

    @Override
    public String getNameOfPlayerTwo() {
        return this.namePlayer2;
    }

    @Override
    public boolean isPlayersTurn(String name) {
        if (startingBowl.getOwner().getName().equals(name)) {
            return startingBowl.getOwner().getIsMyTurn();
        } else {
            return startingBowl.getOpponent().getIsMyTurn();
        }
    }

    @Override
    public void playPit(int index) {
        Bowl nthBowl = this.startingBowl.getNthNeighbourBowl(index);
        if (index == 6 || index == 13) {
            throw new IllegalArgumentException("Cannot do moves on Kalaha Bowls");
        } else {
            NormalBowl normalBowl = (NormalBowl) nthBowl;
            normalBowl.doMove();
        }
    }

    @Override
    public int getStonesForPit(int index) {
        Bowl nthBowl = this.startingBowl.getNthNeighbourBowl(index);
        return nthBowl.getNrOfSeedsInBowl();
    }

    @Override
    public boolean isEndOfGame() {
        NormalBowl player2Bowl = (NormalBowl) startingBowl.getNthNeighbourBowl(7);
        return startingBowl.isGameFinished() || player2Bowl.isGameFinished();
    }

    @Override
    public Winner getWinner() {
        if (isEndOfGame()) {

            if (startingBowl.getWinningPlayer().getName().equals(getNameOfPlayerOne())) {
                return Winner.PLAYER_1;
            } else if (startingBowl.getWinningPlayer().getName().equals(getNameOfPlayerTwo())) {
                return Winner.PLAYER_2;
            } else {
                return Winner.DRAW;
            }
        } else {
            return Winner.NO_ONE;
        }
    }

}
