package view;

import controller.BrandControllers;
import controller.CategoryControllers;
import controller.ProductControllers;
import model.Brand;
import model.Category;
import model.Product;

import java.util.InputMismatchException;
import java.util.List;
import java.util.Scanner;

public class PatikaStore {
    private static final Scanner scanner = new Scanner(System.in);
    private final CategoryControllers categoryController;
    private final BrandControllers brandController;
    private final ProductControllers productController;


    public PatikaStore(CategoryControllers categoryController, BrandControllers brandController, ProductControllers productController) {
        this.categoryController = categoryController;
        this.brandController = brandController;
        this.productController = productController;
    }


    public void storeMenu() {
        String str = "--------------------------------------\n" + "PatikaStore Ürün Yönetim Paneli!\n" + "1- Kategori Operasyonlar,\n" + "2- Marka Operasyonları,\n" + "3- Ürün Operasyonları,\n" + "0- Çıkış!\n" + "--------------------------------------";
        System.out.println(str);


        int preference = getIntegerFromMinToMaxFromUser(0, 3, "Seçiminiz: ");


        switch (preference) {
            case 1 -> categoryOperationsMenu();
            case 2 -> brandOperationsMenu();
            case 3 -> productOperationsMenu();
            case 0 -> exitTheProgram();
        }
    }


    // CATEGORY OPERATIONS.....................
    private void categoryOperationsMenu() {
        String str = "---------------------------------\n" + "Patika Store Kategori Operasyonları!\n" + "1- Tüm Kategorileri Listele,\n" + "2- Kategorinin Ürünlerini Listele,\n" + "3- Yeni Kategori Ekle,\n" + "4- Kategoriyi Sil,\n" + "0- Ana Menüye Dön!\n" + "---------------------------------";
        System.out.println(str);


        int preference = getIntegerFromMinToMaxFromUser(0, 4, "Seçiminiz: ");


        switch (preference) {
            case 1 -> listAllCategories();
            case 2 -> listProductByCategory();
            case 3 -> addNewCategory();
            case 4 -> deleteCategory();
            case 0 -> storeMenu();
        }
    }


    public void listAllCategories() {
        List<Category> categories = categoryController.getCategories();


        if (categories.size() == 0) {
            System.out.println("Kategori bulunamadı!");
            categoryOperationsMenu();
            return;
        }


        System.out.println("----------------------------\nKategoriler...\n----------------------------");


        System.out.printf("| %1$-4s| %2$-20s|%n", "ID", "Kategori İsmi");


        for (Category c : categories) {
            System.out.printf("| %1$-4d| %2$-20s|%n", c.getCategoryID(), c.getCategoryName());
        }


        System.out.println("----------------------------");


        categoryOperationsMenu();
    }


    public void listProductByCategory() {
        List<Category> categories = categoryController.getCategories();


        if (categories.size() == 0) {
            System.out.println("Kategori bulunamadı!");
            categoryOperationsMenu();
            return;
        }


        System.out.println("----------------------------\nKategoriler...\n----------------------------");


        System.out.printf("| %1$-4s| %2$-20s|%n", "ID", "Kategori İsmi");


        for (Category c : categories) {
            System.out.printf("| %1$-4d| %2$-20s|%n", c.getCategoryID(), c.getCategoryName());
        }


        System.out.println("----------------------------");


        Category category = null;
        while (category == null) {
            int preference = getIntegerFromMinToMaxFromUser(0, categoryController.getLastCategoryId(), "Ürünlerini listelemek istediğiniz kategori ID'sini girin : ");


            for (Category c : categories) {
                if (c.getCategoryID() == preference) {
                    category = c;
                    break;
                }
            }
        }


        List<Product> products = productController.getProducts();


        System.out.println("----------------------------------------------\nÜrünler...\n----------------------------------------------");


        System.out.printf("| %1$-4s| %2$-20s| %3$-15s|%n", "ID", "Ürün İsmi", "Kategori");


        for (Product p : products) {
            if (p.getProductCategory().equals(category))
                System.out.printf("| %1$-4d| %2$-20s| %3$-15s| %n", p.getProductID(), p.getProductName(), p.getProductCategory().getCategoryName());
        }


        System.out.println("----------------------------------------------");


        categoryOperationsMenu();
    }


