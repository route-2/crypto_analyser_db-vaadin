package crypto_analyser_db.crypto.services;

import java.util.List;
import java.util.Optional;

import crypto_analyser_db.crypto.models.CryptoCategory;
import crypto_analyser_db.crypto.models.CryptoModel;

/**
 * This interface defines the contract for the service layer that will handle cryptocurrency operations.
 * It includes methods for retrieving, adding, updating, and deleting cryptocurrency data.
 */

public interface CryptoService {

    /**
     * Retrieve all cryptocurrencies.
     *
     * @return a list of all cryptocurrencies.
     */
    List<CryptoModel> getAllCryptos();

    /**
     * Retrieve a cryptocurrency by its ID.
     *
     * @param id the unique ID of the cryptocurrency
     * @return the cryptocurrency if found
     */
    CryptoModel getCryptoById(Long id);

    /**
     * Add a new cryptocurrency to the system.
     *
     * @param newCrypto the cryptocurrency to be added
     * @return true if successfully added, false if it already exists
     */
    boolean addCrypto(CryptoModel newCrypto);

    /**
     * Update an existing cryptocurrency's information.
     *
     * @param id the unique ID of the cryptocurrency to be updated
     * @param updatedCrypto the updated cryptocurrency object
     * @return true if successfully updated, false if not found
     */
    boolean updateCrypto(Long id, CryptoModel updatedCrypto);

    /**
     * Delete a cryptocurrency by its ID.
     *
     * @param id the unique ID of the cryptocurrency to be deleted
     * @return true if successfully deleted, false if not found
     */
    boolean deleteCrypto(Long id);

    /**
     * Find cryptocurrencies within a specific price range.
     *
     * @param minPrice the minimum price (inclusive)
     * @param maxPrice the maximum price (inclusive)
     * @return a list of cryptocurrencies that fall within the given price range
     */
    List<CryptoModel> findByPriceBetween(double minPrice, double maxPrice);

    /**
     * Retrieve a cryptocurrency by its symbol.
     *
     * @param symbol the symbol of the cryptocurrency (e.g., BTC for Bitcoin)
     * @return an Optional containing the cryptocurrency if found
     */
    Optional<CryptoModel> getCryptocurrencyBySymbol(String symbol);

	/**
	 * Retrieve the one-hour change for a cryptocurrency by its ID.
	 *
	 * @param id the unique ID of the cryptocurrency
	 * @return the one-hour change if found
	 * @throws CryptoNotFoundExceptions if the cryptocurrency with the given ID is not found
	 */
    /**
     * Retrieve the one-hour change for a cryptocurrency by its ID.
     *
     * @param id the unique ID of the cryptocurrency
     * @return the one-hour change if found
     * @throws CryptoNotFoundExceptions if the cryptocurrency with the given ID is not found
     */
    Double getOneHourChangeById(Long id);

	
    
   
    
    
}
