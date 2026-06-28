public class Rectangle extends Shape implements Drawable , Transformable{
    private double longeur;
    private double larg;
    public Rectangle(double longeur,double larg){
        super();
        this.longeur=longeur;
        this.larg=larg;
    }
    @Override
    public double getArea(){
        return(longeur*larg);
    }
    @Override
    public void draw(){
        System.out.println("dessiner un rectangle de longeur :"+longeur +" et de largeur:"+larg);
    }
    @Override
    public void resize(double f){
        System.out.println("redimentionnement du rectangle par un facteur de "+f);
    }
}
