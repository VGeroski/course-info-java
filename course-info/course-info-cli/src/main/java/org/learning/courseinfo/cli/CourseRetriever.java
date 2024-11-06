package org.learning.courseinfo.cli;

import org.learning.courseinfo.cli.service.CourseRetrievalService;
import org.learning.courseinfo.cli.service.PluralsightCourse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.List;

public class CourseRetriever {

    private static final Logger LOG = LoggerFactory.getLogger(CourseRetriever.class);

    public static void main(String[] args) {
        LOG.info("Starting CourseRetriever...");

        if (args.length == 0) {
            LOG.warn("Please provide an author name as first argument");
            return;
        }

        try {
            retrieveCourses(args[0]);

        } catch (Exception e) {
            LOG.error("Unexpected error occurred", e);
        }
    }

    private static void retrieveCourses(String authorId) {
        LOG.info("Retrieving courses for author '{}'", authorId);

        CourseRetrievalService courseRetrievalService = new CourseRetrievalService();
        List<PluralsightCourse> coursesToStore = courseRetrievalService.getCourseFor(authorId)
                .stream()                              // convert list to stream, and then apply filtering
                .filter(course -> !course.isRetired()) // filter to present only courses that are not retired
                .toList();                             // return to List after filtering
        LOG.info("Retrieved the following {} courses {}", coursesToStore.size(), coursesToStore);
    }

}
