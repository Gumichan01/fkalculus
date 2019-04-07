package com.gumichan01.fkalculus.eval

import com.gumichan01.fkalculus.ast.*
import com.gumichan01.fkalculus.util.Option
import com.gumichan01.fkalculus.util.Some
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test as test

class TestEvaluator {

    @test
    fun `test init class`() {

        val evaluator = Evaluator()
        println(evaluator)
        println("========")
    }

    @test
    fun `test eval help`() {

        val ast = Help
        val result: Option<ResultValue> = Evaluator().eval(ast)
        assertTrue(result is Some)
        assertTrue(result is Some && result.t is HelpText)
    }

    @test
    fun `test eval var`() {

        val ast = Var("x")
        val result: Option<ResultValue> = Evaluator().eval(ast)
        println(result)
        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(result is Some && result.t is IdentifierValue && (result.t as IdentifierValue).value == Var("x"))
        println("========")
    }

    @test
    fun `test eval pi`() {

        val ast = Pi
        val result: Option<ResultValue> = Evaluator().eval(ast)
        println(result)
        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(result is Some && result.t is IdentifierValue && (result.t as IdentifierValue).value is Const)
        println("========")
    }

    @test
    fun `test eval addition`() {

        val ast = Binop(Plus, Const(2.0), Const(2.0))
        val result: Option<ResultValue> = Evaluator().eval(ast)
        println(result)
        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(check(result))
        assertTrue(result is Some && check(result) && (result.t as IdentifierValue).value == Const(4.0))
        println("========")
    }

    @test
    fun `test eval sub`() {

        val ast = Binop(Minus, Const(2.0), Const(2.0))
        val result: Option<ResultValue> = Evaluator().eval(ast)
        println(result)
        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(check(result))
        assertTrue(result is Some && check(result) && (result.t as IdentifierValue).value == Const(0.0))
        println("========")
    }

    private fun check(result: Option<ResultValue>): Boolean {

        return result is Some && result.t is IdentifierValue && (result.t as IdentifierValue).value is Const
    }
}