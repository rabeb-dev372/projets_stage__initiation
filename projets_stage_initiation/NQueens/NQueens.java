public class NQueens {
    private int n;
    private int [][]board;
    public NQueens(int n){
        this.n=n;
        this.board=new int[n][n];
    }
    public boolean zonesecurise(int l,int c){
        for(int i=0;i<c;i++){
            if(board[l][i]==1){
                return false;
            }}
            for(int j=0;j<l;j++){
                if(board[j][c]==1){
                    return false;
                }}

        for(int i=l, j=c;i>=0 &&j>=0;i--,j--){
            if (board[i][j] == 1) {
                return false;
            }}
        for(int i=l, j=c;i<n && j>=0;i++,j--){
            if (board[i][j]==1){
                return false;
            }
        }return true;
            }
        public boolean back(int c){
        if(c>=n){
            System.out.println("jeu resolu!!");
            return true;
        }
        for(int i=0;i<n;i++){
            if(zonesecurise(i,c)){
                board[i][c]=1;
                System.out.println(" Placement reine: ligne " + i + ", colonne " + c);
                afficher();
                if(back(c+1)){
                return true;
            }else{
                board[i][c]=0;//retirer le reine placer dans la colonne precedente
                System.out.println("retirer la reine(backtraking) de la ligne :"+i+" et colone:"+c);
                afficher();
            }}
        }return false;
        }
    public void afficher() {
        for (int i = 0; i < n; i++) {
            for (int j = 0; j < n; j++) {
                System.out.print(board[i][j]);
            }
            System.out.println();
        }
    }
        public static void main(String []args){
            int n=4;
            NQueens tour=new NQueens(n);
            if ((tour.back(0))==true){//on commance par la premiere colonne
                //System.out.println("jeu resolu");

                tour.afficher();
            }else{
                System.out.println("non resolu!");}
            }
        }



