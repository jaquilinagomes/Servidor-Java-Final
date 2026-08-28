package model;

public class ProdutoExterno {
    private String id;
    private String title;
    private String description;
    private String category;
    private Double price;

    public ProdutoExterno(String id, String title, String description, String category, Double price) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.category = category;
        this.price = price;
    }
    public void setId(String id) {
        this.id = id;
    }
    public String getid() {
        return this.id;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    public String getTitle() {
        return this.title;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    public String getDescription() {
        return this.description;
    }

    public void setCategory(String category) {
        this.category = category;
    }
    public String getCategory() {
        return this.category;
    }

    public void setPrice(Double price) {
        this.price = price;
    }
    public Double getPrice() {
        return this.price;
    }

}
