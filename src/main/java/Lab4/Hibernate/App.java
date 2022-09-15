package Lab4.Hibernate;

import org.hibernate.Hibernate;
import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Lab4Hibernate.xml"); //интерфейс не меняется, одинаково по JDBC и Hinernate
        CourseDAO courseDAO = context.getBean(CourseDAO.class);

        // --- Delete

        //courseDAO.delete(8);

        // --- Update
/*
        Courses s = courseDAO.findById(11);
        s.setLength(54);
        courseDAO.update(s);
        System.out.println(s);
*/

        // --- Insert
/*
        Courses courses = new Courses();
        courses.setTitle("Hibernate");
        courses.setDescription("ORM");
        courses.setLength(23);
        System.out.println(courses);
        courseDAO.insert(courses);
        System.out.println(courses);
*/

        //--- Find by Title
/*
        for(Courses c : courseDAO.findByTitle("Web")){
           System.out.println(c);}
*/
        //--- Find All

        for(Courses c : courseDAO.findAll()){
            System.out.println(c);}


        // --- Find by ID
        /*
        System.out.printf("Курс: %s\n", courseDAO.findById(4));
        context.close(); */

    }
}
