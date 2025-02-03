package mancala.domain;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class KalahaBowlTest {
    private static String player1;
    private static String player2;

    @BeforeAll
    public static void setPlayerNames() {
        player1 = "test_player_1";
        player2 = "test_player_2";
    }

    @Test
    public void addFiveSeedsToEmptyBowl() {
        Bowl normalBowl = new NormalBowl(player1, player2);
        KalahaBowl ownKahalaBowl = normalBowl.getKalahaBowl(normalBowl.getOwner());

        ownKahalaBowl.addSeeds(5);

        assertEquals(5, ownKahalaBowl.getNrOfSeedsInBowl());
    }

    @Test
    public void addSevenSeedsToEmptyBowl() {
        Bowl normalBowl = new NormalBowl(player1, player2);
        KalahaBowl ownKahalaBowl = normalBowl.getKalahaBowl(normalBowl.getOwner());

        ownKahalaBowl.addSeeds(7);

        assertEquals(7, ownKahalaBowl.getNrOfSeedsInBowl());
    }

    @Test
    public void keepOneSeedAndGiveRestToNextBowl() {
        Bowl normalBowl = new NormalBowl(player1, player2);
        KalahaBowl ownKahalaBowl = normalBowl.getKalahaBowl(normalBowl.getOwner());
        Player opponent = normalBowl.getOpponent();

        ownKahalaBowl.keepOneSeedPassRemainingSeedsToNextBowlRecursively(5, opponent);

        assertEquals(1, ownKahalaBowl.getNrOfSeedsInBowl());
        assertEquals(5, ownKahalaBowl.getRightSideNeighbour().getNrOfSeedsInBowl());
    }

    @Test
    public void doNotKeepOneSeedButGiveSeedsToNextBowl() {
        Bowl normalBowl = new NormalBowl(player1, player2);
        KalahaBowl opponentsKalahaBowl = normalBowl.getKalahaBowl(normalBowl.getOpponent());
        Player opponent = opponentsKalahaBowl.getOwner();

        opponentsKalahaBowl.keepOneSeedPassRemainingSeedsToNextBowlRecursively(5, opponent);

        assertEquals(0, opponentsKalahaBowl.getNrOfSeedsInBowl());
        assertEquals(5, opponentsKalahaBowl.getRightSideNeighbour().getNrOfSeedsInBowl());
    }
}
