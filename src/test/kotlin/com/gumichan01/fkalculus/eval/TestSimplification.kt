package com.gumichan01.fkalculus.eval

import com.gumichan01.fkalculus.ast.*
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
import java.util.stream.Collector
import java.util.stream.Collectors
import kotlin.math.PI

internal class TestSimplification {

    @Test
    fun `simplify 42`() {
        val result = Simplification(Environment()).simplify(Simpl(Const(42.0)))
        val expectedValue = Const(42.0)

        assertTrue(result == expectedValue)
    }

    @Test
    fun `simplify Pi`() {
        val result = Simplification(Environment()).simplify(Simpl(Pi))
        val expectedValue = Pi

        assertTrue(result == expectedValue)
    }

    @Test
    fun `simplify e`() {
        val result = Simplification(Environment()).simplify(Simpl(Exp1))
        val expectedValue = Exp1

        assertTrue(result == expectedValue)
    }

    @Test
    fun `simplify x`() {
        val result = Simplification(Environment()).simplify(Simpl(Var("x")))
        val expectedValue = Var("x")

        assertTrue(result == expectedValue)
    }

    @Test
    fun `simplify identifier`() {

        val name = "v0"
        val expectedValue = Const(2.0)
        val fakeEnvironment = mock<Environment> { on { find(name) } doReturn Const(2.0) }
        val result = Simplification(fakeEnvironment).simplify(Simpl(Identifier(name)))

        assertTrue(result == expectedValue)
    }

    @Test
    fun `simplify Math function calls`() {

        val funcalls = listOf(Sqrt(Const(2.0)), Expo(Const(2.0)), Ln(Const(2.0)), Log10(Const(2.0)),
                Log2(Const(2.0)), Cos(Const(2.0)), Sin(Const(2.0)), Tan(Const(2.0)), Acos(Const(2.0)),
                Asin(Const(2.0)), Atan(Const(2.0)), Sec(Const(2.0)), Cosec(Const(2.0)), Cotan(Const(2.0)),
                Asec(Const(2.0)), Acosec(Const(2.0)), Acotan(Const(2.0)))

        val expectedValue = Const(2.0)
        val interpreter = Simplification(Environment())
        funcalls.map { x -> interpreter.simplify(Simpl(x)) }.map { x -> assertTrue(x == expectedValue) }
    }

    @Test
    fun `simplify 0 + 0`() {
        val result = Simplification(Environment()).simplify(Simpl(Binop(Plus, Const(0.0), Const(0.0))))
        val expectedValue = Const(0.0)

        assertTrue(result == expectedValue)
    }

    @Test
    fun `simplify 42 + 0`() {
        val result = Simplification(Environment()).simplify(Simpl(Binop(Plus, Const(42.0), Const(0.0))))
        val expectedValue = Const(42.0)

        assertTrue(result == expectedValue)
    }

    @Test
    fun `simplify 0 + 42`() {
        val result = Simplification(Environment()).simplify(Simpl(Binop(Plus, Const(42.0), Const(0.0))))
        val expectedValue = Const(42.0)

        assertTrue(result == expectedValue)
    }
}