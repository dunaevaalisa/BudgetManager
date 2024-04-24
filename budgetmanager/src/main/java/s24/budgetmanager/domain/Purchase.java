package s24.budgetmanager.domain;

import java.time.LocalDateTime;


import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;


@Entity
public class Purchase {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String title;
    private double price;
    private String place;
    private LocalDateTime purchaseDateTime;

    @ManyToOne
    @JoinColumn(name = "categoryid")
    private Category category;

     @ManyToOne
     @JoinColumn(name = "budgetid")
     private Budget budget;

    public Purchase() {
    }

    public Purchase(String title, double price, String place, LocalDateTime purchaseDateTime, Category category, Budget budget) {
        super();
        this.title = title;
        this.price = price;
        this.place = place;
        this.purchaseDateTime = purchaseDateTime;
        this.category = category;
        this.budget = budget;
    }

    //getters and setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }
    

    public String getPlace() {
        return place;
    }

    public void setPlace(String place) {
        this.place = place;
    }

     public LocalDateTime getPurchaseDateTime() {
         return purchaseDateTime;
     }

     public void setPurchaseDateTime(LocalDateTime purchaseDateTime) {
         this.purchaseDateTime = purchaseDateTime;
     }
    public Category getCategory() {
		return category;
	}

	public void setCategory(Category category) {
		this.category = category;
	}
    public Budget getBudget() {
		return budget;
	}

	public void setBudget(Budget budget) {
		this.budget = budget;
	}

    @Override
public String toString() {
    if (this.category != null) {
        return "Purchase [id=" + id + ", title=" + title + ", price=" + price + ", comment=" + place +
                ", purchaseDateTime=" + purchaseDateTime + ", category=" + category.getName() + "]";
    } else {
        return "Purchase [id=" + id + ", title=" + title + ", price=" + price + ", comment=" + place +
                ", purchaseDateTime=" + purchaseDateTime + "]";
    }
}
}


