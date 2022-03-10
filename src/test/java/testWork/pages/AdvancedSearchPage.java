package testWork.pages;

import com.codeborne.selenide.Condition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class AdvancedSearchPage extends BasePage {

    protected Logger logger = LogManager.getLogger(AdvancedSearchPage.class.getName());

    private String configurationsBtn = "//span[@class='filter__open-modal']";

    public void goToSectionConfigurations() {
        $(By.xpath(configurationsBtn)).shouldBe(Condition.exist).click();
        logger.info("Перешли на страницу Настройки");
    }
}
