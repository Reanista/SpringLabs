package Lab2;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component("myPoint")
@Scope("prototype")
public class Point extends Shape {
	private Coords coords;
	
	
	public Coords getCoords() {
		return coords;
	}
	public void setCoords(Coords coords) {
		this.coords = coords;
	}
	public Point(@Value("#{coords}") Coords coords) {
		this.coords = coords;
	}
	
	public int getX() {
		return getCoords().getX();
	}
	
	@Value("#{T(java.lang.Math).random()*100}")
	public void setX(int y) {
		getCoords().setX(y);
	}
	public int getY() {
		return getCoords().getY();
	}
	
	@Value("#{T(java.lang.Math).random()*100}")
	public void setY(int y) {
		getCoords().setY(y);
	}
	
	public void draw() {
		System.out.printf("Point (%d, %d)Color: %s\n", 
				getX(), getY(), getColor());
		
	}

}
