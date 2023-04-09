package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class SavingAccountTest {

    @Test // Пополнение баланса
    public void shouldAddLessThanMaxBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                5
        );

        account.add(5_000);

        Assertions.assertEquals(2_000 + 3_000, account.getBalance());
    }

    @Test // Уменьшение баланса на сумму покупки
    public void shouldPayReduceBalance() {
        SavingAccount account = new SavingAccount(
                3_000,
                1_000,
                10_000,
                5
        );
        account.pay(1_000);

        Assertions.assertEquals(2_000, account.getBalance());

    }

    @Test //Расчет процентов на остаток суммы
    public void shouldYearChangeBalance() {
        SavingAccount account = new SavingAccount(
                1_000,
                1_000,
                10_000,
                5
        );
        account.yearChange();

        Assertions.assertEquals(50, account.yearChange());
    }

    @Test //Должно появиться сообщение об ошибке, накопительная ставка всегда положительная
    public void notSavingAccount() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    1_000,
                    1_000,
                    10_000,
                    -5
            );

        });


    }

}
