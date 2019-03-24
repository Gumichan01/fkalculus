package com.gumichan01.fkalculus

import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test as test

class TestArguments {

    @test
    fun `test basic arguments`() {
        val arguments = Arguments(true)
        println(arguments.toString())

        assertTrue(arguments.verbose)
    }

    @test
    fun `test basic arguments 2`() {
        val arguments = Arguments(false)
        println(arguments.toString())

        assertFalse(arguments.verbose)
    }
}