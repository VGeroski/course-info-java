package org.learning.courseinfo.domain;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CourseTest {

    @Test
    void testFilled() {
        assertThrows(IllegalArgumentException.class, () -> {
            Course course = new Course("id", "", 10, "url", Optional.empty());
        });
    }

    @Test
    void testFilledBlankNotes() {
        assertThrows(IllegalArgumentException.class, () -> {
            Course course = new Course("id", "title", 10, "url", Optional.of(""));
        });
    }
}