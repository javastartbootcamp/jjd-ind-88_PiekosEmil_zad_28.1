package pl.javastart.restoffers.offers;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequestMapping("api/offers")
@RestController
public class OffersRestController {

    private final OffersService offersService;

    public OffersRestController(OffersService offersService) {
        this.offersService = offersService;
    }

    @GetMapping
    List<Offer> getAllOffers(@RequestParam(name = "title", required = false) String text) {
        if (text != null) {
            return offersService.findOffers(text);
        } else {
            return offersService.findAll();
        }
    }

    @GetMapping("/count")
    int countOffers() {
        return offersService.findAll().size();
    }

    @PostMapping
    public ResponseEntity<Offer> saveOffer(@RequestBody Offer receivedOffer) {
        Offer newOffer = new Offer();
        newOffer.setTitle(receivedOffer.getTitle());
        newOffer.setDescription(receivedOffer.getDescription());
        newOffer.setImgUrl(receivedOffer.getImgUrl());
        newOffer.setPrice(receivedOffer.getPrice());
        newOffer.setCategory(receivedOffer.getCategoryName());
        offersService.saveOffer(newOffer);
        return ResponseEntity.ok(newOffer);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Offer> findOfferById(@PathVariable Long id) {
        return ResponseEntity.ok(offersService.findOfferById(id));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteOffer(@PathVariable Long id) {
        offersService.deleteOffer(id);
        return ResponseEntity.ok("Offer deleted successfully!");
    }

}