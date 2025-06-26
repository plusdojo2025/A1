package servlet;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import dao.BadgeDAO;
import dto.BadgeDTO;
import dto.UserDTO;

@WebServlet("/BabgeServlet")
public class BabgeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public BabgeServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        HttpSession session = request.getSession(false);

        if (session != null) {
            UserDTO loginUser = (UserDTO) session.getAttribute("loginUser");
            if (loginUser != null) {
                String userId = loginUser.getUser_id();

                BadgeDAO dao = new BadgeDAO();
                List<BadgeDTO> badgeList = dao.getAllBadgesWithUserStatus(userId);

                session.setAttribute("badgeList", badgeList);
                request.getRequestDispatcher("/WEB-INF/jsp/babge.jsp").forward(request, response);
                return;
            }
        }

        response.sendRedirect("LoginServlet");
    }
}
