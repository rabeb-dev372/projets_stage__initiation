package Data;

public class Node {
    public String key;
    public int value;
    public Node next;//pointeur vers un objet node(receursivité de type)(refernce)
    public Node(String key, int value){
        this.key =key;
        this.value=value;
        this.next=null;
    }
    public String getKey(){
        return this.key;
    }
    public int getValue(){
        return value;
    }
    public void setKey(String key){
        this.key=key;
    }
    public void setValue(int v){
        this.value=v;
    }
}

