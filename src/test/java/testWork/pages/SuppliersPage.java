package testWork.pages;

import com.codeborne.selenide.Condition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class SuppliersPage extends BasePage {

    protected Logger logger = LogManager.getLogger(SuppliersPage.class.getName());

    private String advancedSearchBtn = "//a[@href='/poisk/poisk-223-fz/']/..";

    public void goToSectionAdvancedSearch() {
        $(By.xpath(advancedSearchBtn)).shouldBe(Condition.exist).click();
        logger.info("Перешли на страницу Расширенный поиск");
    }
}
