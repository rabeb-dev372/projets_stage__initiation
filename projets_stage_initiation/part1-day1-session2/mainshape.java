import java.util.List;
import java.util.ArrayList;
public class mainshape {
    public static void main(String [] args){
        List<Shape>poly=new ArrayList<>();
        poly.add(new Rectangle(4,5));
        poly.add(new Triangle(4,3));
        poly.add(new Circle(2));
        for (Shape sch :poly){
            sch.draw();
            System.out.println("l'aire est: "+sch.getArea());
            sch.resize(2.3);
    }}
}
