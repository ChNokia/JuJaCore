package week2.recursion.lab19ExchangeMoney;
/*
Номинал мелких монет - 1,2,5,10,25,50.
1 копейку можно разменять 1 разным способом - {1}
2 - двумя разными способами {1 + 1; 2}
5 - 4 {1 + 1 + 1 + 1 + 1; 1 + 1 + 1 + 2; 1 + 2 + 2; 5}
........
Сколькими разными способами можно разменятьгривну (100 монет) ?

Реализовать алгоритм подсчета колличества разных разменов для входящего параметра.
 */
public class ExchangeMoney {
    final static int[] exchangeMonets = {1,2,5,10,25,50};

    public static int exchangeAmountOfCoinsBrutForce(int amountForExchange) {
        for ( int i = exchangeMonets.length-1; i >= 0; i-- ) {
            if ( amountForExchange / exchangeMonets[i] >= 1 ) {
                return exchangeAmountOfCoinsBrutForce(i, amountForExchange);
            }
        }

        return 0;
    }

    public static int exchangeAmountOfCoinsBrutForce(int exchangeMonetIndex, int amountForExchange) {
        if ( exchangeMonetIndex == 0 || amountForExchange == 0 ) {
            return 1;
        }

        if ( amountForExchange >= exchangeMonets[exchangeMonetIndex] ) {
            return exchangeAmountOfCoinsBrutForce(exchangeMonetIndex - 1, amountForExchange) + exchangeAmountOfCoinsBrutForce(exchangeMonetIndex, amountForExchange - exchangeMonets[exchangeMonetIndex]);
        }

        return exchangeAmountOfCoinsBrutForce(exchangeMonetIndex - 1, amountForExchange);
    }
}
