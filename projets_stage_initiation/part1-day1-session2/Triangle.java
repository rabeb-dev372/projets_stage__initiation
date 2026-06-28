public class Triangle extends Shape implements Drawable , Transformable{
    private double base;
    private double hauteur;
    public Triangle(double base, double h){
        super();
        this.base=base;
        this.hauteur=h;
    }
    @Override
    public double getArea(){
        return(hauteur*base)/2;
    }
    @Override
    public void draw(){
        System.out.println("dessiner un triangle de base:"+base+" et de hauteur : "+hauteur);
    }
    @Override
    public void resize(double f){
        System.out.println("redimentionnement du triangle par un facteur de "+f);
    }
}
