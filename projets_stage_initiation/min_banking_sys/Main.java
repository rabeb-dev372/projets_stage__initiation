public class Main {
    public static void main(String[] args){
        BankAccount compte1=new BankAccount(8500,"laribi123");
        compte1.deposit(2000);
        compte1.withdraw(15000);
        compte1.withdraw(2600);
    System.out.println("solde finale (apres transactions):"+compte1.getSolde());
    }
}
