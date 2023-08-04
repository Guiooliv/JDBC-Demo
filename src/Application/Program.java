package Application;

import dao.DaoFactory;
import dao.SellerDao;
import model.entites.Departament;
import model.entites.Seller;
import java.util.Date;


public class Program {
    public static void main(String[] args) {

        SellerDao sellerDao = DaoFactory.createSellerDao();
        System.out.println("=== Test 1: seller findById ===");
        Seller seller = sellerDao.findById(3);

        System.out.println(seller);
    }
}