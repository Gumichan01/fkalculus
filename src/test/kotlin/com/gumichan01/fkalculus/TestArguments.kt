package com.gumichan01.fkalculus

import org.junit.jupiter.api.Test as test

class TestArguments() {

    @test
    fun `test basic arguments`() {
        val arguments: Arguments = Arguments(true, "path_to_filename.fkalc", "destination_file.tex")
        println(arguments)

        assertTrue(arguments.verbose)
        assertTrue(arguments.fileInput.isNotEmpty())
        assertTrue(arguments.fileOutput.isNotEmpty())
        assertTrue(arguments.fileInput.endsWith(".fkcalc"))
        assertTrue(arguments.fileOutput.endsWith(".tex"))
    }
}