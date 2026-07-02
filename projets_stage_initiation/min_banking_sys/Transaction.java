import java.util.Date;
public class Transaction {//represente une operation
        private String type; // depot ou retrait
        private double montant;
        private String numCompte;
        private Date date;
        private boolean succes;

        public Transaction(String type, double montant, String numCompte, boolean succes) {
            this.type = type;
            this.montant = montant;
            this.numCompte = numCompte;
            this.succes = succes;
            this.date = new Date(); // date automatique
        }

        public String toString() {
            String statut;
            if (succes) {
                statut = " transaction avec succes";
            } else {
                statut = "  transaction failed";
            }
            return "[" + date + "] " +
                    type + " sur " + numCompte +
                    " montant=" + montant + statut +"]";

    }
}
