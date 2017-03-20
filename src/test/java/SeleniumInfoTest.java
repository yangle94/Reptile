/**
 * Company
 * Copyright (C) 2004-2017 All Rights Reserved.
 */

import cn.ylapl.Base;
import cn.ylapl.entity.YlResult;
import cn.ylapl.mapper.YlResultMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.openqa.selenium.By;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;
import org.openqa.selenium.support.ui.ExpectedCondition;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.io.IOException;
import java.net.URL;

/**
 * @author yangle
 * @version $Id SeleniumInfoTest.java, v 0.1 2017-02-05 下午12:58 yangle Exp $$
 */
//@RunWith(SpringRunner.class)
//@SpringBootTest(classes = Base.class)
public class SeleniumInfoTest {

    @Autowired
    private YlResultMapper ylResultMapper;

    @Test
    public void select() {
        YlResult ylResult1 = new YlResult();
        ylResult1.setId(1);
        YlResult ylResult2 = ylResultMapper.selectByPrimaryKey(ylResult1);
        System.out.println(ylResult2.getHtml());
    }

    public static void main(String[] args) throws IOException {

        Capabilities capabilities = DesiredCapabilities.chrome();

        RemoteWebDriver driver = new RemoteWebDriver(new URL("http://localhost:4444/wd/hub"),capabilities);
        driver.get("http://www.baidu.com");

        WebElement issp = new WebDriverWait(driver,10)
                .until((ExpectedCondition<WebElement>) webDriver -> webDriver.findElement(By.name("ie")));

        driver.findElementById("kw").sendKeys("卧槽你马！");
        driver.findElementById("su").click();

        System.out.println(issp.getText());

        driver.quit();
    }
}