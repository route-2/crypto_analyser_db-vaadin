package crypto_analyser_db.crypto.serviceimpl;

import crypto_analyser_db.crypto.exceptions.CryptoNotFoundExceptions;
import crypto_analyser_db.crypto.models.CryptoModel;
import crypto_analyser_db.crypto.repository.CryptoRepository;
import crypto_analyser_db.crypto.services.CryptoService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

/**
 * Service implementation for managing cryptocurrency data.
 * Implements the CryptoService interface to provide methods for retrieving, adding, updating, and deleting cryptocurrencies.
 */
@Service 
public  class CryptoServiceImpl implements CryptoService {

    // Injecting the CryptoRepository for database operations
    private final CryptoRepository cryptoRepository;

    @Autowired  // Constructor-based dependency injection
    public CryptoServiceImpl(CryptoRepository cryptoRepository) {
        this.cryptoRepository = cryptoRepository;
    }

    /**
     * Retrieve all cryptocurrencies from the database.
     *
     * @return a list of all cryptocurrencies.
     */
    @Override
    public List<CryptoModel> getAllCryptos() {
        return (List<CryptoModel>) cryptoRepository.findAll();
    }

    /**
     * Retrieve a cryptocurrency by its ID.
     *
     * @param id the unique ID of the cryptocurrency
     * @return the cryptocurrency if found
     * @throws CryptoNotFoundExceptions if the cryptocurrency with the given ID is not found
     */
    @Override
    public CryptoModel getCryptoById(Long id) {
        Optional<CryptoModel> cryptoOptional = cryptoRepository.findById(id);
       
        if (cryptoOptional.isPresent()) {
            return cryptoOptional.get();  // Return the found cryptocurrency
        } else {
            throw new CryptoNotFoundExceptions(id);  // Throw an exception if not found
        }
    }

    /**
     * Add a new cryptocurrency to the database.
     *
     * @param newCrypto the cryptocurrency to be added
     * @return true if successfully added, false if it already exists
     */
    @Override
    public boolean addCrypto(CryptoModel newCrypto) {
        if (cryptoRepository.findByName(newCrypto.getName()).isPresent()) {
            return false;  // Return false if the crypto already exists
        }
        cryptoRepository.save(newCrypto);  // Save the new cryptocurrency to the database
        return true;
    }

    /**
     * Update an existing cryptocurrency's details.
     *
     * @param id the unique ID of the cryptocurrency to be updated
     * @param updatedCrypto the updated cryptocurrency object
     * @return true if successfully updated, false if the crypto was not found
     */
    @Override
    public boolean updateCrypto(Long id, CryptoModel updatedCrypto) {
        if (!cryptoRepository.existsById(id)) {
            return false;  // Return false if the cryptocurrency is not found
        }
        updatedCrypto.setId(id);  // Set the ID to ensure the correct record is updated
        cryptoRepository.save(updatedCrypto);  // Save the updated details to the database
        return true;
    }

    /**
     * Delete a cryptocurrency by its ID.
     *
     * @param id the unique ID of the cryptocurrency to be deleted
     * @return true if successfully deleted, false if not found
     */
    @Override
    public boolean deleteCrypto(Long id) {
        if (!cryptoRepository.existsById(id)) {
            return false;  // Return false if the cryptocurrency is not found
        }
        cryptoRepository.deleteById(id);  // Delete the cryptocurrency from the database
        return true;
    }

    /**
     * Retrieve a cryptocurrency by its symbol.
     *
     * @param symbol the symbol of the cryptocurrency (e.g., BTC for Bitcoin)
     * @return an Optional containing the cryptocurrency if found
     */
    @Override
    public Optional<CryptoModel> getCryptocurrencyBySymbol(String symbol) {
        return cryptoRepository.findBySymbol(symbol);  // Query the repository for the cryptocurrency by its symbol
    }

    /**
     * Find cryptocurrencies within a specific price range.
     *
     * @param minPrice the minimum price (inclusive)
     * @param maxPrice the maximum price (inclusive)
     * @return a list of cryptocurrencies that fall within the given price range
     */
    @Override
    public List<CryptoModel> findByPriceBetween(double minPrice, double maxPrice) {
        return cryptoRepository.findByPriceBetween(minPrice, maxPrice);  // Query the repository for cryptocurrencies within the price range
    }
    
    /**
     * Retrieve the one-hour change for a cryptocurrency by its ID.
     *
     * @param id the unique ID of the cryptocurrency
     * @return the one-hour change if found
     * @throws CryptoNotFoundExceptions if the cryptocurrency with the given ID is not found
     */
    @Override
    public Double getOneHourChangeById(Long id) {
        Optional<CryptoModel> cryptoOptional = cryptoRepository.findById(id);

        if (cryptoOptional.isPresent()) {
            return cryptoOptional.get().getOneHourChange();  // Return the one-hour change of the found cryptocurrency
        } else {
            throw new CryptoNotFoundExceptions(id);  // Throw an exception if not found
        }
    }
}
