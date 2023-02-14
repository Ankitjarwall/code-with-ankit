import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;

public class webscrap{
    public static void main(String[] args)
    {
        System.setProperty("webdriver.chrome.driver","F:/Code-with-Ankit/web/WEB SCRAPING/chromedriver.exe");
        WebDriver driver=new chromedriver();    
        driver.manage().window().maximize();
        // Launch Website
        driver.get("https://www.geeksforgeeks.org/");
    }
}