package Data;

public class NodeList {
    private Node tete;//tete de la liste
    private int size;
    public NodeList(){
        this.tete=null;
        this.size=0;
    }
    public Node getTete(){
        return tete;
    }
    public int getSize(){
        return this.size;
    }
    public boolean vide(){
        return tete==null;
    }
    //ajouter un elemenet
    public void ajoutFin(String key , int value){
        Node newNode = new Node(key,value);
        if(tete == null){
            tete=newNode;
        }else{
            Node imm=tete;
            while(imm.next!= null){
                imm=imm.next;
            }imm.next=newNode;//ajout a la fin
        }size++;
    }
    //supprimer un element par cle
    public boolean supprimer(String key){
        if(tete==null){
            return false;
        }//si la tete correspond au noeud a charcher
        if(tete.getKey().equals(key)){
            tete=tete.next;
            size--;
            return true;
        }//ca generale
        Node imm = tete;
        while(imm.next != null){
            if(imm.next.getKey().equals(key)){
                imm.next=imm.next.next;
                size--;
                return true;
            }
            imm=imm.next;
        }
        return false;
    }

    public int find (String key){
        Node imm = tete;
        //int pos=0;
        while(imm!=null) {
            if (imm.getKey().equals(key)) {
                return imm.getValue();
            }
            imm=imm.next;
        }
        return -1;
    }


    public boolean isEmpty() {
        return tete==null;
    }
}
