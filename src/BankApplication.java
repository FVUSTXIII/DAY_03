import com.faust.bank.dao.AccountDao;
import com.faust.bank.dao.CustomerDao;
import com.faust.bank.dao.TransactionDao;
import com.faust.bank.model.Account;
import com.faust.bank.model.CreditAccount;
import com.faust.bank.model.DebitAccount;
import com.faust.bank.service.CustomerService;
import com.faust.bank.service.TransactionService;

import java.time.Instant;
import java.util.Date;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicBoolean;

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
                    if (c_s.getC().getAccounts_owned().size() > 0) {
                        transactionSwitch(displayTransactionMenu(), c_s);
                    } else {
                        System.out.println("You need to create an account in order to perform a transaction");
                    }
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
        Scanner input = new Scanner(System.in);
        Integer id = 1;
        while (CustomerDao.getInstance().fetchCustomer(id) != null) {
            id += 1;
        }
        System.out.println("Enter your full name");
        String name;
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

    static String displayTransactionMenu() {
        System.out.println("What would you like to do?");
        System.out.println("---------- Transaction history ---------------");
        System.out.println("---------- Pay my debt -----------------------");
        System.out.println("---------- Deposit ---------------------------");
        System.out.println("---------- Withdraw --------------------------");
        System.out.println("---------- Transfer --------------------------");
        Scanner input = new Scanner(System.in);
        String option;
        option = input.nextLine();
        return option.toLowerCase(Locale.ROOT);
    }
    static void transactionSwitch(String option, CustomerService c) {
        TransactionService t = new TransactionService();
        Integer id = 1;
        while (TransactionDao.getInstance().fetchTransaction(id) != null) {
            id += 1;
        }
        switch (option) {
            case "transaction history":
                transationHistory(c);
                break;
            case "pay my debt":
                payDebt(c, t);
                break;
            case "deposit":
                break;
            case "withdraw":
                break;
            case "transfer":
                break;
            default:
                System.out.println("I don't recognize your answer");
                break;
        }
    }

    static void transationHistory(CustomerService customerService) {
        customerService.getC().getTransactions_made().forEach(transaction -> {
            System.out.println("Transaction ID: " + transaction.getId()
                    + "\nSender Account: " + transaction.getSender_account().getAccount_number()
                    + "\nReceiver Account: " + transaction.getReceiver_account().getAccount_number()
                    + "\nAmount: " + transaction.getAmount()
                    + "\nDate: " + transaction.getDate());
        });
    }

    static void payDebt(CustomerService c, TransactionService t) {
        AtomicBoolean c_a = new AtomicBoolean(false);
        Scanner input = new Scanner(System.in);
        Integer account_to_pay, payer_account, id;
        id = 1;
        while (TransactionDao.getInstance().fetchTransaction(id) != null) {
            id += 1;
        }
        double amount;
        System.out.println("Select the account: ");
        c.getC().getAccounts_owned().forEach(account -> {
            if (account instanceof CreditAccount) {
                System.out.println("Account: " + account.getAccount_number());
                System.out.println("Balance: " + account.getBalance());
                c_a.set(true);
            }
        });
        if (!c_a.get()) {
            System.out.println("You don't have any credit accounts");
        } else {
            System.out.print("Enter the account number for the account in debt: ");
            account_to_pay = input.nextInt();
            System.out.println("Available accounts");
            int finalAccount_to_pay = account_to_pay;
            c.getC().getAccounts_owned().forEach(account -> {
                if (account instanceof CreditAccount && account.getAccount_number() != finalAccount_to_pay) {
                    System.out.println("Credit Account: " + account.getAccount_number());
                    System.out.println("Available Credit: " +
                            (((CreditAccount) account).getLine_of_credit() - account.getBalance()));
                } else if (account instanceof DebitAccount) {
                    System.out.println("Debit Account: " + account.getAccount_number());
                    System.out.println("Balance: " + account.getBalance());
                }
            });
            System.out.println("Select the account that you want to use to make the payment or type 0 to pay with cash: ");
            payer_account = input.nextInt();
            System.out.println("Select the amount that you want to pay: ");
            amount = input.nextDouble();
            Account temp;
            if (payer_account != 0) {
                temp = AccountDao.getInstance().fetchAccount(payer_account);
            } else {
                temp = AccountDao.getInstance().fetchAccount(finalAccount_to_pay);
            }
            if (temp instanceof CreditAccount) {
                if (payer_account != 0) {
                    double available_credit = (((CreditAccount) temp).getLine_of_credit() - temp.getBalance());
                    if (amount > available_credit) {
                        System.out.println("You don't have sufficient credit to perform this operation");
                    } else {
                        if (AccountDao.getInstance().fetchAccount(finalAccount_to_pay) instanceof CreditAccount) {
                            CreditAccount receiver_account = (CreditAccount) AccountDao.getInstance()
                                   .fetchAccount(finalAccount_to_pay);
                            if (amount <= receiver_account.getBalance()) {
                                t.excecTransaction(id, temp, receiver_account, amount,
                                        "PWC", Date.from(Instant.now()).toString());
                            } else {
                                System.out.println("You can't go over the balance");
                            }
                        }
                    }
                } else {
                    if (amount <= temp.getBalance()) {
                        System.out.println("You can't go over the balance");
                    } else {
                        t.excecTransaction(id, temp,temp, amount, "PCB", Date.from(Instant.now()).toString());
                    }
                }
            } else if (temp instanceof DebitAccount) {
                if (amount < temp.getBalance()) {
                    if (AccountDao.getInstance().fetchAccount(finalAccount_to_pay) instanceof CreditAccount) {
                        CreditAccount receiver_account = (CreditAccount) AccountDao.getInstance()
                                .fetchAccount(finalAccount_to_pay);
                        if (amount <= receiver_account.getBalance()) {
                            t.excecTransaction(id, temp, receiver_account, amount, "PTD", Date.from(Instant.now()).toString());
                        } else {
                            System.out.println("You can't go over the balance");
                        }
                    }
                } else {
                    System.out.println("Insufficient funds");
                }
            }
        }
    }

}


