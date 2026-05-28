import java.sql.*;

public class DbManager {

    public static Connection connect() {

        String url = "jdbc:sqlite:resources/CalendarTodos.db";
        try {
            //System.out.println("Connected");
            return DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }

   void createTable() {
        String sql = "Create table if not exists allTodos(" +
                "todoId Integer Primary key," +
                "todoDescription Text," +
                "todoDate Date,"+
                "todoComplete boolean" +
                ");";

        try (Connection con = connect();
             Statement stmt = con.createStatement()
        ) {
          stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
   }

    void handleCreateTodo(Todo todo) {
        String sql = "INSERT into allTodos(todoDescription, todoDate, todoComplete) values(?,?,?)";
        try (Connection con = connect();
             PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, todo.description);
            pstmt.setString(2, todo.date);
            pstmt.setBoolean(3, todo.complete);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }







}
