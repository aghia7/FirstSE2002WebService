package entities;

public class Product {
    private int id;
    private String name;
    private String description;
    private double price;
    private int categoryId;
    private Category category;

    public Product() {

    }

    public Product(String name, String description, double price, int categoryId) {
        setName(name);
        setDescription(description);
        setPrice(price);
        setCategoryId(categoryId);
    }

    public Product(int id, String name, String description, double price, int categoryId, Category category) {
        this(name, description, price, categoryId);
        setId(id);
        setCategory(category);
    }

    public int getId() {
        return id;
    }

    protected void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public void setCategoryId(int categoryId) {
        this.categoryId = categoryId;
    }

    public int getCategoryId() {
        return categoryId;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    @Override
    public String toString() {
        return "Product{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", price=" + price +
                ", category=" + category +
                '}';
    }
}
