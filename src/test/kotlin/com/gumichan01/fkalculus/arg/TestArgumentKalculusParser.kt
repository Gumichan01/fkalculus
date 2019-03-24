package com.gumichan01.fkalculus

import com.gumichan01.fkalculus.arg.ArgumentParser
import org.junit.jupiter.api.Assertions.assertFalse
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test as test

class TestArgumentKalculusParser {

    @test
    fun `test argument parser with no argument`() {

        val args = emptyArray<String>()
        val argumentParser = ArgumentParser()
        val arguments = argumentParser.parse(args)

        args.forEach { arg -> print("$arg ") }
        println("- $arguments")
        assertFalse(arguments.verbose)
        println("========")
    }

    @test
    fun `test argument parser with short verbose`() {

        val args = arrayOf("-v")
        val argumentParser = ArgumentParser()
        val arguments = argumentParser.parse(args)

        args.forEach { arg -> print("$arg ") }
        println("- $arguments")
        assertTrue(arguments.verbose)
        println("========")
    }

    @test
    fun `test argument parser with verbose`() {

        val args = arrayOf("--verbose")
        val argumentParser = ArgumentParser()
        val arguments = argumentParser.parse(args)

        args.forEach { arg -> print("$arg ") }
        println("- $arguments")
        assertTrue(arguments.verbose)
        println("========")
    }
}