package com.gumichan01.fkalculus.ast

import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test as test

class TestAst {

    @test
    fun `test help command`() {

        println(Help)
        println("========")
    }

    @test
    fun `test simple expression`() {

        val expression: Expression = Const(5.0)
        println(expression)
        println("========")
    }

    @test
    fun `test identifier value`() {

        val expression: ResultValue = IdentifierValue("v1", Const(5.0))
        println(expression)
        assertTrue(expression is IdentifierValue)
        println("========")
    }

    @test
    fun `test identifier use`() {

        val expression: Expression = Identifier("v1")
        println(expression)
        assertTrue(expression is Identifier)
        println("========")
    }

    @test
    fun `test simpl instruction`() {

        val expression: Instruction = Simpl(Identifier("v0"))
        println(expression)
        assertTrue(expression is Simpl)
        println("========")
    }

    @test
    fun `test eval instruction`() {

        val expression: Instruction = Eval(Const(5.0))
        println(expression)
        assertTrue(expression is Eval)
        println("========")
    }

    @test
    fun `test subst instruction`() {

        val expression: Instruction = Subst(Var("x"), "x", Const(5.0))
        println(expression)
        assertTrue(expression is Subst)
        println("========")
    }

    @test
    fun `test solve instruction`() {

        val expression: Instruction = Solve(Binop(Plus, Var("x"), Const(5.0)), "x")
        println(expression)
        assertTrue(expression is Solve)
        println("========")
    }

    @test
    fun `test derive instruction`() {

        val expression: Instruction = Derive(Var("x"), "x")
        println(expression)
        assertTrue(expression is Derive)
        println("========")
    }

    @test
    fun `test integration instruction`() {

        val expression: Instruction = Integ(Var("x"), "x", Const(5.0), Const(10.0))
        println(expression)
        assertTrue(expression is Integ)

        println("========")
    }
}