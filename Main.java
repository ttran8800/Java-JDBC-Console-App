import java.util.List;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        EmployeeDao empImplement = EmployeeDaoFactory.getEmployeeDao();
        Scanner scanner = new Scanner(System.in);

        boolean flag = true;

        //display user menu option
        while (flag) {
            System.out.println("*****************************");
            System.out.println("Select from the options below");
            System.out.println("PRESS 1: ADD EMPLOYEE");
            System.out.println("PRESS 2: UPDATE EMPLOYEE");
            System.out.println("PRESS 3: DELETE EMPLOYEE");
            System.out.println("PRESS 4: GET ALL EMPLOYEE");
            System.out.println("PRESS 5: GET EMPLOYEE BY ID");
            System.out.println("PRESS 6: EXIT");

            //preventing user from entering strings and characters
            if (scanner.hasNextInt()) {
                int input = scanner.nextInt();


                switch (input) {
                    case 1: {
                        //add employee
                        System.out.print("Enter Name: ");
                        String name = scanner.next();
                        System.out.print("Enter Email: ");
                        String email = scanner.next();
                        Employee employee = new Employee();
                        employee.setName(name);
                        employee.setEmail(email);
                        empImplement.addEmployee(employee);
                        System.out.println();
                        break;
                    }
                    case 2: {
                        //update employee
                        //entering non-numeric value returns to main menu
                        System.out.print("Enter employee ID: ");
                        try {
                            String inputID = scanner.next();
                            int id = Integer.parseInt(inputID);
                            //try to get employee, if not found, returns to main menu
                            Employee employee = empImplement.getEmployeeById(id);
                            if (employee == null) {
                                break;
                            }
                            //if found ask for new data
                            System.out.print("Enter new name: ");
                            String name = scanner.next();
                            System.out.print("Enter new email: ");
                            String email = scanner.next();
                            employee.setName(name);
                            employee.setEmail(email);
                            empImplement.updateEmployee(employee);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a valid employee ID");
                            System.out.println("Returning to main menu");
                            scanner.nextLine();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    }
                    case 3: {
                        //delete employee
                        //entering non-numeric value returns to main menu
                        System.out.print("Enter employee ID: ");
                        try {
                            String inputID = scanner.next();
                            int id = Integer.parseInt(inputID);
                            Employee employee = empImplement.getEmployeeById(id);
                            empImplement.deleteEmployee(employee);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a valid employee ID");
                            System.out.println("Returning to main menu");
                            scanner.nextLine();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    }
                    case 4: {
                        //get all employee
                        System.out.println("Retrieving all employee records.....");
                        List<Employee> empList = empImplement.getEmployees();
                        for (Employee per : empList) {
                            System.out.println(per);
                        }
                        break;
                    }
                    case 5: {
                        //get employee by id
                        //if not found returns error and null
                        System.out.print("Enter employee ID: ");
                        try {
                            String inputID = scanner.next();
                            int id = Integer.parseInt(inputID);
                            Employee employee = empImplement.getEmployeeById(id);
                            System.out.println(employee);
                        } catch (NumberFormatException e) {
                            System.out.println("Invalid input. Please enter a valid employee ID");
                            System.out.println("Returning to main menu");
                            scanner.nextLine();
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }
                        break;
                    }
                    case 6: {
                        //exit loop
                        System.out.println("Thank you");
                        System.out.println("Exiting...");
                        flag = false;
                        break;
                    }
                    default: {
                        System.out.println("Please choose between 1 - 6");
                        break;
                    }
                }
            } else {
                //avoiding infinite loop
                scanner.next();
                System.out.println("Please enter a number from 1 - 6 (no letters)");
            }

        }
    }
}
