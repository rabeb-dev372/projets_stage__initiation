import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
public class Main {
    public static void main(String[]args){
        BookStoreAPI api=new BookStoreAPI();
        Scanner sc=new Scanner(System.in);
        System.out.println("** SERVEUR BOOKSTORE SIMULATION (CLI) **");
        boolean traitement=true;
        while (traitement){
            System.out.println("\n1-ajouter un livre");
            System.out.println("2-afficher la liste de toutes les livres disponibles");
            System.out.println("3-chercher un livre dans le stock");
            System.out.println("4-quitter");
            System.out.println("choisir l'action volu!:");

            int choix=sc.nextInt();
            sc.nextLine();
            switch (choix){
                case 1:
                    System.out.println("donner le titre de ce livre:");
                    String t=sc.nextLine();
                    System.out.println("donner l'auteur de ce livre:");
                    String a=sc.nextLine();
                    api.addBook(new Book(t,a));
                    break;
                case 2:
                    api.listBooks();
                    break;
                case 3:
                    System.out.println("donner le titre de livre a rechercher:");
                    String trech=sc.nextLine();
                    api.searchByTitle(trech);
                    break;
                case 4:
                    traitement=false;
                    System.out.println("au revoir!!");
                    break;
                default:
                    System.out.println("Option invalide, veuillez réessayer.");
            }

        }sc.close();
    }
}
