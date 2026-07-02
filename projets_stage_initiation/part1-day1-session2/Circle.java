
public class Circle extends Shape implements Drawable , Transformable {
    private double rayon;
    public Circle(double r){
        super();
        this.rayon=r;
    }
    @Override
    public double getArea(){
        return(3.14*rayon*rayon);
    }
    @Override
    public void draw(){
        System.out.println("dessiner un cercle de rayon:"+rayon);
    }
    @Override
    public void resize(double f){
        System.out.println("redimentionnement du cercle par un facteur de "+f);
    }
}
