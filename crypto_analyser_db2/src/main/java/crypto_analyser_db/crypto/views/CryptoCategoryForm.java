package crypto_analyser_db.crypto.views;


import com.vaadin.flow.component.button.Button;
import com.vaadin.flow.component.formlayout.FormLayout;
import com.vaadin.flow.component.notification.Notification;
import com.vaadin.flow.component.orderedlayout.HorizontalLayout;
import com.vaadin.flow.component.textfield.TextField;
import crypto_analyser_db.crypto.models.CryptoCategory;
import crypto_analyser_db.crypto.services.CryptoCategoryService;

public class CryptoCategoryForm extends FormLayout {

    private static final long serialVersionUID = 1L;
	private final CryptoCategoryService categoryService;
    private final TextField name = new TextField("Category Name");
    private CryptoCategory category;

    public CryptoCategoryForm(CryptoCategoryService categoryService) {
        this.categoryService = categoryService;
        add(name, createButtonsLayout());
    }

    private HorizontalLayout createButtonsLayout() {
        Button save = new Button("Save", event -> save());
        Button delete = new Button("Delete", event -> delete());
        return new HorizontalLayout(save, delete);
    }

    public void setCategory(CryptoCategory category) {
        this.category = category;
        if (category != null) {
            name.setValue(category.getCategoryName());
        } else {
            name.clear();
        }
    }

    private void save() {
        if (category == null) {
            category = new CryptoCategory();
        }
        category.setCategoryName(name.getValue());
        categoryService.save(category);
        Notification.show("Category saved");
        setCategory(null); // Clear the form after saving
    }

    private void delete() {
        if (category != null) {
            categoryService.deleteCategory(category.getId());
            Notification.show("Category deleted");
            setCategory(null); // Clear the form after deleting
        }
    }
}
