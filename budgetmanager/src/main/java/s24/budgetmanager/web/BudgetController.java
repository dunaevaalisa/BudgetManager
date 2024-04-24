package s24.budgetmanager.web;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import s24.budgetmanager.domain.Purchase;
import s24.budgetmanager.domain.PurchaseRepository;
import s24.budgetmanager.domain.SignupForm;
import s24.budgetmanager.domain.Budget;
import s24.budgetmanager.domain.BudgetRepository;
import s24.budgetmanager.domain.Categoryrepository;
import java.time.LocalDateTime; 


//Controller class for handling budget-related operations.

@Controller
public class BudgetController {

    @Autowired
    private PurchaseRepository repository;

    @Autowired
    private Categoryrepository crepository;

    @Autowired
    private BudgetRepository brepository;

    // Login page request mapping
    @RequestMapping(value = "/login")
    public String login() {
        return "login";
    }

        // Mapping to display the purchase list
    @RequestMapping(value = { "/purchaselist" })
    public String purchaseList(Model model) {
    model.addAttribute("purchases", repository.findAll());

    // Fetch the latest budget from the repository
    Budget latestBudget = brepository.findFirstByOrderByBudgetidDesc(); 
    model.addAttribute("budget", latestBudget);
    return "purchaselist";
    }


    // Mapping to retrieve purchase list data as JSON
    @RequestMapping(value = "/purchases")
    public @ResponseBody List<Purchase> purchaseListRest() {
        return (List<Purchase>) repository.findAll();
    }

    // Mapping to find a specific purchase by ID
    @RequestMapping(value = "/purchase/{id}", method = RequestMethod.GET)
    public @ResponseBody Optional<Purchase> findPurchaseRest(@PathVariable("id") Long purchaseId) {
        return repository.findById(purchaseId);
    }

    // Mapping to add a new purchase
    @RequestMapping(value = "/add")
    public String addPurchase(Model model){
        Purchase purchase = new Purchase();
        purchase.setPurchaseDateTime(LocalDateTime.now()); 
        model.addAttribute("purchase", purchase);
        model.addAttribute("categories", crepository.findAll());
        return "addpurchase";
    } 

    // Mapping to add a new budget
    @RequestMapping(value = "/addbudget")
    public String addBudget(Model model){
        Budget budget = new Budget();
        model.addAttribute("budget", budget);
        return "addbudget";
    } 

     // Mapping to save a new budget
    @RequestMapping(value = "/savebudget", method = RequestMethod.POST)
    public String saveBudget(@RequestParam("name") String name,
                         @RequestParam("month") int month,
                         @RequestParam("year") int year,
                         @RequestParam("amount") double amount,
                         Model model) {
    Budget existingBudget = brepository.findByName(name);
    if (existingBudget != null) {
        existingBudget.setMonth(month);
        existingBudget.setYear(year);
        existingBudget.setAmount(amount);
    } else {
        existingBudget = new Budget(name, month, year, amount);
    }
    brepository.save(existingBudget);
    model.addAttribute("budget", existingBudget);
    return "redirect:/purchaselist";
    }


    // Mapping to save a purchase
    @RequestMapping(value = "/save", method = RequestMethod.POST)
    public String save(Purchase purchase) {
        Budget latestBudget = brepository.findFirstByOrderByBudgetidDesc();
        double updatedAmount = latestBudget.getAmount() - purchase.getPrice();
        latestBudget.setAmount(updatedAmount);
        brepository.save(latestBudget);
        repository.save(purchase);
        return "redirect:/purchaselist";
    }


    // Mapping to edit a purchase
    @RequestMapping(value = "/edit/{id}", method = RequestMethod.GET)
    public String editPurchase(@PathVariable("id") Long purchaseId, Model model) {
        Optional<Purchase> optionalPurchase = repository.findById(purchaseId);
        
        if (optionalPurchase.isPresent()) {
            Purchase purchase = optionalPurchase.get();
            model.addAttribute("purchase", purchase);
            model.addAttribute("categories", crepository.findAll());
            return "editpurchase";
        } else {
            return "error"; 
        }
    }

    // Mapping to delete a purchase
    @RequestMapping(value = "/delete/{id}", method = RequestMethod.GET)
    public String deletePurchase(@PathVariable("id") Long purchaseId) {
        Purchase purchase = repository.findById(purchaseId).orElse(null);
        if (purchase != null) {
            Budget budget = purchase.getBudget();
            if (budget != null) {
                double remainingAmount = budget.getAmount() - purchase.getPrice();
                budget.setAmount(remainingAmount);
                brepository.save(budget);
            }
            repository.deleteById(purchaseId);
        }
        Budget latestBudget = brepository.findFirstByOrderByBudgetidDesc();
        double updatedAmount = latestBudget.getAmount() + purchase.getPrice();
        latestBudget.setAmount(updatedAmount);
        brepository.save(latestBudget);
        return "redirect:/purchaselist";
    }

    // Mapping to process signup form
    @RequestMapping(value = "/signup", method = RequestMethod.POST)
    public String processSignUpForm(@ModelAttribute("signup") SignupForm signUp) {
        return "redirect:/purchaselist"; 
    }

}