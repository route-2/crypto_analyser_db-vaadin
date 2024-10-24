package crypto_analyser_db.crypto.controller;

import crypto_analyser_db.crypto.exceptions.CryptoNotFoundExceptions;
import crypto_analyser_db.crypto.models.CryptoModel;
import crypto_analyser_db.crypto.services.CryptoService;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import lombok.AllArgsConstructor;

import java.util.List;
import java.util.Optional;

@CrossOrigin(origins = "*")  // Allows cross-origin requests from any domain

@RestController  // Marks the class as a REST controller for handling HTTP requests
@AllArgsConstructor  // Generates a constructor with parameters for all final fields
@RequestMapping("/crypto")  // Defines the base URL path for this controller
public class CryptoController {

    private final CryptoService cryptoService;

    // Constructor for manual injection (if needed)
    public CryptoController(CryptoService cryptoService) {
        this.cryptoService = cryptoService;
    }

    /**
     * Get all cryptocurrencies in the database.
     * 
     * @return a list of all cryptocurrencies with HTTP status 200 (OK) or 204 (NO_CONTENT) if the list is empty.
     */
    @GetMapping("/all")
    public ResponseEntity<List<CryptoModel>> getAllCryptos() {
        System.out.println("Fetching all cryptocurrencies...");
        List<CryptoModel> cryptos = cryptoService.getAllCryptos();
        if (cryptos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // Returns 204 if no data
        }
        return new ResponseEntity<>(cryptos, HttpStatus.OK);  // Returns 200 with list of cryptos
    }
    
    @GetMapping("/one-hour-change/{id}")
    public ResponseEntity<Double> getOneHourChange(@PathVariable Long id) {
        try {
            Double oneHourChange = cryptoService.getOneHourChangeById(id);
            return new ResponseEntity<>(oneHourChange, HttpStatus.OK);
        } catch (CryptoNotFoundExceptions ex) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    /**
     * Get cryptocurrency by ID.
     * 
     * @param id the unique ID of the cryptocurrency
     * @return the cryptocurrency object with HTTP status 200 (OK) or throws CryptoNotFoundExceptions if not found.
     */
    @GetMapping("/{id}")
    public ResponseEntity<CryptoModel> getCryptoById(@PathVariable Long id) {
        CryptoModel crypto = cryptoService.getCryptoById(id);
        if (crypto == null) {
            throw new CryptoNotFoundExceptions(id);  // Custom exception thrown if crypto not found
        }
        return new ResponseEntity<>(crypto, HttpStatus.OK);  // Returns 200 with crypto data
    }

    /**
     * Get cryptocurrency by symbol.
     * 
     * @param symbol the unique symbol of the cryptocurrency (e.g., BTC for Bitcoin)
     * @return the cryptocurrency object with HTTP status 200 (OK) or 404 (NOT_FOUND) if the symbol does not exist.
     */
    @GetMapping("/symbol/{symbol}")
    public ResponseEntity<CryptoModel> getCryptocurrencyBySymbol(@PathVariable String symbol) {
        Optional<CryptoModel> crypto = cryptoService.getCryptocurrencyBySymbol(symbol);
        return crypto.map(ResponseEntity::ok)  // Returns 200 with crypto if found
                     .orElseGet(() -> ResponseEntity.notFound().build());  // Returns 404 if not found
    }

    /**
     * Add a new cryptocurrency to the database.
     * 
     * @param newCrypto the cryptocurrency object to be added
     * @return HTTP status 201 (CREATED) if successfully added, or 400 (BAD_REQUEST) if already exists or failed.
     */
    @PostMapping("/add")
    public ResponseEntity<String> addCrypto(@RequestBody CryptoModel newCrypto) {
        if (cryptoService.addCrypto(newCrypto)) {
            return new ResponseEntity<>("Crypto added successfully", HttpStatus.CREATED);
        } else {
            return new ResponseEntity<>("Crypto already exists or failed to save", HttpStatus.BAD_REQUEST);
        }
    }

    /**
     * Update an existing cryptocurrency in the database.
     * 
     * @param id the unique ID of the cryptocurrency to be updated
     * @param updatedCrypto the updated cryptocurrency data
     * @return HTTP status 200 (OK) if successfully updated, or 404 (NOT_FOUND) if the crypto was not found.
     */
    @PutMapping("/update/{id}")
    public ResponseEntity<String> updateCrypto(@PathVariable Long id, @RequestBody CryptoModel updatedCrypto) {
        if (cryptoService.updateCrypto(id, updatedCrypto)) {
            return new ResponseEntity<>("Crypto updated successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Crypto not found or failed to update", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Delete a cryptocurrency by ID.
     * 
     * @param id the unique ID of the cryptocurrency to be deleted
     * @return HTTP status 200 (OK) if successfully deleted, or 404 (NOT_FOUND) if the crypto was not found.
     */
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> deleteCrypto(@PathVariable Long id) {
        if (cryptoService.deleteCrypto(id)) {
            return new ResponseEntity<>("Crypto deleted successfully", HttpStatus.OK);
        } else {
            return new ResponseEntity<>("Crypto not found or failed to delete", HttpStatus.NOT_FOUND);
        }
    }

    /**
     * Get cryptocurrencies by a specified price range.
     * 
     * @param minPrice the minimum price to filter by
     * @param maxPrice the maximum price to filter by
     * @return a list of cryptocurrencies within the specified price range, or 204 (NO_CONTENT) if none found.
     */
    @GetMapping("/price-range")
    public ResponseEntity<List<CryptoModel>> getCryptosByPriceRange(@RequestParam double minPrice, @RequestParam double maxPrice) {
        List<CryptoModel> filteredCryptos = cryptoService.findByPriceBetween(minPrice, maxPrice);
        if (filteredCryptos.isEmpty()) {
            return new ResponseEntity<>(HttpStatus.NO_CONTENT);  // Returns 204 if no cryptos in the range
        }
        return new ResponseEntity<>(filteredCryptos, HttpStatus.OK);  // Returns 200 with the filtered list
    }
}
