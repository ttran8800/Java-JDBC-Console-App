import java.sql.SQLException;
import java.util.List;
public interface EmployeeDao {
    void addEmployee(Employee employee);
    void updateEmployee(Employee employee);
    void deleteEmployee(Employee employee);
    List<Employee> getEmployees();
    Employee getEmployeeById(int id);
}
