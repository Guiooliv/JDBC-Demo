package Application;
import dao.DaoFactory;
import dao.DepartamentDao;
import model.entites.Departament;
import java.util.List;
import java.util.Scanner;

public class Program2 {
    public static void main(String[] args) {

        Scanner scan = new Scanner(System.in);


        DepartamentDao departamentDao = DaoFactory.createDepartmentDao();

        System.out.println("=== Test 1: Department findById ===");
        Departament departament = departamentDao.findById(2);
        System.out.println(departament);

        System.out.println("\n=== Test 2: department findAll ===");
        List<Departament> list = departamentDao.findAll();
        for (Departament d: list) {
            System.out.println(d);
        }

        System.out.println("\n=== Test 3: departament insert ===");
        Departament newDepartament = new Departament(null,"Music");
        departamentDao.insert(newDepartament);
        System.out.println("Inserted! new id = " + newDepartament.getId());

        System.out.println("\n=== Test 4: departament update ===");
        Departament dep2 = departamentDao.findById(1);
        dep2.setName("Food");
        departamentDao.update(dep2);
        System.out.println("update completed");

        System.out.println("\n=== Test 5: departament delete ===");
        System.out.println("Enter id for delete test:");
        int id = scan.nextInt();
        departamentDao.deleteById(id);
        System.out.println("Deletd Completed");

        scan.close();
    }
}