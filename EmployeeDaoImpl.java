import java.util.ArrayList;
import java.util.List;
import java.sql.*;

public class EmployeeDaoImpl implements EmployeeDao{
    Connection connection;
    public EmployeeDaoImpl () {
        connection = ConnectionFactory.getConnection();
    }
    @Override
    public void addEmployee(Employee employee) {
        String sql = "insert into employee (name, email) values (?,?)";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getEmail());
            int count = preparedStatement.executeUpdate();
            if(count > 0) {
                System.out.println("Employee saved");
            } else {
                System.out.println("Oops! something went wrong, pls try again");
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
    @Override
    public void updateEmployee(Employee employee) {
        String sql = "update employee set name = ?, email = ? where id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setString(1, employee.getName());
            preparedStatement.setString(2, employee.getEmail());
            preparedStatement.setInt(3, employee.getId());
            int count = preparedStatement.executeUpdate();
            if (count > 0) {
                System.out.println("Success, employee updated");
            } else {
                System.out.println("Error, no update");
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
    @Override
    public void deleteEmployee(Employee employee) {
        String sql = "delete from employee where id = ?";
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1, employee.getId());
            int count = preparedStatement.executeUpdate();
            if (count > 0) {
                System.out.println("Success - Employee deleted");
            } else {
                System.out.println("Error");
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
    }
    @Override
    public List<Employee> getEmployees() {
        List<Employee> empList = new ArrayList<>();
        String sql = "select * from employee";
        try {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery(sql);
            while (resultSet.next()) {
                empList.add(new Employee( resultSet.getInt("id"),resultSet.getString("name"),resultSet.getString("email")));
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
        return empList;
    }
    @Override
    public Employee getEmployeeById(int id) {
        String sql = "select id, name, email from employee where id = ?";
        Employee emp = new Employee();
        try {
            PreparedStatement preparedStatement = connection.prepareStatement(sql);
            preparedStatement.setInt(1,id);
            try(ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    emp.setId(resultSet.getInt("id"));
                    emp.setName(resultSet.getString("name"));
                    emp.setEmail(resultSet.getString("email"));
                } else {
                    emp = null;
                }
            } catch (SQLException e) {
                e.getStackTrace();
            }
        } catch (SQLException e) {
            e.getStackTrace();
        }
        if (emp == null) {
            System.out.println("Employee not found!");
            return null;
        }
        return emp;
    }
}
