package umontpellier.gl.erl.softscanner.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.io.IOException;
import java.util.logging.FileHandler;
import java.util.logging.Logger;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class User {
    private String ID;
    private String name;
    private String age;
    private String email;
    private String password;

    @Override
    public String toString() {
        return "User [ID=" + ID + ", Name=" + name + ", Age=" + age + ", Email=" + email + ", Password=" + password	+ "]";
    }
}
