package controller;

import model.Category;

import java.util.ArrayList;
import java.util.List;

public class CategoryControllers {
    private static final List<Category> categories = new ArrayList<>();
    private static int lastCategoryID;

    public CategoryControllers() {
        createDefaultObject();
    }

    private void createDefaultObject() {
        Category phone = new Category(++lastCategoryID, "Cep Telefonu");
        Category notebook = new Category(++lastCategoryID, "Notebook");
        categories.add(phone);
        categories.add(notebook);
    }

    public List<Category> getCategories() {
        return categories;
    }

    public Category getCategoryByName(String name) {
        for (Category category : categories) {
            if (category.getCategoryName().equalsIgnoreCase(name)) return category;
        }

        return null;
    }

    public int getLastCategoryId() {
        return lastCategoryID;
    }

    public void addNewCategory(String name) {
        categories.add(new Category(++lastCategoryID, name));
    }

    public Category addAndGetCategory(String name) {
        Category category = new Category(++lastCategoryID, name);
        categories.add(category);
        return category;
    }

    public void deleteCategory(Category category) {
        categories.remove(category);
    }

}
