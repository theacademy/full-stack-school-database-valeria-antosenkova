package mthree.com.fullstackschool.service;

import mthree.com.fullstackschool.dao.CourseDao;
import mthree.com.fullstackschool.model.Course;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseServiceInterface {


    //YOUR CODE STARTS HERE
    private final CourseDao courseDao;

    public CourseServiceImpl(CourseDao courseDao) {
        this.courseDao = courseDao;
    }

    //YOUR CODE ENDS HERE

    public List<Course> getAllCourses() {
        //YOUR CODE STARTS HERE

        return courseDao.getAllCourses();
        //YOUR CODE ENDS HERE
    }

    public Course getCourseById(int id) {
        //YOUR CODE STARTS HERE

        return courseDao.findCourseById(id);

        //YOUR CODE ENDS HERE
    }

    public Course addNewCourse(Course course) {
        //YOUR CODE STARTS HERE

        if (course.getCourseName().isBlank()) {
            course.setCourseName("Name blank, course NOT added");
        }

        if (course.getCourseDesc().isBlank()) {
            course.setCourseDesc("Description blank, course NOT added");
        }

        return courseDao.createNewCourse(course);

        //YOUR CODE ENDS HERE
    }

    public Course updateCourseData(int id, Course course) {
        //YOUR CODE STARTS HERE

        if (id != course.getCourseId()) {
            course.setCourseName("IDs do not match, course not updated");
            course.setCourseDesc("IDs do not match, course not updated");
            return course;
        }

        courseDao.updateCourse(course);

        //YOUR CODE ENDS HERE
        return course;
    }

    public void deleteCourseById(int id) {
        //YOUR CODE STARTS HERE
        courseDao.deleteAllStudentsFromCourse(id);
        courseDao.deleteCourse(id);

        //YOUR CODE ENDS HERE
    }
}
