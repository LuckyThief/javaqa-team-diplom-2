package ru.netology.javaqadiplom;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

public class CreditAccountTest {

    @Test //Пополнение баланса на положительную сумму
    public void shouldAddToPositiveBalance() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.add(3_000);

        Assertions.assertEquals(3_000, account.getBalance());
    }

    @Test //Баланс не должен измениться, если сумма пополнения равна 0
    public void shouldBalanceNotChange() {
        CreditAccount account = new CreditAccount(
                3_000,
                5_000,
                15
        );

        account.add(0);

        Assertions.assertEquals(3_000, account.getBalance());
    }

    @Test //Должна пройти покупка, если баланс + лимит больше суммы покупки
    public void shouldPayIfAmountLessThanBalanceAndLimit() {
        CreditAccount account = new CreditAccount(
                3_000,
                6_000,
                15
        );

        account.pay(8_000);

        Assertions.assertEquals(1_000, account.getBalance());
    }

    @Test //Должна пройти покупка, если баланс + лимит равен сумме покупки
    public void shouldPayIfAmountEqualBalanceAndLimit() {
        CreditAccount account = new CreditAccount(
                3_000,
                5_000,
                15
        );

        account.pay(8_000);

        Assertions.assertEquals(-5_000, account.getBalance());
    }

    @Test //Не должна пройти покупка, если баланс + лимит меньше суммы покупки
    public void shouldNotPayIfAmountMoreThanBalanceAndLimit() {
        CreditAccount account = new CreditAccount(
                3_000,
                5_000,
                15
        );

        account.pay(8_001);

        Assertions.assertEquals(0_000, account.getBalance());
    }

    @Test //Не должна пройти покупка, если сумма покупки равна 0
    public void shouldNotPayIfAmountEqualZero() {
        CreditAccount account = new CreditAccount(
                3_000,
                5_000,
                15
        );

        account.pay(0);

        Assertions.assertEquals(3_000, account.getBalance());
    }

    @Test //Должна появиться процентная ставка, если баланс отрицательный
    public void shouldAppearRateWithNegativeBalance() {
        CreditAccount account = new CreditAccount(
                -200,
                5_000,
                15
        );

        account.yearChange();

        Assertions.assertEquals(-30, account.yearChange());
    }

    @Test //Не должна появиться процентная ставка, если баланс положительный
    public void shouldNotAppearRateWithPositiveBalance() {
        CreditAccount account = new CreditAccount(
                200,
                5_000,
                15
        );

        account.yearChange();

        Assertions.assertEquals(0, account.yearChange());
    }

    @Test //Не должна появиться процентная ставка, если баланс равен 0
    public void shouldNotAppearRateWithBalanceEqualZero() {
        CreditAccount account = new CreditAccount(
                0,
                5_000,
                15
        );

        account.yearChange();

        Assertions.assertEquals(0, account.yearChange());
    }

    @Test //Должно быть сообщение об ошибке, т.к. ставка не может быть отрицательной
    public void shouldNotCreateCreditAccountWithNegativeRate() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(0, 20_000, -20);
        });
    }

    @Test //Должно быть сообщение об ошибке, т.к. лимит не может быть отрицательным
    public void shouldNotCreateCreditAccountWithNegativeLimit() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(0, -5_000, 1);
        });
    }

    @Test //Должно быть сообщение об ошибке, т.к. баланс не может быть отрицательным при создании
    public void shouldNotCreateCreditAccountWithNegativeBalance() {

        Assertions.assertThrows(IllegalArgumentException.class, () -> {
            CreditAccount account = new CreditAccount(-1_000, 10_000, 10);
        });
    }
}

