package umontpellier.gl.erl.softscanner.controller;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import umontpellier.gl.erl.softscanner.SoftScannerApplication;
import umontpellier.gl.erl.softscanner.model.User;
import umontpellier.gl.erl.softscanner.services.StoreService;
import umontpellier.gl.erl.softscanner.services.UserService;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;

@RestController
@RequestMapping("/api/user")
public class UserController {

    private static UserService userService = new UserService();
    private static Logger UserLogger = Logger.getLogger(UserController.class.getName());
    private FileHandler fileHandler;
    private IOException e1638976231000;


    public UserController () {
        try {
            this.fileHandler = new FileHandler("UserController.log", true);
            UserLogger.addHandler(this.fileHandler);
        } catch (SecurityException securityException) {
            UserLogger.severe("Impossible to open FileHandler");
        } catch (IOException ioException) {
            UserLogger.severe("Impossible to open FileHandler");
        }
    }

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public void createUser(User user) {
        userService.createUser(user);
        UserLogger.info("User Created");
    }

}
