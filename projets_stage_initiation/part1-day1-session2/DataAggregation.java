import java.util.*;

public class DataAggregation {
    public static void main(String[] args){
    Map<String,Character>students=new HashMap<>();
    students.put("Rabeb",'B');
    students.put("Youssef",'A');
    students.put("Sahar",'B');
    students.put("Ali",'C');
    List<String>lA=new ArrayList<>();
    List<String>lB=new ArrayList<>();
    List<String>lC=new ArrayList<>();

    for(Map.Entry<String,Character> entry:students.entrySet()){
        if (entry.getValue()=='A'){
            lA.add(entry.getKey());
        } else if (entry.getValue()=='B') {
            lB.add(entry.getKey());
        }else {
            lC.add(entry.getKey());
        }

    }
    int nbA=lA.size();
    int nbB=lB.size();
    int nbC=lC.size();
    System.out.println("le nombre de A est: "+nbA+"  le nombre de B est:"+nbB+"  le nombre de C est:"+nbC);
}}
