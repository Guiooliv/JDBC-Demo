package Application;

import model.entites.Departament;
import model.entites.Seller;
import java.util.Date;


public class Program {
    public static void main(String[] args) {

        Departament obj = new Departament(1, "Books");

        Seller seller = new Seller(21, "bob", "bob@gmail.com", new Date(), 3000.0, obj );

        System.out.println(seller);
    }
}