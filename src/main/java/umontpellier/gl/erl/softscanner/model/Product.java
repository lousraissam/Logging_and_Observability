package umontpellier.gl.erl.softscanner.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Product {
    private String ID;
    private String name;
    private String description;
    private String price;
    private String expirationDate;


    public Product(String iD, String name, String price, String expirationDate) {
        ID = iD;
        this.name = name;
        this.price = price;
        this.expirationDate = expirationDate;
    }

    @Override
    public String toString() {
        return "Product [ID=" + ID + ", Name=" + name + ", Price=" + price + ", Expiration Date=" + expirationDate	+ "]";
    }

}
