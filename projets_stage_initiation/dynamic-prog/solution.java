import java.util.ArrayList;
import java.util.Arrays;
public class solution {
    public boolean contient(int[] tableau, int nombre) {
        for (int val : tableau) {
            if (val == nombre) return true;
        }
        return false;
    }
    public int coinChange( int amount){
        int []coinsDispo = {50,100,200,500,1000,2000,5000};
        int nb=0;
        //dp[0] = 0;
        //for (int i = 50; i <= amount; i+=+50) {
        //for (int j=1;j<7;j++)
        //{
        int j=1;   //int reste=amount%coinsDispo[j];
            while((j<7)){
                if(contient(coinsDispo,amount%coinsDispo[j])){
                //if(amount%coinsDispo[j]==coinsDispo[j-1]) {
                        amount=amount-(amount%coinsDispo[j]);
                        dp[j]=amount%coinsDispo[j];
                        nb++;
                        j++;
                }else{
                    j++;
                }
            }

                //if (i - coin >= 0) {//si le montant superieur a coin on passe au coin suivant
                    //dp[i] = Math.min(dp[i], dp[i - coin] + 1);//le nb min de piece pour i



                return nb;
        int[] dp = new int[amount/50];
        for (int i = 50; i <= amount; i++50) {
           int ind=i/50;
            int v=amount;
            dp[ind]=v;
            int x =(amount/coinsDispo[i]+dp[v%coinsDispo[i]]);
                if (x<=dp[ind]){
                    dp[ind]=x;
                }
            }}}

