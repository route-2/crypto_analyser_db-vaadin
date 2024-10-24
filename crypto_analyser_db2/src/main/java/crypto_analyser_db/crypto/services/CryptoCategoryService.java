package crypto_analyser_db.crypto.services;

import crypto_analyser_db.crypto.models.CryptoCategory;

import java.util.List;
import java.util.Optional;

import com.github.javaparser.JavaToken.Category;
import com.vaadin.flow.data.provider.DataProvider;


public interface CryptoCategoryService {

    List<CryptoCategory> getAllCategories();

    Optional<CryptoCategory> getCategoryById(Long id);

    List<CryptoCategory> getCategoryByName(String categoryName);

    CryptoCategory addCategory(CryptoCategory category);

    CryptoCategory updateCategory(Long id, CryptoCategory category);

    void deleteCategory(Long id);

	void save(CryptoCategory category);




	
}
