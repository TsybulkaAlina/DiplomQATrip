package test;

import data.DatabaseHelper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import page.BuyPage;

import static com.codeborne.selenide.Selenide.open;

public class SuccessfulPaymentTest {
    @BeforeEach
    public void openSource() {
        open("http://localhost:8080");
        DatabaseHelper.deleteTable();
    }

    @Test
    @DisplayName("the buy form should be open") // Переход в режим "Купить"
    public void ShouldFormBuyCard() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
    }

    @Test
    @DisplayName("a credit purchase form should be open") // Переход в режим "Купить в кредит"
    public void ShouldFormBuyCredit() {
        var buyPage = new BuyPage();
        buyPage.BuyCreditCard();
    }
}