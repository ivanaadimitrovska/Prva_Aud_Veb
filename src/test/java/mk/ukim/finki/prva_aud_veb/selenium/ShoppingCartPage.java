package mk.ukim.finki.prva_aud_veb.selenium;

import lombok.Getter;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class ShoppingCartPage  extends AbstractPage{

    @FindBy(css = "tr[class=cart-item]")
    private List<WebElement> cartRows;

    public ShoppingCartPage(WebDriver driver) {
        super(driver);
    }

    public static ShoppingCartPage init(WebDriver webDriver){
        return PageFactory.initElements(webDriver, ShoppingCartPage.class);
    }

    public void assertElemts(int cartItemNumber) {
        Assert.assertEquals("rows do not match", cartItemNumber, this.getCartRows().size());

    }

}
