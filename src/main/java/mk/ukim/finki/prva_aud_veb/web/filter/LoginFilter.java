package mk.ukim.finki.prva_aud_veb.web.filter;

import ch.qos.logback.classic.helpers.MDCInsertingServletFilter;
import mk.ukim.finki.prva_aud_veb.model.User;
import org.springframework.context.annotation.Profile;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebFilter
@Profile("servlet")
public class LoginFilter implements Filter {

    @Override
    public void init(FilterConfig filterConfig) throws ServletException {
        Filter.super.init(filterConfig);
    }

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain) throws IOException, ServletException {
        HttpServletRequest req= (HttpServletRequest) request;
        HttpServletResponse resp= (HttpServletResponse) response;

        String path=req.getServletPath();
        User user= (User) req.getSession().getAttribute("user");

        if(!path.equals("/login") && user==null && !path.equals("/register") && !path.equals("/login.css")){
            resp.sendRedirect("/login");
        }else{
            chain.doFilter(request,response);
        }

    }

    @Override
    public void destroy() {
        Filter.super.destroy();
    }
}