package mthree.com.fullstackschool.dao;

import mthree.com.fullstackschool.dao.mappers.CourseMapper;
import mthree.com.fullstackschool.model.Course;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class CourseDaoImpl implements CourseDao {

    private final JdbcTemplate jdbcTemplate;

    public CourseDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Course createNewCourse(Course course) {
        //YOUR CODE STARTS HERE

        final String INSERT_COURSE = "INSERT INTO course (courseCode, courseDesc, teacherId) VALUES (?, ?, ?)";

        jdbcTemplate.update(INSERT_COURSE,
                course.getCourseName(),
                course.getCourseDesc(),
                course.getTeacherId());

        return course;

        //YOUR CODE ENDS HERE
    }

    @Override
    public List<Course> getAllCourses() {
        //YOUR CODE STARTS HERE

        final String SELECT_ALL_COURSES = "SELECT cid, courseCode, courseDesc, teacherId " +
                "FROM course";
        return jdbcTemplate.query(SELECT_ALL_COURSES, new CourseMapper());

        //YOUR CODE ENDS HERE
    }

    @Override
    public Course findCourseById(int id) {
        //YOUR CODE STARTS HERE

        final String SELECT_COURSE_BY_ID = "SELECT * FROM course WHERE cid = ?";
        try {
            return jdbcTemplate.queryForObject(SELECT_COURSE_BY_ID, new CourseMapper(), id);
        } catch (EmptyResultDataAccessException e) {
            return null;
        }

        //YOUR CODE ENDS HERE
    }

    @Override
    public void updateCourse(Course course) {
        //YOUR CODE STARTS HERE

        final String UPDATE_COURSE_BY_ID = "UPDATE course " +
                "SET courseCode = ?, courseDesc = ?, teacherId = ? " +
                "WHERE cid = ?";
        jdbcTemplate.update(UPDATE_COURSE_BY_ID,
                course.getCourseName(),
                course.getCourseDesc(),
                course.getTeacherId() == 0 ? null : course.getTeacherId(),
                course.getCourseId());

        //YOUR CODE ENDS HERE
    }

    @Override
    public void deleteCourse(int id) {
        //YOUR CODE STARTS HERE

        final String DELETE_COURSE_BY_ID = "DELETE FROM course " +
                "WHERE cid = ?";
        jdbcTemplate.update(DELETE_COURSE_BY_ID, id);

        //YOUR CODE ENDS HERE
    }

    @Override
    public void deleteAllStudentsFromCourse(int courseId) {
        //YOUR CODE STARTS HERE

        final String DELETE_STUDENTS_BY_COURSE_ID = "DELETE FROM course_student " +
                "WHERE course_id = ?";
        jdbcTemplate.update(DELETE_STUDENTS_BY_COURSE_ID, courseId);

        //YOUR CODE ENDS HERE
    }
}
