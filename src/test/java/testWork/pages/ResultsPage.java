package testWork.pages;

import com.codeborne.selenide.Condition;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

import static com.codeborne.selenide.Condition.matchText;
import static com.codeborne.selenide.Selenide.$;

public class ResultsPage extends BasePage {

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

        if (countPages > 1) {
            for (int c = 2; c <= countPages; c++) {

                collectDataAndWriterInFile((c - 2) * 10);

                $(By.xpath("//a[@href='?page=" + c + "' and text()='" + c + "']")).shouldHave(Condition.exist).click();

            }

            $(By.xpath("//a[@href='?page=" + countPages + "' and text()='" + countPages + "']")).shouldHave(Condition.exist).click();

            collectDataAndWriterInFile(countPages * 10);


        } else {

            collectDataAndWriterInFile(0);

        }


        fileWriter.close();


    }


}
