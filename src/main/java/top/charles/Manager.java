package top.charles;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
public class Manager extends Employee {

    private List<Employee> employees;

    // transient：部门总开销，内部计算，不序列化
    private transient double totalExpense;

    public Manager(String name, double salary, List<Employee> employees) {
        super(name, salary);
        this.employees = employees;
        this.totalExpense = initTotalExpense();
    }
    private double initTotalExpense() {
        double sum = salary;
        for (Employee employee : employees) {
            sum += employee.salary;
        }
        return sum;
    }
    public double getTotalExpense() {
        totalExpense = initTotalExpense();
        return totalExpense;
    }
}