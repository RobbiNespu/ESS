package io.gitlab.robbinespu.ess.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotSame;

class StudentTest {

    @Test
    @DisplayName("Set name - String")
    public void setNameNotBlank() {
        Student std = new Student();
        std.setName("Ayam");
        assertEquals("Ayam", std.getName());
    }

    @Test
    @DisplayName("Set name - null")
    public void setNameBlank() {
        Student std = new Student();
        std.setName(null);
        assertEquals(null, std.getName());
    }

}