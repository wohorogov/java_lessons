package games;

import java.io.IOException;

public class Choice {
    public static void main(String[] args) throws IOException {
        int choice;
        do {
            System.out.println("Выберите игру:\n1 - однорукий бандит, 2 - пьяница, 0 - выход из игры");
            choice = System.in.read();
            switch (choice) {
                case '1':
                    Slot.main();
                    break;
                case '2':
                    Drunkard.main();
                    break;
                case '0':
                    return;
                default:
                    System.out.println("Игры с таким номером нет!");
            }
        } while (choice > 0);
    }
}
