package Lab4.Hibernate;

import java.util.List;

public interface CourseDAO {
    Courses findById(int id);
    List<Courses> findAll();

    //CRUD
    List<Courses> findByTitle(String title);
    void insert(Courses course);
    void update(Courses course);
    void delete(int id);


}
