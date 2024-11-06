package org.learning.courseinfo.repository;

import org.learning.courseinfo.domain.Course;

import java.util.List;

public interface CourseRepository {
    void save(Course course);

    List<Course> getAllCourses();

    void addNotes(String id, String notes);

    static CourseRepository openCourseRepository(String databaseFile) {
        return new CourseJdbcRepository(databaseFile);
    }
}
