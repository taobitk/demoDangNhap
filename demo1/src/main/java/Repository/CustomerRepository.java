package Repository;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class CustomerRepository {
    private static Connection connection;

    public CustomerRepository(Connection connection) {
        this.connection = connection;
    }

    public static boolean validateCustomer(String email, String password) {
        String emailQuery = "SELECT Password FROM Customer WHERE Email = ?";
        try (PreparedStatement stmt = connection.prepareStatement(emailQuery)) {
            stmt.setString(1, email);
            try (ResultSet rs = stmt.executeQuery()) {
                if (rs.next()) {
                    String storedPassword = rs.getString("Password");
                    // So sánh mật khẩu (nên mã hóa mật khẩu trước khi so sánh nếu sử dụng hashing)
                    return storedPassword.equals(password);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false; // Trả về false nếu không tìm thấy email hoặc mật khẩu không khớp
    }
}
