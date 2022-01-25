import com.faust.bank.service.CustomerService;

import java.util.Scanner;

public class BankApplication {
    public static void main(String[] args) {
        System.out.println("BIENVENIDO");
        System.out.println("INICIE SESIÓN");
        Integer usuario = new Scanner(System.in).nextInt();
        CustomerService c_s = new CustomerService(usuario);
        if (c_s != null) {}
    }
}
