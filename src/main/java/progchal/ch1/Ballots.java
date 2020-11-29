package progchal.ch1;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

class Ballots {
    private final List<String> names = new ArrayList<>();
    private final List<Ballot> ballots = new ArrayList<>();

    void addName(String name) {
        names.add(name);
    }

    void addBallot(Ballot b) {
        ballots.add(b);
    }

    String winner() {
        List<Integer> votes = new ArrayList<>();
        for (var i = 0; i < names.size(); i++) {
            votes.add(0);
        }

        var half = ballots.size() / 2.0;

        for (; ; ) {
            populateVotes(votes);

            var winner = findWinner(votes, half);
            if (winner.isPresent()) {
                return names.get(winner.get());
            }

            cull(votes);
        }
    }

    private void cull(List<Integer> votes) {
        var min = votes.stream().mapToInt(i -> i).min().getAsInt();
        List<Integer> toRemove = new ArrayList<>();

        for (var index = 0; index < votes.size(); index++) {
            if (votes.get(index) == min) {
                toRemove.add(index);
            }
        }

        for (var index : toRemove) {
            for (var ballot : ballots) {
                ballot.eliminate(index);
            }
        }
    }

    private Optional<Integer> findWinner(List<Integer> votes, double half) {
        for (var i = 0; i < votes.size(); i++) {
            if (votes.get(i) > half) {
                return Optional.of(i);
            }
        }

        return Optional.empty();
    }

    private void populateVotes(List<Integer> votes) {
        for (var ballot : ballots) {
            var i = ballot.firstChoice();
            votes.set(i, votes.get(i) + 1);
        }
    }
}

class Ballot {
    private final List<Integer> choices = new ArrayList<>();

    int firstChoice() {
        return choices.get(0);
    }

    static Ballot fromText(String line) {
        var ballot = new Ballot();
        var parts = line.split(" ");

        for (var s : parts) {
            ballot.choices.add(Integer.parseInt(s.trim()) - 1);
        }

        return ballot;
    }

    void eliminate(int number) {
        choices.remove(number);
    }
}
