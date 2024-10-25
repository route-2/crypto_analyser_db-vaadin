package crypto_analyser_db.crypto.views;

import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.grid.Grid;
import com.vaadin.flow.component.orderedlayout.VerticalLayout;
import com.vaadin.flow.component.textfield.TextField;
import com.vaadin.flow.router.Route;
import crypto_analyser_db.crypto.models.CryptoCategory;
import crypto_analyser_db.crypto.services.CryptoCategoryService;


@jakarta.annotation.security.PermitAll
@Route("categories")
public class CryptoCategoryView extends VerticalLayout {

    private static final long serialVersionUID = 1L;
	private CryptoCategoryService categoryService = null;
    private final Grid<CryptoCategory> grid = new Grid<>(CryptoCategory.class);
    private final CryptoCategoryForm categoryForm = new CryptoCategoryForm(categoryService);

    public CryptoCategoryView(CryptoCategoryService categoryService) {
        this.categoryService = categoryService;
        setSizeFull();
        configureGrid();
        add(createFilter(), grid, categoryForm);
        refreshGrid();
    }

    private TextField createFilter() {
        TextField filterTextField = new TextField("Filter by name");
        filterTextField.setPlaceholder("Type a name...");
        filterTextField.addValueChangeListener(event -> refreshGrid(filterTextField.getValue()));
        return filterTextField;
    }

    private void configureGrid() {
    	grid.setColumns("id", "categoryName"); // Use "categoryName" if that's the actual field name
        grid.asSingleSelect().addValueChangeListener(event -> {
            CryptoCategory selectedCategory = event.getValue();
            categoryForm.setCategory(selectedCategory); // Populate form with selected category
        });
    }

    private void refreshGrid() {
        grid.setItems(categoryService.getAllCategories()); // Load all categories into the grid
    }

    private void refreshGrid(String filterText) {
        if (filterText.isEmpty()) {
            refreshGrid();
        } else {
            grid.setItems(categoryService.getCategoryByName(filterText)); // Filter categories
        }
    }
}
