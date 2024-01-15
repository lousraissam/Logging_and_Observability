package umontpellier.gl.erl.softscanner;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import umontpellier.gl.erl.softscanner.controller.ProductController;
import umontpellier.gl.erl.softscanner.controller.StoreController;
import umontpellier.gl.erl.softscanner.controller.UserController;
import umontpellier.gl.erl.softscanner.exception.ProductAlreadyExisteException;
import umontpellier.gl.erl.softscanner.exception.ProductNotFoundException;
import umontpellier.gl.erl.softscanner.model.Store;
import umontpellier.gl.erl.softscanner.model.User;

import java.io.IOException;
import java.util.Scanner;
import java.util.logging.FileHandler;
import java.util.logging.Logger;
import java.util.logging.SimpleFormatter;
import java.util.logging.Handler;
import java.util.logging.Level;

@SpringBootApplication
public class SoftScannerApplication {

    private static Logger LOGGER = Logger.getLogger(SoftScannerApplication.class.getName());
    private static FileHandler fh;
    private IOException e1638976230999;


    static {
        try {
            fh = new FileHandler("SoftScannerApplication.log", true);
            LOGGER.addHandler(fh);
            fh.setFormatter(new SimpleFormatter()); // Set the formatter
            LOGGER.setLevel(Level.INFO); // Set the logging level
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    static Scanner sc;
    static User user = new User();
    static Store store = new Store();
    static UserController userController = new UserController();
    static StoreController storeController = new StoreController();
    static ProductController productController = new ProductController();

    public static void main(String[] args) throws ProductAlreadyExisteException, ProductNotFoundException {
        LOGGER.addHandler(fh);
        SpringApplication.run(SoftScannerApplication.class, args);
        sc = new Scanner(System.in);
        String userInput = "";
        System.out.println("Please, insert your personal information : ");
        userController.createUser(user);
        do {
            menu();
            userInput = sc.nextLine();
            processUserInput(userInput);
        } while (!userInput.equals("-1"));
        sc.close();
    }

    private static void processUserInput(String userInput) throws ProductAlreadyExisteException, ProductNotFoundException {
        switch (userInput) {
            case "0": {
                storeController.getStore(store);
                break;
            }
            case "1": {
                productController.getProduct(store);
                break;
            }
            case "2": {
                productController.createProduct(store);
                break;
            }
            case "3": {
                productController.deleteProduct(store);
                break;
            }
            case "4": {
                productController.updateProduct(store);
                break;
            }
            case "5": {
                StoreController.initStore(store);
                break;
            }
            case "-1":
                System.out.println("Happy Hacking");
                return;

            default:
                System.err.println("Sorry, wrong input. Please try again.");
                return;
        }
    }


    private static void menu() {
        StringBuilder stringBuilder = new StringBuilder();
        stringBuilder.append("You can do the following actions, enter a number :");
        stringBuilder.append('\n');
        String[] actions = { "Display products", "Fetch a product by ID", "Add a new Product", "Delete a product by ID",
                "Update a product info", "Initialize with defaults" };
        for (int i = 0; i < actions.length; i++) {
            stringBuilder.append(i);
            stringBuilder.append(". ");
            stringBuilder.append(actions[i]);
            stringBuilder.append('\n');
        }
        stringBuilder.append("\n" + "-1" + ". To quit.");

        System.out.println(stringBuilder.toString());
    }

    public static User getCurrentUser() {
        return user;
    }

}