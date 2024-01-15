package umontpellier.gl.erl.softscanner.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Store {

	private String ID;
	private List<Product> products = new ArrayList<>();

	@Override
	public String toString() {
		return "Store [ID=" + ID + ", Products=" + products + "]";
	}

}
