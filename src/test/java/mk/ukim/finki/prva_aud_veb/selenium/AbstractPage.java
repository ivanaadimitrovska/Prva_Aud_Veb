package mk.ukim.finki.prva_aud_veb.selenium;

import lombok.Getter;
import org.openqa.selenium.WebDriver;

@Getter
public class AbstractPage {

    WebDriver webDriver;

    public AbstractPage(WebDriver webDriver) {
        this.webDriver = webDriver;
    }

    static void get(WebDriver driver, String relativeUrl){
        String url = System.getProperty("geb.build.baseUrl", "http://localhost:9998") + relativeUrl;
        driver.get(url);
    }
}
