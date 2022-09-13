package Lab2;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("myCircle")
@Scope("prototype")
public class Circle extends Shape {
	private Coords center;
	private int r;
	
	
	public Coords getCenter() {
		return center;
	}
	public void setCenter(Coords center) {
		this.center = center;
	}
	public int getR() {
		return r;
	}
	
	@Value("#{T(java.lang.Math).sqrt(myPoint.x*myPoint.x+myPoint.y*myPoint.y)}")
	public void setR(int r) {
		this.r = r;
	}
	
	
	@Autowired
	public Circle(Coords center) {
		this(center, 0);
	}
	
	public Circle(Coords center, int r) 
	{
		super();
		this.center = center;
		this.r = r;
	}
	
	public int getX() {
		return getCenter().getX();
	}
	
	public void setX(int y) {
		getCenter().setX(y);
	}
	public int getY() {
		return getCenter().getY();
	}
	
	public void setY(int y) {
		getCenter().setY(y);
	}
	
	@Override
	@Value("${circle.color}")
	public void setColor(String color) {
		super.setColor(color);
	}
	
	public void draw() {
		System.out.printf("Circle (%d, %d) R: %d Color: %s\n", 
				getX(), getY(), getR(), getColor());
		
	}
}
