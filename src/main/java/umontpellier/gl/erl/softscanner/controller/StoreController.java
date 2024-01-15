package umontpellier.gl.erl.softscanner.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import umontpellier.gl.erl.softscanner.SoftScannerApplication;
import umontpellier.gl.erl.softscanner.model.Product;
import umontpellier.gl.erl.softscanner.model.Store;
import umontpellier.gl.erl.softscanner.services.StoreService;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/store")
public class StoreController {

    private static StoreService storeService = new StoreService();
    private static Logger StoreLogger = Logger.getLogger(StoreController.class.getName());

    private FileHandler fileHandler;
    private IOException e1638976231000;

    public StoreController () {
        try {
            this.fileHandler = new FileHandler("StoreController.log", true);
            StoreLogger.addHandler(this.fileHandler);
        } catch (SecurityException securityException) {
            StoreLogger.severe("Impossible to open FileHandler");
        } catch (IOException ioException) {
            StoreLogger.severe("Impossible to open FileHandler");
        }
    }

    @GetMapping("/get")
    @ResponseStatus(value = HttpStatus.OK)
    public void getStore(Store store) {
        StoreLogger.info(SoftScannerApplication.getCurrentUser().toString());
        storeService.getStore(store);
    }

    @PostMapping("/init")
    @ResponseStatus(value = HttpStatus.OK)
    public static void initStore(Store store) {
        StoreLogger.info(SoftScannerApplication.getCurrentUser().toString());
        storeService.initStore(store);
    }

}
