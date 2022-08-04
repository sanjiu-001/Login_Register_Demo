package com.yh.web;

import com.yh.pojo.User;
import com.yh.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet("/registerServlet")
public class RegisterServlet extends HttpServlet {

    private UserService service=new UserService();
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //接受用户名密码
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        User user = new User();
        user.setUsername(username);
        user.setPassword(password);

        //获取用户输入发验证码·
        String checkCode = request.getParameter("checkCode");

        //获取程序生成的验证码
        HttpSession session = request.getSession();
        String checkCodeGen =(String) session.getAttribute("checkCodeGen");


        //比对验证码
        if(!checkCodeGen.equalsIgnoreCase(checkCode)){
            //不允许注册

            request.setAttribute("register_msg","验证码错误！");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
            return;
        }
        boolean flag = service.register(user);
        if(flag){
            //注册成功，跳转到登陆页面
            request.setAttribute("register","注册成功，请登陆！");
            request.getRequestDispatcher("/login.jsp").forward(request,response);
        }else{
            //注册失败，跳转到注册页面
            request.setAttribute("register_msg","用户名已存在！");
            request.getRequestDispatcher("/register.jsp").forward(request,response);
        }

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.doGet(request, response);
    }
}
