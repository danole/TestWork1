package testWork.steps;

import com.codeborne.selenide.Configuration;
import io.cucumber.java.ru.И;
import testWork.pages.*;

import java.io.IOException;

import static com.codeborne.selenide.Selenide.open;

public class UISteps {

    @И("^открыли сайт \"([^\"]*)\"$")
    public void openUrl(String url) {
        Configuration.browserSize = "1920x1080";
        open(url);
    }

    @И("^перешли в footer в раздел Поставщикам$")
    public void goToSuppliers() {
        ElementFooter elementFooter = new ElementFooter();
        elementFooter.goToSectionSuppliers();
    }

    @И("^перешли в расширенный поиск$")
    public void goToAdvancedSearch() {
        SuppliersPage suppliersPage = new SuppliersPage();
        suppliersPage.goToSectionAdvancedSearch();
    }

    @И("^перешли в настройки$")
    public void goToConfigurations() {
        AdvancedSearchPage advancedSearchPage = new AdvancedSearchPage();
        advancedSearchPage.goToSectionConfigurations();
    }

    @И("^выбрали 615-ПП ФР$")
    public void enableOption615PPFR() {
        ElementConfigurations elementConfigurations = new ElementConfigurations();
        elementConfigurations.clickOption615PPFR();
    }

    @И("^выбрали Исключить совместные закупки$")
    public void enableOptionExcludeJointPurchases() {
        ElementConfigurations elementConfigurations = new ElementConfigurations();
        elementConfigurations.clickOptionExcludeJointPurchases();
    }

    @И("^нажали Фильтры по датам$")
    public void goToDateFiltersBtn() {
        ElementConfigurations elementConfigurations = new ElementConfigurations();
        elementConfigurations.clickDateFiltersBtn();
    }

    @И("^ввели в поле \"Подача заявок с,по\" текущую дату$")
    public void inputCurrentDateInPeriodFrom() {
        ElementConfigurations elementConfigurations = new ElementConfigurations();
        elementConfigurations.clickDateInPeriodFrom();
        elementConfigurations.hideModalWindow();
        elementConfigurations.inputDateInPeriodFrom(elementConfigurations.getNowDate());
        elementConfigurations.clickDateInPeriodBefore();
        elementConfigurations.hideModalWindow();
//        elementConfigurations.inputDateInPeriodBefore(elementConfigurations.getNowDate());
        elementConfigurations.inputDateInPeriodBefore("09-03-2022");
    }

    @И("^нажали Регион поставки$")
    public void clickDeliveryRegion() {
        ElementConfigurations elementConfigurations = new ElementConfigurations();
        elementConfigurations.clickDeliveryRegionBtn();
    }

    @И("^выбрали \"([^\"]*)\"$")
    public void clickChoiceTerritory(String territory) {
        ElementConfigurations elementConfigurations = new ElementConfigurations();
        elementConfigurations.choiceTerritory(territory);
        elementConfigurations.clickFirstResultInTerritoryInput();
    }

    @И("^нажали на кнопку найти$")
    public void clickButtonFind() {
        ElementConfigurations elementConfigurations = new ElementConfigurations();
        elementConfigurations.clickFindBtn();
    }

    @И("^собираем начальная цена и количество закупок на всех карточках и записываем в файл$")
    public void collectAllStartingPriceAndNumber() throws InterruptedException, IOException {
        ResultsPage resultsPage = new ResultsPage();
        Thread.sleep(1000);
        resultsPage.collectAllData();

    }


}
