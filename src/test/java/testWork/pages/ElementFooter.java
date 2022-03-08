package testWork.pages;

import com.codeborne.selenide.Condition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import static com.codeborne.selenide.Selenide.$;

public class ElementFooter {

    protected Logger logger = LogManager.getLogger(ElementFooter.class.getName());

    private String forSuppliers="//a[@href='/zakupki-223/participants-223']";

    public void goToSectionSuppliers(){
        $(By.xpath(forSuppliers)).shouldBe(Condition.exist).click();
        logger.info("Перешли на страницу Поставщикам");
    }

}
