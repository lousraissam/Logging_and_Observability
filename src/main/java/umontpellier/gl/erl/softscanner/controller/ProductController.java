package umontpellier.gl.erl.softscanner.controller;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import umontpellier.gl.erl.softscanner.SoftScannerApplication;
import umontpellier.gl.erl.softscanner.exception.ProductAlreadyExisteException;
import umontpellier.gl.erl.softscanner.exception.ProductNotFoundException;
import umontpellier.gl.erl.softscanner.model.Product;
import umontpellier.gl.erl.softscanner.model.Store;
import umontpellier.gl.erl.softscanner.services.ProductService;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private static ProductService productService = new ProductService();
    private static Logger productLogger = Logger.getLogger(ProductController.class.getName());
    private FileHandler fileHandler;

    public ProductController() {
        try {
            this.fileHandler = new FileHandler("ProductController.log", true);
            productLogger.addHandler(this.fileHandler);
        } catch (SecurityException | IOException e) {
            productLogger.severe("Error initializing FileHandler");
        }
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(Store store) {
        Product product = productService.createProduct(store);

        // Log structured information directly
        productLogger.info(String.format("Timestamp: %s, Event: Product Creation, User: %s, Action: createProduct, Product: %s",
                LocalDateTime.now(), SoftScannerApplication.getCurrentUser(), product));
    }

    @PutMapping("/update")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void updateProduct(Store store) throws ProductAlreadyExisteException, ProductNotFoundException {
        Product product = productService.updateProduct(store);

        // Log structured information directly
        productLogger.info(String.format("Timestamp: %s, Event: Product Update, User: %s, Action: updateProduct, Product: %s",
                LocalDateTime.now(), SoftScannerApplication.getCurrentUser(), product));
    }

    @DeleteMapping("/delete")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void deleteProduct(Store store) {
        // Log structured information directly
        productLogger.info(String.format("Timestamp: %s, Event: Product Deletion, User: %s, Action: deleteProduct",
                LocalDateTime.now(), SoftScannerApplication.getCurrentUser()));

        productService.deleteProduct(store);
    }

    @GetMapping("/get")
    @ResponseStatus(value = HttpStatus.OK)
    public void getProduct(Store store) {
        Product product = productService.getProduct(store);

        // Log structured information directly
        productLogger.info(String.format("Timestamp: %s, Event: Product Retrieval, User: %s, Action: getProduct, Product: %s",
                LocalDateTime.now(), SoftScannerApplication.getCurrentUser(), product));
    }
}
