package Lab4.JDBC;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreatorFactory;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.List;

public class JDBCCourseDAO implements CourseDAO {
    private static final String SQL_SELECT_COURSE=
            "SELECT id, title, length, description from courses";
    private static final String SQL_SELECT_COURSE_BY_ID=
            SQL_SELECT_COURSE + " WHERE id = ?";
    private static final String SQL_DELETE_COURSE_BY_ID=
             "DELETE from courses WHERE id = ?";
    private static final String SQL_SELECT_COURSE_BY_TITLE=
            SQL_SELECT_COURSE + " WHERE title LIKE ?";
    private static final String SQL_INSERT_COURSE=
            "INSERT INTO courses (title, length, description) VALUES (?, ?, ?)";
    private static final String SQL_UPDATE_COURSE=
            "UPDATE courses SET title = ?, length = ?, description = ? where id = ?";

    private JdbcTemplate jdbcTemplate;

    public JdbcTemplate getJdbcTemplate() {
        return jdbcTemplate;
    }

    class CourseRowMapper implements RowMapper<Courses> { //не нужно беспокоиться о типах данных
        @Override
        public Courses mapRow(ResultSet rs, int rowNum) throws SQLException {
            Courses c = new Courses();
            c.setId(rs.getInt("id"));
            c.setTitle(rs.getString("title"));
            c.setLength(rs.getInt("length"));
            c.setDescription(rs.getString("description"));
            return c;
        }
    }

    public void setJdbcTemplate(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Courses> findByTitle(String title){
        return getJdbcTemplate().query(SQL_SELECT_COURSE_BY_TITLE, new Object[] {"%" + title + "%"}, new BeanPropertyRowMapper(Courses.class));

    }

    @Override
    public void insert(Courses course){
        PreparedStatementCreatorFactory f = new PreparedStatementCreatorFactory(SQL_INSERT_COURSE, Types.NVARCHAR, Types.INTEGER, Types.NVARCHAR);
        f.setGeneratedKeysColumnNames("id");
        KeyHolder kh = new GeneratedKeyHolder();
        getJdbcTemplate().update(f.newPreparedStatementCreator(new Object[] {
                course.getTitle(), course.getLength(), course.getDescription()
        }), kh);
        course.setId(kh.getKey().intValue());

    }

    @Override
    public void update(Courses course){
        getJdbcTemplate().update(SQL_UPDATE_COURSE, course.getTitle(), course.getLength(), course.getDescription(), course.getId());
    }
    @Override
    public void delete(int id){
        getJdbcTemplate().update(SQL_DELETE_COURSE_BY_ID, id);
    }


    @Override
    public Courses findById(int id){
        Courses courses = getJdbcTemplate().queryForObject(SQL_SELECT_COURSE_BY_ID, new Object[] {id},
                new CourseRowMapper());
        return courses;
    }
    @Override
    public List<Courses> findAll() {
        //Приведение листа к типа Courses и создание элементов вручную
        //Вернется List<Map<String, Object>> - лист, состоящая из ассоциативной коллекции, в которой будет название колонки и значение
        /*List<Map<String, Object>> rows =
                getJdbcTemplate().queryForList(SQL_SELECT_COURSE);

        List<Courses> courses = new ArrayList<Courses>();
        for (Map<String, Object> row : rows) {
            Courses c = new Courses();
            c.setId((int) row.get("id"));
            c.setTitle((String) row.get("title"));
            c.setLength((int) row.get("length"));
            c.setDescription((String) row.get("description"));
            courses.add(c);
        }*/

        //Использование RowMapper, отдаем все Spring. Берет строки и колонки мэппит в одноименное проперти
        //Можем передать дефолтный BeanPropertyRowMapper, если проперти совпадают, а можем передать свой CourseRowMapper
        List<Courses> courses = getJdbcTemplate().query(SQL_SELECT_COURSE, new BeanPropertyRowMapper(Courses.class));
        List<Courses> courses1 = getJdbcTemplate().query(SQL_SELECT_COURSE, new CourseRowMapper());
        return courses1;

    }

    }

