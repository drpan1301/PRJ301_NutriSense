package nutrisense.controller.web.cart_wishlist;

import java.io.IOException;
import java.io.PrintWriter;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import java.util.List;
import nutrisense.model.Cart;
import nutrisense.model.Item;

@WebServlet(name="PriceViewCartServlet", urlPatterns={"/priceviewcart"})
public class PriceViewCartServlet extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet PriceViewCartServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet PriceViewCartServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
        // Phan cart
        Cart cart = null;
        Object o = session.getAttribute("cart");
        // Check
        if (o != null) {
            cart = (Cart) o;
        } else {
            cart = new Cart();
        }
        String tRid = request.getParameter("rid");
        int rid;
        try {

            rid = Integer.parseInt(tRid);
            cart.removeItem(rid);
        } catch (Exception e) {
        }
        List<Item> list = cart.getListItems();
        session.setAttribute("cart", cart);
        session.setAttribute("listItemsInCart", list);
        session.setAttribute("cartSize", list.size());

        //
        request.getRequestDispatcher("viewcart_ajax.jsp").forward(request, response);
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
