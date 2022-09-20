package com.wang.xiao;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLDecoder;
import java.net.URLEncoder;

@WebServlet("/")
public class MyServlet extends HttpServlet {
    @Override
    protected void service(HttpServletRequest req, HttpServletResponse resp) throws  IOException {
        resp.setHeader("content-type", "text/html;charset=UTF-8");
        PrintWriter writer = resp.getWriter();
        Cookie[] cookies = req.getCookies();
        String  value = "";
        for(Cookie cookie: cookies){
            if(cookie.getName().equals("sentence")){
                 value = cookie.getValue();
            }
        }
        if(value.equals("")){
            String welcome_sentence = "嗨，欢迎您再次到 from zero to expert.";
            welcome_sentence = URLEncoder.encode(welcome_sentence, "UTF-8");
            Cookie cookie = new Cookie("sentence",welcome_sentence);
            cookie.setMaxAge(60 * 60 *24);
            resp.addCookie(cookie);
            writer.println("嗨，欢迎您来到 from zero to expert.");
        }
        else {
            value = URLDecoder.decode(value, "UTF-8");
            writer.println(value);
        }

    }
}
