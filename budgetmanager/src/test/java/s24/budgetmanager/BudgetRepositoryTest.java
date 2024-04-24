package s24.budgetmanager;

import static org.assertj.core.api.Assertions.assertThat;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.context.SpringBootTest;

import s24.budgetmanager.domain.Budget;
import s24.budgetmanager.domain.BudgetRepository;
import s24.budgetmanager.domain.Category;
import s24.budgetmanager.domain.Categoryrepository;
import s24.budgetmanager.domain.Purchase;
import s24.budgetmanager.domain.PurchaseRepository;

import org.junit.jupiter.api.Test;

/**
 * Test class for testing the BudgetRepository.
 */
@SpringBootTest(classes = BudgetmanagerApplication.class)
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
public class BudgetRepositoryTest {

    @Autowired
    private BudgetRepository brepository;

    @Autowired
    private Categoryrepository crepository;

    @Autowired
    private PurchaseRepository prepository;

    // Test method to verify if a purchase with a given title can be retrieved.
    @Test
    public void findByTitleShouldReturnPurchase() {
        List<Purchase> purchases = prepository.findByTitle("Pen");
        
        assertThat(purchases).hasSize(1);
        assertThat(purchases.get(0).getPrice()).isEqualTo(23);
    }
    
    // Test method to create a new purchase and verify its creation.
    @Test
    public void createNewPurchase() {
    	Category category = new Category("Other");
    	crepository.save(category);
        Budget budget = new Budget("BudgetForApril", 04, 2024, 3000);
    	brepository.save(budget);
        LocalDateTime automaticDate = LocalDateTime.now();
    	Purchase purchase = new Purchase("Watch", 120, "Jysk", automaticDate , category, budget);
    	prepository.save(purchase);
    	assertThat(purchase.getId()).isNotNull();
    }    

    // Test method to delete a purchase and verify its deletion.
    @Test
    public void deleteNewPurchases() {
		List<Purchase> purchases = prepository.findByTitle("Table");
		Purchase purchase = purchases.get(0);
		prepository.delete(purchase);
		List<Purchase> newPurchases = prepository.findByTitle("Table");
		assertThat(newPurchases).hasSize(0);
     }

}
