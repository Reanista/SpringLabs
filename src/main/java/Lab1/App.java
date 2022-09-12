package Lab1;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Lab1Shape.xml");
        Circle firstCircle = context.getBean("myCircle", Circle.class);
        firstCircle.draw();
        Point firstPoint = context.getBean("myPoint", Point.class);
        firstPoint.draw();

    }
}

