package pl.javastart.restoffers.offers;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface OffersRepository extends CrudRepository<Offer, Long> {

    Offer findById(long id);

    List<Offer> findByTitleContainingIgnoreCase(String text);

    void deleteAllByCategoryName(String categoryName);
}
