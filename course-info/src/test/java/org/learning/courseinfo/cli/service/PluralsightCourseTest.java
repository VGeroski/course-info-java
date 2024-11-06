package org.learning.courseinfo.cli.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.*;

class PluralsightCourseTest {

    @Test
    void durationInMinutes() {
        PluralsightCourse pluralsightCourse = new PluralsightCourse("id", "Test course", "00:05:37", "url", false);

        assertEquals(5, pluralsightCourse.durationInMinutes());
    }

    @Test
    void durationInMinutesOverHour() {
        PluralsightCourse pluralsightCourse = new PluralsightCourse("id", "Test course", "01:05:37.961", "url", false);

        assertEquals(65, pluralsightCourse.durationInMinutes());
    }

    @Test
    void durationInMinutesZero() {
        PluralsightCourse pluralsightCourse = new PluralsightCourse("id", "Test course", "00:00:00", "url", false);

        assertEquals(0, pluralsightCourse.durationInMinutes());
    }

    /**
     * Parametrized test (JUnit 5) can replace 3 tests above as one
     * with csv source we can add multiple scenarios
     * @param input duration
     * @param expected minutes
     */
    @ParameterizedTest
    @CsvSource(textBlock = """
            00:05:37, 5
            01:05:37.961, 65
            00:00:00, 0
            """)
    void durationInMinutesParametrized(String input, long expected) {
        PluralsightCourse pluralsightCourse = new PluralsightCourse("id", "Test course", input, "url", false);

        assertEquals(expected, pluralsightCourse.durationInMinutes());
    }
}