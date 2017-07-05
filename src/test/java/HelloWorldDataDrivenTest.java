import static com.github.webdriverextensions.Bot.assertCurrentUrlContains;
import static com.github.webdriverextensions.Bot.assertTextContains;
import static com.github.webdriverextensions.Bot.click;
import static com.github.webdriverextensions.Bot.open;
import static com.github.webdriverextensions.Bot.type;
import static com.github.webdriverextensions.Bot.waitFor;
import static java.util.concurrent.TimeUnit.SECONDS;
import junit.framework.Assert;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import com.github.webdriverextensions.junitrunner.annotations.Chrome;
import com.github.webdriverextensions.junitrunner.annotations.InternetExplorer;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
//note that data provider runner extends webdriver runner in turn
@RunWith(DataProviderRunner.class)
@Chrome
@InternetExplorer
public class HelloWorldDataDrivenTest {
	
    @DataProvider
    public static Object[][] searchKeywords() {
        // @formatter:off
        return new Object[][] {
                { "Arjun Khatri" },
                { "Colonel" },
                { "Swapnil Shrikhande" }
        };
    }
	
    @FindBy(name = "q")
    WebElement queryInput;
    @FindBy(name = "btnG")
    WebElement searchButton;
    @FindBy(id = "search")
    WebElement searchResult;

    @Test
    @UseDataProvider("searchKeywords")
    public void searchGoogleForHelloWorldTest(String keyWord) {
    	
    	System.out.println("Inside searchGoogleForHelloWorldTest keyword="+keyWord);
    	
        open("http://www.google.com");
        assertCurrentUrlContains("google");
        
        type(keyWord, queryInput);
        click(searchButton);

        waitFor(3, SECONDS);
        assertTextContains(keyWord, searchResult);
    }
}
