package pl.javastart.restoffers.categories;

import jakarta.transaction.Transactional;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import pl.javastart.restoffers.offers.OffersService;

import java.util.ArrayList;
import java.util.List;

@RequestMapping("api/categories")
@RestController
public class CategoriesRestController {

    private final CategoriesService categoryService;
    private final OffersService offersService;

    public CategoriesRestController(CategoriesService categoryService, OffersService offersService) {
        this.categoryService = categoryService;
        this.offersService = offersService;
    }

    @GetMapping
    List<CategoryDto> getAllCategories() {
        List<CategoryDto> all = categoryService.findAll();
        return all;
    }

    @GetMapping("/names")
    List<String> getAllCategoriesNames() {
        List<String> categoriesNames = new ArrayList<>();
        for (CategoryDto category : categoryService.findAll()) {
            categoriesNames.add(category.getName());
        }
        return categoriesNames;
    }

    @PostMapping
    public ResponseEntity<Category> saveOffer(@RequestBody Category receivedCategory) {
        Category newCategory = new Category();
        newCategory.setId(categoryService.findAll().size() + 1);
        newCategory.setName(receivedCategory.getName());
        newCategory.setDescription(receivedCategory.getDescription());
        newCategory.setOffers(new ArrayList<>());
        categoryService.save(newCategory);
        return ResponseEntity.ok(newCategory);
    }

    @Transactional
    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteCategoryAndOffers(@PathVariable int id) {
        String categoryName = categoryService.findById(id).getName();
        categoryService.deleteById(id);
        offersService.deleteAllOffersFromCategory(categoryName);
        return ResponseEntity.ok("Category and its offers was deleted successfully!");
    }
}
