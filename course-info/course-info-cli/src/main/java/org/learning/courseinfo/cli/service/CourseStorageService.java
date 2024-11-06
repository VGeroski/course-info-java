package org.learning.courseinfo.cli.service;

import org.learning.courseinfo.domain.Course;
import org.learning.courseinfo.repository.CourseRepository;

import java.util.List;
import java.util.Optional;

public class CourseStorageService {
    private static final String PS_BASE_URL = "https://app.pluralsight.com";
    private final CourseRepository courseRepository;

    public CourseStorageService(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    public void storePluralsightCourses(List<PluralsightCourse> pluralsightCourses) {
        for (PluralsightCourse course : pluralsightCourses) {
            Course newCourse = new Course(
                    course.id(), course.title(), course.durationInMinutes(), PS_BASE_URL + course.contentUrl(), Optional.empty()
            );
            courseRepository.save(newCourse);
        }
    }
}
