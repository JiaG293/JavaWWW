package vn.edu.iuh.fit.week01.services;
import vn.edu.iuh.fit.week01.db.MariaDbConnection;
import vn.edu.iuh.fit.week01.models.User;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AccountService {
    public static boolean loginUser(String username, String password) throws SQLException {
        MariaDbConnection connection = new MariaDbConnection();
        String sql = """
                SELECT username, password
                FROM accounts
                WHERE username = ? AND password = ?
                """;

        try (PreparedStatement statement = connection.getConnection().prepareStatement(sql)) {
            statement.setString(1, username);
            statement.setString(2, password);
            try (ResultSet resultSet = statement.executeQuery()) {
                connection.closeConnection();
                // neu it hon 1 ket qua thi tra ve false
                return resultSet.next();
            }
        }

    }

    public static User getAllUser(String username) throws SQLException {
        MariaDbConnection connection = new MariaDbConnection();
        String sql = """
                    SELECT username
                    FROM accounts
                """;

        try (PreparedStatement statement = connection.getConnection().prepareStatement(sql)) {
            // Đặt tham số vào câu lệnh SQL
            statement.setString(1, username);

            try (ResultSet resultSet = statement.executeQuery()) {
                if (resultSet.next()) {
                    String user = resultSet.getString("username");
                    String email = resultSet.getString("email");
                    int id = resultSet.getInt("id");
                    return new User(user, "", email, id);
                } else {
                    return null; // Không tìm thấy người dùng
                }
            }
        }
    }


}
