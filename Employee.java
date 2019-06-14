package tugas;

public class Employee implements Taxable {
    private String name;
    private double salary;

    public Employee(String name, double salary) {
        check(salary);
        this.name = name;
        this.salary = salary;
    }

    public void check(double salary){
        if(salary<0) throw new RuntimeException("salary harus lebih besar 0");
    }
    public String getName() {
        return name;
    }

    public double getSalary() {
        return salary;
    }

    public double getTakeHomePay(){
        return this.salary-getTax();
    }

    public double getTax(){
        return getSalary()*0.1;
    }

}
