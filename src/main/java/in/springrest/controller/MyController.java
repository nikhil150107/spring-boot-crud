package in.springrest.controller;

import in.springrest.entities.Course;
import in.springrest.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class MyController {

    @Autowired
    private CourseService courseService;

    @GetMapping("/")
    public String home() {
        return "Welcome to SpringRest!";
    }

    // get all courses
    @GetMapping("/courses")
    public ResponseEntity<?> getCourses() {
        try {
            List<Course> courses = courseService.getCourses();
            return new ResponseEntity<>(courses, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error fetching courses: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // single course get
    @GetMapping("/courses/{courseId}")
    public ResponseEntity<?> getCourse(@PathVariable Long courseId) {
        try {
            Course course = courseService.getCourse(courseId);
            if (course == null) {
                return new ResponseEntity<>("Course not found with id: " + courseId, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(course, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error fetching course: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // course add
    @PostMapping("/courses")
    public ResponseEntity<?> addCourse(@RequestBody Course course) {
        try {
            Course saved = courseService.addCourse(course);
            return new ResponseEntity<>(saved, HttpStatus.CREATED);
        } catch (Exception e) {
            return new ResponseEntity<>("Error adding course: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // update course using PUT request
    @PutMapping("/courses/{courseId}")
    public ResponseEntity<?> updateCourse(@PathVariable Long courseId, @RequestBody Course course) {
        try {
            Course updated = courseService.updateCourse(courseId, course);
            if (updated == null) {
                return new ResponseEntity<>("Course not found with id: " + courseId, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating course: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // delete course
    @DeleteMapping("/courses/{courseId}")
    public ResponseEntity<?> deleteCourse(@PathVariable Long courseId) {
        try {
            Course existing = courseService.getCourse(courseId);
            if (existing == null) {
                return new ResponseEntity<>("Course not found with id: " + courseId, HttpStatus.NOT_FOUND);
            }
            courseService.deleteCourse(courseId);
            return new ResponseEntity<>("Course deleted successfully with id: " + courseId, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error deleting course: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    // Partially update
    @PatchMapping("/courses/{courseId}")
    public ResponseEntity<?> partiallyUpdateCourse(@PathVariable Long courseId, @RequestBody Course course) {
        try {
            Course updated = courseService.partiallyUpdateCourses(courseId, course);
            if (updated == null) {
                return new ResponseEntity<>("Course not found with id: " + courseId, HttpStatus.NOT_FOUND);
            }
            return new ResponseEntity<>(updated, HttpStatus.OK);
        } catch (Exception e) {
            return new ResponseEntity<>("Error updating course: " + e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}