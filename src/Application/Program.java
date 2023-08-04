package Application;

import dao.DaoFactory;
import dao.SellerDao;
import model.entites.Departament;
import model.entites.Seller;
import java.util.Date;
import java.util.List;
import java.util.Scanner;


public class Program {
    public static void main(String[] args) {
        Scanner scan = new Scanner(System.in);

        SellerDao sellerDao = DaoFactory.createSellerDao();

        System.out.println("=== Test 1: seller findById ===");
        Seller seller = sellerDao.findById(3);
        System.out.println(seller);

        System.out.println("\n=== Test 2: seller findByDepartment ===");
        Departament departament = new Departament(2, null);
        List<Seller> list = sellerDao.findByDepartment(departament);
        for (Seller obj: list) {
            System.out.println(obj);
        }

        System.out.println("\n=== Test 3: seller findAll ===");
        list = sellerDao.findAll();
        for (Seller obj: list) {
            System.out.println(obj);
        }

        System.out.println("\n=== Test 4: seller insert ===");
        Seller newSeller = new Seller(null, "Greg", "greg@gmail.com", new Date(), 4000.0, departament);
        sellerDao.insert(newSeller);
        System.out.println("Inserted! new id = " + newSeller.getId());

        System.out.println("\n=== Test 5: seller update ===");
        seller = sellerDao.findById(1);
        seller.setName("Martha waine");
        sellerDao.update(seller);
        System.out.println("update completed");

        System.out.println("\n=== Test 6: seller Delete ===");
        System.out.println("Enter id for delete test:");
        int id = scan.nextInt();
        sellerDao.deleteById(id);
        System.out.println("Deletd Completed");

        scan.close();
    }
}