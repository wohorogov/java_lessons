package games;

import java.io.IOException;

public class BlackJack {
    private static final int MAX_VALUE = 21;
    private static final int MAX_CARDS_COUNT = 8;
    private static int[] playersMoney = {100, 100};

    private static int[] cards; // Основная колода
    private static int cursor; // Счётчик карт основной колоды

    private static int[][] playersCards; // карты игроков. Первый индекс - номер игрока
    private static int[] playersCursors; // курсоры карт игроков. Индекс - номер игрока

    public static void main() throws IOException {
        boolean exit = false;
        int playerSum, AIsum;
        do {
            initRound();
            playerSum = play(0);
            System.out.println("Ваше количество очков " + playerSum);
            AIsum = play(1);
            System.out.println("Количество очков компьютера " + AIsum);
            if (playerSum > AIsum)
            {
                playersMoney[0] += 10;
                playersMoney[1] -= 10;
            }
            else if (playerSum < AIsum){
                playersMoney[0] -= 10;
                playersMoney[1] += 10;
            }
            for (int i = 0; i < 2; i++) {
                if (playersMoney[i] == 0) {
                    exit = true;
                }
            }
        } while (!exit);

        if (playersMoney[0] > 0)
            System.out.println("Вы выиграли! Поздравляем!");
        else
            System.out.println("Вы проиграли. Соболезнуем...");
    }

    private static int play(int player) throws IOException {
        boolean playerPlay = true;
        int playerSum;
        String infoMsg;
        if (player == 0)
            infoMsg = "Вам выпала карта ";
        else
            infoMsg = "Компьютеру выпала карта ";
        addCard2Player(player);
        System.out.println(infoMsg + CardUtils.toString(playersCards[player][playersCursors[player] - 1]));
        addCard2Player(player);
        System.out.println(infoMsg + CardUtils.toString(playersCards[player][playersCursors[player] - 1]));
        while (playerPlay) {
            playerPlay = false;
            playerSum = sum(player);
            if (player == 0) {
                if (playerSum < MAX_VALUE - 1) {
                    playerPlay = confirm("Ваше количество очков - " + playerSum + ", будете брать еще карту?");
                    if (playerPlay) {
                        addCard2Player(player);
                        System.out.println(infoMsg + CardUtils.toString(playersCards[player][playersCursors[player] - 1]));
                    }

                }
            }
            else {
                if (playerSum < MAX_VALUE - 4) {
                    playerPlay = true;
                    addCard2Player(player);
                    System.out.println(infoMsg + CardUtils.toString(playersCards[player][playersCursors[player] - 1]));
                }
            }
        }
        return getFinalSum(player);
    }
    private static boolean confirm(String message) throws IOException {
        System.out.println(message + " \"Y\" - Да, {любой другой символ} - нет (Чтобы выйти из игры, нажмите Ctrl + C)");
        switch (Choice.getCharacterFromUser()) {
            case 'Y':
            case 'y': return true;
            default: return false;
        }
    }
    private static int addCard2Player(int player) {
        playersCards[player][playersCursors[player]] = cards[cursor];
        cursor += 1;
        playersCursors[player] += 1;
        return playersCards[player][playersCursors[player] - 1];
    }
    private static void initRound() {
        System.out.println("\nУ Вас " + playersMoney[0] + "$, у компьютера - " + playersMoney[1] + "$. Начинаем новый раунд!");
        cards = CardUtils.getShaffledCards();
        playersCards = new int[2][MAX_CARDS_COUNT];
        playersCursors = new int[]{0, 0};
        cursor = 0;
    }
    private static int sum(int player) {
        int sum = 0;
        for (int i = 0; i < playersCursors[player]; i++) {
            sum += value(playersCards[player][i]);
        }
        return sum;
    }

    private static int getFinalSum(int player) {
        int sum = sum(player);
        if (sum > MAX_VALUE) {
            return 0;
        } else
            return sum;
    }

    private static int value(int card) {
        switch (CardUtils.getPar(card)) {
            case JACK: return 2;
            case QUEEN: return 3;
            case KING: return 4;
            case SIX: return 6;
            case SEVEN: return 7;
            case EIGHT: return 8;
            case NINE: return 9;
            case TEN: return 10;
            case ACE:
            default: return 11;
        }
    }
}
