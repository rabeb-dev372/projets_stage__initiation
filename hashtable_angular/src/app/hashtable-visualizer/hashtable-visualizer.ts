import { Component } from '@angular/core';
import { Hashtable , Hashnode} from '../services/hashtable'

@Component({
  selector: 'app-hashtable-visualizer',
  standalone: true,
  imports: [],
  templateUrl: './hashtable-visualizer.html',
  styleUrl: './hashtable-visualizer.css',
})
export class HashtableVisualizer {
    //injection du service
    //methode 2 (syntaxe):constructor(public hashtableservice: Hashtable){
      //
      inputcle: string = '';
      inputvaleur: string = '';
      hashtableservice: Hashtable;//on nomme le servise hashtableservice
      constructor(hashtableservice: Hashtable){
        this.hashtableservice=hashtableservice;
        }
      getNodes(noeud: Hashnode): Hashnode[]{//transformer la liste chainée en un tab pour pouvoir l'utiliser dans @for
          const tablist: Hashnode[]=[];
          let courant: Hashnode|null = noeud;
          while(courant!= null){
              tablist.push(courant);
              courant=courant.next;
            }return tablist;
        }}




