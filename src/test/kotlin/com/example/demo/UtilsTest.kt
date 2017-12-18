package com.example.demo

import org.junit.jupiter.api.Assertions.assertEquals
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test
import org.slf4j.LoggerFactory

class UtilsTest{

    @Test
    fun `slugify string "Post one" should return  "post-one"`() {
        assertEquals("post-one", "Post one".slugify())
    }
}