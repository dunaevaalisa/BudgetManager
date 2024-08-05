package s24.budgetmanager;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import s24.budgetmanager.domain.AppUser;
import s24.budgetmanager.domain.AppUserRepository;
import s24.budgetmanager.domain.Budget;
import s24.budgetmanager.domain.BudgetRepository;
import s24.budgetmanager.domain.Category;
import s24.budgetmanager.domain.Categoryrepository;
import s24.budgetmanager.domain.Purchase;
import s24.budgetmanager.domain.PurchaseRepository;

@SpringBootApplication
public class BudgetmanagerApplication {
    private static final Logger log = LoggerFactory.getLogger(BudgetmanagerApplication.class);

    public static void main(String[] args) {
        SpringApplication.run(BudgetmanagerApplication.class, args);
    }
    
    @Bean
    public CommandLineRunner purchaseDemo(PurchaseRepository prepository, Categoryrepository crepository, BudgetRepository brepository, AppUserRepository uRepository) {
        return (args) -> {
            log.info("save a couple of purchases");

            // Save categories
            Category category1 = new Category("Home");
            Category category2 = new Category("Transport");
            Category category3 = new Category("Groceries");
            Category category4 = new Category("Cafes");
            Category category5 = new Category("Subscriptions");
            Category category6 = new Category("Clothes");
            Category category7 = new Category("Health");
            Category category8 = new Category("Other");

            crepository.save(category1);
            crepository.save(category2);
            crepository.save(category3);
            crepository.save(category4);
            crepository.save(category5);
            crepository.save(category6);
            crepository.save(category7);
            crepository.save(category8);

            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate endDate1 = LocalDate.parse("2024-06-01", formatter);
            LocalDate endDate2 = LocalDate.parse("2024-07-01", formatter);  
            LocalDate endDate3 = LocalDate.parse("2024-08-01", formatter);  
            LocalDate startDate1 = LocalDate.parse("2024-05-21", formatter);
            LocalDate startDate2 = LocalDate.parse("2024-06-15", formatter);  
            LocalDate startDate3 = LocalDate.parse("2024-07-01", formatter);  

            // Save budgets
            Budget budget1 = new Budget("first", 2000, startDate1, endDate1);
            Budget budget2 = new Budget("second", 1000 , startDate2, endDate2);
            Budget budget3 = new Budget("third", 3000, startDate3, endDate3);
            brepository.save(budget1);
            brepository.save(budget2);
            brepository.save(budget3);

            LocalDateTime automaticDate = LocalDateTime.now();

            // Save purchases
            prepository.save(new Purchase("Pen", 2, "Prisma", automaticDate, category1, budget1));
            prepository.save(new Purchase("Table", 60, "Tokmani", automaticDate, category1, budget1));    
            prepository.save(new Purchase("Food", 23, "K-market", automaticDate, category3, budget1));    
            prepository.save(new Purchase("Netflix", 10, "Netflix.com", automaticDate, category5, budget1));  
            prepository.save(new Purchase("T-shirt", 25, "HM", automaticDate, category6, budget1));   
            prepository.save(new Purchase("Burana", 8, "Yliopiston apteekki", automaticDate, category7, budget1));   
            prepository.save(new Purchase("Phone", 1100, "Elisa", automaticDate, category8, budget1));  

            // Save users
            AppUser user1 = new AppUser("user", "$2a$06$3jYRJrg0ghaaypjZ/.g4SethoeA51ph3UD4kZi9oPkeMTpjKU5uo6", "USER");
            AppUser user2 = new AppUser("admin", "$2a$10$0MMwY.IQqpsVc1jC8u7IJ.2rT8b0Cd3b3sfIBGV2zfgnPGtT4r0.C","ADMIN");
            uRepository.save(user1);
            uRepository.save(user2);

            log.info("fetch all purchases");
            for (Purchase purchase : prepository.findAll()) {
                log.info(purchase.toString());
            }
        };
    }
}
