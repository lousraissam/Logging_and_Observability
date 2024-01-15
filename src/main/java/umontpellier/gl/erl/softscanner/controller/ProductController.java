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
import java.util.logging.FileHandler;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/product")
public class ProductController {

    private static ProductService productService = new ProductService();
    private static Logger ProductLogger = Logger.getLogger(ProductController.class.getName());
    private FileHandler fileHandler;
    private IOException e1638976231000;


    public ProductController () {
        try {
            this.fileHandler = new FileHandler("ProductController.log", true);
            ProductLogger.addHandler(this.fileHandler);
        } catch (SecurityException securityException) {
            ProductLogger.severe("Impossible to open FileHandler");
        } catch (IOException ioException) {
            ProductLogger.severe("Impossible to open FileHandler");
        }
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createProduct(Store store) {
        Product product = productService.createProduct(store);
        ProductLogger.info(SoftScannerApplication.getCurrentUser().toString()+ ", "+  product.toString());

    }


    @PutMapping("/update")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void updateProduct(Store store) throws ProductAlreadyExisteException, ProductNotFoundException {
        Product product = productService.updateProduct(store);
        ProductLogger.info(SoftScannerApplication.getCurrentUser().toString()+ ", "+  product.toString());
    }


    @DeleteMapping("/delete")
    @ResponseStatus(value = HttpStatus.ACCEPTED)
    public void deleteProduct(Store store) {
        ProductLogger.info(SoftScannerApplication.getCurrentUser().toString());
        productService.deleteProduct(store);
    }


    @GetMapping("/get")
    @ResponseStatus(value = HttpStatus.OK)
    public void getProduct(Store store) {
        Product product = productService.getProduct(store);
        ProductLogger.info(SoftScannerApplication.getCurrentUser().toString()+ ", "+  product.toString());
    }
}
