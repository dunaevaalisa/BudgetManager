package s24.budgetmanager.domain;


import org.springframework.data.repository.CrudRepository;

public interface BudgetRepository extends CrudRepository<Budget, Long> {
    Budget findByName(String name);
    Budget findFirstByOrderByBudgetidDesc();
}