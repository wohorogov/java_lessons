package games;

import java.io.IOException;

public class Choice {
    private static final String LINE_SEPARATOR = System.lineSeparator();
    public static void main(String[] args) throws IOException {
        int choice = 1;
        do {
            System.out.println("Выберите игру:\n1 - однорукий бандит, 2 - пьяница, 3 - очко, 0 - выход из игры");
            switch (getCharacterFromUser()) {
                case '1':
                    Slot.main();
                    break;
                case '2':
                    Drunkard.main();
                    break;
                case '3':
                    BlackJack.main();
                case '0':
                    choice = 0;
                    break;
                default:
                    System.out.println("Игры с таким номером нет!");
            }
        } while (choice > 0);
    }

    public static char getCharacterFromUser() throws IOException {
        byte[] input = new byte[1 + LINE_SEPARATOR.length()];
        if (System.in.read(input) != input.length)
            throw new RuntimeException("Пользователь ввёл недостаточное кол-во символов");
        return (char) input[0];
    }
}
