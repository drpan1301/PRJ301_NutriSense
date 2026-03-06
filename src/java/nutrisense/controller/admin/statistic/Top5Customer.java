package nutrisense.controller.admin.statistic;

import nutrisense.dal.UserDAO;
import nutrisense.model.User;
import nutrisense.model.Order;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import nutrisense.model.Spending;


@WebServlet(name = "Top5KhachHangControl", urlPatterns = {"/top5khachhang"})
public class Top5Customer extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
      
        UserDAO dao = new UserDAO();

        List<User> listAllAccount = dao.getAllUsers();
        List<Spending> listTop5CustomerSpending = dao.getTop5Customers();


        request.setAttribute("listAllAccount", listAllAccount);
        request.setAttribute("listTop5Spending", listTop5CustomerSpending);
//        request.getRequestDispatcher("ManagerAccount.jsp").forward(request, response);
        request.getRequestDispatcher("dashboard/top5customer.jsp").forward(request, response);
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
