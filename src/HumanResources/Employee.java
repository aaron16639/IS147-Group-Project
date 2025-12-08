package HumanResources;
public class Employee   {
    private String name;
    private int salary;
    private int ptoDays;
    
    public Employee() {
        this.ptoDays = 0;
    }

    public Employee(String name, int salary) {
        this.name = name;
        this.salary = salary;
        this.ptoDays = 0;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public int getPtoDays() {
        return ptoDays;
    }

    public void addPtoDays(int days) {
        this.ptoDays += days;
    }

    public boolean deductPtoDays(int days) {
        if (this.ptoDays >= days) {
            this.ptoDays -= days;
            return true;
        }
        return false;
    }

    public void displayInfo() {
        System.out.println("Name: " + name + ", Salary: $" + salary + ", PTO Days: " + ptoDays);
    }
}
