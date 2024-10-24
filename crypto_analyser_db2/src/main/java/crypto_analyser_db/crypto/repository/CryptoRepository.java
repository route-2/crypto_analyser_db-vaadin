package crypto_analyser_db.crypto.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Repository;

import crypto_analyser_db.crypto.models.CryptoCategory;
import crypto_analyser_db.crypto.models.CryptoModel;

import java.util.List;
import java.util.Optional;

/**
 * Repository interface for performing CRUD operations on CryptoModel.
 * Extends JpaRepository, which provides standard JPA methods to interact with the database.
 */
@Repository  // Marks the interface as a Spring Data repository for dependency injection
public interface CryptoRepository extends JpaRepository<CryptoModel, Long> {

    /**
     * Find a cryptocurrency by its name.
     * 
     * @param name the name of the cryptocurrency
     * @return an Optional containing the cryptocurrency if found, or an empty Optional if not found.
     */
    Optional<CryptoModel> findByName(String name);

    /**
     * Find all cryptocurrencies whose price falls within a specific range.
     * 
     * @param minPrice the minimum price (inclusive)
     * @param maxPrice the maximum price (inclusive)
     * @return a list of cryptocurrencies that have a price within the specified range.
     */
    List<CryptoModel> findByPriceBetween(double minPrice, double maxPrice);
    
    // Finds all cryptocurrencies by category ID
    List<CryptoModel> findByCategoryId(Long categoryId);
    
    CryptoCategory findByCategoryName(String categoryName);


    /**
     * Find a cryptocurrency by its symbol (e.g., BTC for Bitcoin).
     * 
     * @param symbol the symbol of the cryptocurrency
     * @return an Optional containing the cryptocurrency if found, or an empty Optional if not found.
     */
    Optional<CryptoModel> findBySymbol(String symbol);

    /**
     * Find all cryptocurrencies.
     * 
     * @return a list of all cryptocurrencies in the database.
     */
    @NonNull
	List<CryptoModel> findAll();
    
    
    
}
