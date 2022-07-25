package games;

public class Drunkard {
    private static final int[][] playersCards = new int[2][CardUtils.CARDS_TOTAL_COUNT];
    private static final int[] playerCardTails = new int[2];
    private static final int[] playerCardHeads = new int[2];

    public static void main() {
        int [] deck = CardUtils.getShaffledCards();
        int i = 0;
        int j = 0;
        
        while (i < CardUtils.CARDS_TOTAL_COUNT) {
            playersCards[0][j] = deck[i];
            playersCards[1][j] = deck[i+1];
            j++;
            i = i + 2;
        }
        i = 0;
        while (i < CardUtils.CARDS_TOTAL_COUNT / 2) {
            System.out.println("1:"+i+","+CardUtils.toString(playersCards[0][i]));
            System.out.println("2:"+i+","+CardUtils.toString(playersCards[1][i]));
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
                case 2:
                default: {
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

    private static int incrementIndex(int i) { return (i + 1) % CardUtils.CARDS_TOTAL_COUNT; }

    private static boolean playerCardsIsEmpty(int playerIndex) {
        int tail = playerCardTails[playerIndex];
        int head = playerCardHeads[playerIndex];

        return tail == head;
    }

    public static int cardComparison(int first, int second) {
        CardUtils.Par firstPar = CardUtils.getPar(first);
        CardUtils.Par secondPar = CardUtils.getPar(second);

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
            result += CardUtils.CARDS_TOTAL_COUNT;
        }
        return result;
    }
}
