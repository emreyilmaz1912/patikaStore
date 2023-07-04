import controller.BrandControllers;
import controller.CategoryControllers;
import controller.ProductControllers;
import view.PatikaStore;

public class Main {
    public static void main(String[] args) {
        System.out.println("Patika Store Hoşgeldiniz!");

        CategoryControllers categoryControllers = new CategoryControllers();
        BrandControllers brandControllers = new BrandControllers();
        ProductControllers productControllers = new ProductControllers(categoryControllers, brandControllers);

        PatikaStore patikaStore = new PatikaStore(categoryControllers, brandControllers, productControllers);
        patikaStore.storeMenu();

    }
}