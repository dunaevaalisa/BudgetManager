package s24.budgetmanager.domain;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.temporal.ChronoUnit;
import java.util.List;

@Entity
public class Budget {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long budgetid;
    
    private String name;
    private double totalAmount;
    private double totalSpent;
    private LocalDate startDate;
    private LocalDate endDate;
    private double dailyBudget;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "budget")
	private List<Purchase> purchases;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "budget")
	private List<Income> incomes;

     @ManyToOne
     @JoinColumn(name = "incomeid")
     private Income income;

    public Budget() {
    }

    public Budget(String name,double totalAmount, LocalDate endDate, LocalDate startDate) {
        super();
        this.name = name;
        this.totalAmount = totalAmount;
        this.startDate = startDate;
        this.endDate = endDate;
        this.dailyBudget = calculateDailyBudget();
    }

    private double calculateDailyBudget() {
        long daysBetween = ChronoUnit.DAYS.between(endDate, startDate);
        return totalAmount / daysBetween;
    }

    //getters and setters

    public double getDailyBudget() {
        return calculateDailyBudget();
    }

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

    public double getTotalAmount() {
        return totalAmount;
    }

    public void setTotalAmount(double totalAmount) {
        this.totalAmount = totalAmount;
    }


    public LocalDate getStartDate() {
        return startDate;
    }

    public void setStartDate(LocalDate startDate) {
        this.startDate = startDate;
    }
    public LocalDate getEndDate() {
        return endDate;
    }
    public void setEndDate(LocalDate endDate) {
        this.endDate = endDate;
    }

    public long getDaysLeft() {
        return ChronoUnit.DAYS.between(endDate, startDate);
    }
      
}
