package games;

import static org.apache.commons.math3.util.MathArrays.shuffle;

public class Drunkard {
    private static final int PARS_TOTAL_COUNT = Par.values().length; //9
    private static final int CARDS_TOTAL_COUNT = PARS_TOTAL_COUNT * Suit.values().length; //36
    private static int[][] playersCards = new int[2][CARDS_TOTAL_COUNT];
    private static int[] playerCardTails = new int[2];
    private static int[] playerCardHeads = new int[2];
    public static void main() {
        int [] deck;
        deck = new int[CARDS_TOTAL_COUNT];
        int i = 0;
        while (i < CARDS_TOTAL_COUNT) {
            deck[i] = i;
            i++;
        }
        shuffle(deck);
        i = 0;
        int j = 0;
        
        while (i < CARDS_TOTAL_COUNT) {
            playersCards[0][j] = deck[i];
            playersCards[1][j] = deck[i+1];
            j++;
            i = i + 2;
        }
        i = 0;
        j = 0;
        Par par;
        while (i < CARDS_TOTAL_COUNT / 2) {
            System.out.println("1:"+i+","+toString(playersCards[0][i]));
            System.out.println("2:"+i+","+toString(playersCards[1][i]));
            par = getPar(playersCards[0][i]);
            System.out.println(par.ordinal());
            i++;
        }
        System.out.println("---------------END---------------");
        playerCardHeads[0] = 18;
        playerCardHeads[1] = 18;
        playerCardTails[0] = 0;
        playerCardTails[1] = 0;
        boolean someone_win = false;
        boolean first_win = true;
        while (!someone_win) {
            switch (cardComparison(playersCards[0][playerCardTails[0]], playersCards[1][playerCardTails[1]])) {
                case 0: {
                    playerCardHeads[0] = incrementIndex(playerCardHeads[0]);
                    playerCardHeads[1] = incrementIndex(playerCardHeads[1]);
                    playersCards[0][playerCardHeads[0]] = playersCards[0][playerCardTails[0]];
                    playersCards[1][playerCardHeads[1]] = playersCards[1][playerCardTails[1]];
                    playerCardTails[0] = incrementIndex(playerCardTails[0]);
                    playerCardTails[1] = incrementIndex(playerCardTails[1]);
                    first_win = false;
                    break;
                }
                case 1: {
                    playerCardHeads[0] = incrementIndex(playerCardHeads[0]);
                    playersCards[0][playerCardHeads[0]] = playersCards[0][playerCardTails[0]];
                    playerCardHeads[0] = incrementIndex(playerCardHeads[0]);
                    playersCards[0][playerCardHeads[0]] = playersCards[1][playerCardTails[1]];
                    playerCardTails[0] = incrementIndex(playerCardTails[0]);
                    playerCardTails[1] = incrementIndex(playerCardTails[1]);
                    first_win = true;
                    break;
                }
                case 2: {
                    playerCardHeads[1] = incrementIndex(playerCardHeads[1]);
                    playersCards[1][playerCardHeads[1]] = playersCards[1][playerCardTails[1]];
                    playerCardHeads[1] = incrementIndex(playerCardHeads[1]);
                    playersCards[1][playerCardHeads[1]] = playersCards[0][playerCardTails[0]];
                    playerCardTails[1] = incrementIndex(playerCardTails[1]);
                    playerCardTails[0] = incrementIndex(playerCardTails[0]);
                    first_win = false;
                    break;
                }
            }
            System.out.println(first_win);
            if (first_win) {
                if (playerCardsIsEmpty(0)) {
                    someone_win = true;
                }
            } else {
                if (playerCardsIsEmpty(1)) {
                    someone_win = true;
                }
            }

            System.out.println("Количество кард у первого игрока: " + getCardsTotalCount(0));
            System.out.println("Количество кард у второго игрока: " + getCardsTotalCount(1));
        }

        if (first_win) {
            System.out.println("Победил первый игрок!");
        }
        else {
            System.out.println("Победил второй игрок!");
        }
    }

    private static Suit getSuit(int cardNumber) {
        return Suit.values()[cardNumber / PARS_TOTAL_COUNT];
    }

    private static Par getPar(int cardNumber) {
        return Par.values()[cardNumber % PARS_TOTAL_COUNT];
    }

    private static String toString(int cardNumber) {
        return getPar(cardNumber) + " " + getSuit(cardNumber);
    }

    private static int incrementIndex(int i) { return (i + 1) % CARDS_TOTAL_COUNT; }

    private static boolean playerCardsIsEmpty(int playerIndex) {
        int tail = playerCardTails[playerIndex];
        int head = playerCardHeads[playerIndex];

        return tail == head;
    }

    public static int cardComparison(int first, int second) {
        Par firstPar = getPar(first);
        Par secondPar = getPar(second);

        if (firstPar.ordinal() == 0 && secondPar.ordinal() == 8) {
            System.out.println("+");
            return 1;
        } else if (firstPar.ordinal() == 8 && secondPar.ordinal() == 0) {
            return 2;
        } else if (firstPar.ordinal() == secondPar.ordinal()) {
            return 0;
        } else if (firstPar.ordinal() > secondPar.ordinal()) {
            System.out.println("+");
            return 1;
        } else return 2;
    }

    private static int getCardsTotalCount(int playerIndex) {
        int result;
        result = playerCardHeads[playerIndex] - playerCardTails[playerIndex];
        if (result < 0) {
            result += CARDS_TOTAL_COUNT;
        }
        return result;
    }

    enum Suit{
        SPADES,    //пики
        HEARTS,    //червы
        CLUBS,     //трефы
        DIAMONDS   //бубны
    }

    enum Par{
        SIX,
        SEVEN,
        EIGHT,
        NINE,
        TEN,
        JACK,
        QUEEN,
        KING,
        ACE
    }
}
