package crypto_analyser_db.crypto.services;

import crypto_analyser_db.crypto.models.CryptoCategory;
import java.util.List;
import java.util.Optional;

public interface CryptoCategoryService {

    List<CryptoCategory> getAllCategories();

    Optional<CryptoCategory> getCategoryById(Long id);

    Optional<CryptoCategory> getCategoryByName(String categoryName);

    CryptoCategory addCategory(CryptoCategory category);

    CryptoCategory updateCategory(Long id, CryptoCategory category);

    void deleteCategory(Long id);
}
