package cn.ylapl.util;

import org.apache.maven.tools.plugin.generator.Generator;
import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.exception.InvalidConfigurationException;
import org.mybatis.generator.exception.XMLParserException;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.io.IOException;
import java.io.Serializable;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 根据数据库中表结构生成对应的类文件
 * Created by Angle on 2017/3/14.
 */
public class GetClassByMysql implements Serializable {

    public static void main(String[] args) {
        List<String> warnings = new ArrayList<>();
        boolean overwrite = true;
        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config;
        try {
            config = cp.parseConfiguration(
                    Generator.class.getResourceAsStream("/generatorConfig.xml"));
            DefaultShellCallback callback = new DefaultShellCallback(overwrite);
            MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);
            myBatisGenerator.generate(null);
        } catch (IOException | XMLParserException | InvalidConfigurationException | SQLException | InterruptedException e) {
            e.printStackTrace();
        }

    }
}
