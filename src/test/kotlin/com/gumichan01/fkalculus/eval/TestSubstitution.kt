package com.gumichan01.fkalculus.eval

import com.gumichan01.fkalculus.ast.*
import org.junit.jupiter.api.Test as test

/**
Copyright, Luxon JEAN-PIERRE (2019)

luxon.jean.pierre@gmail.com

This software is a computer program whose purpose is to [describe
functionalities and technical features of your software].

This software is governed by the CeCILL license under French law and
abiding by the rules of distribution of free software.  You can  use,
modify and/ or redistribute the software under the terms of the CeCILL
license as circulated by CEA, CNRS and INRIA at the following URL
"http://www.cecill.info".

As a counterpart to the access to the source code and  rights to copy,
modify and redistribute granted by the license, users are provided only
with a limited warranty  and the software's author,  the holder of the
economic rights,  and the successive licensors  have only  limited
liability.

In this respect, the user's attention is drawn to the risks associated
with loading,  using,  modifying and/or developing or reproducing the
software by the user in light of its specific status of free software,
that may mean  that it is complicated to manipulate,  and  that  also
therefore means  that it is reserved for developers  and  experienced
professionals having in-depth computer knowledge. Users are therefore
encouraged to load and test the software's suitability as regards their
requirements in conditions enabling the security of their systems and/or
data to be ensured and,  more generally, to use and operate it in the
same conditions as regards security.

The fact that you are presently reading this means that you have had
knowledge of the CeCILL license and that you accept its terms.
 */

class TestSubstitution {

    @org.junit.jupiter.api.Test
    fun `test init class`() {
        Substitution(Environment())
    }

    @test
    fun `test substitution - Pi`() {
        val ast = Subst(Pi, "x", Const(2.0))
        val result: Expression = Substitution(Environment()).subst(ast)
        assert(result == Pi)
    }

    @test
    fun `test substitution - e`() {
        val ast = Subst(Exp1, "x", Const(2.0))
        val result: Expression = Substitution(Environment()).subst(ast)
        assert(result == Exp1)
    }

    @test
    fun `test substitution - constant value`() {
        val ast = Subst(Const(1.0), "x", Const(2.0))
        val result: Expression = Substitution(Environment()).subst(ast)
        assert(result == Const(1.0))
    }

    @test
    fun `test substitution - identifier v0`() {

        val ast = Subst(Identifier("v0"), "x", Const(2.0))
        val env = Environment().update("v0", Const(42.0))
        val result: Expression = Substitution(env).subst(ast)
        assert(result == Const(42.0))
    }

    @test
    fun `test substitution - identifier v0 again`() {
        val ast = Subst(Identifier("v0"), "x", Const(2.0))
        val env = Environment().update("v0", Var("x"))
        val result: Expression = Substitution(env).subst(ast)
        assert(result == Const(2.0))
    }

    @test
    fun `test substitution - identifier v0 again 2`() {
        val ast = Subst(Identifier("v0"), "y", Const(2.0))
        val env = Environment().update("v0", Var("x"))
        val result: Expression = Substitution(env).subst(ast)
        assert(result == Var("x"))
    }

    @test
    fun `test substitution - bound variable`() {
        val ast = Subst(Var("x"), "x", Const(2.0))
        val result: Expression = Substitution(Environment()).subst(ast)
        assert(result == Const(2.0))
    }

    @test
    fun `test substitution - free variable`() {
        val ast = Subst(Var("x"), "y", Const(2.0))
        val result: Expression = Substitution(Environment()).subst(ast)
        assert(result == Var("x"))
    }

    @test
    fun `test substitution - plus`() {
        val ast = Subst(Binop(Plus, Var("x"), Var("y")), "x", Const(2.0))
        val result: Expression = Substitution(Environment()).subst(ast)
        assert(result == Binop(Plus, Const(2.0), Var("y")))
    }

    @test
    fun `test substitution - minus`() {
        val ast = Subst(Binop(Minus, Var("x"), Var("y")), "x", Const(2.0))
        val result: Expression = Substitution(Environment()).subst(ast)
        assert(result == Binop(Minus, Const(2.0), Var("y")))
    }

    @test
    fun `test substitution - mult`() {
        val ast = Subst(Binop(Mult, Var("x"), Var("y")), "x", Const(2.0))
        val result: Expression = Substitution(Environment()).subst(ast)
        assert(result == Binop(Mult, Const(2.0), Var("y")))
    }

    @test
    fun `test substitution - div`() {
        val ast = Subst(Binop(Div, Var("x"), Var("y")), "x", Const(2.0))
        val result: Expression = Substitution(Environment()).subst(ast)
        assert(result == Binop(Div, Const(2.0), Var("y")))
    }

