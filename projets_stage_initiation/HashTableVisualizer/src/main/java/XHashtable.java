public class XHashtable {
    private NodeList[] table;
    private int taille;
    public XHashtable(int taille){
        this.taille=taille;
        this.table=new NodeList[taille];
        for(int i=0; i<taille ;i++){
            table[i]=new NodeList();
        }
    }
    public NodeList[] getTable() {
        return this.table;
    }
    public int add(String key, int value){
        int index= calculerhash(key);
        table[index].ajoutFin(key, value);
        //Entry nouvelleEntree = new Entry(key, value);
        //table[index].addLast(nouvelleEntree);
        /*if (table[index]==null){
            table[index]=nouvelleEntree;
        }else{//probleme de collision
            Entry courant = table[index];
            while(courant.getNext()!= null){
                    courant=courant.getNext();
                }courant.setNext(nouvelleEntree);
            }*/return index;
        }

    /*public int add(Entry couple) {
        int index = Math.abs(couple.getKey().hashCode()) % table.length;        int startIndex = index;
        int indexLibre=-1;
        while (table[index] != null) {//probleme de collision
            if (table[index].getKey().equals(couple.getKey())) {
                table[index] = couple;
                return index;
            }
            index = (index + 1) % this.taille;
        //lorqu'on atteint la fin de tableau on revient a 0

        if (startIndex == index) {
            return -1;
        }}
        table[index]=couple;

        return index;
    }*/
    public int calculerhash(String k){
        long hash=5381;
        for(int i=0 ; i<k.length() ; i++){
            char car =k.charAt(i);//recuperer la lettre
            int CASCII=(int)car;//det le codeascii de la lettre
            hash=hash*33+CASCII;
        }return (int)(Math.abs(hash)%taille);
        //return Math.abs(k.hashCode())%taille ;
    }
    public int delete(String key){
        int index=calculerhash(key);
        boolean succes = table[index].supprimer(key);
        if(succes){
            return index;
        /*Entry supp= null;
        for(Entry c : liste){
            if(c.getKey().equals(key)){
                supp=c;
                break;
            }
        }
        if(supp != null){
            return index;
       */ }return -1;}
        /*while(table[index]!=null ){
            if(table[index].getKey().equals(key)){
                table[index]=null;
                return index;
            }
            index=(index+1)%taille;
        if(index==startIndex){
            return -1;}//valeur a supprimer n'existe pas
         }
    return -1;*/
    public int find(String key) {
        int index = calculerhash(key);
        //LinkedList<Entry> liste = table[index];
        int valtrouvee=table[index].find(key);
        if(valtrouvee!=-1){
            return index;
        }return -1;
        /*for(Entry c : liste){
            if(c.getKey().equals(key)){
                return index;
            }
        }return -1;*/
        /*int pos = 0;
        while (courant != null) {
            if (courant.getKey().equals(key)){
                return index;  // retourner AVANT d'incrémenter
            }
            index = (index + 1) % taille;
            if (index == startIndex) {
                return -1;

            }}
            return -1;
        }*/
    }}

