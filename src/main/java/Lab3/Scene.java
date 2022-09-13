package Lab3;

import java.util.List;

public class Scene {
	
	List<Shape> objects;
	

	public void draw() {
		for(Shape s : objects)
			s.draw();
	}
}
