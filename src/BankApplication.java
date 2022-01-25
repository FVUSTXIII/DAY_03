import com.faust.bank.dao.CustomerDao;
import com.faust.bank.model.CreditAccount;
import com.faust.bank.model.DebitAccount;
import com.faust.bank.service.CustomerService;

import java.io.IOException;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class BankApplication {
    private static boolean signed = false;
    public static void main(String[] args) {
        boolean flag = true;
        System.out.println("Log in");
        Scanner input_scanner = new Scanner(System.in);
        Integer usuario = input_scanner.nextInt();
        input_scanner.nextLine();
        CustomerService c_s = new CustomerService(usuario);
        String option;
        while(flag) {
            displayAccounts(c_s);
            displayMenu();
            option = input_scanner.nextLine().toLowerCase(Locale.ROOT);
            switch (option) {
                case "sign up":
                    SingUp(c_s);
                    break;
                case "transactions":
                    System.out.println("transaction method");
                    break;
                case "accounts":
                    System.out.println("accounts method");
                    break;
                case "exit":
                    flag = false;
                    break;
                case "log out":
                    c_s.setC(null);
                    c_s.setId(0);
                    BankApplication.signed = false;
                    break;
            }
        }
        System.out.println("Bye :)");
    }
    static void displayAccounts(CustomerService c) {
        if (c.login()) {
            BankApplication.signed = true;
            System.out.println("Welcome, " + c.getC().getName());
            if (c.getC().getAccounts_owned().size() > 0) {
                System.out.println("Here's a list of your accounts");
                c.getC().getAccounts_owned().forEach(account -> {
                    if (account instanceof CreditAccount) {
                        System.out.println("Account type: Credit\nAccount Holder: "
                                + account.getCustomer().getName() + "\nAccount Balance: "
                                + account.getBalance() + "\n Line of Credit: "
                                + ((CreditAccount) account).getLine_of_credit()
                                +"\nAvailable Credit: " + (((CreditAccount) account).getLine_of_credit() - account.getBalance()));
                    } else if (account instanceof DebitAccount) {
                        System.out.println("Account type: Debit\nAccount Holder: "
                                + account.getCustomer().getName() + "\nAccount Balance: "
                                + account.getBalance());
                    }
                });
            } else {
                System.out.println("you don't have any accounts at the moment");
            }
        } else {
            System.out.println("Customer not found... would you like to sing up?");
        }
    }
    static void displayMenu() {
        if (!BankApplication.signed) {
            System.out.println("Type " +
                    "\n\t----- Sign Up ----" +
                    "\n\t----- Log In -----" +
                    "\n\t----- Exit -------");
        } else {
            System.out.println("Type " +
                    "\n\t--- Transactions --" +
                    "\n\t--- Accounts ------" +
                    "\n\t--- Log out -------");
        }
    }
    static void SingUp(CustomerService c) {
        System.out.println("New User");
        Integer id = 1;
        if (CustomerDao.getInstance().fetchCustomer(id) != null) {
            id += 1;
        }
        System.out.println("Enter your full name");
        String name;
        Scanner input = new Scanner(System.in);
        name = input.nextLine();
        String telephone_number;
        System.out.println("Enter your phone number");
        telephone_number = input.nextLine();
        String email;
        System.out.println("Enter your e-mail address");
        email = input.nextLine();
        c.signUp(name, id, telephone_number, email);
    }
    static void addAccount(CustomerService c) {
        String account_type;
        Scanner input = new Scanner(System.in);

        System.out.println("What kind of account do you want to create?");
        System.out.println("Type: " +
                "-----Mortage------" +
                "-----Savings------");
        account_type = input.nextLine();
        account_type = account_type.toLowerCase(Locale.ROOT);
        Random r = new Random();
        switch (account_type) {
            case "mortage":
                Double line_of_credit = 5000.00 + (r.nextDouble() * (50000000.00 - 5000.00));
                c.CreateCustomerAccount(account_type, c.getC().getCustomer_id(), 0.00, line_of_credit, 0.00);
                break;
            case "savings":
                Double initial_balance = 100.00 + (r.nextDouble() * (10000000 - 100.00));
                Double interest_rate = 1.01 + (r.nextDouble() * (1.50 - 1.01));
                c.CreateCustomerAccount(account_type, c.getC().getCustomer_id(), initial_balance, 0.00, interest_rate);
                break;
            default:
                break;
        }
    }

}


