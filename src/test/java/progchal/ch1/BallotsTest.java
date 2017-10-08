package progchal.ch1;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class BallotsTest {

    @Test
    public void senario() {
        Ballots ballots = new Ballots();
        ballots.addName("John Doe");
        ballots.addName("Jane Smith");
        ballots.addName("Jane Austen");

        String[] votes = {
                "1 2 3",
                "2 1 3",
                "2 3 1",
                "1 2 3",
                "3 1 2"
        };

        for (String s : votes) {
            ballots.addBallot(Ballot.fromText(s));
        }

        assertEquals("John Doe", ballots.winner());
    }
}