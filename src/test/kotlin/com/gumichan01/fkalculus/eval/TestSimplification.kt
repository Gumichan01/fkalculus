package com.gumichan01.fkalculus.eval

import com.gumichan01.fkalculus.ast.*
import com.nhaarman.mockitokotlin2.doReturn
import com.nhaarman.mockitokotlin2.mock
import org.junit.jupiter.api.Test

import org.junit.jupiter.api.Assertions.*
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
    fun `simplify identifier`() {

        val name = "v0"
        val expectedValue = Const(2.0)
        val fakeEnvironment = mock<Environment> { on { find(name) } doReturn Const(2.0) }
        val result = Simplification(fakeEnvironment).simplify(Simpl(Identifier(name)))

        assertTrue(result == expectedValue)
    }

    @Test
    fun `simplify 0 + 0`() {
        val result = Simplification(Environment()).simplify(Simpl(Binop(Plus, Const(0.0), Const(0.0))))
        val expectedValue = Const(42.0)

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