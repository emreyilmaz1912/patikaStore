package controller;

import model.Brand;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class BrandControllers {

    private static final List<Brand> brands = new ArrayList<>();
    private static int lastBrandID;

    public BrandControllers() {
        createObjects();
    }

    private void createObjects() {
        String[] brandNames = {"Apple", "Samsun", "Lenovo", "Huawei", "Casper", "Asus", "HP", "Xiaomi", "Monster"};
        for (String name : brandNames) {
            brands.add(new Brand(++lastBrandID, name));
        }
    }

    public Brand getBrandName(String name) {
        for (Brand brand : brands) {
            if (brand.getBrandName().equalsIgnoreCase(name)) return brand;
        }
        return null;
    }

    public List<Brand> getBrands() {
        Collections.sort(brands);
        return brands;
    }

    public int getLastBrandID() {
        return lastBrandID;
    }

    public void addNewBrand(String name) {
        brands.add(new Brand(++lastBrandID, name));
    }

    public void deleteBrand(Brand brand) {
        brands.remove(brand);
    }

    public Brand addAndGetBrand(String name) {
        Brand brand = new Brand(++lastBrandID, name);
        brands.add(brand);
        return brand;
    }

}
