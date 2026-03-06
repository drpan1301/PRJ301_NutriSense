package nutrisense.controller.admin.statistic;

import nutrisense.dal.ProductDAO;
import nutrisense.dal.OrderDAO;
import nutrisense.dal.SupplierDAO;
import nutrisense.dal.UserDAO;
import java.io.IOException;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "DashBoardServlet", urlPatterns = {"/admin"})
public class DashBoardServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {        
        ProductDAO dao = new ProductDAO();
        UserDAO udao = new UserDAO();
        OrderDAO odao = new OrderDAO();
       SupplierDAO sdao = new SupplierDAO();
        int count = dao.countAllProduct();
        int countS = dao.countAllTypeProduct();
        int countu = udao.countAllUser();
        int countSupplier = sdao.countAllSupplier();
        int sumquantitySold = dao.getSumQuantitySold();
        double totalmoneyAll = odao.sumAllMoneyOrder();
        request.setAttribute("countProduct", count);
        request.setAttribute("countSupplier", countSupplier);
        request.setAttribute("countTypeProduct", countS);
        request.setAttribute("sumquantitySold", sumquantitySold);
        request.setAttribute("countUser", countu);
        request.setAttribute("totalmoneyAll", totalmoneyAll);
        request.getRequestDispatcher("dashboard/dashboard.jsp").forward(request, response);
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
