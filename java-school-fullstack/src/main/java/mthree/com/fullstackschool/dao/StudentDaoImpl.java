package mthree.com.fullstackschool.dao;

import mthree.com.fullstackschool.dao.mappers.StudentMapper;
import mthree.com.fullstackschool.model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import java.util.List;

@Repository
public class StudentDaoImpl implements StudentDao {

    @Autowired
    private final JdbcTemplate jdbcTemplate;

    public StudentDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public Student createNewStudent(Student student) {
        //YOUR CODE STARTS HERE

        final String INSERT_STUDENT = "INSERT INTO student (fName, lName) VALUES (?, ?)";
        jdbcTemplate.update(INSERT_STUDENT,
                student.getStudentFirstName(),
                student.getStudentLastName());

        return student;
        //YOUR CODE ENDS HERE
    }

    @Override
    public List<Student> getAllStudents() {
        //YOUR CODE STARTS HERE

        final String SELECT_ALL_STUDENTS = "SELECT * FROM student";
        return jdbcTemplate.query(SELECT_ALL_STUDENTS, new StudentMapper());

        //YOUR CODE ENDS HERE
    }

    @Override
    public Student findStudentById(int id) {
        //YOUR CODE STARTS HERE

        final String SELECT_STUDENT_BY_ID = "SELECT * FROM student WHERE sid = ?";
        // runs sql expecting exactly one row
        return jdbcTemplate.queryForObject(SELECT_STUDENT_BY_ID, new StudentMapper(), id);

        //YOUR CODE ENDS HERE
    }

    @Override
    public void updateStudent(Student student) {
        //YOUR CODE STARTS HERE

        final String UPDATE_STUDENT_BY_ID = "UPDATE student SET fName = ?, lName = ? WHERE sid = ?";
        jdbcTemplate.update(UPDATE_STUDENT_BY_ID,
                student.getStudentFirstName(),
                student.getStudentLastName(),
                student.getStudentId());
        //YOUR CODE ENDS HERE
    }

    @Override
    public void deleteStudent(int id) {
        //YOUR CODE STARTS HERE

        final String DELETE_STUDENT_BY_ID = "DELETE FROM student WHERE sid = ?";
        jdbcTemplate.update(DELETE_STUDENT_BY_ID, id);

        //YOUR CODE ENDS HERE
    }

    @Override
    public void addStudentToCourse(int studentId, int courseId) {
        //YOUR CODE STARTS HERE

        final String INSERT_STUDENT_COURSE_ENROLLMENT = "INSERT INTO course_student (student_id, course_id) " +
                "VALUES (?, ?)";
        jdbcTemplate.update(INSERT_STUDENT_COURSE_ENROLLMENT, studentId, courseId);

        //YOUR CODE ENDS HERE
    }

    @Override
    public void deleteStudentFromCourse(int studentId, int courseId) {
        //YOUR CODE STARTS HERE

        final String DELETE_STUDENT_COURSE_ENROLLMENT = "DELETE FROM course_student " +
                "WHERE student_id = ? AND course_id = ?";
        jdbcTemplate.update(DELETE_STUDENT_COURSE_ENROLLMENT, studentId, courseId);

        //YOUR CODE ENDS HERE
    }
}
