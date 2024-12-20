package data;

import com.github.javafaker.Faker;
import lombok.Value;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

import static java.lang.Math.random;
import static java.lang.String.format;

public class CardDataHelper {
    public static final Faker faker = new Faker(new Locale("en"));

    private CardDataHelper() {
    }
    //  Заполнение поля "Номер карты"
    public static String getApprovedCardNumber() {
        return ("4444 4444 4444 4441");
    }

    public static String getDeclinedCardNumber() {
        return ("4444 4444 4444 4442");
    }

    //  Заполнение поля "Месяц"
    public static String getValidMonth() {
        LocalDate localDate = LocalDate.now();
        return format("%02d", localDate.getMonthValue());}

    //  Заполнение поля "Год"
    public static String getValidYear() {
        return LocalDate.now().plusYears(1).format(DateTimeFormatter.ofPattern("yy"));
    }
    // Заполнение поля "Владелец"
    public static String getValidHolder() {
        return faker.name().firstName();
    }

    // Заполнение поля "код CVC/CVV"
    public static String getValidCodcvccvv() {
        double x = random()*1000;
        int result = (int)Math.ceil(x);
        return String.valueOf(result);
    }

    // Данные карты
    @Value
    public static class CardInfo {
        String cardnumber;
        String month;
        String year;
        String holder;
        String codcvccvv;
    }

    // Заполнение поля "Номер карты" невалидными данными
    public static CardInfo getCardNumberWithSymbol() {
        return new CardInfo("44444444444444#1", getValidMonth(), getValidYear(), getValidHolder(), getValidCodcvccvv());
    }
    public static CardInfo getCardNumberWithCyrillic() {
        return new CardInfo("44444444444абв41", getValidMonth(), getValidYear(), getValidHolder(), getValidCodcvccvv());
    }
    public static CardInfo getCardNumberWithLatin() {
        return new CardInfo("12345678900qwe42", getValidMonth(), getValidYear(), getValidHolder(), getValidCodcvccvv());
    }
    public static CardInfo getCardNumberNull() {
        return new CardInfo("0000000000000000", getValidMonth(), getValidYear(), getValidHolder(), getValidCodcvccvv());
    }
    public static CardInfo getCardNumberEmpty() {
        return new CardInfo("", getValidMonth(), getValidYear(), getValidHolder(), getValidCodcvccvv());
    }
    public static CardInfo getCardNumberLess() {
        return new CardInfo("123456789", getValidMonth(), getValidYear(), getValidHolder(), getValidCodcvccvv());
    }

    // Заполнение поля "Месяц" невалидными данными
    public static CardInfo getMonthNonExisting() {
        return new CardInfo(getApprovedCardNumber(),"15", getValidYear(), getValidHolder(), getValidCodcvccvv());
    }
    public static CardInfo getMonthEmpty() {
        return new CardInfo(getApprovedCardNumber(),"", getValidYear(), getValidHolder(), getValidCodcvccvv());
    }
    public static CardInfo getMonthWithSymbol() {
        return new CardInfo(getApprovedCardNumber(),"7?", getValidYear(), getValidHolder(), getValidCodcvccvv());
    }
    public static CardInfo getMonthWithLetter() {
        return new CardInfo(getApprovedCardNumber(),"A4", getValidYear(), getValidHolder(), getValidCodcvccvv());
    }
    public static CardInfo getMonthUnformatted() {
        return new CardInfo(getApprovedCardNumber(),"9", getValidYear(), getValidHolder(), getValidCodcvccvv());
    }
    public static CardInfo getMonthOneNull() {
        return new CardInfo(getApprovedCardNumber(),"0", getValidYear(), getValidHolder(), getValidCodcvccvv());
    }
    public static CardInfo getMonthNullNull() {
        return new CardInfo(getApprovedCardNumber(),"00", getValidYear(), getValidHolder(), getValidCodcvccvv());
    }

    // Заполнение поля "Год" невалидными данными
    public static CardInfo getYearOneDigit() {
        return new CardInfo(getApprovedCardNumber(),getValidMonth(),"2", getValidHolder(), getValidCodcvccvv());
    }
    public static CardInfo getYearEmpty() {
        return new CardInfo(getApprovedCardNumber(),getValidMonth(),"", getValidHolder(), getValidCodcvccvv());
    }
    public static CardInfo getYearLessThanTheCurrentOne() {
        return new CardInfo(getApprovedCardNumber(),getValidMonth(),"23", getValidHolder(), getValidCodcvccvv());
    }
    public static CardInfo getTheYearIs10YearsLongerThanTheCurrentOne() {
        return new CardInfo(getApprovedCardNumber(),getValidMonth(),"35", getValidHolder(), getValidCodcvccvv());
    }
    public static CardInfo getYearWithLetter() {
        return new CardInfo(getApprovedCardNumber(),getValidMonth(),"2b", getValidHolder(), getValidCodcvccvv());
    }
    public static CardInfo getYearWithSymbol() {
        return new CardInfo(getApprovedCardNumber(),getValidMonth(),"2*", getValidHolder(), getValidCodcvccvv());
    }

    // Заполнение поля "Владелец" невалидными данными
    public static CardInfo getOwnerUseCyrillic() {
        return new CardInfo(getApprovedCardNumber(),getValidMonth(),getValidYear(),"Алина", getValidCodcvccvv());
    }
    public static CardInfo getOwnerMoreThan100Letters() {
        return new CardInfo(getApprovedCardNumber(),getValidMonth(),getValidYear(),"Petieeeaaaaaaaaaayyyyyytttteeeeeeeefffffrrriiiiiiyyyyuaaaaaaaaaaaaaaiiiiaaaaaaa", getValidCodcvccvv());
    }
    public static CardInfo getOwnerEmpty() {
        return new CardInfo(getApprovedCardNumber(),getValidMonth(),getValidYear(),"", getValidCodcvccvv());
    }
    public static CardInfo getOwnerWithDigit() {
        return new CardInfo(getApprovedCardNumber(),getValidMonth(),getValidYear(),"Pet4ia", getValidCodcvccvv());
    }
    public static CardInfo getOwnerOneLetter() {
        return new CardInfo(getApprovedCardNumber(),getValidMonth(),getValidYear(),"A", getValidCodcvccvv());
    }
    public static CardInfo getOwnerUseSymbol() {
        return new CardInfo(getApprovedCardNumber(),getValidMonth(),getValidYear(),"Pet$ia", getValidCodcvccvv());
    }

    // Заполнение поля "CVC/CVV" невалидными данными
    public static CardInfo getCVCCVVWithSymbol() {
        return new CardInfo(getApprovedCardNumber(),getValidMonth(),getValidYear(),getValidHolder(),"34%");
    }
    public static CardInfo getCVCCVV000() {
        return new CardInfo(getApprovedCardNumber(),getValidMonth(),getValidYear(),getValidHolder(),"000");
    }
    public static CardInfo getCVCCVVuseOneDigit() {
        return new CardInfo(getApprovedCardNumber(),getValidMonth(),getValidYear(),getValidHolder(),"5");
    }
    public static CardInfo getCVCCVVWithLetter() {
        return new CardInfo(getApprovedCardNumber(),getValidMonth(),getValidYear(),getValidHolder(),"12A");
    }
    public static CardInfo getCVCCVVEmpty() {
        return new CardInfo(getApprovedCardNumber(),getValidMonth(),getValidYear(),getValidHolder(),"");
    }
    public static CardInfo getCVCCVVuseTwoDigit() {
        return new CardInfo(getApprovedCardNumber(),getValidMonth(),getValidYear(),getValidHolder(),"12");
    }

}