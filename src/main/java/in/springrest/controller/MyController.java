package in.springrest.controller;

import in.springrest.entities.Course;
import in.springrest.services.CourseService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public List<Course> getCourses() {
        return this.courseService.getCourses();
    }

    // single course get
    @GetMapping("/courses/{courseId}")
    public Course getCourse(@PathVariable Long courseId) {
        return this.courseService.getCourse(courseId);
    }

    // course add
    @PostMapping("/courses")
    public Course addCourse(@RequestBody Course course) {
        return this.courseService.addCourse(course);
    }

    // update course using PUT request
    @PutMapping("/courses/{courseId}")
    public Course updateCourse(@PathVariable Long courseId, @RequestBody Course course) {
        return this.courseService.updateCourse(courseId, course);
    }

    // delete course
    @DeleteMapping("/courses/{courseId}")
    public void deleteCourse(@PathVariable Long courseId) {
        this.courseService.deleteCourse(courseId);
    }

    //Partially update
    @PatchMapping("/courses/{courseId}")
    public Course partiallyUpdateCourse(@PathVariable Long courseId,@RequestBody Course course){
        return this.courseService.partiallyUpdateCourses(courseId,course);
    }

}