
import org.junit.jupiter.api.Test;
import java.time.Duration;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.*;
import static com.codeborne.selenide.SetValueOptions.withText;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Condition.exactText;

public class CardDeliveryTest {
    @Test
    void shouldTestCardDeliveryForm() {
        open("http://localhost:9999/");
            $("[data-test-id = city] input").setValue("Владивосток"); //когда нужно значение вводить в поле, то пишем input
            $("[data-test-id = date] input").setValue("02.08.2023");
            $$(".input__control").first().setValue("Юлия Максимова");
            $("[name=phone] input").setValue("+79166065820");
            $("[data-test-id = agreement]").click();
            $("button").click();
            $(".notification").shouldHave(exactText ("Успешно! Ваша встреча успешно забронирована на 02.08.2023"), Duration.ofSeconds(10));
       // $ (String.valueOf(withText("Встреча успешно забронирована"))).shouldBe(visible, Duration.ofSeconds(10));
    }
}
