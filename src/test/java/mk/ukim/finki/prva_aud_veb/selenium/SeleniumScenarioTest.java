package mk.ukim.finki.prva_aud_veb.selenium;

import mk.ukim.finki.prva_aud_veb.model.Category;
import mk.ukim.finki.prva_aud_veb.model.Manufacturer;
import mk.ukim.finki.prva_aud_veb.model.User;
import mk.ukim.finki.prva_aud_veb.model.enumCreations.Role;
import mk.ukim.finki.prva_aud_veb.service.CategoryService;
import mk.ukim.finki.prva_aud_veb.service.ManufacturerService;
import mk.ukim.finki.prva_aud_veb.service.ProductService;
import mk.ukim.finki.prva_aud_veb.service.UserService;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.openqa.selenium.htmlunit.HtmlUnitDriver;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;

@ActiveProfiles("test")
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.DEFINED_PORT)
public class SeleniumScenarioTest {

    @Autowired
    UserService userService;

    @Autowired
    ManufacturerService manufacturerService;

    @Autowired
    CategoryService categoryService;


    @Autowired
    ProductService productService;

    private HtmlUnitDriver htmlUnitDriver;
    private static Category c1;
    private static Category c2;
    private static Manufacturer m1;
    private static Manufacturer m2;
    private static User regularUser;
    private static User adminUser;
    private static boolean dataInitialized = false;
    private static String user = "user";
    private static String admin = "admin";

    @BeforeEach
    public void setUp(){
        htmlUnitDriver=new HtmlUnitDriver(true);
        initData();
    }

    @AfterEach
    public void destroy(){
        if(this.htmlUnitDriver!=null){
            this.htmlUnitDriver.close();
        }
    }

    public void initData(){
        if (!dataInitialized) {
            c1 = categoryService.create("c1", "c1");
            c2=categoryService.create("c2", "c2");

            m1 = manufacturerService.save("m1", "m1").get();
            m2=manufacturerService.save("m2", "m2").get();

            regularUser=userService.register(user, user, user, user, user, Role.ROLE_USER);
            adminUser=userService.register(admin, admin, admin, admin, admin, Role.ROLE_ADMIN);
            dataInitialized = true;
        }
    }

    @Test
    public void testScenario() throws Exception{
        Product productsPage=Product.to(htmlUnitDriver);
        productsPage.assertElemts(0,0,0,0,0);
        Login login=Login.openLogin(htmlUnitDriver);
        productsPage=Login.doLogin(htmlUnitDriver, login, adminUser.getUsername(), admin);
        productsPage.assertElemts(0,0,0,0,1);
        productsPage=AddOrEditProduct.addProduct(htmlUnitDriver, "test", "100", "5", c2.getName(), m2.getName());
        productsPage.assertElemts(1,1,1,1,1);
        productsPage=AddOrEditProduct.addProduct(htmlUnitDriver, "test1", "200", "4", c1.getName(), m2.getName());
        productsPage.assertElemts(2,2,2,2,1);
        productsPage.getDeleteButtons().get(1).click();
        productsPage.assertElemts(1,1,1,1,1);
        productsPage=AddOrEditProduct.editProduct(htmlUnitDriver,productsPage.getEditButtons().get(0),"test1", "200", "4", c1.getName(), m2.getName());
        productsPage.assertElemts(1,1,1,1,1);

        login=Login.logout(htmlUnitDriver);
        productsPage=Login.doLogin(htmlUnitDriver, login, regularUser.getUsername(), user);
        productsPage.assertElemts(1,0,0,1,0);
        productsPage.getCartButtons().get(0).click();
        Assert.assertEquals("url do not match", "http://localhost:9998/shop-cart", htmlUnitDriver.getCurrentUrl());
        //Assertions.assertEquals("http://localhost:9999/shop-cart", htmlUnitDriver.getCurrentUrl(), "url do not match");

        ShoppingCartPage cartPage = ShoppingCartPage.init(htmlUnitDriver);
        cartPage.assertElemts(1);
    }
}
