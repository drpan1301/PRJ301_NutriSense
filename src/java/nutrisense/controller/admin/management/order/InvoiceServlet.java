package nutrisense.controller.admin.management.order;

import nutrisense.dal.OrderDAO;
import nutrisense.dal.UserDAO;
import java.io.IOException;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import nutrisense.model.Order;
import nutrisense.model.User;


@WebServlet(name = "InvoiceServlet", urlPatterns = {"/invoice"})
public class InvoiceServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
      
        OrderDAO dao = new OrderDAO();
        UserDAO dao2 = new UserDAO();
      
        

        double sumAllInvoice = dao.sumAllMoneyOrder();
        
        List<Order> listAllInvoice = dao.getAll();
        List<User> listAllAccount = dao2.getAllUsers();
        
        request.setAttribute("listAllInvoice", listAllInvoice);
        request.setAttribute("listAllAccount", listAllAccount);
        request.setAttribute("sumAllInvoice", sumAllInvoice);
        
      
        request.getRequestDispatcher("dashboard/invoice.jsp").forward(request, response);
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
    }// </editor-fold>

}
