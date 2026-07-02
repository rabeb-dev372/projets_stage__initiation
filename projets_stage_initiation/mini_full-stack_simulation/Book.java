public class Book {
    private String titre;
    private String auteur;
    public Book(String t,String a){
        this.titre=t;
        this.auteur=a;
    }
    public String getTitre(){
        return titre;
    }
    public String getAuteur(){
        return auteur;
    }
    @Override
    public String toString(){
        return "livre{titre:"+this.getTitre()+",auteur:"+this.getAuteur()+"}";
    }
    @Override
    public boolean equals(Object obj){
        if(this==obj){
            return true;
        } else if (obj==null) {
            return false;
        } else if (this.getClass()!=obj.getClass()) {
            return false;
        }else {
            Book otherBook=(Book) obj;
            return ((this.getTitre().equals(otherBook.getTitre()))&& this.getAuteur().equals(otherBook.getAuteur()));

        }}}

