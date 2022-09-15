package Lab4.Hibernate;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;
@Transactional
@Repository("courseDAO")
public class HibernateCourseDAO implements CourseDAO {
    private static final Log LOG = LogFactory.getLog(HibernateCourseDAO.class);
    SessionFactory sessionFactory ;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    // //DependencyInjection - связывается с бином sessionFactory, сконфиг-м в xml.
    // Можно использовать @Autowire, тк наименования бина сходится с конфигурацией. Resource из javax.annotation
    @Resource(name = "sessionFactory")
    public void setSessionFactory(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    @Transactional(readOnly = true)
    public Courses findById(int id){
        return (Courses)getSessionFactory().getCurrentSession().
                byId(Courses.class).load(id);
    }

    @Override
    @Transactional(readOnly = true)
    public List<Courses> findAll(){
        return getSessionFactory().getCurrentSession().createQuery("from Course c").list(); //HQL

    }
    @Override
    public List<Courses> findByTitle(String title){
        return getSessionFactory().getCurrentSession().createQuery("from Course c where c.title = :web")
                .setParameter("title", "%" + title.trim() + "%").list(); //HQ


    }

    @Override
    public void insert(Courses courses){
        getSessionFactory().getCurrentSession().save(courses);
        LOG.info("Course saved with id: " + courses.getId()); //автоматически обновится значение ID за счет аннотации @GeneratedValue
    }

    @Override

    public void update(Courses courses){
        getSessionFactory().getCurrentSession().update(courses);
        LOG.info("Course updated with id: " + courses.getId());
    }
    @Override
    public void delete(int id){
        Courses c = new Courses();
        c.setId(id);
        getSessionFactory().getCurrentSession().delete(c); // так как принимает в качестве параметра не ID, а объект, необходимо создать
        // объект, в который вложим ID записи, которбу хотим удалить
        LOG.info("Course deleted with id: " + c.getId());
    }


}