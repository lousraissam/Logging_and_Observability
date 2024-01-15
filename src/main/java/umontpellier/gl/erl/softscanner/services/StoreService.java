package umontpellier.gl.erl.softscanner.services;

import org.springframework.stereotype.Service;
import umontpellier.gl.erl.softscanner.exception.ProductAlreadyExisteException;
import umontpellier.gl.erl.softscanner.exception.ProductNotFoundException;
import umontpellier.gl.erl.softscanner.model.Product;
import umontpellier.gl.erl.softscanner.model.Store;

import java.util.List;

@Service
public class StoreService {


    public void getStore(Store store) {
        List<Product> products = store.getProducts();
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("Products :");
        stringBuilder.append('\n');
        if (products.size() == 0) System.out.println("The Store is empty");
        else {
            for (Product product : products) {
                stringBuilder.append(product);
                stringBuilder.append('\n');
            }
            System.out.println(stringBuilder.toString());
            System.out.println("Store displayed");
        }
    }

    public void initStore(Store store) {
        Product p1 = new Product("10", "Nouilles pad Thai Bio CEREAL BIO", "125", "12/01/23");
        Product p2 = new Product("11", "Gélatine alimentaire en feuilles bio VAHINE", "115", "12/01/23");
        Product p3 = new Product("12", "Gélifiant alimentaire Agar-agar VAHINE", "95", "12/01/23");
        Product p4 = new Product("13", "Complément alimentaire brûle graisse JUVAMINE", "99", "12/01/23");
        Product p5 = new Product("14", "Complément alimentaire transit figues/fibres GAYELORD HAUSER", "100", "12/01/23");

        try {
            addProduct(store, p1);
            addProduct(store, p2);
            addProduct(store, p3);
            addProduct(store, p4);
            addProduct(store, p5);
        } catch (ProductAlreadyExisteException e) {
            System.out.println("Exception : ");
            System.out.println(e.getMessage());
        }
        System.out.println("Store initialized");
    }

    public Product addProduct(Store store, Product product) throws ProductAlreadyExisteException {
        //try {
            //fetchProduct(store, product.getID());
            store.getProducts().add(product);
            return product;
          //  throw new ProductAlreadyExisteException("A product with the same ID already exists.");
        /*} catch (ProductNotFoundException e) {
            store.getProducts().add(product);
            return product;
        }*/
    }

    public Product fetchProduct(Store store, String ID) throws ProductNotFoundException {
        if (store.getProducts().size() == 0) {
            System.out.println("Sorry, The store is empty");
        }
        else {
            for (Product product : store.getProducts()) {
                if (product.getID().equals(ID)) {
                    return product;
                }
            }
            System.out.println("No product with the provided ID exists.");
        }
        return null;
        //throw new ProductNotFoundException("No product with the provided ID exists.");
    }

    public Product deleteProduct(Store store, String ID) throws ProductNotFoundException {
        Product p = fetchProduct(store, ID);
        store.getProducts().remove(p);
        return p;
    }
}
