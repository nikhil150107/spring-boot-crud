package in.springrest.services;

import in.springrest.entities.Course;
import java.util.List;

public interface CourseService {
    List<Course> getCourses();
    Course getCourse(Long courseId);
    Course addCourse(Course course);
    Course updateCourse(Long courseId, Course course);
    void deleteCourse(Long courseId);
    Course partiallyUpdateCourses(Long courseId,Course course);
}