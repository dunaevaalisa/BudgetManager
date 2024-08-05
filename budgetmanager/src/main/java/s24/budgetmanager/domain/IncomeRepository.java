package s24.budgetmanager.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface IncomeRepository extends CrudRepository<Income, Long> {
    Income findByName(String name);
    Income findFirstByOrderByIncomeidDesc();
    List<Income> findAll();
}