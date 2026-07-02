import java.util.Objects;
public class Pair<T,U> {
    private T var1;
    private U var2;
    public Pair(T cont1, U cont2){
        this.var1=cont1;
        this.var2=cont2;
    }
    public T getT(){
        return(var1);
    }
    public U getU(){
        return(var2);
    }    @Override
    public boolean equals(Object obj){
        if (this==obj){
            return(true);
        }
        else if(obj==null) {
            return (false);
        }
        else if (this.getClass()!=obj.getClass()) {
            return(false);
        }else{
            Pair<T,U> autre= (Pair<T,U>) obj;
            return(this.var1.equals(autre.var1))&&(this.var2.equals(autre.var2));
        }
    }
    @Override
    public int hashCode(){
        return(Objects.hash(var1, var2));
    }
}
