#include <stdio.h>
#include <stdlib.h>

int * tri(){
    int tab[5]={7,2,5,1,4};
    int *tabp = NULL;
    int *tabimp = NULL;
    int n1=0;
    int n2=0;
    for (int i=0;i<5;i++){
        if(tab[i]%2==0){
            n1++;
            tabp =realloc(tabp,n1*sizeof(int));
            tabp[n1-1]=tab[i];
    }else{
        n2++;
        tabimp = realloc (tabimp,n2*sizeof(int));
        tabimp[n2-1]=tab[i];

    }
    }int *tabf= malloc((n1+n2)*sizeof(int));
    for (int i=0; i<n1; i++){
        tabf[i]=tabp[i];

    }
    for (int i=0; i<n1; i++){
        tabf[i]=tabp[i];
        
    }
    for (int i=0; i<n2; i++){
        tabf[i+n1]=tabimp[i];
        
    }
    return tabf;
    free(tabp);
    free(tabimp);
}
int main(){
    int * r=tri();
    printf("le tableau triée est:");
     for (int i = 0; i < 5; i++) {
        printf("%d ", r[i]);
    }
    free(r);
}