package pl.javastart.restoffers.categories;

import org.springframework.data.repository.CrudRepository;

public interface CategoriesRepository extends CrudRepository<Category, Long> {

    Category findById(long id);
}
