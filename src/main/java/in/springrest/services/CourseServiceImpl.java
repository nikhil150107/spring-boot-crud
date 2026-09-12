package in.springrest.services;

import in.springrest.Repository.CourseDao;
import in.springrest.entities.Course;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

   @Autowired
   private CourseDao courseDao;


    @Override
    public List<Course> getCourses() {
        return courseDao.findAll();
    }

    @Override
    public Course getCourse(Long courseId) {
        return courseDao.findById(courseId).orElse(null);
    }

    @Override
    public Course addCourse(Course course) {
        return courseDao.save(course);
    }

    @Override
    public Course updateCourse(Long courseId, Course updatedCourse) {
        Course existing = getCourse(courseId);
        if (existing != null) {
            existing.setCourseName(updatedCourse.getCourseName());
            existing.setInstructor(updatedCourse.getInstructor());
            return courseDao.save(existing);
        }
        return null;
    }

    @Override
    public void deleteCourse(Long courseId) {
        courseDao.deleteById(courseId);
    }

    @Override
    public Course partiallyUpdateCourses(Long courseId, Course course) {
        Course existing = getCourse(courseId);
        if (existing != null) {
            if (course.getCourseName() != null) {
                existing.setCourseName(course.getCourseName());
            }
            if (course.getInstructor() != null) {
                existing.setInstructor(course.getInstructor());
            }
            return courseDao.save(existing);
        }
        return null;
    }
}