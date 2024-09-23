package pl.javastart.restoffers.categories;

import org.springframework.stereotype.Service;
import pl.javastart.restoffers.offers.OffersService;

import java.util.ArrayList;
import java.util.List;

@Service
public class CategoriesService {

    private final CategoriesRepository categoriesRepository;
    private final OffersService offersService;

    CategoriesService(CategoriesRepository categoriesRepository, OffersService offersService) {
        this.categoriesRepository = categoriesRepository;
        this.offersService = offersService;
    }

    List<CategoryDto> findAll() {
        List<CategoryDto> categories = new ArrayList<>();
        for (Category category : categoriesRepository.findAll()) {
            CategoryDto categoryDto = new CategoryDto();
            categoryDto.setId(category.getId());
            categoryDto.setName(category.getName());
            categoryDto.setDescription(category.getDescription());
            categoryDto.setOffers(offersService.countOffersInCategory(category.getName()));
            categories.add(categoryDto);
        }
        return categories;
    }

    Category findById(long id) {
        return categoriesRepository.findById(id);
    }

    void deleteById(long id) {
        categoriesRepository.deleteById(id);
    }

    void save(Category category) {
        categoriesRepository.save(category);
    }
}
