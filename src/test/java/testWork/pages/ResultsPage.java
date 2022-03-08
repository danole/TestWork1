package testWork.pages;

import com.codeborne.selenide.Condition;
import com.codeborne.selenide.ElementsCollection;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.$$;

public class ResultsPage {

    protected Logger logger = LogManager.getLogger(ResultsPage.class.getName());

    private String card = "//div[@class='cards'][1]/*[2]/*/*[7]//*[1]/*/*/*/*[3]";
    private String startingPrice = "//div[@itemprop='price']";
    private String numberOfUnits = "//div[@class='card-item']/*[7]/*[1]/*/*/*/*[3]";
    private String showMore = "//span[@class='more-position show-more']";
    private String countCards = "//span[@id='Notifications']/*/*[1]";

    public void collectAllData() throws IOException {

        int parseCount = Integer.parseInt($(By.xpath(countCards)).should(matchText("\\w")).getText());
        double parseCountDouble = ((double) parseCount) / 10;
        double fullParseCount = Math.ceil(parseCountDouble);
        int countPages = (int) fullParseCount;

        File outFile = new File("output.txt");
        FileWriter fileWriter = new FileWriter(outFile, false);

        for (int c = 2; c <= countPages; c++) {

            ElementsCollection hidden = $$(By.xpath("//span[@class='more-position show-more']"));

            for (int i = 0; i < hidden.size(); i++) {
                if (hidden.get(i).isDisplayed()) {
                    hidden.get(i).shouldBe(visible).click();
                }
            }

            ElementsCollection card = $$(By.xpath("//div[@class='card-item']"));
            ElementsCollection price = $$(By.xpath(startingPrice));

            for (int i = 1; i <= card.size(); i++) {

                ElementsCollection cards = $$(By.xpath("//div[@class='cards'][" + i + "]/*[2]/*/*[7]//*[1]/*/*/*/*[3]"));

                System.out.println("Карточка " + (i + ((c - 2) * 10)) + " : ");
                fileWriter.write("Карточка " + (i + ((c - 2) * 10)) + " : \n");
                System.out.println("Начальная цена : " + price.get(i - 1).getText());
                fileWriter.write("Начальная цена : " + price.get(i - 1).getText() + "\n");

                for (int j = 0; j < cards.size(); j++) {
                    if (!cards.get(j).should().isDisplayed()) {
                        continue;
                    }
                    fileWriter.write(cards.get(j).getText() + "\n");
                    System.out.println(cards.get(j).getText());
                }

                card = $$(By.xpath("//div[@class='card-item']"));

            }

            if (c == countPages + 1) {
                continue;
            }

            $(By.xpath("//a[@href='?page=" + c + "' and text()='" + c + "']")).shouldHave(Condition.exist).click();

        }

        fileWriter.close();


    }

}
