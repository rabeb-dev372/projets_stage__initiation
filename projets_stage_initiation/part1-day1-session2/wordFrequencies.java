import java.io.FileReader;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class wordFrequencies {
    public static void main(String []args){
        Map<String,Integer> map=new HashMap<>();
    try(BufferedReader reader = new BufferedReader(new FileReader("file.txt"))){
            String ligne;
            while((ligne=reader.readLine())!=null){
                String[]words=ligne.split(" ");
            for(String word : words){
                if (map.get(word)!=null){
                    map.put(word,map.get(word)+1);
                }else{
                    map.put(word,1);
                }
            }}
    }catch(IOException e){
        System.out.println("Erreur:"+e.getMessage());
    }
        for(Map.Entry<String,Integer> entry:map.entrySet()){
            System.out.println(entry.getKey()+":"+entry.getValue());

        }}}


