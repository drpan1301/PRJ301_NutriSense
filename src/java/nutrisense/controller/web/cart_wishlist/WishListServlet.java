package nutrisense.controller.web.cart_wishlist;

import nutrisense.dal.ProductDAO;
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
import nutrisense.model.Product;

@WebServlet(name="WishListServlet", urlPatterns={"/wishlist"})
public class WishListServlet extends HttpServlet {

    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            /* TODO output your page here. You may use following sample code. */
            out.println("<!DOCTYPE html>");
            out.println("<html>");
            out.println("<head>");
            out.println("<title>Servlet WishListServlet</title>");  
            out.println("</head>");
            out.println("<body>");
            out.println("<h1>Servlet WishListServlet at " + request.getContextPath () + "</h1>");
            out.println("</body>");
            out.println("</html>");
        }
    } 

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        HttpSession session = request.getSession();
         // Phan wishlist
        Cart wishList = null;
        ProductDAO pd = new ProductDAO();
        Object w = session.getAttribute("wishList");
        // Check
        if (w != null) {
            wishList = (Cart) w;
        } else {
            wishList = new Cart();
        }
        String rWishId = request.getParameter("wishId");
        int wishId;
        try {
            wishId = Integer.parseInt(rWishId);
            Product p = pd.getProductByID(wishId);
            Item e = new Item(p, 1);

            if (wishList.getListItems().contains(e)) {
                wishList.removeItem(wishId);
            } else {
                wishList.addItem(e);
            }

        } catch (Exception e) {
        }
        List<Item> listItemsInWishList = wishList.getListItems();
        session.setAttribute("wishList", wishList);
        session.setAttribute("listItemsInWishList", listItemsInWishList);
        session.setAttribute("wishListSize", listItemsInWishList.size());

        request.getRequestDispatcher("ajax/header_right_ajax.jsp").forward(request, response);
    } 

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        request.getRequestDispatcher("wishlist.jsp").forward(request, response);
    }

    @Override
    public String getServletInfo() {
        return "Short description";
    }

}

