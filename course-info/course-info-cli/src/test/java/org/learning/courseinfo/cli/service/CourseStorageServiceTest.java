package org.learning.courseinfo.cli.service;

import org.junit.jupiter.api.Test;
import org.learning.courseinfo.domain.Course;
import org.learning.courseinfo.repository.CourseRepository;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CourseStorageServiceTest {

    @Test
    void storePluralsightCourses() {

        CourseRepository repository = new InMemoryCourseRepository();
        CourseStorageService courseStorageService = new CourseStorageService(repository);

        PluralsightCourse ps1 = new PluralsightCourse("1", "Title", "01:40:00", "/url-1", false);
        courseStorageService.storePluralsightCourses(List.of(ps1));

        Course expected = new Course("1", "Title", 100, "https://app.pluralsight.com/url-1");

        // check if translation between pluralsight course and course is correct
        assertEquals(List.of(expected), repository.getAllCourses());
    }

    /**
     * In memory mock of database just for testing purposes
     */
    static class InMemoryCourseRepository implements CourseRepository {
        private final List<Course> courses = new ArrayList<>();

        @Override
        public void save(Course course) {
            courses.add(course);
        }

        @Override
        public List<Course> getAllCourses() {
            return courses;
        }
    }
}