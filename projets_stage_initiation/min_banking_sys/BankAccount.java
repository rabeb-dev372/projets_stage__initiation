import java.time.LocalDateTime;
public class BankAccount {
    private double solde;
    private String numCompte;
    public BankAccount(double s,String n){
        this.solde=s;
        this.numCompte=n;
    }
    public String getNumCompte() {
        return numCompte;
    }

    public double getSolde() {
        return solde;
    }
    public void deposit(double montant){
        if(montant>0){
            this.solde+=montant;
            Transaction t = new Transaction("DEPOT", montant, numCompte, true);
            logManager.enregistrerLog(t.toString());
            //String log="["+LocalDateTime.now()+"] depot sur "+numCompte+": +"+montant;
            System.out.println("nouveau solde(apres depot)="+solde+" dt");
        }else{
            System.out.println("Erreur:le montant de depot doit etre superieur à 0!");
        }
    }
    public void withdraw(double montant){
        if (montant>0 && montant<=solde){
            this.solde-=montant;
            Transaction t = new Transaction("Restrait", montant, numCompte, true);
            logManager.enregistrerLog(t.toString());
            //String log="["+LocalDateTime.now()+"] retrait sur "+numCompte+": -"+montant;
            System.out.println("nouveau solde(apres retrait) est:"+solde);
        } else {
            String erreurlog="["+LocalDateTime.now()+"] echec retrait sur "+numCompte+"| solde insuffisant=" + solde;;
            logManager.enregistrerLog(erreurlog);
            System.out.println("Solde insuffisant (" + this.solde + " DT disponibles).");
    }
}

}
