package s24.budgetmanager.domain;
import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface Categoryrepository extends CrudRepository<Category, Long> {
    List<Category> findByName(String name);
}


