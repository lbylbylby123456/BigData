package com.cyz.actions;

import com.cyz.bean.User_g;
import com.cyz.dao.LRDao;
import com.cyz.util.MD5Util;
import com.opensymphony.xwork2.ActionSupport;
import org.apache.struts2.ServletActionContext;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.PrintWriter;

public class RegisterAction1 extends ActionSupport {
    @Override
    public String execute() throws Exception {
        HttpServletRequest request = ServletActionContext.getRequest();
        HttpServletResponse response = ServletActionContext.getResponse();
        request.setCharacterEncoding("UTF-8");
        response.setCharacterEncoding("UTF-8");
        response.setContentType("text/html;setchar=UTF-8");// 处理弹出框的中文乱码问题
        String name = request.getParameter("username");
        String psw0 = request.getParameter("password");
        RequestDispatcher d = request.getRequestDispatcher("home1.jsp");
        String code = request.getParameter("rand");
        String session_code = (String) request.getSession().getAttribute("Code");
        String psw = MD5Util.MD5Encode(psw0, "UTF-8");

        PrintWriter out1 = response.getWriter();

        User_g g = new User_g(name, psw);
        LRDao r = new LRDao();

        boolean b = r.insert(g);
        if (session_code.equals(code)) {
            g.setUsername_g(name);
            g.setPassword_g(psw);
            System.out.println("注册成功！");
//            response.sendRedirect("login1.jsp");
            out1.print("<script>alert('注册成功！！\\n \\n 去登陆...')</script>");
            out1.print("<script>window.location.href='login2.jsp'</script>");
            out1.flush();
            out1.close();
//            response.getWriter()
//                    .println("<script>window.alert('恭喜你！注册成功！\\n \\n前往登录...');window.location.href='login1.jsp';</script>");
        } else {
            System.out.println("注册失败！");
            response.sendRedirect("register1.jsp");
            out1.print("<script>alert('注册失败！！\\n \\n 验证信息...')</script>");
            out1.print("<script>window.location.href='login2.jsp'</script>");
            out1.flush();
            out1.close();
//            response.getWriter()
//                    .println("<script>window.alert('请输入正确的验证码！');window.location.href='register.jsp';</script>");
        }
        return "success";
    }
}



//package com.cyz.actions;
//
//import com.cyz.bean.User_g;
//import com.cyz.dao.LRDao;
//import com.cyz.util.MD5Util;
//import com.opensymphony.xwork2.ActionSupport;
//import org.apache.struts2.ServletActionContext;
//
//import javax.servlet.RequestDispatcher;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.PrintWriter;
//
//public class RegisterAction1 extends ActionSupport {
//    @Override
//    public String execute() throws Exception {
//        HttpServletRequest request = ServletActionContext.getRequest();
//        HttpServletResponse response = ServletActionContext.getResponse();
//        request.setCharacterEncoding("UTF-8");
//        response.setCharacterEncoding("UTF-8");
//        response.setContentType("text/html;setchar=UTF-8");// 处理弹出框的中文乱码问题
//        String name = request.getParameter("username");
//        String psw0 = request.getParameter("password");
//        RequestDispatcher d = request.getRequestDispatcher("home1.jsp");
//        String code = request.getParameter("rand");
//        String session_code = (String) request.getSession().getAttribute("Code");
//        String psw = MD5Util.MD5Encode(psw0, "UTF-8");
//
//        PrintWriter out = response.getWriter();
//
//        User_g g = new User_g(name, psw);
//        LRDao r = new LRDao();
//
//        boolean b = r.insert(g);
//        if (session_code.equals(code)) {
//            g.setUsername(name);
//            g.setPassword(psw);
//            System.out.println("注册成功！");
////            response.sendRedirect("login1.jsp");
//            out.print("<script>alert('注册成功！！\\n \\n 去登陆...')</script>");
//            out.print("<script>window.location.href='login2.jsp'</script>");
//            out.flush();
//            out.close();
////            response.getWriter()
////                    .println("<script>window.alert('恭喜你！注册成功！\\n \\n前往登录...');window.location.href='login1.jsp';</script>");
//        } else {
//            System.out.println("注册失败！");
//            response.sendRedirect("register1.jsp");
//            out.print("<script>alert('注册失败！！\\n \\n 验证信息...')</script>");
//            out.print("<script>window.location.href='login2.jsp'</script>");
//            out.flush();
//            out.close();
////            response.getWriter()
////                    .println("<script>window.alert('请输入正确的验证码！');window.location.href='register.jsp';</script>");
//        }
//        return "success";
//    }
//}
