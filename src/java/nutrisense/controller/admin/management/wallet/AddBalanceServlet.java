package nutrisense.controller.admin.management.wallet;

import nutrisense.dal.WalletDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@WebServlet(name = "AddBalanceServlet", urlPatterns = {"/addbalance"})
public class AddBalanceServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        String balance_raw = request.getParameter("balance");
        String userName = request.getParameter("userName");
        WalletDAO dao = new WalletDAO();
        double balance;
        try {
            balance = Double.parseDouble(balance_raw);
            dao.inputMoney(userName, balance);
        } catch (Exception e) {
        }
        response.sendRedirect("walletmanager");
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
