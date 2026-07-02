import {Injectable, signal } from '@angular/core';

export class Hashnode{//unnoeud du liste chainée
  cle: string;
  valeur: string;
  next: Hashnode | null = null;
estNouveau = signal(true);
  constructor(cle: string, valeur: string){
      this.cle = cle;
      this.valeur= valeur;
    }
  }

@Injectable({
  providedIn: 'root'//service disponible dd'une maniere unique dans l'app
  })

export class Hashtable {
  readonly taille=10;
  public table = signal<(Hashnode | null)[]>(Array(this.taille).fill(null));
         public indexSelectionne = signal<number | null>(null);
   private hashfunction(cle: string): number{
     let hash =5381;
     for(let i=0; i<cle.length; i++){
          hash = ((hash << 5) + hash) + cle.charCodeAt(i);
          }
        return Math.abs(hash) % this.taille;

     }
   public addcase(cle: string, valeur: string){
     if(cle=="" || valeur=="" || cle=="" && valeur==""){
        alert("il faut saisir une cle et une valeur");
        return;
       }
     const indice =this.hashfunction(cle);
     const tableactuel = this.table();//copie du tableau
      const newnoad = new Hashnode(cle,valeur);
      if(tableactuel[indice]==null){
          tableactuel[indice]=newnoad;
       }else{//cas de collision
            let neudactuel= tableactuel[indice]!;
            while(neudactuel.next!=null){
                neudactuel=neudactuel.next;
              }neudactuel.next=newnoad;//on ajoute le neud a la fin de la liste chainée

              const tete = tableactuel[indice]

          }

     this.table.set([...tableactuel]);
     return newnoad;

}
  public deletecase(cle: string, valeur: string):void {
     if(cle=="" || valeur=="" || cle=="" && valeur==""){
                 alert("il faut saisir une cle et une valeur");
                 return;
       }
     const indice =this.hashfunction(cle);
          const tableactuel = this.table();//copie du tableau
     let actuel = tableactuel[indice];
     let precedent :Hashnode| null=null;
     let trouve = false;
         if(tableactuel[indice]==null){
           alert("cet element n'existe pas!")
          }else{/*parcourir la lise chainée*/
            while(actuel!=null){

              if(actuel.cle==cle && actuel.valeur==valeur){
                    trouve=true;
                    if(precedent==null){
                        tableactuel[indice]=actuel.next;
                      }else{
                        precedent.next=actuel.next;
                        }
                      break;/*apres supresssion on quitte la boucle*/

                    }/*avancer dans le parcours de la liste chainée */
                  precedent=actuel;
                  actuel=actuel.next;
            }
            if(trouve==false){
                alert("cet element n'existe pas dans le tableau!");
                return;
              }
            this.table.set([...tableactuel]);
                  }}
  public findcase(cle: string){
      if(cle===""){
        alert("il faut saisir un cle");
        return;
        }
           const indice =this.hashfunction(cle);
                const tableactuel = this.table();//copie du tableau
           let actuel = tableactuel[indice];
           let trouve = false;
               if(tableactuel[indice]==null){
                 alert("cet element n'existe pas!")
                 return;
                }
               while(actuel!=null){
                             if(actuel.cle===cle){
                               trouve=true;
                               break;}
                             actuel=actuel.next;
                           }
              if (trouve) {
                  // On active le signal de clignotement sur l'indice trouvé
                  this.indexSelectionne.set(indice);
                   setTimeout(() => {
                    this.indexSelectionne.set(null);
                  }, 2000);
                } else {
                  alert("La clé n'existe pas dans cette liste chaînée !");
                }
              }
            }
