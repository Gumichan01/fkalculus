package com.gumichan01.fkalculus

import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test as test

class TestArgumentParser() {

    @test
    fun `test argument parser with no argument`() {

        val args = arrayOf("")
        val argumentParser: ArgumentParser = ArgumentParser()
        val arguments = argumentParser.parse(args)

        println(arguments)
        assertFalse(arguments.verbose)
        assertTrue(arguments.fileInput.isEmpty())
        assertTrue(arguments.fileOutput.isEmpty())
    }

    @test
    fun `test argument parser with short verbose`() {

        val args = arrayOf("-v")
        val argumentParser: ArgumentParser = ArgumentParser()
        val arguments = argumentParser.parse(args)

        println(arguments)
        assertTrue(arguments.verbose)
    }

    @test
    fun `test argument parser with verbose`() {

        val args = arrayOf("--verbose")
        val argumentParser: ArgumentParser = ArgumentParser()
        val arguments = argumentParser.parse(args)

        println(arguments)
        assertTrue(arguments.verbose)
    }

    @test
    fun `test argument parser with short file`() {

        val args = arrayOf("-f", "hello.fkalc")
        val argumentParser: ArgumentParser = ArgumentParser()
        val arguments = argumentParser.parse(args)

        println(arguments)
        assertTrue(arguments.fileInput.isNotEmpty())
    }

    @test
    fun `test argument parser with file`() {

        val args = arrayOf("--file=hello.fkalc")
        val argumentParser: ArgumentParser = ArgumentParser()
        val arguments = argumentParser.parse(args)

        println(arguments)
        assertTrue(arguments.fileInput.isNotEmpty())
    }

    @test
    fun `test argument parser with short output`() {

        val args = arrayOf("-o", "hello.tex")
        val argumentParser: ArgumentParser = ArgumentParser()
        val arguments = argumentParser.parse(args)

        println(arguments)
        assertTrue(arguments.fileOutput.isNotEmpty())
    }

    @test
    fun `test argument parser with output`() {

        val args = arrayOf("--output=hello.tex")
        val argumentParser: ArgumentParser = ArgumentParser()
        val arguments = argumentParser.parse(args)

        println(arguments)
        assertTrue(arguments.fileOutput.isNotEmpty())
    }

    @test
    fun `test argument parser with short files`() {

        val args = arrayOf("-f", "hello.fkalc", "-o", "hello.tex")
        val argumentParser: ArgumentParser = ArgumentParser()
        val arguments = argumentParser.parse(args)

        println(arguments)
        assertTrue(arguments.fileInput.isNotEmpty())
        assertTrue(arguments.fileOutput.isNotEmpty())
    }

    @test
    fun `test argument parser with files`() {

        val args = arrayOf("--file=hello.fkalc", "--output=hello.tex")
        val argumentParser: ArgumentParser = ArgumentParser()
        val arguments = argumentParser.parse(args)

        println(arguments)
        assertTrue(arguments.fileInput.isNotEmpty())
        assertTrue(arguments.fileOutput.isNotEmpty())
    }

    @test
    fun `test argument parser with all short arguments`() {

        val args = arrayOf("-v", "-f", "hello.fkalc", "-o", "hello.tex")
        val argumentParser: ArgumentParser = ArgumentParser()
        val arguments = argumentParser.parse(args)

        println(arguments)
        assertTrue(arguments.verbose)
        assertTrue(arguments.fileInput.isNotEmpty())
        assertTrue(arguments.fileOutput.isNotEmpty())
    }

    @test
    fun `test argument parser with all arguments`() {

        val args = arrayOf("--verbose","--file=hello.fkalc", "--output=hello.tex")
        val argumentParser: ArgumentParser = ArgumentParser()
        val arguments = argumentParser.parse(args)

        println(arguments)
        assertTrue(arguments.verbose)
        assertTrue(arguments.fileInput.isNotEmpty())
        assertTrue(arguments.fileOutput.isNotEmpty())
    }
}