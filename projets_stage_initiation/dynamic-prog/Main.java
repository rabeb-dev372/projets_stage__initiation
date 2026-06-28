import java.util.Scanner;
public class Main {
    public static void main(String [] args){
        Scanner sc = new Scanner(System.in);

        System.out.print("Entrer le montant : ");
        int amount = sc.nextInt();

        solution sol = new solution();
        int nb = sol.coinChange(amount);
        System.out.println("le nombre min de ce montant est :"+nb);
        sc.close();
}}

