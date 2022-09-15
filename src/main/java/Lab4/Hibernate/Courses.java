package Lab4.Hibernate;

import javax.persistence.*;
import java.io.Serializable;
// Использовали аннотации JPA которые будет использовать Hibernate
import static javax.persistence.GenerationType.IDENTITY; // Генерация автоматически базой и чтение автоматом


@Entity // превращает класс в класс-сущности
@Table // связывает класс-сущности с таблицей в MySQL
public class Courses implements Serializable {
    private int id;
    private String title;
    private int length;
    private String description;
@Id //Первичный ключ
@GeneratedValue(strategy = IDENTITY) // Генерация автоматически базой и чтение автоматом
@Column(name="id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    @Column(name="title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }
    @Column(name="length")
    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }
    @Column(name="description")
    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    @Override
    public  String toString(){
        return String.format("%3d %-50s %-4d", getId(), getTitle(),getLength());
    }
}
