package nutrisense.controller.admin.management.wallet;

import nutrisense.dal.UserDAO;
import nutrisense.dal.WalletDAO;
import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.util.List;
import nutrisense.model.User;
import nutrisense.model.Wallet;

@WebServlet(name="ManageSearchWalletServlet", urlPatterns={"/searchWallet"})
public class ManageSearchWalletServlet extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet ManageSearchAccount</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet ManageSearchAccount at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        request.setCharacterEncoding("UTF-8");
        String txtSearch = request.getParameter("txt");
        WalletDAO dao = new WalletDAO();

        List<Wallet> list = dao.getWalletBySearchName(txtSearch);

        request.setAttribute("listWallet", list);
        request.setAttribute("searchValue", txtSearch);
        request.getRequestDispatcher("ajax/search_wallet_ajax.jsp").forward(request, response);
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
