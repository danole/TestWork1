package testWork.pages;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.openqa.selenium.By;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.time.Duration;

import static com.codeborne.selenide.Condition.*;
import static com.codeborne.selenide.Selenide.$;
import static com.codeborne.selenide.Selenide.executeJavaScript;

public class ResultsPage extends BasePage {

    protected Logger logger = LogManager.getLogger(ResultsPage.class.getName());

    private String countCards = "//span[@id='Notifications']/*/*[1]";
    private String loader = "//div[@class='loader']";

    public void collectAllData() throws IOException {

        $(By.xpath(loader)).should(disappear, Duration.ofSeconds(15));

        int parseCount = Integer.parseInt($(By.xpath(countCards)).shouldBe(visible, Duration.ofSeconds(15)).getText());
        double parseCountDouble = ((double) parseCount) / 10;
        double fullParseCount = Math.ceil(parseCountDouble);
        int countPages = (int) fullParseCount;

        File outFile = new File("output.txt");
        FileWriter fileWriter = new FileWriter(outFile, false);

        deleteModalWindow();

        if (countPages > 1) {
            for (int c = 2; c <= countPages; c++) {
                collectDataAndWriterInFile(((c - 2) * 10), fileWriter);
                $(By.xpath("//a[@class='page-link next']")).shouldBe(exist).click();
                $(By.xpath(loader)).should(disappear, Duration.ofSeconds(15));
            }

            $(By.xpath(loader)).should(disappear, Duration.ofSeconds(15));
            collectDataAndWriterInFile((countPages - 1) * 10, fileWriter);

        } else if (countPages == 1) {
            collectDataAndWriterInFile(0, fileWriter);
        } else {
            logger.error("Результатов поиска нет!");
        }

        fileWriter.close();
        logger.info("Записали данные в файл output.txt");

    }

}
