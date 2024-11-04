package crypto_analyser_db.crypto.repository;

import crypto_analyser_db.crypto.models.CryptoCategory;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Optional;

public interface CryptoCategoryRepository extends CrudRepository<CryptoCategory, Long> {
    
	CryptoCategory findByCategoryName(String categoryName);
	
	    void deleteByCategoryName(String categoryName);

	    @Query("SELECT c.id FROM CryptoCategory c WHERE c.name = :name")
	    Long findIdByName(@Param("name") String categoryName);
}
