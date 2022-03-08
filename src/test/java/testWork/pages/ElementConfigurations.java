package testWork.pages;

import com.codeborne.selenide.Condition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import java.text.SimpleDateFormat;
import java.util.Date;

import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class ElementConfigurations extends BasePage {

    protected Logger logger = LogManager.getLogger(ElementConfigurations.class.getName());

    private String option615PPFR = "//label[contains(text(),'615-ПП РФ')]";
    private String optionExcludeJointPurchases = "//label[contains(text(),'Исключить совместные закупки')]";
    private String dateFiltersBtn = "//div[contains(text(),'Фильтры по датам')]";
    private String periodFrom = "//div[contains(text(),'ПОДАЧА ЗАЯВОК')]/../*[2]/*[1]/*/*/*";
    private String periodBefore = "//div[contains(text(),'ПОДАЧА ЗАЯВОК')]/../*[2]/*[3]/*/*/*";
    private String deliveryRegionBtn = "//div[contains(text(),'Регион поставки')]";
    private String territoryInput = "//input[@placeholder='Регион поставки']";
    private String firstResultInTerritoryInput = "//div[@class='cstm-search__dropdown']/*[1]/*[1]";
    private String findBtn = "//button[@class='search__btn bottomFilterSearch']";

    public String getNowDate() {
        Date dateNow = new Date();
        SimpleDateFormat formatForDateNow = new SimpleDateFormat("dd-MM-yyyy");
        formatForDateNow.format(dateNow);
        return formatForDateNow.format(dateNow);
    }

    public void clickOption615PPFR() {
        $(By.xpath(option615PPFR)).shouldHave(Condition.exist).click();
        logger.info("Нажали опцию 615ППФР");
    }

    public void clickOptionExcludeJointPurchases() {
        $(By.xpath(optionExcludeJointPurchases)).shouldHave(Condition.exist).click();
        logger.info("Нажали опцию Исключить совместные покупки");
    }

    public void clickDateFiltersBtn() {
        $(By.xpath(dateFiltersBtn)).shouldHave(Condition.exist).click();
        logger.info("Нажали Фильтр по датам");
    }

    public void inputDateInPeriodFrom(String date) {
        $(By.xpath(periodFrom)).shouldHave(Condition.exist).setValue(date);
        logger.info("Установили значение в период с");
    }

    public void inputDateInPeriodBefore(String date) {
        $(By.xpath(periodBefore)).shouldHave(Condition.exist).setValue(date);
        logger.info("Установили значение в период по");
    }

    public void clickDateInPeriodFrom() {
        $(By.xpath(periodFrom)).shouldHave(Condition.exist).click();
        logger.info("Нажали период с");
    }

    public void clickDateInPeriodBefore() {
        $(By.xpath(periodBefore)).shouldHave(Condition.exist).click();
        logger.info("Нажали период по");
    }

    public void hideModalWindow() {
        executeJavaScript("document.getElementsByClassName('react-datepicker__portal')[0].style.display='none';");
        logger.info("Убрали модальное окно");
    }

    public void clickDeliveryRegionBtn() {
        $(By.xpath(deliveryRegionBtn)).shouldHave(Condition.exist).click();
        logger.info("Нажали Регион поставки");
    }

    public void choiceTerritory(String string) {
        $(By.xpath(territoryInput)).shouldHave(Condition.exist).sendKeys(string);
        logger.info("Установили значение в Регион поставки");
    }

    public void clickFirstResultInTerritoryInput() {
        $(By.xpath(firstResultInTerritoryInput)).shouldHave(Condition.exist).click();
        logger.info("Выбрали первый результат в Регион поставки");
    }

    public void clickFindBtn() {
        $(By.xpath(findBtn)).shouldHave(Condition.exist).click();
        logger.info("Нажали Начать поиск");
    }

}
