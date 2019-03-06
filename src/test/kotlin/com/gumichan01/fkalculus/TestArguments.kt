package com.gumichan01.fkalculus

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test as test

class TestArguments {

    @test
    fun `test basic arguments`() {
        val arguments = Arguments(true, "path_to_filename.fkalc", "destination_file.tex")
        println(arguments.toString())

        assertTrue(arguments.verbose)
        assertTrue(arguments.fileInput.isNotEmpty())
        assertTrue(arguments.fileOutput.isNotEmpty())
        assertTrue(arguments.fileInput.endsWith(".fkalc"))
        assertTrue(arguments.fileOutput.endsWith(".tex"))
    }
}