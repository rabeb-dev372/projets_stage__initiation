import java.util.LinkedList;
public class Entry {
    private String key;
    private LinkedList<Integer> values;
    private Entry next;//pointeur(adresse memoire) vers un objet de type entry
    public Entry(String key, int value){
        this.key=key;
        this.values=new LinkedList<>();
        this.next=null;
    }
    public void setNext(Entry e){
        this.next=e;
    }
    public Entry getNext(){
        return this.next;
    }
    public String getKey(){
        return key;
    }
    public LinkedList<Integer> getValues(){
        return values;
    }
    public void addValue(int value){
        values.addLast(value);
    }
    @Override
    public String toString() {
        return this.key + " [" + this.values + "]";
    }
}
