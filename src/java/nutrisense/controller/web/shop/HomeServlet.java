package nutrisense.controller.web.shop;

import nutrisense.dal.CategoryDAO;
import nutrisense.dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nutrisense.model.Category;
import nutrisense.model.Product;


public class HomeServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try ( PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet NewServlet</title>");
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet NewServlet at " + request.getContextPath() + "</h1>");
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
        List<Product> productsTop5Sellers = p.getTopBestSellers("5");
        List<Product> giftSets = p.getGiflSets();
        List<Product> listAll = p.getAll();
        List<Product> productFooter1 = p.getFeaturedProducts();
        List<Product> productFooter2 = p.getFeaturedProducts();

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
        Boolean[] chid = new Boolean[categories.size() + 1];
        chid[0] = true;

        List<Product> listByPage = p.getListByPage(listAll, start, end);

        request.setAttribute("chid", chid);
        request.setAttribute("listAll", listAll);
        request.setAttribute("category", categories);
        request.setAttribute("productsYear", productsYear);
        request.setAttribute("hotDeal", spHot);
        request.setAttribute("productPage", listByPage);
        request.setAttribute("page", page);
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
        request.getRequestDispatcher("home.jsp").forward(request, response);

    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}
