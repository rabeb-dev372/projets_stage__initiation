import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;


public class logManager {
        private static final String nom_fichier="transactions_logs.txt";
        public static void enregistrerLog(String msg){
            try(FileWriter fw=new FileWriter(nom_fichier,true);//true ➜ ajoute à la fin
            PrintWriter pw=new PrintWriter(fw)){//permet l'ecriture ne utilisant println
                pw.println(msg);
            }catch(IOException e){
                System.out.println("erreur:"+e.getMessage());
            }
        }
    }

