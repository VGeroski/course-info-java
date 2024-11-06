package org.learning.courseinfo.server;

import jakarta.ws.rs.*;
import jakarta.ws.rs.core.MediaType;
import org.learning.courseinfo.domain.Course;
import org.learning.courseinfo.repository.CourseRepository;
import org.learning.courseinfo.repository.RepositoryException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Comparator;
import java.util.List;
import java.util.stream.Stream;

@Path("/courses")
public class CourseResource {
    private static final Logger LOG = LoggerFactory.getLogger(CourseResource.class);

    private final CourseRepository courseRepository;

    public CourseResource(CourseRepository courseRepository) {
        this.courseRepository = courseRepository;
    }

    @GET
    @Produces(MediaType.APPLICATION_JSON)
    public Stream<Course> getCourses() {
        try {
            return courseRepository
                    .getAllCourses()
                    .stream()
                    .sorted(Comparator.comparing(Course::id));

        } catch (RepositoryException e) {
            LOG.error("Could not retrieve courses from database", e);
            throw new NotFoundException(); // 404 code
        }
    }

    @POST
    @Path("/{id}/notes")
    @Consumes(MediaType.TEXT_PLAIN)
    public void addNotes(@PathParam("id") String id, String notes) {
        // test: curl -X POST -H "Content-Type: text/plain" --data "My Course notes ..." http://localhost:8080/courses/0db3d4f0-4268-4e80-a441-58a8ea19b4c0/notes
        courseRepository.addNotes(id, notes);
    }
}
