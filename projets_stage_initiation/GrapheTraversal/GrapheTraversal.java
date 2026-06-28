import java.util.*;

public class GrapheTraversal {
    public Map<Integer, List<Integer>> represent(int n){
        Scanner sc=new Scanner(System.in);
        Map<Integer,List<Integer>>graphe=new HashMap<>();
        List<Integer> noeuds=new ArrayList<>();
        for(int i=0;i<n;i++){
            System.out.println("taper le noeud "+i+" de ce graphe:");
            int noeud=sc.nextInt();
            noeuds.add(noeud);
            graphe.put(noeud, new ArrayList<>());
        }
        for(int noeud :noeuds){
            System.out.println("combien de voisins a le noeud "+noeud+"?");
            int nbvoisins=sc.nextInt();
            List<Integer> voisins=new ArrayList<>();
            System.out.println("Entrer les " + nbvoisins + " voisins du noeud " + noeud + " : ");
            for (int j = 0; j < nbvoisins; j++) {
                voisins.add(sc.nextInt());
            }
            graphe.put(noeud,voisins);
            }
    sc.close();
        return graphe;}
        public void afficher(Map<Integer, List<Integer>> graphe) {
            System.out.println(" ce graphe representer par cetteListe d'adjacence  :");
            for (Map.Entry<Integer, List<Integer>> entry : graphe.entrySet()) {
                System.out.println("noeud " + entry.getKey() + " : " + entry.getValue());
            }
        }
        public List<Integer> BFS (Map<Integer, List<Integer>>g,int n,int depart,int arrive){
            Map<Integer, Integer> parent = new HashMap<>();
            Set <Integer> visited=new HashSet<>();
            Queue<Integer>q=new LinkedList<>();
            int i=0;
            visited.add(depart);//Set contenant les noeuds déjà découverts
            parent.put(depart, -1);  // depart n'a pas de parent
            q.add(depart);
            System.out.println("BFS de " + depart + " vers " + arrive + " :");
            while (!q.isEmpty()){
                int noeud = q.poll(); // on prend le premier de la file(defiler)
                System.out.print(noeud + " ");
                if (noeud == arrive) {
                    System.out.println("\nArrivée atteinte !");
                    return reconstruireChemin(parent, depart, arrive);
                }
                for (int voisin : g.get(noeud)) {//on descent d'un niveau
                    if (!visited.contains(voisin)) {
                        visited.add(voisin);
                        parent.put(voisin, noeud);  // on mémorise d'où on vient(noeud,parent)
                        q.add(voisin);//ajout a la fin
                    }
                }
            }System.out.println("\nAucun chemin trouvé !");
            return new ArrayList<>();

            }
    private List<Integer> reconstruireChemin(Map<Integer, Integer> parent, int depart, int arrive) {
        List<Integer> chemin = new ArrayList<>();
        int noeud = arrive;
        while (noeud != -1) {
            chemin.add(0, noeud);  // ajouter au début pour avoir le bon ordre
            noeud = parent.get(noeud);
        }

        System.out.println("Chemin trouvé : " + chemin);
        return chemin;
    }


public List<Integer> DFS(Map<Integer, List<Integer>> g, int depart, int arrive) {
    Map<Integer, Integer> parent = new HashMap<>();
    Set<Integer> visited = new HashSet<>();
    Stack<Integer> pile = new Stack<>();

    pile.push(depart);
    visited.add(depart);
    parent.put(depart, -1);

    System.out.println("\n DFS Itératif de " + depart + " vers " + arrive + "  :");

    while (!pile.isEmpty()) {
        int noeud = pile.pop();
        System.out.print(noeud + " ");

        if (noeud == arrive) {
            System.out.println("\nArrivée atteinte !");
            return reconstruireChemin(parent, depart, arrive);
        }

        if (g.get(noeud) == null) continue;

        for (int voisin : g.get(noeud)) {
            if (!visited.contains(voisin)) {
                visited.add(voisin);
                parent.put(voisin, noeud);
                pile.push(voisin);
            }
        }
    }

    System.out.println("\nAucun chemin trouvé !");
    return new ArrayList<>();
}

    public static void main(String[] args){
        GrapheTraversal g=new GrapheTraversal();
        Map<Integer, List<Integer>>graphe =g.represent(4);
        g.afficher(graphe);
        List<Integer> chemin = g.BFS(graphe, 4,0, 3);
        System.out.println("Chemin final : " + chemin);
}
}
