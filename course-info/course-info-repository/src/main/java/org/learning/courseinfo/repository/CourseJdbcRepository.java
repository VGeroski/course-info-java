package org.learning.courseinfo.repository;

import org.h2.jdbcx.JdbcDataSource;
import org.learning.courseinfo.domain.Course;

import javax.sql.DataSource;
import java.sql.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

class CourseJdbcRepository implements CourseRepository {
    private static final String H2_DATABASE_URL =
            "jdbc:h2:file:%s;AUTO_SERVER=TRUE;INIT=RUNSCRIPT FROM './db_init.sql'";

    private static final String INSERT_COURSE = """
            MERGE INTO Courses (id, name, length, url)
            VALUES (?, ?, ?, ?)
            """;

    private static final String ADD_NOTES = """
            UPDATE Courses SET notes = ?
            WHERE ID = ?
            """;

    private final DataSource dataSource;

    public CourseJdbcRepository(String databaseFile) {
        JdbcDataSource jdbcDataSource = new JdbcDataSource();
        jdbcDataSource.setURL(H2_DATABASE_URL.formatted(databaseFile));
        this.dataSource = jdbcDataSource;
    }

    @Override
    public void save(Course course) {
        // connection will be close automatically setup this way
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(INSERT_COURSE);
            // Since we have placeholders in prepare statement for values, we need to provide actual values into statement
            statement.setString(1, course.id());
            statement.setString(2, course.name());
            statement.setLong(3, course.length());
            statement.setString(4, course.url());
            statement.execute();

        } catch (SQLException e) {
            throw new RepositoryException("Failed to save " + course, e);
        }
    }

    @Override
    public List<Course> getAllCourses() {
        try (Connection connection = dataSource.getConnection()) {
            Statement statement = connection.createStatement();
            ResultSet resultSet = statement.executeQuery("SELECT * FROM Courses");

            // Iterate over results query
            List<Course> courses = new ArrayList<>();
            while (resultSet.next()) {
                Course course = new Course(
                        resultSet.getString(1),
                        resultSet.getString(2),
                        resultSet.getLong(3),
                        resultSet.getString(4),
                        Optional.ofNullable(resultSet.getString(5))
                );

                courses.add(course);
            }
            return Collections.unmodifiableList(courses);

        } catch (SQLException e) {
            throw new RepositoryException("Failed to retrieve courses", e);
        }
    }

    @Override
    public void addNotes(String id, String notes) {
        try (Connection connection = dataSource.getConnection()) {
            PreparedStatement statement = connection.prepareStatement(ADD_NOTES);
            statement.setString(1, notes);
            statement.setString(2, id);
            statement.execute();

        } catch (SQLException e) {
            throw new RepositoryException("Failed to add notes to " + id, e);
        }
    }
}
