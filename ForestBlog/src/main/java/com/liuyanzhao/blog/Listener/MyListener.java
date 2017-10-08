package com.liuyanzhao.blog.Listener;

import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

//统计在线人数
public class MyListener implements HttpSessionListener {
    private int userNumber = 0;
    public void sessionCreated(HttpSessionEvent arg0) {
       // System.out.println(userNumber);
        userNumber++;
        arg0.getSession().setAttribute("userNumber", userNumber);
    }
    public void sessionDestroyed(HttpSessionEvent arg0) {
        userNumber--;
        arg0.getSession().setAttribute("userNumber", userNumber);
    }
}
