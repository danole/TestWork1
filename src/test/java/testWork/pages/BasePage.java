package testWork.pages;

import com.codeborne.selenide.ElementsCollection;
import org.openqa.selenium.By;

import java.io.FileWriter;
import java.io.IOException;

import static com.codeborne.selenide.Condition.visible;
import static com.codeborne.selenide.Selenide.$$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class BasePage {

    private String startingPrice = "//div[@itemprop='price']";

    public void collectDataAndWriterInFile(int number, FileWriter fileWriter) throws IOException {

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

            fileWriter.write("Карточка " + (i + number) + " : \n");
            fileWriter.write("Начальная цена : " + price.get(i - 1).getText() + "\n");

            for (int j = 0; j < cards.size(); j++) {
                fileWriter.write(cards.get(j).getText() + "\n");
            }

        }

    }

    public void deleteModalWindow(){

        executeJavaScript("document.getElementsByTagName('body')[0]" +
                ".removeChild(document.getElementsByClassName('modal-wrap consultation_modal')[0]);" +
                "document.getElementsByTagName('body')[0]" +
                ".removeChild(document.getElementsByClassName('modal-overlap modal-overlap--animation')[0]);");

    }

}
