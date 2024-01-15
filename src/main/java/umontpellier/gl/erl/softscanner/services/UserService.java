package umontpellier.gl.erl.softscanner.services;
import org.springframework.stereotype.Service;
import umontpellier.gl.erl.softscanner.SoftScannerApplication;
import umontpellier.gl.erl.softscanner.model.User;

import java.util.Scanner;


@Service
public class UserService {

    static Scanner sc = new Scanner(System.in);


    public void createUser(User user) {
        System.out.print("ID : ");
        String intput = sc.nextLine();
        user.setID(intput);

        System.out.print("Name : ");
        intput = sc.nextLine();
        user.setName(intput);

        System.out.print("Age : ");
        intput = sc.nextLine();
        user.setAge(intput);

        System.out.print("Email : ");
        intput = sc.nextLine();
        user.setEmail(intput);


        System.out.print("Password : ");
        intput = sc.nextLine();
        user.setPassword(intput);
        System.out.println("User created");
    }

}
