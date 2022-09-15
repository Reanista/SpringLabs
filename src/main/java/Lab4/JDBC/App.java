package Lab4.JDBC;

import org.springframework.context.support.ClassPathXmlApplicationContext;

public class App {
    public static void main(String[] args) {
        ClassPathXmlApplicationContext context = new ClassPathXmlApplicationContext("Lab4DAO.xml");
        CourseDAO courseDAO = context.getBean(CourseDAO.class);

        // --- Delete

        courseDAO.delete(8);

        // --- Update
        /*

        Courses s = courseDAO.findById(8);
        s.setLength(54);
        courseDAO.update(s);*/

        /*
        --- Insert

        Courses courses = new Courses();
        courses.setTitle("Spring");
        courses.setDescription("Spring framework");
        courses.setLength(40);
        System.out.println(courses);
        courseDAO.insert(courses);
        System.out.println(courses);*/

        //--- Find by Title

        //for(Courses c : courseDAO.findByTitle("Web")){
        //    System.out.println(c);}

        //--- Find All

        for(Courses c : courseDAO.findAll()){
            System.out.println(c);}
        //System.out.printf("Курс: %s\n", courseDAO.findById(4));
        context.close();

    }
}
