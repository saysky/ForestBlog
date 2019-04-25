package com.liuyanzhao.ssm.blog.util.others;

/**
 * Created by 言曌 on 2017/8/26.
 */
	import java.io.IOException;
	import java.io.StringWriter;
	import java.util.regex.Matcher;
	import java.util.regex.Pattern;
	
	import javax.servlet.jsp.JspException;
	import javax.servlet.jsp.PageContext;
	import javax.servlet.jsp.tagext.JspFragment;
	import javax.servlet.jsp.tagext.SimpleTagSupport;


public class HtmlFilterTag extends SimpleTagSupport {
	
	private static final int subLength = 100; //截取字符串长度
	private static final String regEx_script = "<script[^>]*?>[\\s\\S]*?<\\/script>"; // 定义script的正则表达式
	private static final String regEx_style = "<style[^>]*?>[\\s\\S]*?<\\/style>"; // 定义style的正则表达式
	private static final String regEx_html = "<[^>]+>"; // 定义HTML标签的正则表达式
	private static final String regEx_space = "\\s*|\t|\r|\n";//定义空格回车换行符
	
	public static String filter(String htmlStr) {
		Pattern p_script = Pattern.compile(regEx_script, Pattern.CASE_INSENSITIVE);
		Matcher m_script = p_script.matcher(htmlStr);
		htmlStr = m_script.replaceAll(""); // 过滤script标签
		
		Pattern p_style = Pattern.compile(regEx_style, Pattern.CASE_INSENSITIVE);
		Matcher m_style = p_style.matcher(htmlStr);
		htmlStr = m_style.replaceAll(""); // 过滤style标签
		
		Pattern p_html = Pattern.compile(regEx_html, Pattern.CASE_INSENSITIVE);
		Matcher m_html = p_html.matcher(htmlStr);
		htmlStr = m_html.replaceAll(""); // 过滤html标签
		
		Pattern p_space = Pattern.compile(regEx_space, Pattern.CASE_INSENSITIVE);
		Matcher m_space = p_space.matcher(htmlStr);
		htmlStr = m_space.replaceAll(""); // 过滤空格回车标签
		return htmlStr.trim(); // 返回文本字符串
	}
	
	@Override
	public void doTag() throws JspException, IOException {
		
		StringWriter sw = new StringWriter();
		JspFragment jf = this.getJspBody();
		jf.invoke(sw);
		String content = sw.getBuffer().toString();
		content = filter(content);
		content = content.replaceAll(" ", "");
		int contentLength =content.length();
		if(contentLength>subLength) {
			content = content.substring(0,subLength);
		} else {
			content = content.substring(0,contentLength);
		}
		((PageContext) this.getJspContext()).getOut().write(content);
	}
	
	
}