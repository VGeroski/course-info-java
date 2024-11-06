package org.learning.courseinfo.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class CourseTest {

    @Test
    void testFilled() {
        assertThrows(IllegalArgumentException.class, () -> {
            Course course = new Course("id", "", 10, "url");
        });
    }
}