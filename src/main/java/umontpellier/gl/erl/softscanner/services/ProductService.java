package umontpellier.gl.erl.softscanner.services;

import org.springframework.stereotype.Service;
import umontpellier.gl.erl.softscanner.exception.ProductAlreadyExisteException;
import umontpellier.gl.erl.softscanner.exception.ProductNotFoundException;
import umontpellier.gl.erl.softscanner.model.Product;
import umontpellier.gl.erl.softscanner.model.Store;

import java.util.Scanner;

@Service
public class ProductService {

    static Scanner sc = new Scanner(System.in);
    StoreService storeService = new StoreService();
    public Product createProduct(Store store) {
        System.out.println("Product ID :");
        String idProduct = sc.nextLine();
        System.out.println("Product name :");
        String nameProduct = sc.nextLine();
        System.out.println("Product price :");
        String priceProduct = sc.nextLine();
        System.out.println("Product Expiration Date :");
        String dateString = sc.nextLine();
        Product product = null;
        try {
            product = new Product(idProduct, nameProduct, priceProduct, dateString);
            System.out.println(storeService.addProduct(store, product));
        } catch (ProductAlreadyExisteException e) {
            System.out.println("Exception : ");
            System.out.println(e.getMessage());
        }
        System.out.println("Product created");
        return product;
    }


    public Product updateProduct(Store store) throws ProductNotFoundException {
        System.out.println("ID :");
        String idProduct = sc.nextLine();
        Product product = storeService.deleteProduct(store, idProduct);
        if (product != null) {
            System.out.println("New product name :");
            String nameProduct = sc.nextLine();
            System.out.println("New product price :");
            String priceProduct = sc.nextLine();
            System.out.println("New product Expiration Date :");
            String dateString = sc.nextLine();
            try {
                System.out.println(storeService.addProduct(store, new Product(idProduct, nameProduct, priceProduct, dateString)));
            } catch (ProductAlreadyExisteException e) {
                System.out.println("Exception : ");
                System.out.println(e.getMessage());
            }
            System.out.println("Product updated");
        }
        return product;
    }


    public void deleteProduct(Store store) {
        System.out.println("ID :");
        String idProduct = sc.nextLine();
        try {
            System.out.println(storeService.deleteProduct(store, idProduct));
        } catch (ProductNotFoundException e) {
            System.out.println("Exception : ");
            System.out.println(e.getMessage());
        }
        System.out.println("Product deleted");
    }


    public Product getProduct(Store store) {
        System.out.println("ID :");
        String idProduct = sc.nextLine();
        Product product = null;
        try {
            product = storeService.fetchProduct(store, idProduct);
            System.out.println(product);
        } catch (ProductNotFoundException e) {
            System.out.println("Exception : ");
            System.out.println(e.getMessage());
        }
        System.out.println("Product retrieved");
        return product;
    }

}
