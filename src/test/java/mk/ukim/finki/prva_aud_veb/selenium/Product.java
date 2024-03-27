package mk.ukim.finki.prva_aud_veb.selenium;

import lombok.Getter;
import org.junit.Assert;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.util.List;

@Getter
public class Product extends AbstractPage{

    @FindBy(css = "tr[class=product]")
    private List<WebElement> productRows;


    @FindBy(css = ".delete-product")
    private List<WebElement> deleteButtons;


    @FindBy(className = "edit-product")
    private List<WebElement> editButtons;


    @FindBy(css = ".cart-product")
    private List<WebElement> cartButtons;


    @FindBy(css = ".add-product-btn")
    private List<WebElement> addProductButton;

    public Product(WebDriver driver) {
        super(driver);
    }

    public static Product to(WebDriver driver) {
        get(driver, "/product");
        return PageFactory.initElements(driver, Product.class);
    }

    public void assertElemts(int productsNumber, int deleteButtons, int editButtons, int cartButtons, int addButtons) {
        Assert.assertEquals("rows do not match", productsNumber, this.getProductRows().size());
        Assert.assertEquals("delete do not match", deleteButtons, this.getDeleteButtons().size());
        Assert.assertEquals("edit do not match", editButtons, this.getEditButtons().size());
        Assert.assertEquals("cart do not match", cartButtons, this.getCartButtons().size());
        Assert.assertEquals("add is visible", addButtons, this.getAddProductButton().size());
    }

}