    public void addNewCategory() {
        System.out.print("Lütfen yeni kategori ismini giriniz : ");
        String name = scanner.nextLine();


        if (categoryController.getCategoryByName(name) != null) {
            System.out.println("Kategori ismi : (" + name + ") zaten var.");
        } else {
            categoryController.addNewCategory(name);


            System.out.println("Yeni kategori \"" + name + "\" eklendi.");
        }


        categoryOperationsMenu();
    }


    public void deleteCategory() {
        List<Category> categories = categoryController.getCategories();


        if (categories.size() == 0) {
            System.out.println("Kategori bulunamadı!");
            categoryOperationsMenu();
            return;
        }


        System.out.println("----------------------------\nKategoriler...\n----------------------------");


        System.out.printf("| %1$-4s| %2$-20s|%n", "ID", "Kategori İsmi");


        for (Category c : categories) {
            System.out.printf("| %1$-4d| %2$-20s|%n", c.getCategoryID(), c.getCategoryName());
        }


        System.out.println("----------------------------");


        Category category = null;
        while (category == null) {
            int preference = getIntegerFromMinToMaxFromUser(0, categoryController.getLastCategoryId(), "Silmek istediğiniz kategori ID'sini girin: ");


            for (Category c : categories) {
                if (c.getCategoryID() == preference) {
                    category = c;
                    break;
                }
            }
        }


        String repeatingMessage = "Kategori \"" + category.getCategoryName() + "\" ve \"ilgili tüm ürünler\" silinecek. Emin misiniz? (1-Evet, 2-Hayır) : ";
        int selected = getIntegerFromMinToMaxFromUser(1, 2, repeatingMessage);


        if (selected == 1) {


            for (Product p : productController.getProducts()) {
                if (p.getProductCategory().equals(category)) {
                    p.setProductCategory(null);
                }
            }
            categoryController.deleteCategory(category);


            System.out.println("Kategori silindi.");


            productController.deleteUncategorizeProducts();
        } else {
            System.out.println("Silme işleme iptal edildi..");
        }


        categoryOperationsMenu();
    }


    // CATEGORY OPERATIONS.....................


    // BRAND OPERATIONS........................
    private void brandOperationsMenu() {
        String str = "------------------------------\n" + "Patika Store Marka Operasyonları!\n" + "1- Tüm Markaları Listele,\n" + "2- Markaya Ait Tüm Ürünleri Listele,\n" + "3- Yeni Marka Ekle,\n" + "4- Marka Sil,\n" + "0- Ana Menüye Dön!\n" + "------------------------------";
        System.out.println(str);


        int preference = getIntegerFromMinToMaxFromUser(0, 4, "Seçiminiz: ");


        switch (preference) {
            case 1 -> listAllBrands();
            case 2 -> listProductsByBrand();
            case 3 -> addNewBrand();
            case 4 -> deleteBrand();
            case 0 -> storeMenu();
        }
    }

    private void listAllBrands() {
        List<Brand> brands = brandController.getBrands();


        if (brands.size() == 0) {
            System.out.println("Marka bulunamadı!");
            brandOperationsMenu();
            return;
        }


        System.out.println("----------------------------\nMarkalar...\n----------------------------");


        System.out.printf("| %1$-4s| %2$-20s|%n", "ID", "Marka İsmi");


        for (Brand b : brands) {
            System.out.printf("| %1$-4d| %2$-20s|%n", b.getBrandID(), b.getBrandName());
        }


        System.out.println("----------------------------");


        brandOperationsMenu();
    }


