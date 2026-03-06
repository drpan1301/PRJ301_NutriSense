package nutrisense.controller.web.shop;

import nutrisense.dal.CategoryDAO;
import nutrisense.dal.ProductDAO;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import nutrisense.model.Category;
import nutrisense.model.Product;

@WebServlet(name = "Home1Servlet", urlPatterns = {"/home1"})
public class Home1Servlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet Home1Servlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet Home1Servlet at " + request.getContextPath() + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        CategoryDAO d = new CategoryDAO();
        ProductDAO p = new ProductDAO();
        List<Category> categories = d.getAll();
        List<Product> productsYear = p.getAll();
        Boolean[] chid = new Boolean[categories.size() + 1];
        List<Product> productsTop5Sellers = p.getTopBestSellers("5");
        List<Product> giftSets = p.getGiflSets();
        List<Product> listAll = p.getAll();
        String cidYear_raw = request.getParameter("cidYear");
        List<Product> productFooter1 = p.getFeaturedProducts();
        List<Product> productFooter2 = p.getFeaturedProducts();

        //phần product 2023
        int cidYear;
        if (cidYear_raw != null) {
            cidYear = Integer.parseInt(cidYear_raw);
            Category category = d.getCategoryById(cidYear);
            productsYear = p.getProductsBrandByInYear(2023, category);
        }

        //phan trang
        int page = 1, numPerPage = 9;
        int size = listAll.size();
        int numberpage = ((size % numPerPage == 0) ? (size / 9) : (size / 9) + 1);
        String xpage = request.getParameter("page");
        if (xpage == null) {
            page = 1;
        } else {
            page = Integer.parseInt(xpage);
        }
        int start, end;
        start = (page - 1) * 9;
        end = Math.min(page * numPerPage, size);

        //Hot product
        Product spHot = p.getHotDeal();

        if (cidYear_raw == null) {
            chid[0] = true;
        } else {
            chid[0] = false;
        }

        List<Product> listByPage = p.getListByPage(listAll, start, end);

        request.setAttribute("listAll", listAll);
        request.setAttribute("cidYear", cidYear_raw);
        request.setAttribute("category", categories);
        request.setAttribute("productsYear", productsYear);
        request.setAttribute("hotDeal", spHot);
        request.setAttribute("productPage", listByPage);
        request.setAttribute("page", page);
        request.setAttribute("chid", chid);
        request.setAttribute("numberpage", numberpage);
        request.setAttribute("productsTopSellers", productsTop5Sellers);
        request.setAttribute("giftSets", giftSets);
        request.setAttribute("productFooter1", productFooter1);
        request.setAttribute("productFooter2", productFooter2);
        request.getRequestDispatcher("home.jsp").forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
