package org.learning.courseinfo.repository;

import org.learning.courseinfo.domain.Course;

import java.util.List;

public interface CourseRepository {
    void save(Course course);

    List<Course> getAllCourses();

    static CourseRepository openCourseRepository(String databaseFile) {
        return new CourseJdbcRepository(databaseFile);
    }
}
