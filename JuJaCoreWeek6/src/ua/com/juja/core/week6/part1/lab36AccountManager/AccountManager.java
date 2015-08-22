package ua.com.juja.core.week6.part1.lab36AccountManager;

/**
 Для работы с банковским счетом отдельного пользователя был разработан абстрактный класс Account.

 Поле amount хранит сумму на счете пользователя.

 Метод change(int delta) throws TryAgainException, BlockAccountException изменяет счет пользователя на значение дельты. Дельта может быть отрицательной.

 Этот метод имеет две нестандратные ситуации:

 class TryAgainException extends Exception {

 }

 Метод сообщает о временной неудаче, что значит:

 1. Ничего не сделано.

 2. Необходимо попробывать вызвать метод еще раз (а потом еще раз, и т.д. пока операция не пройдет).

 class BlockAccountException extends Exception {

 }

 Метод сообщает о полном блокировании счета, что значит:

 1. Ничего не сделано.

 2. Нет смысла вызывать метод еще раз.

 AccountManager - часть системы, которую необходимо изменить. (Менеджер счетов).

 На вход подается массив счетов и массив дельт для изменения. Массивы одинаковой длинны.

 В теле метода используется следующий код: accounts[k].change(deltas[k]) для всего массива счетов.

 Если при работе со счетом возникает TryAgainException необходимо повторять ситуацию до появления положительного результата.

 При BlockAccountException необходимо сделать откат всех предыдущих изменений и завершить работу. Т.е вернуть деньги на счета, с которых уже успели снять/положить деньги.

 На выходе метод возвращает true/false.

 true - если получилось перевести деньги на все счета.

 false - во всех остальных случаях.

 public class AccountManager {
 public static boolean transfer(Account[] accounts, int[] delta) {
 //BODY/////
}
        }

abstract class Account {
    protected int amount;

    public Account(int amount) {
        this.amount = amount;
    }

    public abstract void change(int delta) throws TryAgainException, BlockAccountException;

    public int getAmount() {
        return amount;
    }
}


class TryAgainException extends Exception {
}

class BlockAccountException extends Exception {
}
*/

public class AccountManager {
    public static boolean transfer(Account[] accounts, int[] delta) {
        /*BODY*/

            for ( int i = 0; i < accounts.length; i++ ) {
                try {
                    accounts[i].change(delta[i]);
                } catch (TryAgainException ex) {
                    i -= 1;
                } catch (BlockAccountException ex) {
                    for ( int j = i - 1; j > -1; j-- ) {
                        try {
                            accounts[j].change(-delta[j]);
                        } catch (TryAgainException e) {
                            j += 1;
                        } catch (Exception e) {

                        }
                    }

                    return false;
                }
            }

        return true;
    }
}

abstract class Account {
    protected int amount;

    public Account(int amount) {
        this.amount = amount;
    }

    public abstract void change(int delta) throws TryAgainException, BlockAccountException;

    public int getAmount() {
        return amount;
    }
}


class TryAgainException extends Exception {
}

class BlockAccountException extends Exception {
}
