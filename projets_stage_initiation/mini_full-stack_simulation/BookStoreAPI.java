import java.util.List;
import java.util.ArrayList;
public class BookStoreAPI {
    private List<Book>DB=new ArrayList<>();
        //simulation de la requete post
    public void addBook(Book b){
        System.out.println("*ajout d'un livre au stock:");
        DB.add(new Book(b.getTitre(),b.getAuteur()));
        System.out.println("[BACKEND API] Succès : Livre enregistré.");
    }
    //simulation de la requete get

    public void listBooks(){
        System.out.println("*liste de stock des livres:\n");
        if(DB.isEmpty()){
            System.out.println("stok vide(aucun livre disponible)");
        }
        for(Book b :DB){
            System.out.println(b.toString());
            }
    }
    public void searchByTitle(String t){
        boolean trouve=false;
        System.out.println("*recherche du livre "+t+" dans le stok(DB):");
        for(Book b :DB){
            if (b.getTitre().equals(t)){
                trouve=true;
            }}
        if(trouve==false){
            System.out.println("livre non existant!");
        }
        else{System.out.println("le livre "+t+" est trouvée!");}
}}
