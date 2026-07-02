class Node{
    constructor(cle,valeur){
        this.cle=cle;
        this.value=valeur;
        this.next=null;
    }
}
window.addEventListener("load",()=>{

const table=new Array(10).fill(null);
let indexAllume = null;
const canvas=document.getElementById("mycanvas");
const ctx = canvas.getContext('2d');

function dessinertable(){
    ctx.clearRect(0, 0, canvas.width, canvas.height);
    const debutx=100;
    const debuty=45;
    for(let i=0;i<10;i++){
        const y=debuty+(i*70);
        if(i==indexAllume){
            ctx.fillStyle = "#3498db";
            } else {
                ctx.fillStyle = "white";
            }

        ctx.strokeStyle="black";
        ctx.lineWidth = 2;
        //ctx.fillStyle="white";
        ctx.fillRect(debutx,y,100,45);
        ctx.strokeRect(debutx,y,100,45);
        //ctx.fillStyle = (i === indexAllume) ? "white" : "black";

        //ctx.strokeStyle="black";
        //ctx.fillText(`[${i}]`,debutx-30,y+25);

        let actuel = table[i];
        if(actuel==null){
                ctx.strokeStyle="black";
                ctx.font = "bold 14px Arial";
                ctx.fillText(`[${i}]`,debutx-30,y+25);
                    ctx.fillStyle="grey";
                    ctx.font="italic 14px Arial";
                    ctx.fillText("null",debutx+40,y+25);
        }else{
ctx.fillStyle = (i === indexAllume) ? "white" : "black";
          ctx.font = "bold 14px Arial";
          ctx.font = "bold 14px Arial";
            ctx.fillText(`${i}`, debutx + 40, y + 27);

            let decalagex= 130;
            ctx.beginPath();
            ctx.moveTo(debutx + 100, y + 22);
            ctx.lineTo(debutx + 130, y + 22);
            ctx.strokeStyle = "black";
            ctx.stroke();
    while(actuel !== null) {
        const noeudx = debutx + decalagex;

    // Ligne de liaison
        ctx.beginPath();
        ctx.moveTo(noeudx, y + 22);
        ctx.lineTo(noeudx + 35, y + 22);
        ctx.strokeStyle = "black";
        ctx.lineWidth = 2;
        ctx.stroke();

    // Case données
        ctx.fillStyle = "white";
        ctx.fillRect(noeudx + 35, y, 80, 45);
        ctx.strokeStyle = "black";
        ctx.strokeRect(noeudx + 35, y, 80, 45);
        ctx.fillStyle = "black";
        ctx.font = "12px Arial";
        ctx.fillText(`${actuel.cle}:${actuel.value}`, noeudx + 42, y + 27);

    // Case pointeur (noire)
        ctx.fillStyle = "black";
        ctx.fillRect(noeudx + 115, y, 40, 45);
        ctx.strokeStyle = "black";
        ctx.strokeRect(noeudx + 115, y, 40, 45);

    if(actuel.next === null) {
        ctx.fillStyle = "white";
        ctx.font = "italic 12px Arial";
        ctx.fillText("null", noeudx + 120, y + 27);
    }

    actuel = actuel.next;
    decalagex += 155; //

    //35 (flèche) + 80 (données) + 40 (pointeur)
}


                /*//la calse principale de données
                ctx.fillStyle = "white";
                ctx.fillRect(noeudx, y, 100, 45);
                ctx.strokeStyle = "black";
                ctx.strokeRect(noeudx,y,100,45);
                ctx.fillStyle="black";
                ctx.font = "12px Arial";
                ctx.fillText(`${actuel.cle}:${actuel.value}`, noeudx + 10, y + 27);

                //case pointeur
                ctx.fillStyle="black";
                ctx.fillRect(noeudx+80,y,50,45);
                ctx.strokeStyle = "black";
                ctx.strokeRect(noeudx+80,y,50,45);
                //ctx.fillStyle="white";
                //ctx.font="italic 14px Arial";
                //ctx.fillText("null",debutx+220,y+28);*/

                /*if (actuel.next!== null){
                       ctx.beginPath();
                       ctx.moveTo(noeudx+100,y+22);
                       ctx.lineTo(noeudx + 130, y + 22);
                       ctx.strokeStyle = "black";
                       ctx.stroke();
                }/*else{
                ctx.fillStyle="white";
                ctx.font="italic 14px Arial";
                ctx.fillText("null",debutx+220,y+28);
                }
                actuel=actuel.next;
                decalagex+=145;
            }
                            ctx.fillStyle="white";
                            ctx.font="italic 14px Arial";
                            ctx.fillText("null",debutx+220,y+28);
        }*/
}}}
function calculerhash(cle){
    let hash=5381;
    for(let i=0; i<cle.length;i++){
        const ascii= cle.charCodeAt(i);
        hash=hash*33+ascii;
    }
    return Math.abs(hash)%10;
}
function supprimer(cle){
    let index=calculerhash(cle);
    if(table[index]==null){
    alert("Cette clé n'existe pas !");
        return false;
    }
    if(table[index].cle === cle){
        table[index]=table[index].next;
        dessinertable();
        return true;
    }
    let actuel = table[index];
        while(actuel.next!==null){
        if (actuel.next.cle === cle) {
            actuel.next=actuel.next.next;
            dessinertable();
       return true;}
       actuel=actuel.next;
    }
    alert("Cette clé n'existe pas !");
        return false;
}
function add(cle, valeur){
        const index=calculerhash(cle);
        const n = new Node(cle,valeur);
        if(table[index]==null){
            table[index]=n;
        }else{
            let actuel =table[index];
            while(actuel.next!=null){
                actuel=actuel.next;
            }
            actuel.next=n;
        }

dessinertable();
}
function find(cle){
    const index = calculerhash(cle);
    if(table[index]==null){
        alert("cle non trouvée!");
            return false;
     }
        let actuel = table[index];//un pointeur actuel
            while(actuel!==null){
            if (actuel.cle === cle) {
            indexAllume = index;
            dessinertable();
                 //alert(`Élément trouvé ! Index: [${index}], Clé: ${actuel.cle}, Valeur: ${actuel.value}`);
                 setTimeout(() => {
                 indexAllume=null;
                 dessinertable();        // On redessine pour remettre la case en blanc
                             }, 2000);
                           return true;}
        actuel = actuel.next;
        }
        alert("Cette clé n'existe pas !");
            return false;
    }



dessinertable();

const btnadd= document.getElementById("btnadd");
const btndelete= document.getElementById("btndelete");
const btnfind= document.getElementById("btnfind");
const inputs = document.querySelectorAll(".appbar input");//renvoie une liste de ces éléments sous la forme d'un tableau
const inputcle=inputs[0];
const inputvaleur=inputs[1];

btnadd.addEventListener("click",()=>{
    const cle=inputcle.value.trim();
    const valeur=inputvaleur.value.trim();

    if(cle=="" || valeur==""){
        alert("tape une cle et une valeur!");
        return;
    }
    add(cle, valeur);

    inputvaleur.value="";
    inputcle.value="";

});

btndelete.addEventListener("click",()=>{
       const cle = inputcle.value.trim();
       if(cle==""){
            alert("tape une cle!");
            return;
       }
       supprimer(cle);
       inputcle.value="";
});

btnfind.addEventListener("click",()=>{
    const cle = inputcle.value.trim();
            if (cle == "") {
                alert("tape une cle !");
                return;
            }
            find(cle);
            inputcle.value = "";
});
})