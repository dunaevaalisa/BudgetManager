package s24.budgetmanager.domain;

import java.util.List;

import jakarta.persistence.*;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long categoryid;
	private String name;

    	
	@OneToMany(cascade = CascadeType.ALL, mappedBy = "category")
	private List<Purchase> purchases;
	
	public Category() {}

    public Category(String name) {
		super();
		this.name = name;
	}

	//getters and setters

    public Long getCategoryid() {
		return categoryid;
	}
	
	public void setCategoryid(Long categoryid) {
		this.categoryid = categoryid;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}

	public List<Purchase> getPurchases() {
		return purchases;
	}

	public void setPurchases(List<Purchase> purchases) {
		this.purchases = purchases;
	}

	@Override
	public String toString() {
		return "category [categoryid=" + categoryid + ", name=" + name + "]";
	}

}
