import java.sql.*;
import java.util.HashMap;

public class DbManager {
// This method connects the backend with the SQLite file.
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
// This method queries the database and creates our allTodos table if it doesn't exist already.
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
// This method queries the database and creates a todo with the corresponding Todo object attributes.
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
// This method queries the database and deletes a todo with a corresponding todoId.
// Note: all ID attributes are stored as Strings.
    void handleDeleteTodo(String todoId) {
        String sql = "DELETE FROM allTodos WHERE todoId = ?";
        try (Connection con = connect();
            PreparedStatement pstmt = con.prepareStatement(sql)) {

            pstmt.setString(1, todoId);
            pstmt.executeUpdate();

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
// This method queries the Todos Database and returns a hashmap of Strings and Todo Classes.
    HashMap<String, Todo> handleViewTodo() {
        String sql = "Select * from allTodos";

        try (Connection con = connect();
            Statement stmt = con.createStatement();
            ResultSet rs = stmt.executeQuery(sql);
        ){
            HashMap<String, Todo> todos = new HashMap<>();
            while (rs.next()) {
                Todo currTodo = new Todo(rs.getString("todoDescription"), rs.getString("todoDate"), rs.getBoolean("todoDate"));
                todos.put(rs.getString("todoId"), currTodo);
            }
            return todos;

        } catch (SQLException e) {
            System.out.println(e.getMessage());
            return null;
        }
    }
}
