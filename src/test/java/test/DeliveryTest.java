package test;

import data.DataGenerator;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.Keys;

import java.time.Duration;

import static com.codeborne.selenide.Condition.text;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.open;

public class DeliveryTest {
    @BeforeEach
    void setup() {
        open("http://localhost:9999");
    }
@Test
    @DisplayName("Should successful plan and replan meeting")
    void shouldSuccessfulPlanAndReplanMeeting() {
    var city = DataGenerator.generateCity();
    var name = DataGenerator.generateName();
    var phone = DataGenerator.generatePhone();

    var daysToAddForFirstMeeting = 4;
    var firstMeetingDate = DataGenerator.generateDate(daysToAddForFirstMeeting);
    var daysToAddForSecondMeeting = 7;
    var secondMeetingDate = DataGenerator.generateDate(daysToAddForSecondMeeting);

    //заполняем форму первый раз
    $("[data-test-id='city'] .input__control").setValue(city);
    $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
    $("[data-test-id='date'] input").setValue(firstMeetingDate);
    $("[data-test-id='name'] .input__control").setValue(name);
    $("[data-test-id='phone'] .input__control").setValue(phone);
    $("[data-test-id='agreement'] .checkbox__box").click();
    $(".button").click();
    $("[data-test-id='success-notification']")
            .shouldBe(visible, Duration.ofSeconds(15))
            .shouldHave(text("Успешно"));

    //меняем дату
    $("[data-test-id='date'] input").sendKeys(Keys.chord(Keys.SHIFT, Keys.HOME), Keys.DELETE);
    $("[data-test-id='date'] input").setValue(secondMeetingDate);
    $(".button").click();
    $("[data-test-id='replan-notification']")
            .shouldBe(visible, Duration.ofSeconds(15))
            .shouldHave(text("Необходимо подтверждение"));
    $("[data-test-id='replan-notification'] .button").click();
    $("[data-test-id='success-notification']")
            .shouldBe(visible, Duration.ofSeconds(15))
            .shouldHave(text("Успешно"));




}


}