    private void listProductsByBrand() {
        List<Brand> brands = brandController.getBrands();


        if (brands.size() == 0) {
            System.out.println("Marka bulunamadı!");
            brandOperationsMenu();
            return;
        }


        System.out.println("----------------------------\nMarkalar...\n----------------------------");


        System.out.printf("| %1$-4s| %2$-20s|%n", "ID", "Marka İsmi");


        for (Brand b : brands) {
            System.out.printf("| %1$-4d| %2$-20s|%n", b.getBrandID(), b.getBrandName());
        }


        System.out.println("----------------------------");


        Brand brand = null;
        while (brand == null) {
            int preference = getIntegerFromMinToMaxFromUser(0, brandController.getLastBrandID(), "Ürünlerini listelemek istediğiniz marka ID'sini girin: ");


            for (Brand b : brands) {
                if (b.getBrandID() == preference) {
                    brand = b;
                    break;
                }
            }
        }


        List<Product> products = productController.getProducts();


        System.out.println("----------------------------------------------\nÜrünler...\n----------------------------------------------");


        System.out.printf("| %1$-4s| %2$-20s| %3$-15s|%n", "ID", "Ürün İsmi", "Kategori");


        for (Product p : products) {
            if (p.getProductBrand().equals(brand))
                System.out.printf("| %1$-4d| %2$-20s| %3$-15s| %n", p.getProductID(), p.getProductName(), p.getProductCategory().getCategoryName());
        }


        System.out.println("----------------------------------------------");


        brandOperationsMenu();
    }


    private void addNewBrand() {
        System.out.print("Lütfen yeni marka adını girin : ");
        String name = scanner.nextLine();


        if (brandController.getBrandName(name) != null) {
            System.out.println("Ürün ismi (" + name + ") zaten var.");
        } else {
            brandController.addNewBrand(name);


            System.out.println("Yeni ürün \"" + name + "\" eklendi.");
        }


        brandOperationsMenu();
    }


    private void deleteBrand() {
        List<Brand> brands = brandController.getBrands();


        if (brands.size() == 0) {
            System.out.println("Marka bulunamadı!");
            brandOperationsMenu();
            return;
        }


        System.out.println("----------------------------\nMarkalar...\n----------------------------");


        System.out.printf("| %1$-4s| %2$-20s|%n", "ID", "Marka İsmi");


        for (Brand b : brands) {
            System.out.printf("| %1$-4d| %2$-20s|%n", b.getBrandID(), b.getBrandName());
        }


        System.out.println("----------------------------");


        Brand brand = null;
        while (brand == null) {
            int preference = getIntegerFromMinToMaxFromUser(0, brandController.getLastBrandID(), "Silmek istediğiniz marka ID'sini girin: ");


            for (Brand b : brands) {
                if (b.getBrandID() == preference) {
                    brand = b;
                    break;
                }
            }
        }


        String repeatingMessage = "Marka \"" + brand.getBrandName() + "\" ve \"ilgili tüm ürünler\" silinecek. Emin misiniz? (1-Evet, 2-Hayır) : ";
        int selected = getIntegerFromMinToMaxFromUser(1, 2, repeatingMessage);


        if (selected == 1) {


            for (Product p : productController.getProducts()) {
                if (p.getProductBrand().equals(brand)) {
                    p.setProductBrand(null);
                }
            }
            brandController.deleteBrand(brand);


            System.out.println("Marka silindi.");


            productController.deleteUnbrandedProducts();
        } else {
            System.out.println("Silme işlemi iptal edildi..");
        }


        brandOperationsMenu();
    }


    // BRAND OPERATIONS........................


    // PRODUCT OPERATIONS......................
    private void productOperationsMenu() {
        String str = "--------------------------------\n" + "Patika Store Ürün Operasyonları!\n" + "1- Tüm Ürünleri Listele,\n" + "2- Yeni Ürün Ekle,\n" + "3- Ürün Sil,\n" + "0- Ana Menüye Dön!\n" + "--------------------------------";
        System.out.println(str);


        int preference = getIntegerFromMinToMaxFromUser(0, 3, "Seçiminiz : ");


        switch (preference) {
            case 1 -> listAllProducts();
            case 2 -> addNewProduct();
            case 3 -> deleteProduct();
            case 0 -> storeMenu();
        }
    }


    private void listAllProducts() {
        List<Product> products = productController.getProducts();


        System.out.println("----------------------------\nÜrünler...\n----------------------------");


        System.out.printf("| %1$-4s| %2$-20s|%n", "ID", "Ürün İsmi");


        for (Product product : products) {
            System.out.printf("| %1$-4d| %2$-20s|%n", product.getProductID(), product.getProductName());
        }


        System.out.println("----------------------------");


        productOperationsMenu();
    }


