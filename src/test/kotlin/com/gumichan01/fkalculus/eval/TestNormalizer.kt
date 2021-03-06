package com.gumichan01.fkalculus.eval

import com.gumichan01.fkalculus.ast.*
import com.gumichan01.fkalculus.util.Option
import com.gumichan01.fkalculus.util.Some
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.whenever
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test

class TestNormalizer {

    @Test
    fun `test init class`() {
        Normalizer(true)
    }

    @Test
    fun `test eval help`() {
        val ast = Help
        val result: Option<ResultValue> = Normalizer(true).eval(ast)

        assertTrue(result is Some)
        assertTrue(result is Some && result.t is HelpText)
    }

    @Test
    fun `test evaluate an expression and try to get its value from the identifier`() {
        val interpreter = Normalizer(true)
        val resultConst = interpreter.eval(Const(42.0))
        val idString = if (resultConst is Some) (resultConst.t as IdentifierValue).identifier else ""
        val resultVar = interpreter.eval(Identifier(idString))

        assertTrue(resultVar is Some)
    }

    @Test
    fun `test evaluate an expression, get its value, and retrieve the variable`() {
        val interpreter = Normalizer(true)
        val resultConst = interpreter.eval(Const(42.0))
        val idString = if (resultConst is Some) (resultConst.t as IdentifierValue).identifier else ""
        val resultVar = interpreter.eval(Identifier(idString))
        val (varString, _) = extractIdentifierOrFail(resultVar)

        assertTrue(resultVar is Some)
        assertTrue(idString == "v0")
        assertTrue(varString == "v1")
    }

    @Test
    fun `test evaluate an expression and get its value later`() {
        val interpreter = Normalizer(true)
        val resultConst = interpreter.eval(Const(1.0))
        val idString = if (resultConst is Some) (resultConst.t as IdentifierValue).identifier else ""
        val resultVar = interpreter.eval(Identifier(idString))
        val (varString, value) = extractIdentifierOrFail(resultVar)

        assertTrue(resultVar is Some)
        assertTrue(idString == "v0")
        assertTrue(varString == "v1")
        assertTrue(value == Const(1.0))
    }

    @Test
    fun `test evaluate several expressions and get the variable v3`() {
        val id = "v3"
        val expectedValue = Cos(Var("x"))

        val interpreter = Normalizer(true)
        val expressions = listOf(Pi, Exp1, Binop(Plus, Const(0.0), Const(0.0)), expectedValue, Const(42.0))
        expressions.map { x -> interpreter.eval(x) }
        val resultValue = interpreter.eval(Identifier(id))
        val (varString, value) = extractIdentifierOrFail(resultValue)
        val expectedId = "v" + expressions.size

        assertTrue(resultValue is Some)
        assertTrue(varString == expectedId)
        assertTrue(value == expectedValue)
    }

    @Test
    fun `test evaluate several expressions and get a random variable`() {
        val interpreter = Normalizer(true)
        val expressions = listOf(Const(3.14), Var("x"), Binop(Plus, Var("x"), Const(0.0)), Cos(Var("x")), Const(42.0))

        expressions.map { x -> interpreter.eval(x) }

        val index = (0 until expressions.size).random()
        val id = "v$index"
        val resultValue = interpreter.eval(Identifier(id))
        val (varString, value) = extractIdentifierOrFail(resultValue)
        val expectedId = "v" + expressions.size
        val expectedValue = expressions[index]

        println("index : $index")
        println("expected (id, value): ($expectedId, $expectedValue)")
        println("got (id, value): ($varString, $value)")

        assertTrue(resultValue is Some)
        assertTrue(varString == expectedId)
        assertTrue(value == expectedValue)
    }

    @Test
    fun `test evaluate several expressions that uses identifiers and get an identifier`() {
        val interpreter = Normalizer(true)
        val expressions = listOf(Const(3.14), Binop(Plus, Var("x"), Identifier("v0")), Cos(Identifier("v1")), Const(42.0))

        expressions.map { x -> interpreter.eval(x) }

        val id = "v2"
        val resultValue = interpreter.eval(Identifier(id))
        val (varString, value) = extractIdentifierOrFail(resultValue)
        val expectedId = "v" + expressions.size
        val expectedValue = Cos(Binop(Plus, Var("x"), Const(3.14)))

        println("expected (id, value): ($expectedId, $expectedValue)")
        println("got (id, value): ($varString, $value)")

        assertTrue(resultValue is Some)
        assertTrue(varString == expectedId)
        assertTrue(value == expectedValue)
    }

    @Test
    fun `test execute substitution`() {
        val interpreter = Normalizer(true)
        val substitution = Subst(Ln(Var("x")), "x", Sin(Pi))
        val result = interpreter.eval(substitution)

        assertTrue(result is Some)
        assertTrue(result is Some && (result.t as IdentifierValue).value == Ln(Sin(Pi)))
    }

    @Test
    fun `test execute substitution, get its value, and retrieve the variable`() {
        val interpreter = Normalizer(true)
        val resultConst = interpreter.eval(Subst(Const(42.0), "x", Const(0.0)))
        val idString = if (resultConst is Some) (resultConst.t as IdentifierValue).identifier else ""
        val resultVar = interpreter.eval(Identifier(idString))
        val (varString, _) = extractIdentifierOrFail(resultVar)

        assertTrue(resultVar is Some)
        assertTrue(idString == "v0")
        assertTrue(varString == "v1")
    }

    @Test
    fun `test execute several substitutions and get a random variable`() {
        val interpreter = Normalizer(true)
        val expressions = listOf(Subst(Const(42.0), "x", Const(42.0)), Subst(Var("x"), "x", Const(42.0)))

        expressions.map { x -> interpreter.eval(x) }

        val index = (0 until expressions.size).random()
        val id = "v$index"
        val resultValue = interpreter.eval(Identifier(id))
        val (varString, value) = extractIdentifierOrFail(resultValue)
        val expectedId = "v" + expressions.size

        println("index : $index")
        println("expected (id, value): ($expectedId, ${Const(42.0)})")
        println("got (id, value): ($varString, $value)")

        assertTrue(resultValue is Some)
        assertTrue(varString == expectedId)
    }

    @Test
    fun `test execute simplification`() {
        val interpreter = Normalizer(true)
        val simpl = Simpl(Var("x"))
        val result = interpreter.eval(simpl)

        assertTrue(result is Some)
        assertTrue(result is Some && (result.t as IdentifierValue).value == Var("x"))
    }

    @Test
    fun `test execute simplification, get its value, and retrieve the variable`() {
        val interpreter = Normalizer(true)
        val resultConst = interpreter.eval(Simpl(Const(42.0)))
        val idString = if (resultConst is Some) (resultConst.t as IdentifierValue).identifier else ""
        val resultVar = interpreter.eval(Identifier(idString))
        val (varString, _) = extractIdentifierOrFail(resultVar)

        assertTrue(resultVar is Some)
        assertTrue(idString == "v0")
        assertTrue(varString == "v1")
    }

    private fun extractIdentifierOrFail(result: Option<ResultValue>): Pair<String, Expression> {
        return result.run {
            if (this is Some && t is IdentifierValue) {
                val id = (t as IdentifierValue)
                Pair(id.identifier, id.value)
            } else throw AssertionError("$this is not an Identifier")
        }
    }
}