package Lab3;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.*;
import org.springframework.core.env.Environment;

import java.util.ArrayList;

@Configuration
@PropertySource("shape.properties")
public class GraphConfig {
	
	@Autowired
	private Environment env;
	
	@Bean("default")
	@Scope("prototype")
	public Coords coords() {
		return new Coords();
	}
	
	@Bean
	@Scope("prototype")
	public Coords coords(@Value("#{default.x}") int x, 
			@Value("#{default.x}")int y) {
		return new Coords(x,y);
	}
	
	@Value("${point.color}")
	private String pointColor; 
	
	@Bean
	@Scope("prototype")
	public Point point() {
		Point p = new Point(coords());
		p.setColor(pointColor);
		return p;
	}
	
	@Bean("defaultCircle")
	@Scope("prototype")
	@Primary
	public Circle circle() {
		Circle c = new Circle(
				coords(env.getProperty("circle.x", Integer.class, 0),
					   env.getProperty("circle.y", Integer.class, 0)),
					   env.getProperty("circle.radius", Integer.class, 0));
		c.setColor(env.getProperty("circle.color", Shape.DEFAULT_COLOR));
		return c;
		
	}
	
	@Bean
	@Scope("prototype")
	public Circle circle(int r) {
		Circle c = circle();
		c.setR(r);
		return c;
		
	}
	
	@Bean
	@Lazy
	//@Scope("singleton")
	public Scene scene() {
		Scene s = new Scene();
		s.objects = new ArrayList<Shape>();
		for(int i=0; i < 2; i++) {
			s.objects.add(point());
			s.objects.add(circle(20));
		}
		return s;
	}

}
