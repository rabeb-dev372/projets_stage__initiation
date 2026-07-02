#include <stdio.h>
#include <stdlib.h>

int maxSubarraySum(int* arr , int n, int k){
    int * tab = malloc((n-k+1) * sizeof(int));

    for(int i=0; i<n; i++){
            int s=0;
        for(int j=i; j<i+k ;j++){
            s+=arr[j];
        } 
            tab[i]=s;
}
    
    int max= tab[0];
    for(int i=0; i<n-1; i++){
        if (tab[i]>max){
            max=tab[i];}}
            return max;
}
int main(){
    int t[5]={3,5,1,6,8};
    int a=maxSubarraySum(t,5,2);
    printf("le max de somme est %d\n",a);
    return 0;

} 
