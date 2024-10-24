package crypto_analyser_db.crypto.repository;

import crypto_analyser_db.crypto.models.CryptoCategory;
import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface CryptoCategoryRepository extends CrudRepository<CryptoCategory, Long> {
    
    // Find a category by its name
    List<CryptoCategory> findByCategoryName(String categoryName);
}
