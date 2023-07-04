package controller;

import model.Brand;
import model.Category;
import model.Product;

import java.util.ArrayList;
import java.util.List;

public class ProductControllers {
    private static final List<Product> products = new ArrayList<>();
    private static int lastProductID;
    private final CategoryControllers categoryControllers;
    private final BrandControllers brandControllers;

    public ProductControllers(CategoryControllers categoryControllers, BrandControllers brandControllers) {
        this.categoryControllers = categoryControllers;
        this.brandControllers = brandControllers;
        createDefaultObj();
    }

    private void createDefaultObj() {
        Category phone = categoryControllers.getCategoryByName("Telefon");
        Category notebook = categoryControllers.getCategoryByName("Notebook");

        products.add(new Product(++lastProductID, "Iphone 14 Pro", phone, 60000, 0, 10, brandControllers.getBrandName("Iphone"), "128 GB", "6.5 inches", "6 GB", "Deep Purple", "5200 mAh"));

        products.add(new Product(++lastProductID, "Red-Mi Note 10 Pro", phone, 4012, 0, 15, brandControllers.getBrandName("Xiaomi"), "256 GB", "6.5 inches", "16 GB", "Black", "4000 mAh"));

        products.add(new Product(++lastProductID, "Asus Tuf A15", notebook, 5600, 8, 4, brandControllers.getBrandName("ASUS"), "1 TB", "15 inches", "32 GB", "Black", "***"));
        products.add(new Product(++lastProductID, "Asus Rog Series", notebook, 6500, 8, 4, brandControllers.getBrandName("ASUS"), "2 TB", "15 inches", "32 GB", "Black", "***"));
    }

    public List<Product> getProducts() {
        return products;
    }

    public void deleteUncategorizeProducts() {
        for (Product product : products) {
            if (product.getProductCategory() == null) {
                products.remove(product);
                deleteUncategorizeProducts();
                break;
            }
        }
    }

    public void deleteUnbrandedProducts() {
        for (Product product : products) {
            if (product.getProductBrand() == null) {
                products.remove(product);

                deleteUnbrandedProducts();
                break;
            }
        }
    }

    public int getLastProductID() {
        return lastProductID;
    }

    public void addNewProduct(String productName, Category productCategory, double price, double discountRate, int stockQuantity, Brand productBrand, String internalStorage, String screenSize, String ramCapacity, String color, String batteryCapacity) {
        products.add(
                new Product(++lastProductID, productName, productCategory,
                        price, discountRate, stockQuantity, productBrand,
                        internalStorage, screenSize, ramCapacity, color, batteryCapacity)
        );

        System.out.println("New Product Added.");
    }

    public void deleteProduct(Product product) {
        products.remove(product);
    }

}

