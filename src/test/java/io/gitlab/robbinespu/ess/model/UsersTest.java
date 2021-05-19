package io.gitlab.robbinespu.ess.model;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

class UsersTest {

    @Test
    @DisplayName("Set name - String")
    public void setNameNotBlank() {
        Users std = new Users();
        std.setName("Ayam");
        assertEquals("Ayam", std.getName());
    }

    @Test
    @DisplayName("Set name - null")
    public void setNameBlank() {
        Users std = new Users();
        std.setName(null);
        assertNull(std.getName());
    }

}