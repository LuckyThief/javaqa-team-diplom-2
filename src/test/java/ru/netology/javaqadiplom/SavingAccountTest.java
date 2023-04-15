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

        account.add(3_000);

        Assertions.assertEquals( 3_000, account.getBalance());
    }

    @Test // Уменьшение баланса на сумму покупки
    public void shouldPayReduceBalance() {
        SavingAccount account = new SavingAccount(
                3_000,
                1_000,
                10_000,
                5
        );
        account.pay(2_000);

        Assertions.assertEquals(1_000, account.getBalance());

    }

    @Test // Уменьшение баланса на сумму покупки, баланс не может быть отрицательным
    public void shouldNotPayReduceBalance() {
        SavingAccount account = new SavingAccount(
                3_000,
                1_000,
                10_000,
                5
        );
        account.pay(6_000);

        Assertions.assertEquals(3_000, account.getBalance());

    }

    @Test //Расчет процентов на остаток суммы
    public void shouldYearChangeBalance() {
        SavingAccount account = new SavingAccount(
                200,
                1_000,
                10_000,
                15
        );
        account.yearChange();

        Assertions.assertEquals(0, account.yearChange());
    }

    @Test //Расчет процентов на остаток суммы. остаток суммы не может быть меньше minBalance.
    public void shouldNotYearChangeBalance() {
        SavingAccount account = new SavingAccount(
                2_000,
                1_000,
                10_000,
                15
        );
        account.yearChange();

        Assertions.assertEquals(300, account.yearChange());
    }

    @Test //Должно появиться сообщение об ошибке, накопительная ставка всегда положительная
    public void SavingAccount() {
        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            SavingAccount account = new SavingAccount(
                    11_000,
                    1_000,
                    10_000,
                    -5
            );

        });

    }
}
