package mthree.com.fullstackschool.dao;

import mthree.com.fullstackschool.dao.mappers.TeacherMapper;
import mthree.com.fullstackschool.model.Teacher;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public class TeacherDaoImpl implements TeacherDao {

    private final JdbcTemplate jdbcTemplate;

    public TeacherDaoImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Teacher createNewTeacher(Teacher teacher) {
        //YOUR CODE STARTS HERE

        final String INSERT_TEACHER = "INSERT INTO teacher (tFName, tLName) VALUES (?, ?)";
        jdbcTemplate.update(INSERT_TEACHER,
                teacher.getTeacherFName(),
                teacher.getTeacherLName());

        return teacher;
        //YOUR CODE ENDS HERE
    }

    @Override
    public List<Teacher> getAllTeachers() {
        //YOUR CODE STARTS HERE

        final String SELECT_ALL_TEACHERS = "SELECT * FROM teacher";
        return jdbcTemplate.query(SELECT_ALL_TEACHERS, new TeacherMapper());

        //YOUR CODE ENDS HERE
    }

    @Override
    public Teacher findTeacherById(int id) {
        //YOUR CODE STARTS HERE

        final String SELECT_TEACHER_BY_ID = "SELECT * FROM teacher WHERE tid = ?";
        // runs sql expecting exactly one row
        return jdbcTemplate.queryForObject(SELECT_TEACHER_BY_ID, new TeacherMapper(), id);
        //YOUR CODE ENDS HERE
    }

    @Override
    public void updateTeacher(Teacher t) {
        //YOUR CODE STARTS HERE

        final String UPDATE_TEACHER_BY_ID = "UPDATE teacher SET tFName = ?, tLName = ?, dept = ? WHERE tid = ?";
        jdbcTemplate.update(UPDATE_TEACHER_BY_ID,
                t.getTeacherFName(),
                t.getTeacherLName(),
                t.getDept(),
                t.getTeacherId());

        //YOUR CODE ENDS HERE
    }

    @Override
    public void deleteTeacher(int id) {
        //YOUR CODE STARTS HERE

        final String DELETE_TEACHER_BY_ID = "DELETE FROM teacher WHERE tid = ?";
        jdbcTemplate.update(DELETE_TEACHER_BY_ID, id);

        //YOUR CODE ENDS HERE
    }
}
