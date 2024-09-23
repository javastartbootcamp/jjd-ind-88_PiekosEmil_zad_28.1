package pl.javastart.restoffers.offers;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class OffersService {

    private final OffersRepository offersRepository;

    public OffersService(OffersRepository offersRepository) {
        this.offersRepository = offersRepository;
    }

    List<Offer> findAll() {
        List<Offer> offers = new ArrayList<>();
        for (Offer offer : offersRepository.findAll()) {
            offers.add(offer);
        }
        return offers;
    }

    List<Offer> findOffers(String text) {
        List<Offer> offers = new ArrayList<>();
        for (Offer offer : offersRepository.findByTitleContainingIgnoreCase(text)) {
            offers.add(offer);
        }
        return offers;
    }

    Offer saveOffer(Offer offer) {
        return offersRepository.save(offer);
    }

    void deleteOffer(Long id) {
        offersRepository.deleteById(id);
    }

    public Integer countOffersInCategory(String category) {
        Integer counter = 0;
        for (Offer offer : offersRepository.findAll()) {
            if (offer.getCategoryName().equals(category)) {
                counter++;
            }
        }
        return counter;
    }

    Offer findOfferById(Long id) {
        return offersRepository.findById(id).orElse(null);
    }

    public void deleteAllOffersFromCategory(String categoryName) {
        offersRepository.deleteAllByCategoryName(categoryName);
    }
}
