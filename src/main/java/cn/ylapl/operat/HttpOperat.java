package cn.ylapl.operat;

import cn.ylapl.dto.ParamInfoDto;
import cn.ylapl.exception.ValueIsNullException;
import cn.ylapl.util.GsonUtil;
import cn.ylapl.util.HttpClientByYL;
import cn.ylapl.util.empty.CollectionUtil;
import cn.ylapl.util.empty.StringUtil;
import cn.ylapl.util.logger.LogUtil;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

/**
 * http方法层级的封装
 * Created by Angle on 2017/3/13.
 */

@Component
public class HttpOperat {

    /**
     * 获得页面html
     * @param paramInfoDto 参数信息
     * @return 页面html
     */
    public String getHtml(ParamInfoDto paramInfoDto) {

        LogUtil.info(HttpOperat.class, "paramInfoDto:" + GsonUtil.toJson(paramInfoDto));

        String html = "";

        if(paramInfoDto == null || StringUtil.isEmpty(paramInfoDto.getUrl())) {
            LogUtil.error(HttpOperat.class, "访问URL允许为空", null);
            throw new ValueIsNullException("访问URL不允许为空！");
        }

        String method = paramInfoDto.getMethod();

        if(method.equalsIgnoreCase("post")) {

            html = HttpClientByYL.httpPostRequest(paramInfoDto.getUrl(), paramInfoDto.getParam(), paramInfoDto.getHeader());
        }
        else if(method.equalsIgnoreCase("get")) {

            html = HttpClientByYL.httpGetRequest(paramInfoDto.getUrl(), paramInfoDto.getParam(), paramInfoDto.getHeader());
        }

        LogUtil.debug(HttpOperat.class, "获得html：" + html);
        return html;
    }

    /**
     * 解析文件内容
     * @param document 页面document
     * @return 解析后的值
     */
    public Map<String, String> getValueById(Document document, Set<String> ids) {

        LogUtil.debug(HttpOperat.class, "ids:" + GsonUtil.toJson(ids));

        Map<String, String> idResultMap = new HashMap<>();
        Element element;

        if(CollectionUtil.isNotEmpty(ids)) {
            for (String id : ids) {
                element = document.getElementById(id);
                if(element != null) {
                    idResultMap.put(id, element.val());
                }
            }
        }

        LogUtil.debug(HttpOperat.class, "使用ID解析文件结果：" + GsonUtil.toJson(idResultMap));
        return idResultMap;
    }

    /**
     * 解析文件内容
     * @param document 页面document
     * @return 解析后的值
     */
    public Map<String, String> getValueByClass(Document document, Set<String> classSet) {

        LogUtil.debug(HttpOperat.class, "ids:" + GsonUtil.toJson(classSet) + "document:" + GsonUtil.toJson(document));

        Map<String, String> classResultMap = new HashMap<>();
        Elements elements;
        StringBuilder sb = new StringBuilder();
        sb.append("[");

        if(CollectionUtil.isNotEmpty(classSet)) {
            for (String cls : classSet) {
                elements = document.getElementsByClass(cls);

                for (Element element : elements)
                    sb.append("\"").append(cls).append("\":")
                            .append("\"").append(element.val()).append("\"");

                classResultMap.put(cls, sb.toString());
                sb = new StringBuilder();
            }
        }

        LogUtil.debug(HttpOperat.class, "使用class解析文件结果：" + GsonUtil.toJson(classResultMap));
        return classResultMap;
    }
}
