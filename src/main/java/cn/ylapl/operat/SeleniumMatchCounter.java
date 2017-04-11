package cn.ylapl.operat;

import cn.ylapl.entity.YlCompanyQcc;
import cn.ylapl.mapper.YlCompanyQccMapper;
import com.google.common.base.Function;
import org.openqa.selenium.*;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * Created by Angle on 2017/4/10.
 */
public class SeleniumMatchCounter implements Runnable {

    //公司名
    private String companyName;

    private static List<Cookie> list;

    private RemoteWebDriver webDriver;

    private final YlCompanyQccMapper mapper;

    private static final Logger logger = LoggerFactory.getLogger(SeleniumOperat.class);

    static {

        list = new ArrayList<>();
        list.add(new Cookie("UM_distinctid","15b47e9ec2e720-03ddec25f707c1-317f0158-152ac0-15b47e9ec2f41c"));
        list.add(new Cookie("gr_user_id","d85b4bde-9698-4e55-9b1f-c9822a21b696"));
        list.add(new Cookie("_uab_collina","149156017551584659150686"));
        list.add(new Cookie("_umdata","85957DF9A4B3B3E877BBD074000136653A58256396A7985D0C59280EB886EA4859FAC6728212E5B8CD43AD3E795C914C2F7888978B49EF36F250B0264E01B03A"));
        list.add(new Cookie("acw_tc","AQAAAAMfCBNcOgIA68qBt7uIAFq+IvHC"));
        list.add(new Cookie("PHPSESSID","tem1e35cu264spfoq72kugnjq7"));
        list.add(new Cookie("_uab_collina","149156017551584659150686"));
        list.add(new Cookie("CNZZDATA1254842228","460960818-1491556889-https%253A%252F%252Fwww.baidu.com%252F%7C1491873360"));
        list.add(new Cookie("gr_session_id_9c1eb7420511f8b2","6e1df914-34bf-4025-a1b9-3b4eab59c67a"));
    }

    public SeleniumMatchCounter(String companyName, YlCompanyQccMapper mapper) {
        this.companyName = companyName;
        this.mapper = mapper;
    }

    private void saveCookie() {
        WebDriver.Options options = webDriver.manage();

        for (Cookie cookie : list) {
            options.addCookie(cookie);
        }
    }

