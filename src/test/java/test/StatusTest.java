package test;

import com.codeborne.selenide.logevents.SelenideLogger;
import data.CardDataHelper;
import data.DatabaseHelper;
import io.qameta.allure.selenide.AllureSelenide;
import org.junit.jupiter.api.*;
import page.PaymentPage;
import page.BuyPage;

import static com.codeborne.selenide.Selenide.open;
import static data.CardDataHelper.*;
import static org.junit.jupiter.api.Assertions.assertEquals;

public class StatusTest {

    @BeforeAll
    static void setUpAll() {
        SelenideLogger.addListener("allure", new AllureSelenide());
    }

    @AfterAll
    static void tearDownAll() {
        SelenideLogger.removeListener("allure");
    }

    @BeforeEach
    public void openSource() {
        open("http://localhost:8080");
        DatabaseHelper.deleteTable();
    }

    @Test
    @DisplayName("Successful card payment must be approved")
    void theCardPaymentMustBeApproved() {
        var cardinfo = new CardDataHelper.CardInfo(getApprovedCardNumber(), getValidMonth(), getValidYear(), getValidHolder(), getValidCodcvccvv());
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(cardinfo);
        form.paymentSuccessfull();
        assertEquals("APPROVED", DatabaseHelper.getPaymentStatus());
    }

    @Test
    @DisplayName("Card payment must be declined")
    void theCardPaymentMustBeDeclined() {
        var cardinfo = new CardDataHelper.CardInfo(getDeclinedCardNumber(), getValidMonth(), getValidYear(), getValidHolder(), getValidCodcvccvv());
        var buyPage = new BuyPage();
        buyPage.BuyCreditCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(cardinfo);
        form.declinedPayment();
        assertEquals("DECLINED", DatabaseHelper.getPaymentStatus());
    }

    // Если  вся форма пустая
    @Test
    @DisplayName("The form must be filled in")
    void theCardPaymentEmpty() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.emptyForm();
    }

    // Если в номере карты сожержатся символы
    @Test
    public void theCardSymbol() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getCardNumberWithSymbol());
        form.invalidCardFormat();
    }

    // Если в номере карты содержатся буквы кириллицы
    @Test
    public void theCardCyrillic() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getCardNumberWithCyrillic());
        form.invalidCardFormat();
    }

    // Если в номере карты содержатся буквы латиницы
    @Test
    public void theCardLatinalphabet() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getCardNumberWithLatin());
        form.invalidCardFormat();
    }

    // Если номер карты состоит из нулей
    @Test
    public void theCardIsNull() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getCardNumberNull());
        form.declinedPayment();
    }

    // Поле с номером карты пустое
    @Test
    public void theCardEmpty() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getCardNumberEmpty());
        form.invalidCardFormat();
    }

    // Если номер карты меньше чем 16 цифр
    @Test
    public void theCardLess16() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getCardNumberLess());
        form.invalidCardFormat();
    }

    // Если указать месяц больше чем 12
    @Test
    public void monthMoreThan12() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getMonthNonExisting());
        form.invalidCardExpirationDate();
    }

    // Если поле месяц пустое
    @Test
    public void emptyMonth() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getMonthEmpty());
        form.monthNotValid();
    }

    // Если поле месяц содежит символ
    @Test
    public void SymbolMonth() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getMonthWithSymbol());
        form.monthNotValid();
    }

    // Если поле месяц содежит букву
    @Test
    public void letterMonth() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getMonthWithLetter());
        form.monthNotValid();
    }

    // Если в поле месяц указать неформатное значение
    @Test
    public void unformattedMonth() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getMonthUnformatted());
        form.monthNotValid();
    }

    // Если указать в поле месяц 0
    @Test
    public void zeroMonth() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getMonthOneNull());
        form.monthNotValid();
    }

    // Если в поле месяц ввести 00
    @Test
    public void MonthTwoZeros() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getMonthNullNull());
        form.monthNotValid();
    }


    // Если в поле год указать значение меньше текущего года
    @Test
    public void lessThanCurrentOneYear() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getYearLessThanTheCurrentOne());
        form.theСardExpired();
    }

    // Если в поле год указать значение больше текущего года на 10 лет
    @Test
    public void yearLongerThanTheCurrentOne() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getTheYearIs10YearsLongerThanTheCurrentOne());
        form.invalidCardExpirationDate();
    }

    // Если поле год содежржит символ
    @Test
    public void yearNotValid() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getYearWithSymbol());
        form.yearNotValid();
    }

    // Если поле год содержит букву
    @Test
    public void yearNotValidLetter() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getYearWithLetter());
        form.yearNotValid();
    }

    // Если поле год неформат
    @Test
    public void yearNotValidOne() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getYearOneDigit());
        form.yearNotValid();
    }

    // Если поле год пустое
    @Test
    public void yearNotValidEmpty() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getYearEmpty());
        form.yearNotValid();
    }

    // Если в поле владелец ввести кириллицу
    @Test
    public void whenEnteringTheOwnerInCyrillic() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getOwnerUseCyrillic());
        form.ownerNotValid();
    }

    // Если в поле владелец ввести символы
    @Test
    public void theOwnerFieldCharacters() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getOwnerUseSymbol());
        form.ownerNotValid();
    }

    // Если поле владелец ввести цифры
    @Test
    public void theOwnerFieldDigit() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getOwnerWithDigit());
        form.ownerNotValid();
    }

    // Если в поле владелец ввести только 1 букву
    @Test
    public void EnteringOnlyOneLetterInTheOwner() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getOwnerOneLetter());
        form.ownerNotValid();
    }

    // Ввод в поле владелец более 100 букв
    @Test
    public void EnteringMoreThan100CharactersInTheOwnerField() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getOwnerMoreThan100Letters());
        form.ownerNotValid();
    }

    // Поле владелец пустое
    @Test
    public void TheOwnerFieldIsEmpty() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getOwnerEmpty());
        form.ownerNotValid();
    }

    // Ввод в поле CVCCVV символы
    @Test
    public void InTheCVCCVVFieldTheCharacters() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getCVCCVVWithSymbol());
        form.cvcNotValid();
    }

    // Ввод в поле CVCCVV буквы
    @Test
    public void InTheCVCCVVFieldTheLetters() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getCVCCVVWithLetter());
        form.cvcNotValid();
    }

    // Ввод в поле CVCCVV только одну цифру
    @Test
    public void ThereIsOnlyOneDigitInTheCVCCVVField() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getCVCCVVuseOneDigit());
        form.cvcNotValid();
    }

    // Ввод в поле CVCCVV только двух цифр
    @Test
    public void ThereIsOnlyTwoDigitInTheCVCCVVField() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getCVCCVVuseTwoDigit());
        form.cvcNotValid();
    }

    // Незаполненное поле CVCCVV
    @Test
    public void TheCVCCVVFieldIsEmpty() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getCVCCVVEmpty());
        form.cvcNotValid();
    }

    // Поле CVCCVV содержит 000
    @Test
    public void TheCVCCVVHave000() {
        var buyPage = new BuyPage();
        buyPage.BuyCard();
        var form = new PaymentPage();
        form.fillingOutTheForm(CardDataHelper.getCVCCVV000());
        form.cvcNotValid();
    }
}