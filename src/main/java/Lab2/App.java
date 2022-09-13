package Lab2;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.PropertySource;


@ComponentScan("Lab2")
@PropertySource("shape.properties")
public class App 
{
    public static void main( String[] args )
    {

    	try(AnnotationConfigApplicationContext context =
    			new AnnotationConfigApplicationContext(GraphConfig.class) ) {
    		
    		context.getBean("myPoint", Point.class).draw();
    		context.getBean("myCircle", Circle.class).draw();
			context.close();
    	}


    }
}
