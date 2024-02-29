import java.util.Scanner;

enum Month2{
    JANUARY, FEBRUARY, MARCH, APRIL, MAY, JUNE, JULY, AUGUST, SEPTEMBER, OCTOBER, NOVEMBER, DECEMBER
}

class Student2 {
    private String name;
    private double[] budgets;
    private double[][] expenses;

    public Student2(String name) {
        this.name = name;
        this.budgets = new double[12];
        this.expenses = new double[12][];
    }

    public void setBudget(Month month, double budget) {
        int index = month.ordinal();
        if (index >= 0 && index < budgets.length) {
            budgets[index] = budget;
            System.out.println("Budget for " + month + " set to " + budget + " for " + name);
        } else {
            System.out.println("Invalid month index.");
        }
    }

    public void logExpense(String category, double amount, String description, Month month) {
        int index = month.ordinal();
        if (index >= 0 && index < expenses.length) {
            if (expenses[index] == null) {
                expenses[index] = new double[1];
            } else {
                double[] temp = new double[expenses[index].length + 1];
                System.arraycopy(expenses[index], 0, temp, 0, expenses[index].length);
                expenses[index] = temp;
            }
            expenses[index][expenses[index].length - 1] = amount;
            System.out.println("Expense logged for " + month + ": " + amount + " in category " + category);
        } else {
            System.out.println("Invalid month index.");
        }
    }

    public double getCurrentMonthExpenditure(Month currentMonth) {
        int index = currentMonth.ordinal();
        if (index >= 0 && index < expenses.length && expenses[index] != null) {
            double total = 0;
            for (double expense : expenses[index]) {
                total += expense;
            }
            double budget = budgets[index];
            System.out.println("Total expenditure for " + currentMonth + ": $" + total);
            System.out.println("Budget for " + currentMonth + ": $" + budget);
            if (total > budget) {
                System.out.println("Warning!!!: Expenditure exceeded the budget for " + currentMonth + ". Please start savings....");
            }
            return total;
        } else {
            return 0; 
        }
    }
}

public class TestExpenseTracker2 {
    public static void main(String[] args) {
        Student2 student1 = new Student2("Keertana");
        Student2 student2 = new Student2("Khushi");
        Student2 student3 = new Student2("Moulya");
        Scanner scanner = new Scanner(System.in);

        boolean running = true;
        while (running) {
            System.out.println("Expense Tracker Menu:");
            System.out.println("1. Enter Expense");
            System.out.println("2. Set Budget");
            System.out.println("3. Expenditure of the month");
            System.out.println("4. Exit");
            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine(); 

            switch (choice) {
                case 1:
                    System.out.print("Enter the current Month: ");
                    String monthStr = scanner.nextLine();
                    Month month = Month.valueOf(monthStr.toUpperCase()); 
                    System.out.print("Enter expense category: ");
                    String category = scanner.nextLine();
                    System.out.print("Enter expense amount: ");
                    double amount = scanner.nextDouble();
                    scanner.nextLine();  
                    System.out.print("Enter expense description: ");
                    String description = scanner.nextLine();
                    student2.logExpense(category, amount, description, month);
                    break;

                case 2:
                    System.out.print("Enter budget category: ");
                    String budgetCategory = scanner.nextLine();
                    System.out.print("Enter student name (Keertana/Khushi/Moulya): ");
                    String studentName = scanner.nextLine();
                    Student2 selectedStudent = null;
                    if (studentName.equalsIgnoreCase("Keertana")) {
                        selectedStudent = student1;
                    } else if (studentName.equalsIgnoreCase("Khushi")) {
                        selectedStudent = student2;
                    } else if (studentName.equalsIgnoreCase("Moulya")) {
                        selectedStudent = student3;
                    } else {
                        System.out.println("Invalid student name. Please try again.");
                        break;
                    }
                    System.out.println("Enter budget for each month:");
                    for (Month m: Month.values()) {
                        System.out.println(m+":");
                        double budget = scanner.nextDouble();
                        scanner.nextLine();
                        selectedStudent.setBudget(m,budget);
                    }
                    break;

                case 3:
                    System.out.print("Enter the current Month: ");
                    String currentMonthStr = scanner.nextLine();
                    Month currentMonth = Month.valueOf(currentMonthStr.toUpperCase()); 
                    double expenditure = student2.getCurrentMonthExpenditure(currentMonth);
                    System.out.println("Current month expenditure: $" + expenditure);
                    break;

                case 4:
                    running = false;
                    break;

                default:
                    System.out.println("Invalid choice. Please try again.");
                    break;
            }
        }
        scanner.close();
    }
}
