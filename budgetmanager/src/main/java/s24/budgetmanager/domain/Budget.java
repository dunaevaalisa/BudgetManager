package s24.budgetmanager.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.List;

@Entity
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long budgetid;
    
    private String name;
    private int month;
    private int year;
    private double amount;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "budget")
	private List<Purchase> purchases;

    public Budget() {
    }

    public Budget(String name, int month, int year, double amount) {
        super();
        this.name = name;
        this.month = month;
        this.year = year;
        this.amount = amount;
    }

    //getters and setters

    public Long getBudgetid() {
        return budgetid;
    }

    public void setBudgetid(Long budgetid) {
        this.budgetid = budgetid;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
    public int getMonth() {
        return month;
    }

    public void setMonth(int month) {
        this.month = month;
    }


    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }


    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Budget [id=" + budgetid + ", name=" + name + ", month=" + month + ", year=" + year + ", amount=" + amount + "]";
    }
    
    
}
