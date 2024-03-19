package com.liuyanzhao.ssm.blog.Filter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.util.Map;

public class XssHttpServletRequestWrapper extends HttpServletRequestWrapper {
    HttpServletRequest orgRequest = null;

    public XssHttpServletRequestWrapper(HttpServletRequest request) {
        super(request);
    }
    /**
     * 覆盖getParameter方法，将参数名和参数值都做xss过滤。
     * 如果需要获得原始的值，则通过super.getParameterValues(name)来获取
     * getParameterNames,getParameterValues和getParameterMap也可能需要覆盖
     */
    @Override
    public String getParameter(String name) {
        String value = super.getParameter(xssEncode(name));
        if (value != null) {
            value = xssEncode(value);
        }
        return value;
    }
    @Override
    public String[] getParameterValues(String name) {
        String[] value = super.getParameterValues(name);
        if(value != null){
            for (int i = 0; i < value.length; i++) {
                value[i] = xssEncode(value[i]);
            }
        }
        return value;
    }
    @Override
    public Map getParameterMap() {
        return super.getParameterMap();
    }
    /**
     * 将容易引起xss漏洞的半角字符直接替换成全角字符 在保证不删除数据的情况下保存
     * @return 过滤后的值
     */
    private static String xssEncode(String value) {
        if (value == null || value.isEmpty()) {
            return value;
        }
        value = value.replaceAll("eval\\((.*)\\)", "");
        value = value.replaceAll("[\\\"\\\'][\\s]*javascript:(.*)[\\\"\\\']", "\"\"");
        value = value.replaceAll("(?i)<script.*?>.*?<script.*?>", "");
        value = value.replaceAll("(?i)<script.*?>.*?</script.*?>", "");
        value = value.replaceAll("(?i)<.*?javascript:.*?>.*?</.*?>", "");
        value = value.replaceAll("(?i)<.*?\\s+on.*?>.*?</.*?>", "");
        return value;
    }
}
