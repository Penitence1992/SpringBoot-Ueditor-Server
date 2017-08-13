package org.penitence;

import com.baidu.ueditor.ActionEnter;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.*;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * Created by Penitence on 2017/8/13.
 */
@SpringBootApplication
@RestController
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class,args);
    }

    @RequestMapping("/test")
    public String test(){
        return "Hello World!";
    }

    @RequestMapping("/upload")
    public void editorUpload(HttpServletRequest request,HttpServletResponse response) throws IOException {
        //String rootPath = request.getServletContext().getRealPath("/");
        response.getWriter().write( ActionEnter.build(request,"classpath:", "config.json").exec());
    }

    @Component
    @Order(Integer.MIN_VALUE)
    public static class MyFilter implements Filter{

        @Override
        public void init(FilterConfig filterConfig) throws ServletException {

        }

        @Override
        public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filterChain) throws IOException, ServletException {
            HttpServletResponse response = (HttpServletResponse)servletResponse;
            // Access-Control-Allow-Origin: 指定授权访问的域
            response.addHeader("Access-Control-Allow-Origin", "*");  //此优先级高于@CrossOrigin配置
            response.addHeader("Access-Control-Allow-Methods", "PUT,POST,GET,DELETE,OPTIONS");
            response.addHeader("Access-Control-Allow-Headers", "Content-Type,Authorization");
            response.addHeader("Access-Control-Max-Age", "1800");//30 min
            filterChain.doFilter(servletRequest, response);
        }

        @Override
        public void destroy() {

        }
    }
}
