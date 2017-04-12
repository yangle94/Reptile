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

    private static List<Cookie> list1;

    private static List<Cookie> list2, list3;

    private RemoteWebDriver webDriver;

    private String id;

    private final YlCompanyQccMapper mapper;

    private static final Logger logger = LoggerFactory.getLogger(SeleniumOperat.class);

    static {

        list1 = new ArrayList<>();
        list2 = new ArrayList<>();
        list3 = new ArrayList<>();

        list3.add(new Cookie("UM_distinctid","15b623d5a851096-023b474d729014-396a7807-13c680-15b623d5a866e9"));
        list3.add(new Cookie("gr_user_id","67b0aada-4a9c-4c04-8d2b-79dcedc1d289"));
        list3.add(new Cookie("_uab_collina","149200184840012091234149"));
        list3.add(new Cookie("_umdata","A502B1276E6D5FEFE172818A53763A853A58256396A7985DA6735D5D7B4CCD1AF23F5C6932E729E2CD43AD3E795C914CD0E5D6FF5BFC78C00A68946D8FDCB60B"));
        list3.add(new Cookie("acw_tc","AQAAAGkItUk9jgAADzRafAEnSeibcQIF"));
        list3.add(new Cookie("PHPSESSID","f5j415j3ic330uc2us61qt8hu3"));
        list3.add(new Cookie("_uab_collina","149156017551584659150686"));
        list3.add(new Cookie("CNZZDATA1254842228","1805840269-1491996685-%7C1491996685"));
        list3.add(new Cookie("gr_session_id_9c1eb7420511f8b2","ef0b2a37-0095-4811-a27c-51fba642a93c"));

        list1.add(new Cookie("UM_distinctid","15b47e9ec2e720-03ddec25f707c1-317f0158-152ac0-15b47e9ec2f41c"));
        list1.add(new Cookie("gr_user_id","d85b4bde-9698-4e55-9b1f-c9822a21b696"));
        list1.add(new Cookie("_uab_collina","149156017551584659150686"));
        list1.add(new Cookie("_umdata","85957DF9A4B3B3E877BBD074000136653A58256396A7985D0C59280EB886EA4859FAC6728212E5B8CD43AD3E795C914C2F7888978B49EF36F250B0264E01B03A"));
        list1.add(new Cookie("acw_tc","AQAAAG4/8lF6FQQADzRafL2usN6c/jQK"));
        list1.add(new Cookie("PHPSESSID","tem1e35cu264spfoq72kugnjq7"));
        list1.add(new Cookie("_uab_collina","149156017551584659150686"));
        list1.add(new Cookie("CNZZDATA1254842228","460960818-1491556889-https%253A%252F%252Fwww.baidu.com%252F%7C1491910247"));
        list1.add(new Cookie("gr_session_id_9c1eb7420511f8b2","9d7dc23e-4165-47a7-b85b-b9eb3d0d3084"));

        list2.add(new Cookie("UM_distinctid","15b57aa5aee729-0f3212c6e5d864-396a7807-13c680-15b57aa5aef63e"));
        list2.add(new Cookie("gr_user_id","0cc6a016-b943-49e1-bb5f-81fc5825142b"));
        list2.add(new Cookie("_uab_collina","149156017551584659150686"));
        list2.add(new Cookie("_umdata","A502B1276E6D5FEFE172818A53763A853A58256396A7985DA6735D5D7B4CCD1AF23F5C6932E729E2CD43AD3E795C914C46A3CB78A63A2777F7D0D7A8D2DD41C0"));
        list2.add(new Cookie("acw_tc","AQAAAB0Jvjsz0gwADzRafOhYhxu25QZD"));
        list2.add(new Cookie("PHPSESSID","988cj539tifm66mqduvfm3vav1"));
        list2.add(new Cookie("_uab_collina","149182443836786272614275"));
        list2.add(new Cookie("CNZZDATA1254842228","61004937-1491823770-https%253A%252F%252Fwww.google.com%252F%7C1491876749"));
        list2.add(new Cookie("gr_session_id_9c1eb7420511f8b2","07f85c0a-45e0-466a-bdb8-6bf9be9f8cea"));

    }

    public SeleniumMatchCounter(String companyName, YlCompanyQccMapper mapper, String id) {
        this.companyName = companyName;
        this.mapper = mapper;
        this.id = id;
    }

    private void saveCookie() {
        WebDriver.Options options = webDriver.manage();

        int num =(int)(Math.random() * 120);

        int res = num % 3;

        if (res == 0) {
            for (Cookie cookie : list1) {
                options.addCookie(cookie);
            }
        } else if (res == 1){
            for (Cookie cookie : list2) {
                options.addCookie(cookie);
            }
        } else {
            for (Cookie cookie : list3) {
                options.addCookie(cookie);
            }
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
        ylCompanyQcc.setCompanyInfo(id);

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

        wati();
        //选择第一个企业
        WebElement firstCompany = new WebDriverWait(webDriver, 10).until(
                (Function<WebDriver, WebElement>)webDriver1 ->
                        webDriver1.findElement(By.xpath("//*[@id=\"searchlist\"]/table/tbody/tr[1]/td[2]/a")));
        firstCompany.click();

        wati();

        //切换窗口
        Set<String> winHandels = webDriver.getWindowHandles();
        List<String> it = new ArrayList<>(winHandels);
        webDriver.switchTo().window(it.get(1));

        wati();

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
        ylCompanyQcc.setTrademark(sb2.toString());

        mapper.insert(ylCompanyQcc);

        webDriver.quit();
    }

    private void page(StringBuilder sb, String cssSelector, String cssSelectorActive, RemoteWebDriver webDriver) {
        while (true) {
            WebElement wb5;

            try {
                wb5 = new WebDriverWait(webDriver, 3).until(
                        (Function<WebDriver, WebElement>)webDriver1 ->
                                webDriver1.findElement(By.cssSelector(cssSelector)));

                sb.append(wb5.getText());
            } catch (TimeoutException e) {
                logger.error("未找到某个");
                break;
            }

            WebElement wb6;
            try {
                wb6 = new WebDriverWait(webDriver, 3).until(
                        (Function<WebDriver, WebElement>)webDriver1 ->
                                webDriver1.findElement(By.cssSelector(cssSelectorActive)));
                wb6.click();

                Thread.sleep(2000);
            } catch (TimeoutException e) {
                break;
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }

    private String getInfo(String xpath) {
        try {
            WebElement wb3 = new WebDriverWait(webDriver, 3).until(
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

    private void wati() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
