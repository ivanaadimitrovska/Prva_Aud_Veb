//package mk.ukim.finki.prva_aud_veb.web.servlet;
//
//import mk.ukim.finki.prva_aud_veb.model.User;
//import mk.ukim.finki.prva_aud_veb.model.exception.InvalidUser;
//import mk.ukim.finki.prva_aud_veb.service.Avtentikacija;
//import org.thymeleaf.context.WebContext;
//import org.thymeleaf.spring5.SpringTemplateEngine;
//
//import javax.servlet.ServletException;
//import javax.servlet.annotation.WebServlet;
//import javax.servlet.http.HttpServlet;
//import javax.servlet.http.HttpServletRequest;
//import javax.servlet.http.HttpServletResponse;
//import java.io.IOException;
//
//@WebServlet(name="loginUser", urlPatterns = "/login/user")
//public class LogInServlet extends HttpServlet {
//
//    private final SpringTemplateEngine springTemplateEngine;
//    private final Avtentikacija avtentikacija;
//
//    public LogInServlet(SpringTemplateEngine springTemplateEngine, Avtentikacija avtentikacija) {
//        this.springTemplateEngine = springTemplateEngine;
//        this.avtentikacija = avtentikacija;
//    }
//
//    @Override
//    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        WebContext webContext=new WebContext(req, resp, req.getServletContext());
//        springTemplateEngine.process("login.html", webContext, resp.getWriter());
//
//    }
//
//    @Override
//    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//
//        String username=req.getParameter("username");
//        String password=req.getParameter("password");
//
//        User user=null;
//        try{
//            user=avtentikacija.login(username, password);
//        }catch (InvalidUser invalidUser){
//            WebContext webContext=new WebContext(req, resp, req.getServletContext());
//            webContext.setVariable("hasError", true);
//            webContext.setVariable("error", invalidUser.getMessage());
//            springTemplateEngine.process("login.html", webContext, resp.getWriter());
//        }
//        req.getSession().setAttribute("user", user);
//        resp.sendRedirect("/servlet/thymeleaf/category");
//    }
//}
