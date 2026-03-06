package nutrisense.controller.admin.statistic;

import nutrisense.dal.ProductDAO;
import nutrisense.model.Product;

import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@WebServlet(name = "Top10SellerProduct", urlPatterns = {"/top10"})
public class Top10SellerProduct extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        
        ProductDAO dao = new ProductDAO();
        List<Product> list = dao.getTop10SellerProduct();
        int listTop10Product = list.size();
        
        request.setAttribute("list", list);
        request.setAttribute("listTop10Product", listTop10Product);

        request.getRequestDispatcher("dashboard/top10.jsp").forward(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        processRequest(request, response);
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
