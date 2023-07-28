
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.SetValueOptions.withText;
import static com.codeborne.selenide.Selenide.$;

public class CardDeliveryTest {
    private String generateDate (int addDays, String pattern) {//String, потому что мы в результате получаем строку с датой
        return LocalDate.now().plusDays(addDays).format(DateTimeFormatter.ofPattern(pattern));
    }
    @Test
    void shouldTestCardDeliveryForm () {
        open("http://localhost:9999/");
        $("[data-test-id = city] input").setValue("Влад");//когда нужно значение вводить в поле, то пишем input
        $$(".menu-item__control").findBy(text ("Владивосток")).click();// не забыть кликнуть по городу
        $("[data-test-id = date] input").sendKeys (Keys.chord(Keys.SHIFT, Keys.HOME, Keys.DELETE)); //удаляем текущую дату с сайта, кторая стоит по умолчанию
       String currentDate = generateDate (4, "dd.MM.yyyy"); //задаем переменную с нужными параметрами
        $("[data-test-id = date] input").sendKeys(currentDate); // заполняем поле "Дата" текущей датой. Использовать setValue не можем, тк это не хардкод
        $("[data-test-id = name] input").setValue("Юлия Максимова");
        $("[data-test-id = phone] input").setValue("+79166065820");
        $("[data-test-id = agreement]").click();
        $(".button").click();
        $(".notification__content")
                .shouldBe(visible, Duration.ofSeconds(15))
                    .shouldHave(exactText("Встреча успешно забронирована на " + currentDate));
    }

    }

