package Lab3;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;



public class App 
{
    public static void main( String[] args ) throws Exception
    {
		try (AnnotationConfigApplicationContext context =
				new AnnotationConfigApplicationContext(GraphConfig.class))
		{
			context.getBean(Circle.class).draw();
			context.getBean(Point.class).draw();
			
			context.getBean(Scene.class).draw();
		}
		
    }
}
