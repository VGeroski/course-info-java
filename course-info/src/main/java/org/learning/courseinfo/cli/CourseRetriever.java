package org.learning.courseinfo.cli;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
    }

}
