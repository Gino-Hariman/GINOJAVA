package tugas;

public class ComissionedEmployee extends Employee {

    private double comission;

    public ComissionedEmployee(String name, double salary, double comission) {
        super(name, salary);
        this.comission = comission;
    }

    public double getComission() {
        return comission;
    }

    @Override
    public double getTakeHomePay() {
        return getSalary()+comission;
    }

    @Override
    public double getTax() {
        return (getSalary()+comission)*0.1;
    }
}
