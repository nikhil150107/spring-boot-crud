package in.springrest.Repository;

import in.springrest.entities.Course;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.*;

@Repository
public interface CourseDao extends JpaRepository<Course,Long> {
    List<Course> findByCourseName(String courseName);

}
