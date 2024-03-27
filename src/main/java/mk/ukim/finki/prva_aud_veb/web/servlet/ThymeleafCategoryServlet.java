package mk.ukim.finki.prva_aud_veb.web.servlet;

import mk.ukim.finki.prva_aud_veb.service.CategoryService;
import org.thymeleaf.context.WebContext;
import org.thymeleaf.spring5.SpringTemplateEngine;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "thymeleaf-category-servlet", urlPatterns = "/servlet/thymeleaf/category")

public class ThymeleafCategoryServlet extends HttpServlet {

    final SpringTemplateEngine springTemplateEngine;
    final CategoryService categoryService;

    public ThymeleafCategoryServlet(SpringTemplateEngine springTemplateEngine, CategoryService categoryService) {
        this.springTemplateEngine = springTemplateEngine;
        this.categoryService = categoryService;
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        WebContext webContext = new WebContext(req, resp, req.getServletContext());
        webContext.setVariable("ipAddress", req.getRemoteAddr());
        webContext.setVariable("clientAgent", req.getHeader("User-Agent"));
        webContext.setVariable("categories", categoryService.listCategories());
        resp.setContentType("application/xhtml+xml");
        springTemplateEngine.process("html_file.html", webContext, resp.getWriter());
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String categoryName = req.getParameter("name");
        String categoryDescription = req.getParameter("description");

        categoryService.create(categoryName, categoryDescription);
        resp.sendRedirect("/servlet/thymeleaf/category");
    }
}