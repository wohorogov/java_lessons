package games;

import static java.lang.Math.random;
import static java.lang.Math.round;

public class Slot {
    public static void main() {
        int firstCounter = 0, secondCounter = 0, thirdCounter = 0, size = 7;
        int balance = 100;
        //System.out.println("У вас " + balance + "$, ставка - 10%");
        LogClass.log.info("У вас " + balance + "$, ставка - 10%");
        do {
            firstCounter = (firstCounter + (int) round(random() * 100)) % size;
            secondCounter = (secondCounter + (int) round(random() * 100)) % size;
            thirdCounter = (thirdCounter + (int) round(random() * 100)) % size;
            LogClass.log.info("Крутим барабаны! Розыгрыш принес следующие результаты:");
            //System.out.println("Крутим барабаны! Розыгрыш принес следующие результаты:");
            //System.out.println("Первый барабан " + firstCounter + ", второй барабан " +
            //                    secondCounter + ", третий барабан " + thirdCounter);
            LogClass.log.info("Первый барабан " + firstCounter + ", второй барабан " +
                              secondCounter + ", третий барабан " + thirdCounter);
            if (firstCounter == secondCounter && firstCounter == thirdCounter) {
                balance += 1000;
                LogClass.log.info("Победа! Ваш капитал теперь составляет: " + balance);
                //System.out.println("Победа! Ваш капитал теперь составляет: " + balance);
            }
            else {
                balance -= 10;
                LogClass.log.info("Проигрыш 10%, Ваш капитал теперь составляет: " + balance);
                //System.out.println("Проигрыш 10%, Ваш капитал теперь составляет: " + balance);
            }
        }
        while (balance > 0);
        LogClass.log.info("Игра окончена!");
        //System.out.println("Игра окончена!");
    }
}
