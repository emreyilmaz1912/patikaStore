package model;

public class Product {
    private int productID;
    private String productName;
    private Brand productBrand;
    private Category productCategory;
    private double price;
    private double discountRate;
    private int stockQuantity;
    private String internalStorage;
    private String ramCapacity;
    private String color;
    private String batteryCapacity;

    public Product(int productID, String productName, Category productCategory, double price, double discountRate, int stockQuantity, Brand productBrand, String internalStorage, String screenSize, String ramCapacity, String color, String batteryCapacity) {
        this.productID = productID;
        this.productName = productName;
        this.productCategory = productCategory;
        this.price = price;
        this.discountRate = discountRate;
        this.stockQuantity = stockQuantity;
        this.productBrand = productBrand;
        this.internalStorage = internalStorage;
        this.ramCapacity = ramCapacity;
        this.color = color;
        this.batteryCapacity = batteryCapacity;
    }

    public Brand getProductBrand() {
        return productBrand;
    }

    public void setProductBrand(Brand productBrand) {
        this.productBrand = productBrand;
    }

    public int getProductID() {
        return productID;
    }

    public void setProductID(int productID) {
        this.productID = productID;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public Category getProductCategory() {
        return productCategory;
    }

    public void setProductCategory(Category productCategory) {
        this.productCategory = productCategory;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public double getDiscountRate() {
        return discountRate;
    }

    public void setDiscountRate(double discountRate) {
        this.discountRate = discountRate;
    }

    public int getStockQuantity() {
        return stockQuantity;
    }

    public void setStockQuantity(int stockQuantity) {
        this.stockQuantity = stockQuantity;
    }

    public String getInternalStorage() {
        return internalStorage;
    }

    public void setInternalStorage(String internalStorage) {
        this.internalStorage = internalStorage;
    }

    public String getRamCapacity() {
        return ramCapacity;
    }

    public void setRamCapacity(String ramCapacity) {
        this.ramCapacity = ramCapacity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public String getBatteryCapacity() {
        return batteryCapacity;
    }

    public void setBatteryCapacity(String batteryCapacity) {
        this.batteryCapacity = batteryCapacity;
    }
}
