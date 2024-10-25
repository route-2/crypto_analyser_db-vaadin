package crypto_analyser_db.crypto.requests;

public class CategoryRequest {
    private String categoryName;

    public CategoryRequest() {}

    public CategoryRequest(String name) {
        this.categoryName = name;
    }

    public String getName() {
        return categoryName;
    }

    public void setName(String name) {
        this.categoryName = name;
    }
}
