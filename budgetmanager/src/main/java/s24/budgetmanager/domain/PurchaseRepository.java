package s24.budgetmanager.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PurchaseRepository extends CrudRepository<Purchase, Long> {
    List<Purchase> findByTitle(String title);
}
