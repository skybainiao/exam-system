package via.examsystem.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import java.util.Date;
import java.util.Set;

@Entity
@Table(name = "exams")
public class Exam {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String title;
    private Date examDate;

    private String examPassword;  // 考试密码

    @ManyToOne
    @JoinColumn(name = "course_id")
    private Course course;

    @OneToMany(mappedBy = "exam")
    @JsonManagedReference
    private Set<Question> questions; // 考试的所有问题

    public Exam() {
    }

    public Exam(String title, Date examDate, String examPassword, Course course) {
        this.title = title;
        this.examDate = examDate;
        this.examPassword = examPassword;
        this.course = course;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public Date getExamDate() {
        return examDate;
    }

    public void setExamDate(Date examDate) {
        this.examDate = examDate;
    }

    public Course getCourse() {
        return course;
    }

    public void setCourse(Course course) {
        this.course = course;
    }

    public void setExamPassword(String examPassword) {
        this.examPassword = examPassword;
    }

    public String getExamPassword() {
        return examPassword;
    }

    public Set<Question> getQuestions() {
        return questions;
    }

    public void setQuestions(Set<Question> questions) {
        this.questions = questions;
    }

    @Override
    public String toString() {
        return "Exam{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", examDate=" + examDate +
                ", examPassword='" + examPassword + '\'' +
                ", course=" + course.getName() + // 只打印课程的名称
                '}';
    }
}