    @test
    fun `test substitution - pow`() {
        val ast = Subst(Binop(Pow, Var("x"), Var("y")), "x", Const(2.0))
        val result: Expression = Substitution(Environment()).subst(ast)
        assert(result == Binop(Pow, Const(2.0), Var("y")))
    }

    @test
    fun `test substitution - sqrt`() {
        val ast = Subst(Sqrt(Var("x")), "x", Const(2.0))
        val result: Expression = Substitution(Environment()).subst(ast)
        assert(result == Sqrt(Const(2.0)))
    }

    @test
    fun `test substitution - expo`() {
        val ast = Subst(Expo(Var("x")), "x", Const(2.0))
        val result: Expression = Substitution(Environment()).subst(ast)
        assert(result == Expo(Const(2.0)))
    }

    @test
    fun `test substitution - ln`() {
        val ast = Subst(Ln(Var("x")), "x", Const(2.0))
        val result: Expression = Substitution(Environment()).subst(ast)
        assert(result == Ln(Const(2.0)))
    }

    @test
    fun `test substitution - log`() {
        val ast = Subst(Log10(Var("x")), "x", Const(2.0))
        val result: Expression = Substitution(Environment()).subst(ast)
        assert(result == Log10(Const(2.0)))
    }

    @test
    fun `test substitution - lb`() {
        val ast = Subst(Log2(Var("x")), "x", Const(2.0))
        val result: Expression = Substitution(Environment()).subst(ast)
        assert(result == Log2(Const(2.0)))
    }

    @test
    fun `test substitution - cos`() {
        val ast = Subst(Cos(Var("x")), "x", Const(2.0))
        val result: Expression = Substitution(Environment()).subst(ast)
        assert(result == Cos(Const(2.0)))
    }

    @test
    fun `test substitution - sin`() {
        val ast = Subst(Sin(Var("x")), "x", Const(2.0))
        val result: Expression = Substitution(Environment()).subst(ast)
        assert(result == Sin(Const(2.0)))
    }

    @test
    fun `test substitution - tan`() {
        val ast = Subst(Atan(Var("x")), "x", Const(2.0))
        val result: Expression = Substitution(Environment()).subst(ast)
        assert(result == Atan(Const(2.0)))
    }

    @test
    fun `test substitution - acos`() {
        val ast = Subst(Acos(Var("x")), "x", Const(2.0))
        val result: Expression = Substitution(Environment()).subst(ast)
        assert(result == Acos(Const(2.0)))
    }

    @test
    fun `test substitution - asin`() {
        val ast = Subst(Asin(Var("x")), "x", Const(2.0))
        val result: Expression = Substitution(Environment()).subst(ast)
        assert(result == Asin(Const(2.0)))
    }

    @test
    fun `test substitution - atan`() {
        val ast = Subst(Atan(Var("x")), "x", Const(2.0))
        val result: Expression = Substitution(Environment()).subst(ast)
        assert(result == Atan(Const(2.0)))
    }

    @test
    fun `test substitution - cosec`() {
        val ast = Subst(Cosec(Var("x")), "x", Const(2.0))
        val result: Expression = Substitution(Environment()).subst(ast)
        assert(result == Cosec(Const(2.0)))
    }

    @test
    fun `test substitution - sec`() {
        val ast = Subst(Sec(Var("x")), "x", Const(2.0))
        val result: Expression = Substitution(Environment()).subst(ast)
        assert(result == Sec(Const(2.0)))
    }

    @test
    fun `test substitution - cotan`() {
        val ast = Subst(Cotan(Var("x")), "x", Const(2.0))
        val result: Expression = Substitution(Environment()).subst(ast)
        assert(result == Cotan(Const(2.0)))
    }

    @test
    fun `test substitution - acosec`() {
        val ast = Subst(Acosec(Var("x")), "x", Const(2.0))
        val result: Expression = Substitution(Environment()).subst(ast)
        assert(result == Acosec(Const(2.0)))
    }

    @test
    fun `test substitution - asec`() {
        val ast = Subst(Asec(Var("x")), "x", Const(2.0))
        val result: Expression = Substitution(Environment()).subst(ast)
        assert(result == Asec(Const(2.0)))
    }

    @test
    fun `test substitution - acotan`() {
        val ast = Subst(Acotan(Var("x")), "x", Const(2.0))
        val result: Expression = Substitution(Environment()).subst(ast)
        assert(result == Acotan(Const(2.0)))
    }

}