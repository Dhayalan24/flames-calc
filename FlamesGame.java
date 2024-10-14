

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class FlamesGame {
    private static final String DB_URL = "jdbc:mysql://localhost:3306/flames_db";
    private static final String DB_USER = "root";
    private static final String DB_PASSWORD = "Dhayalan@123";

    public static int countCommonLetters(String name1, String name2) {
        StringBuilder sb1 = new StringBuilder(name1);
        StringBuilder sb2 = new StringBuilder(name2);

        for (int i = 0; i < sb1.length(); i++) {
            for (int j = 0; j < sb2.length(); j++) {
                if (sb1.charAt(i) == sb2.charAt(j)) {
                    sb1.deleteCharAt(i);
                    sb2.deleteCharAt(j);
                    i--;
                    break;
                }
            }
        }

        return sb1.length() + sb2.length();
    }

    public static String calculateFlames(int count) {
        String flames = "FLAMES";

        while (flames.length() > 1) {
            int index = (count % flames.length()) - 1;
            if (index >= 0) {
                flames = flames.substring(0, index) + flames.substring(index + 1);
            } else {
                flames = flames.substring(0, flames.length() - 1);
            }
        }

        return getFlamesMeaning(flames.charAt(0));
    }

    private static String getFlamesMeaning(char letter) {
        switch (letter) {
            case 'F':
                return "Friends";
            case 'L':
                return "Lovers";
            case 'A':
                return "Affection";
            case 'M':
                return "Marriage";
            case 'E':
                return "Enemies";
            case 'S':
                return "Siblings";
            default:
                return "Unknown";
        }
    }

    public static void saveToDatabase(String name1, String name2, String result) {
        String sql = "INSERT INTO flames_results1 (name1, name2, result) VALUES (?, ?, ?)";

        try (Connection conn = DriverManager.getConnection(DB_URL, DB_USER, DB_PASSWORD);
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, name1);
            pstmt.setString(2, name2);
            pstmt.setString(3, result);

            pstmt.executeUpdate();

            System.out.println("Data stored in the database successfully!");

        } catch (SQLException e) {
            System.out.println("Failed to store data in the database.");
            e.printStackTrace();
        }
    }
}
