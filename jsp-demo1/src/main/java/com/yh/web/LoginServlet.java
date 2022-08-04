package com.yh.web;

import com.yh.pojo.User;
import com.yh.service.UserService;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.annotation.*;
import java.io.IOException;

@WebServlet("/loginServlet")
public class LoginServlet extends HttpServlet {

    private UserService service=new UserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接受用户名密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");

        //获取复选框数据
        String remember = request.getParameter("remember");

        //调研service查询
        User user = service.login(username, password);
        if(user!=null){
            //登录成功
            //判断是否勾选复选框
            if("1".equals(remember)){
                //发送cookie
                Cookie c_username=new Cookie("username",username);
                Cookie c_password=new Cookie("password",password);

                //持久化存储，设置存活时间
                c_username.setMaxAge(60*60*24*7);//7day
                c_password.setMaxAge(60*60*24*7);//7day

                response.addCookie(c_username);
                response.addCookie(c_password);
            }

            //将登陆成功的user对象存入session
            HttpSession session = request.getSession();
            session.setAttribute("user",user);
            String contextPath = request.getContextPath();
            response.sendRedirect(contextPath+"/selectAllServlet");//重定向
        }else{
            //登陆失败

            //存储错误信息到request
            request.setAttribute("login_msg","用户名或密码错误！");
            //跳转到login.jsp
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
