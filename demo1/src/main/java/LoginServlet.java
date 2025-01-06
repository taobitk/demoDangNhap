import Repository.DBConnection;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String email = req.getParameter("email");
        String password = req.getParameter("password");

        try (Connection conn = DBConnection.getConnection()) {
            String query = "SELECT * FROM Customer WHERE Email = ? AND Password = ?";
            PreparedStatement pstmt = conn.prepareStatement(query);
            pstmt.setString(1, email);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // Đăng nhập thành công, lưu thông tin khách hàng vào session
                HttpSession session = req.getSession();
                session.setAttribute("authenticated", true);
                session.setAttribute("customerName", rs.getString("Name"));
                resp.sendRedirect("dashboard.jsp"); // Chuyển hướng tới dashboard khách hàng
            } else {
                // Đăng nhập thất bại, trả về thông báo lỗi
                req.setAttribute("errorMessage", "Email hoặc mật khẩu không chính xác.");
                req.getRequestDispatcher("login.jsp").forward(req, resp);
            }
        } catch (Exception e) {
            e.printStackTrace();
            throw new ServletException("Lỗi kết nối cơ sở dữ liệu");
        }
    }
}