    private void addNewProduct() {
        //Scanner scanner = new Scanner(System.in);
        double price, discount;
        int stock;
        System.out.println("Eklemek istediğiniz ürünün özelliklerini giriniz.");


        System.out.print("Ürünün ismini giriniz : ");
        String name = scanner.nextLine().trim();


        System.out.print("Ürünün markasını giriniz : ");
        String brandName = scanner.nextLine().trim();


        System.out.print("Kategori ismini giriniz : ");
        String categoryName = scanner.nextLine().trim();


        System.out.print("Ekren ölçüsünü giriniz : ");
        String screenSize = scanner.nextLine().trim();


        System.out.print("Ram kapasitesini(GB) giriniz  : ");
        String ramCapacity = scanner.nextLine().trim();


        System.out.print("Dahili hafızasını(GB) giriniz : ");
        String internalStorage = scanner.nextLine().trim();


        System.out.print("Rengini giriniz : ");
        String color = scanner.nextLine().trim();


        System.out.print("Batarya kapasitesini giriniz : ");
        String batteryCapacity = scanner.nextLine().trim();


        price = getDoubleFromMinToMaxFromUser(0, Double.MAX_VALUE, "Fiyatı girin (yalnızca sayısal değer) : ");


        discount = getDoubleFromMinToMaxFromUser(0, 100, "İndirimi girin (yalnızca 0-100 arası sayısal değer) : ");


        stock = getIntegerFromMinToMaxFromUser(0, Integer.MAX_VALUE, "Stok miktarını girin (Yalnızca Tamsayı 0 ve üstü) : ");


        Brand brand = brandController.getBrandName(brandName);
        if (brand == null) {
            brand = brandController.addAndGetBrand(brandName);
        }


        Category category = categoryController.getCategoryByName(categoryName);
        if (category == null) {
            category = categoryController.addAndGetCategory(categoryName);
        }


        productController.addNewProduct(name, category, price, discount, stock, brand, internalStorage, screenSize, ramCapacity, color, batteryCapacity);


        productOperationsMenu();
    }


    private void deleteProduct() {
        List<Product> products = productController.getProducts();


        System.out.println("----------------------------\nÜrünler...\n----------------------------");


        System.out.printf("| %1$-4s| %2$-20s|%n", "ID", "Ürün İsmi");


        for (Product product : products) {
            System.out.printf("| %1$-4d| %2$-20s|%n", product.getProductID(), product.getProductName());
        }


        System.out.println("----------------------------");


        Product product = null;
        while (product == null) {
            int preference = getIntegerFromMinToMaxFromUser(0, productController.getLastProductID(), "Silmek istediğiniz ürün ID'sini girin: ");


            for (Product p : products) {
                if (p.getProductID() == preference) {
                    product = p;
                    break;
                }
            }
        }


        String repeatingMessage = "Ürün \"" + product.getProductName() + "\" silinecek. Emin misin?? (1-Evet, 2-Hayır) : ";
        int selected = getIntegerFromMinToMaxFromUser(1, 2, repeatingMessage);


        if (selected == 1) {


            productController.deleteProduct(product);


            System.out.println("Ürün silindi.");
        } else {
            System.out.println("Silme iptal edildi....");
        }


        productOperationsMenu();
    }


    // PRODUCT OPERATIONS......................

    private void exitTheProgram() {
        System.out.println("Çıkış...");
    }


    private int getIntegerFromMinToMaxFromUser(int min, int max, String repeatingMessage) {
        int selection;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(repeatingMessage);
            try {
                selection = scanner.nextInt();
                if (selection >= min && selection <= max) break;
                else System.out.println("Geçersiz Giriş!");
            } catch (InputMismatchException e) {
                System.out.println("Geçersiz Giriş!");
                scanner.next();
            }
        }
        return selection;
    }


    private double getDoubleFromMinToMaxFromUser(double min, double max, String repeatingMessage) {
        double selection;
        Scanner scanner = new Scanner(System.in);
        while (true) {
            System.out.print(repeatingMessage);
            try {
                selection = scanner.nextDouble();
                if (selection >= min && selection <= max) break;
                else System.out.println("Geçersiz Giriş!");
            } catch (InputMismatchException e) {
                System.out.println("Geçersiz Giriş!");
                scanner.next();
            }
        }
        return selection;
    }


}
