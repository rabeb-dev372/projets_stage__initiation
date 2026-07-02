import java.util.ArrayList;
import java.util.List;
public class Custmer {
    private String name;
    private List<BankAccount> accounts; // Un client peut posséder plusieurs comptes
     public Custmer(String name) {
            this.name = name;
            this.accounts = new ArrayList<>();
        }
        public String getName() {
            return name;
        }

        public void setName(String name) {
            this.name = name;
        }

        public List<BankAccount> getAccounts() {
            return accounts;
        }
        public void addAccount(BankAccount account) {
            if (account != null) {
                this.accounts.add(account);
                System.out.println("Le compte " + account.getNumCompte() + " a été ajouté à " + this.name);
            }
        }
        public void statutClient() {
            System.out.println("\nSituation de M./Mme " + this.name + " :");
            if (accounts.isEmpty()) {
                System.out.println("Aucun compte associé.");
            } else {
                for (BankAccount acc : accounts) {
                    System.out.println("- Compte N°: " + acc.getNumCompte() + " | Solde: " + acc.getSolde() + " DT");
                }
            }
    }
}