    private void getHtml() {
        try {
            webDriver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"), DesiredCapabilities.chrome());
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }

        webDriver.get("http://www.qichacha.com/");
        saveCookie();


        YlCompanyQcc ylCompanyQcc = new YlCompanyQcc();

        //查找输入框
        WebElement searchInput = new WebDriverWait(webDriver, 10).until(
                (Function<WebDriver, WebElement>) webDriver12 ->
                        webDriver12.findElement(By.xpath("//*[@id=\"searchkey\"]")));
        searchInput.sendKeys(companyName);

        //点击查找按钮
        WebElement searchBtn = new WebDriverWait(webDriver, 10).until(
                (Function<WebDriver, WebElement>)webDriver1 ->
                        webDriver1.findElement(By.id("V3_Search_bt")));
        searchBtn.submit();

        //选择第一个企业
        WebElement firstCompany = new WebDriverWait(webDriver, 10).until(
                (Function<WebDriver, WebElement>)webDriver1 ->
                        webDriver1.findElement(By.xpath("//*[@id=\"searchlist\"]/table/tbody/tr[1]/td[2]/a")));
        firstCompany.click();

        //切换窗口
        Set<String> winHandels = webDriver.getWindowHandles();
        List<String> it = new ArrayList<>(winHandels);
        webDriver.switchTo().window(it.get(1));

        //工商信息
        ylCompanyQcc.setCompanyBusinessInformation(getInfo("//*[@id=\"Cominfo\"]/table"));

        //股东信息
        ylCompanyQcc.setCompanyShareholder(getInfo("//*[@id=\"Sockinfo\"]/table/tbody"));

        //主要人员
        ylCompanyQcc.setCompanyKeyPersonnel(getInfo("//*[@id=\"Mainmember\"]/div[3]/ul"));

        //分支机构
        ylCompanyQcc.setBranch(getInfo("//*[@id=\"V3_Subcom\"]"));

        //变更记录
        ylCompanyQcc.setCompanyChangeRecord(getInfo("//*[@id=\"Changelist\"]/table/tbody"));

        webDriver.findElementByXPath("//*[@id=\"susong_title\"]").click();

        //判决文书
        StringBuilder sb = new StringBuilder();
        page(sb, "#wenshulist > table > tbody", "#wenshulist > div.col-md-12 > nav > ul > li.active + li > a", webDriver);
        ylCompanyQcc.setDocuments(sb.toString());

        //法院公告
        ylCompanyQcc.setCourtNotice(getInfo("//*[@id=\"gonggaolist\"]/table/tbody"));

        webDriver.findElementByXPath("//*[@id=\"run_title\"]").click();

        //税务信息
        ylCompanyQcc.setTax(getInfo("//*[@id=\"taxCreditList\"]/table/tbody"));

        //产品信息
        ylCompanyQcc.setProduct(getInfo("//*[@id=\"productlist\"]/table/tbody"));

        //融资信息
        ylCompanyQcc.setFinancing(getInfo("//*[@id=\"financingList\"]/table/tbody"));

        //财务总览
        ylCompanyQcc.setFinancial(getInfo("//*[@id=\"V3_cwzl\"]/table/tbody"));

        webDriver.findElementByXPath("//*[@id=\"touzi_title\"]").click();

        //对外投资
        StringBuilder sb1 = new StringBuilder();
        page(sb1, "#touzilist > ul", "#touzilist > nav > ul > li.active + li > a", webDriver);
        ylCompanyQcc.setOutbound(sb1.toString());

        webDriver.findElementByXPath("//*[@id=\"report_title\"]").click();

        //企业年报
        ylCompanyQcc.setAnnual(getInfo("//*[@id=\"report_div\"]/section[1]/div[2]"));

        webDriver.findElementByXPath("//*[@id=\"assets_title\"]").click();

        //商标信息
        StringBuilder sb2 = new StringBuilder();
        page(sb2, "#shangbiaolist > div:nth-child(3) > table > tbody", "#shangbiaolist > div:nth-child(3) > div > nav > ul > li.active + li > a", webDriver);
        ylCompanyQcc.setTrademark(getInfo("//*[@id=\"Sockinfo\"]/table/tbody"));

        mapper.insert(ylCompanyQcc);

        webDriver.quit();
    }

    private void page(StringBuilder sb, String cssSelector, String cssSelectorActive, RemoteWebDriver webDriver) {
        while (true) {
            WebElement wb5;

            try {
                wb5 = new WebDriverWait(webDriver, 5).until(
                        (Function<WebDriver, WebElement>)webDriver1 ->
                                webDriver1.findElement(By.cssSelector(cssSelector)));

                sb.append(wb5.getText());
            } catch (TimeoutException e) {
                logger.error("未找到某个");
                break;
            }


            WebElement wb6;
            try {
                wb6 = new WebDriverWait(webDriver, 5).until(
                        (Function<WebDriver, WebElement>)webDriver1 ->
                                webDriver1.findElement(By.cssSelector(cssSelectorActive)));
                wb6.click();
            } catch (TimeoutException e) {
                break;
            }
        }
    }

    private String getInfo(String xpath) {
        try {
            WebElement wb3 = new WebDriverWait(webDriver, 5).until(
                    (Function<WebDriver, WebElement>)webDriver1 ->
                            webDriver1.findElement(By.xpath(xpath)));

            return wb3.getText();

        } catch (TimeoutException e) {
            logger.error("未获取到某个信息");
            return null;
        }
    }

    @Override
    public void run() {
        getHtml();
    }
}
