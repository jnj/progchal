package progchal.ch2;

import java.util.ArrayList;
import java.util.List;

public class PokerHands {
    public static void main(String[] args) {
        Hand w = new Hand();
        Hand b = new Hand();

        for (String s : new String[]{"2H", "3D", "5S", "9C", "KD"}) {
            b.add(s);
        }

        for (String s : new String[]{"2C", "3H", "4S", "8C", "AH"}) {
            w.add(s);
        }

        int i = w.compareTo(b);
        assert i > 0;
    }
}

class Hand implements Comparable<Hand> {
    private final List<Card> cards = new ArrayList<>();
    private final CountSet countSet = new CountSet();
    private Rank rank;

    private enum Rank {
        HighCard,
        Pair,
        TwoPair,
        ThreeKind,
        Straight,
        Flush,
        FullHouse,
        FourKind,
        StraightFlush
    }

    void add(String desc) {
        add(new Card(desc.charAt(0), desc.charAt(1)));
    }

    private void add(Card card) {
        cards.add(card);
        countSet.increment(card.numericValue());

        if (cards.size() == 5) {
            cards.sort(Card::compareTo);
            assignRank();
        }
    }

    private void assignRank() {
        if (isStraightFlush()) {
            rank = Rank.StraightFlush;
        } else if (isFourOfAKind()) {
            rank = Rank.FourKind;
        } else if (isFullHouse()) {
            rank = Rank.FullHouse;
        } else if (isFlush()) {
            rank = Rank.Flush;
        } else if (isStraight()) {
            rank = Rank.Straight;
        } else if (isThreeOfAKind()) {
            rank = Rank.ThreeKind;
        } else if (isTwoPair()) {
            rank = Rank.TwoPair;
        } else if (isPair()) {
            rank = Rank.Pair;
        } else {
            rank = Rank.HighCard;
        }
    }

    @Override
    public int compareTo(Hand o) {
        int rankCmp = Integer.compare(rank.ordinal(), o.rank.ordinal());

        if (rankCmp != 0) {
            return rankCmp;
        }

        int highCardCmp = Integer.compare(highCard(), o.highCard());

        if (highCardCmp != 0) {
            return highCardCmp;
        }

        switch (rank) {
            case Pair:
                return Integer.compare(nonPairHighCard(), o.nonPairHighCard());
            case TwoPair:
                int lesserPairCmp = Integer.compare(lesserPair(), o.lesserPair());
                return lesserPairCmp != 0 ? lesserPairCmp : Integer.compare(nonPairHighCard(), o.nonPairHighCard());
            default:
                return 0;
        }
    }

    private int lesserPair() {
        return countSet.getLowPair();
    }

    private int nonPairHighCard() {
        return countSet.nonPairHighCard();
    }

    private int highCard() {
        return switch (rank) {
            case ThreeKind, FullHouse -> countSet.getThreeOfAKindValue();
            case FourKind -> countSet.getFourOfAKindValue();
            default -> countSet.highCard();
        };
    }

    private boolean isTwoPair() {
        return countSet.isTwoPair();
    }

    private boolean isFourOfAKind() {
        return countSet.getFourOfAKindValue() != CountSet.NO_VALUE;
    }

    private boolean isFullHouse() {
        return countSet.getThreeOfAKindValue() != CountSet.NO_VALUE &&
               (countSet.getHighPair() != CountSet.NO_VALUE ||
                countSet.getLowPair() != CountSet.NO_VALUE);
    }

    private boolean isThreeOfAKind() {
        return countSet.getThreeOfAKindValue() != CountSet.NO_VALUE &&
               countSet.getHighPair() == CountSet.NO_VALUE;
    }

    private boolean isPair() {
        return countSet.getHighPair() != CountSet.NO_VALUE;
    }

    private boolean isStraightFlush() {
        return isStraight() && isFlush();
    }

    private boolean isStraight() {
        int numValue = cards.get(0).numericValue();

        for (int i = 1; i < 5; i++) {
            if (numValue++ != cards.get(i).numericValue()) {
                return false;
            }
        }

        return true;
    }

    private boolean isFlush() {
        char suit = cards.get(0).suit;

        for (int i = 1; i < 5; i++) {
            if (cards.get(i).suit != suit) {
                return false;
            }
        }

        return true;
    }
}

class CountSet {
    static final int NO_VALUE = -1;
    private final byte[] counts = new byte[15];

    void increment(int numValue) {
        counts[numValue]++;
    }

    boolean isTwoPair() {
        int x = getLowValueWithCount((byte) 2);
        int y = getValueWithCount((byte) 2);
        return x != 0 && y != 0 && x != y;
    }

    private int getValueWithCount(byte count) {
        for (int i = 14; i >= 2; i--) {
            if (counts[i] == count) {
                return i;
            }
        }

        return NO_VALUE;
    }

    private int getLowValueWithCount(byte count) {
        for (int i = 2; i < 15; i++) {
            if (counts[i] == count) {
                return i;
            }
        }

        return NO_VALUE;
    }

    int getFourOfAKindValue() {
        return getValueWithCount((byte) 4);
    }

    int getThreeOfAKindValue() {
        return getValueWithCount((byte) 3);
    }

    int getLowPair() {
        return getLowValueWithCount((byte) 2);
    }

    int getHighPair() {
        return getValueWithCount((byte) 2);
    }

    int highCard() {
        for (int i = 14; i >= 2; i--) {
            if (counts[i] != 0) {
                return i;
            }
        }

        return NO_VALUE;
    }

    int nonPairHighCard() {
        for (int i = 14; i >= 2; i--) {
            if (counts[i] == 1) {
                return i;
            }
        }

        return NO_VALUE;
    }
}

class Card implements Comparable<Card> {
    private final char value;
    final char suit;

    Card(char value, char suit) {
        this.value = value;
        this.suit = suit;
    }

    @Override
    public int compareTo(Card o) {
        return Integer.compare(numericValue(), o.numericValue());
    }

    @Override
    public String toString() {
        return "" + value + suit;
    }

    int numericValue() {
        if (Character.isDigit(value)) {
            return Character.digit(value, 10);
        } else {
            return switch (value) {
                case 'J' -> 11;
                case 'Q' -> 12;
                case 'K' -> 13;
                case 'A' -> 14;
                default -> 0;
            };
        }
    }
}
