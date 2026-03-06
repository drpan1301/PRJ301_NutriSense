package nutrisense.controller.web.shop;

import nutrisense.dal.ProductDAO;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nutrisense.model.Product;


@WebServlet(name = "LoadPagingServlet", urlPatterns = {"/load"})
public class LoadPagingServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        //tam thoi load ra 9 san pham truoc 
        String amount = request.getParameter("exits");
        int iamount = Integer.parseInt(amount);
        ProductDAO p = new ProductDAO();
        List<Product> list = p.getNext9Product(iamount);
        request.setAttribute("productPage", list);
        request.setAttribute("col", 4);
        request.getRequestDispatcher("ajax/search_ajax.jsp").forward(request, response);
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
