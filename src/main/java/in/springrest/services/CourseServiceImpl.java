package in.springrest.services;

import in.springrest.entities.Course;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CourseServiceImpl implements CourseService {

    private final List<Course> courses = new ArrayList<>();

    @Override
    public List<Course> getCourses() {
        return courses;
    }

    @Override
    public Course getCourse(Long courseId) {
        return courses.stream()
                .filter(c -> c.getCourseId() == courseId)
                .findFirst()
                .orElse(null);
    }

    @Override
    public Course addCourse(Course course) {
        courses.add(course);
        return course;
    }

    @Override
    public Course updateCourse(Long courseId, Course updatedCourse) {
        Course existing = getCourse(courseId);
        if (existing != null) {
            existing.setCourseName(updatedCourse.getCourseName());
            existing.setInstructor(updatedCourse.getInstructor());
        }
        return existing;
    }

    @Override
    public void deleteCourse(Long courseId) {
        courses.removeIf(c -> c.getCourseId() == courseId);
    }

    @Override
    public Course partiallyUpdateCourses(Long courseId, Course course) {
        Course existing = getCourse(courseId);
        if (existing != null) {
            existing.setCourseName(course.getCourseName());
            existing.setInstructor(course.getInstructor());
        }
        return existing;
    }
}