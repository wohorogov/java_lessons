package games;

import org.apache.commons.math3.util.MathArrays;

public class CardUtils {
    public static final int PARS_TOTAL_COUNT = CardUtils.Par.values().length; //9
    public static final int CARDS_TOTAL_COUNT = PARS_TOTAL_COUNT * CardUtils.Suit.values().length; //36
    public static Suit getSuit(int cardNumber) {return Suit.values()[cardNumber / PARS_TOTAL_COUNT];}
    public static Par getPar(int cardNumber) {
        return Par.values()[cardNumber % PARS_TOTAL_COUNT];
    }
    public static String toString(int cardNumber) {
        return getPar(cardNumber) + " " + getSuit(cardNumber);
    }
    public static int[] getShaffledCards() {
        int[] cards = {
                0, 1, 2, 3, 4, 5, 6, 7, 8,  // бубны
                9, 10, 11, 12, 13, 14, 15, 16, 17,  // червы
                18, 19, 20, 21, 22, 23, 24, 25, 26,  // трефы
                27, 28, 29, 30, 31, 32, 33, 34, 35}; // пики

        MathArrays.shuffle(cards);
        return cards;
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
