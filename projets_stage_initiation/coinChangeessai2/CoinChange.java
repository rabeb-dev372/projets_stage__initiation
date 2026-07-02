public class CoinChange {
    public int coinChange( int amount){
        int []coinsDispo = {50,100,200,500,1000,2000,5000};
        int nb=0;
        int[] dp = new int[amount/50];
        for (int i = 50; i <= amount; i=i+50){
            for (int j = 0; j < coinsDispo.length; j++) {
                int ind=i/50;
                int v=amount;
                dp[ind]=v;
                int x =(amount/coinsDispo[j]+dp[v%coinsDispo[j]]);
            if (x<=dp[ind]){
                dp[ind]=x;
        }}
    }
    return nb;}
}
