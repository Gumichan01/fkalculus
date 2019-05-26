package com.gumichan01.fkalculus.parse

import com.gumichan01.fkalculus.ast.*
import com.gumichan01.fkalculus.util.Option
import com.gumichan01.fkalculus.util.Some
import org.junit.jupiter.api.Assertions.assertTrue
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

class TestKalculusParser {

    @test
    fun `parse Help command`() {
        val instructionString = "help"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Help)
    }

    @test
    fun `parse Pi`() {
        val instructionString = "Pi"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Pi) // The compiler does not understand that at this point, ast is a Some<T>

    }

    @test
    fun `parse pi`() {
        val instructionString = "pi"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Pi)
    }

    @test
    fun `parse π`() {
        val instructionString = "π"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Pi)
    }

    @test
    fun `parse π - alternative`() {
        val instructionString = "\u03C0"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Pi)
    }

    @test
    fun `parse exp1`() {
        val instructionString = "e"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Exp1)

    }

    @test
    fun `parse identifier 0`() {
        val instructionString = "v0"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Identifier("v0"))
    }

    @test
    fun `parse identifier 1`() {
        val instructionString = "v1"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Identifier("v1"))
    }

    @test
    fun `parse identifier 8`() {
        val instructionString = "v8"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Identifier("v8"))
    }

    @test
    fun `parse identifier 42`() {
        val instructionString = "v42"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Identifier("v42"))
    }

    @test
    fun `parse identifier 999`() {
        val instructionString = "v999"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Identifier("v999"))
    }

    @test
    fun `parse const`() {
        val instructionString = "42"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Const(42.0)))
    }

    @test
    fun `parse negative const`() {
        val instructionString = "-64"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Const(-64.0)))
    }

    @test
    fun `parse Var`() {
        val instructionString = "x"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Var("x")))
    }

    @test
    fun `parse number addition 0`() {
        val instructionString = "4+2"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Plus, Const(4.0), Const(2.0))))
    }

    @test
    fun `parse number addition`() {
        val instructionString = "4 + 2"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Plus, Const(4.0), Const(2.0))))
    }

    @test
    fun `parse number + var`() {
        val instructionString = "4 + x"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Plus, Const(4.0), Var("x"))))
    }

    @test
    fun `parse number complex addition`() {
        val instructionString = "4 + x + e + Pi"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Plus, Binop(Plus, Binop(Plus, Const(4.0), Var("x")), Exp1), Pi)))
    }

    @test
    fun `parse -`() {
        val instructionString = "4 - 2"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Minus, Const(4.0), Const(2.0))))
    }

    @test
    fun `parse number - variable`() {
        val instructionString = "4 - x"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Minus, Const(4.0), Var("x"))))
    }

    @test
    fun `parse number complex sub`() {
        val instructionString = "4 - x - e - Pi"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Minus, Binop(Minus, Binop(Minus, Const(4.0), Var("x")), Exp1), Pi)))
    }

    @test
    fun `parse add and sub same priority`() {
        val instructionString = "4 + 2 - 3" // (4 + 2) - 3
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Minus, Binop(Plus, Const(4.0), Const(2.0)), Const(3.0))))
    }

    @test
    fun `parse sub and add same priority`() {
        val instructionString = "4 - 2 + 3" // (4 - 2) + 3
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Plus, Binop(Minus, Const(4.0), Const(2.0)), Const(3.0))))
    }

    @test
    fun `parse add negative value`() {
        val instructionString = "4 + -2"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Plus, Const(4.0), Const(-2.0))))
    }

    @test
    fun `parse add negative value (again)`() {
        val instructionString = "-2 + 4"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Plus, Const(-2.0), Const(4.0))))
    }

    @test
    fun `parse add negative values`() {
        val instructionString = "-2 + -4"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Plus, Const(-2.0), Const(-4.0))))
    }

    @test
    fun `parse add negative values (tricky)`() {
        val instructionString = "-2+-4"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Plus, Const(-2.0), Const(-4.0))))
    }

    @test
    fun `parse sub negative value`() {
        val instructionString = "4 - -2"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Minus, Const(4.0), Const(-2.0))))
    }

    @test
    fun `parse sub negative value (again)`() {
        val instructionString = "-2 - 4"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Minus, Const(-2.0), Const(4.0))))
    }

    @test
    fun `parse sub negative value (tricky)`() {
        val instructionString = "-2 -4"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Minus, Const(-2.0), Const(4.0))))
    }

    @test
    fun `parse number multiply`() {
        val instructionString = "4 * 2"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Mult, Const(4.0), Const(2.0))))
    }

    @test
    fun `parse number * var`() {
        val instructionString = "4 * x"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Mult, Const(4.0), Var("x"))))
    }

    @test
    fun `parse mult negative value `() {
        val instructionString = "-2 * -4"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Mult, Const(-2.0), Const(-4.0))))
    }

    @test
    fun `parse mult negative value again`() {
        val instructionString = "2 * -4"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Mult, Const(2.0), Const(-4.0))))
    }

    @test
    fun `parse mult add value`() {
        val instructionString = "2 + -4 * 1"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Plus, Const(2.0), Binop(Mult, Const(-4.0), Const(1.0)))))
    }

    @test
    fun `parse mult sub value`() {
        val instructionString = "2 - 4 * 1"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Minus, Const(2.0), Binop(Mult, Const(4.0), Const(1.0)))))
    }

    @test
    fun `parse mult sub value (tricky)`() {
        val instructionString = "2-4*1"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Minus, Const(2.0), Binop(Mult, Const(4.0), Const(1.0)))))
    }

    @test
    fun `parse number complex multiplication`() {
        val instructionString = "4 * x * e * Pi"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Mult, Binop(Mult, Binop(Mult, Const(4.0), Var("x")), Exp1), Pi)))
    }

    @test
    fun `parse number div`() {
        val instructionString = "4 / 2"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Div, Const(4.0), Const(2.0))))
    }

    @test
    fun `parse number div var`() {
        val instructionString = "4 / x"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Div, Const(4.0), Var("x"))))
    }

    @test
    fun `parse number complex div`() {
        val instructionString = "4 / x / e / Pi"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Div, Binop(Div, Binop(Div, Const(4.0), Var("x")), Exp1), Pi)))
    }

    @test
    fun `parse number pow`() {
        val instructionString = "4^2"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Pow, Const(4.0), Const(2.0))))
    }

    @test
    fun `parse number pow var`() {
        val instructionString = "4^x"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Pow, Const(4.0), Var("x"))))

    }

    @test
    fun `parse number complex pow`() {
        val instructionString = "4^x^e^Pi" // ((4^x)^e)^π
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Pow, Binop(Pow, Binop(Pow, Const(4.0), Var("x")), Exp1), Pi)))
    }

    @test
    fun `parse priority op + *`() {
        val instructionString = "2*x + 1"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Plus, Binop(Mult, Const(2.0), Var("x")), Const(1.0))))
    }

    @test
    fun `parse priority op - *`() {
        val instructionString = "2*x - 1"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Minus, Binop(Mult, Const(2.0), Var("x")), Const(1.0))))
    }

    @test
    fun `parse priority op div *`() {
        val instructionString = "4 * 2 / 3"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Div, Binop(Mult, Const(4.0), Const(2.0)), Const(3.0))))
    }

    @test
    fun `parse priority op div * (2)`() {
        val instructionString = "2 / 3 * 4" // 2 / (3 * 4)
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Div, Const(2.0), Binop(Mult, Const(3.0), Const(4.0)))))
    }

    @test
    fun `parse operation, paren 1`() {
        val instructionString = "(2 + 3)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Plus, Const(2.0), Const(3.0))))
    }

    @test
    fun `parse operation, paren 2`() {
        val instructionString = "(2 - 3)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Minus, Const(2.0), Const(3.0))))

    }

    @test
    fun `parse operation, paren 3`() {
        val instructionString = "(2 * 3)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Mult, Const(2.0), Const(3.0))))
    }

    @test
    fun `parse operation, paren 4`() {
        val instructionString = "(2 / 3)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Div, Const(2.0), Const(3.0))))
    }

    @test
    fun `parse priority op div * (3)`() {
        val instructionString = "4 * (2 / 3)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Mult, Const(4.0), Binop(Div, Const(2.0), Const(3.0)))))
    }

    @test
    fun `parse priority op div * (4)`() {
        val instructionString = "(2 / 3) * 4"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Mult, Binop(Div, Const(2.0), Const(3.0)), Const(4.0))))
    }

    @test
    fun `parse priority op ^ +`() {
        val instructionString = "4^2 + 3"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Plus, Binop(Pow, Const(4.0), Const(2.0)), Const(3.0))))
    }

    @test
    fun `parse priority op ^ + (2)`() {
        val instructionString = "4^(2 / 3)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Pow, Const(4.0), Binop(Div, Const(2.0), Const(3.0)))))
    }

    @test
    fun `parse priority op ^ + (3)`() {
        val instructionString = "3 + 4^2"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Plus, Const(3.0), Binop(Pow, Const(4.0), Const(2.0)))))
    }

    @test
    fun `parse priority op ^ + (4)`() {
        val instructionString = "(3 + 4)^2"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Pow, Binop(Plus, Const(3.0), Const(4.0)), Const(2.0))))
    }

    @test
    fun `parse basic sqrt`() {
        val instructionString = "sqrt(2)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Sqrt(Const(2.0))))
    }

    @test
    fun `parse basic √`() {
        val instructionString = "√(2)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Sqrt(Const(2.0))))
    }

    @test
    fun `parse sqrt of var`() {
        val instructionString = "sqrt(x)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Sqrt(Var("x"))))
    }

    @test
    fun `parse complex sqrt`() {
        val instructionString = "sqrt(x + 1)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Sqrt(Binop(Plus, Var("x"), Const(1.0)))))
    }

    @test
    fun `parse sqrt tricky`() {
        val instructionString = "sqrt(x + 1) / 2"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Div, Sqrt(Binop(Plus, Var("x"), Const(1.0))), Const(2.0))))
    }

    @test
    fun `parse basic expo`() {
        val instructionString = "exp(2)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Expo(Const(2.0))))
    }

    @test
    fun `parse expo of var`() {
        val instructionString = "exp(x)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Expo(Var("x"))))
    }

    @test
    fun `parse complex expo`() {
        val instructionString = "exp(x + 1)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Expo(Binop(Plus, Var("x"), Const(1.0)))))
    }

    @test
    fun `parse expo tricky`() {
        val instructionString = "exp(x + 1) / 2"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Div, Expo(Binop(Plus, Var("x"), Const(1.0))), Const(2.0))))
    }

    @test
    fun `parse basic ln`() {
        val instructionString = "ln(2)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Ln(Const(2.0))))
    }

    @test
    fun `parse ln of var`() {
        val instructionString = "ln(x)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Ln(Var("x"))))
    }

    @test
    fun `parse complex ln`() {
        val instructionString = "ln(x + 1)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Ln(Binop(Plus, Var("x"), Const(1.0)))))
    }

    @test
    fun `parse ln tricky`() {
        val instructionString = "ln(x + 1) / 2"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Div, Ln(Binop(Plus, Var("x"), Const(1.0))), Const(2.0))))
    }

    @test
    fun `parse basic log10`() {
        val instructionString = "log10(2)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Log10(Const(2.0))))
    }

    @test
    fun `parse basic normalized notation, lg = log10`() {
        val instructionString = "lg(2)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Log10(Const(2.0))))
    }

    @test
    fun `parse log10 of var`() {
        val instructionString = "log10(x)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Log10(Var("x"))))
    }

    @test
    fun `parse complex log10`() {
        val instructionString = "log10(x + 1)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Log10(Binop(Plus, Var("x"), Const(1.0)))))
    }

    @test
    fun `parse log10 tricky`() {
        val instructionString = "log10(x + 1) / 2"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Div, Log10(Binop(Plus, Var("x"), Const(1.0))), Const(2.0))))
    }

    @test
    fun `parse log10 alternative tricky`() {
        val instructionString = "lg(x + 1) / 2"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Div, Log10(Binop(Plus, Var("x"), Const(1.0))), Const(2.0))))
    }

    @test
    fun `parse basic log2`() {
        val instructionString = "log2(2)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Log2(Const(2.0))))
    }

    @test
    fun `parse basic normalized notation, lb = log2`() {
        val instructionString = "lb(2)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Log2(Const(2.0))))
    }

    @test
    fun `parse log2 of var`() {
        val instructionString = "log2(x)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Log2(Var("x"))))
    }

    @test
    fun `parse complex log2`() {
        val instructionString = "log2(x + 1)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Log2(Binop(Plus, Var("x"), Const(1.0)))))
    }

    @test
    fun `parse log2 tricky`() {
        val instructionString = "log2(x + 1) / 2"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Div, Log2(Binop(Plus, Var("x"), Const(1.0))), Const(2.0))))
    }

    @test
    fun `parse lb tricky`() {
        val instructionString = "lb(x + 1) / 2"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Div, Log2(Binop(Plus, Var("x"), Const(1.0))), Const(2.0))))
    }

    @test
    fun `parse basic Sin`() {
        val instructionString = "sin(2)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Sin(Const(2.0))))
    }

    @test
    fun `parse Sin of var`() {
        val instructionString = "sin(x)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Sin(Var("x"))))
    }

    @test
    fun `parse complex Sin`() {
        val instructionString = "sin(x + 1)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Sin(Binop(Plus, Var("x"), Const(1.0)))))
    }

    @test
    fun `parse basic Cos`() {
        val instructionString = "cos(2)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Cos(Const(2.0))))
    }

    @test
    fun `parse Cos of var`() {
        val instructionString = "cos(x)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Cos(Var("x"))))
    }

    @test
    fun `parse complex Cos`() {
        val instructionString = "cos(x + 1)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Cos(Binop(Plus, Var("x"), Const(1.0)))))
    }

    @test
    fun `parse basic Tan`() {
        val instructionString = "tan(2)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Tan(Const(2.0))))
    }

    @test
    fun `parse Tan of var`() {
        val instructionString = "tan(x)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Tan(Var("x"))))
    }

    @test
    fun `parse complex Tan`() {
        val instructionString = "tan(x + 1)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Tan(Binop(Plus, Var("x"), Const(1.0)))))
    }

    @test
    fun `parse basic arcsin`() {
        val instructionString = "arcsin(2)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Asin(Const(2.0))))
    }

    @test
    fun `parse basic alternative arcsin`() {
        val instructionString = "asin(2)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Asin(Const(2.0))))
    }

    @test
    fun `parse arcsin of var`() {
        val instructionString = "arcsin(x)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Asin(Var("x"))))
    }

    @test
    fun `parse complex arcsin`() {
        val instructionString = "arcsin(x + 1)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Asin(Binop(Plus, Var("x"), Const(1.0)))))
    }

    @test
    fun `parse basic arccos`() {
        val instructionString = "arccos(2)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Acos(Const(2.0))))
    }

    @test
    fun `parse basic alternative arccos`() {
        val instructionString = "acos(2)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Acos(Const(2.0))))
    }

    @test
    fun `parse arccos of var`() {
        val instructionString = "arccos(x)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Acos(Var("x"))))
    }

    @test
    fun `parse complex arccos`() {
        val instructionString = "arccos(x + 1)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Acos(Binop(Plus, Var("x"), Const(1.0)))))
    }

    @test
    fun `parse basic arctan`() {
        val instructionString = "arctan(2)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Atan(Const(2.0))))
    }

    @test
    fun `parse basic alternative arctan`() {
        val instructionString = "atan(2)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Atan(Const(2.0))))
    }

    @test
    fun `parse arctan of var`() {
        val instructionString = "arctan(x)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Atan(Var("x"))))
    }

    @test
    fun `parse complex arctan`() {
        val instructionString = "arctan(x + 1)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Atan(Binop(Plus, Var("x"), Const(1.0)))))
    }

    @test
    fun `parse basic sec`() {
        val instructionString = "sec(2)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Sec(Const(2.0))))
    }

    @test
    fun `parse sec of var`() {
        val instructionString = "sec(x)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Sec(Var("x"))))
    }

    @test
    fun `parse complex sec`() {
        val instructionString = "sec(x + 1)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Sec(Binop(Plus, Var("x"), Const(1.0)))))
    }

    @test
    fun `parse basic cosec`() {
        val instructionString = "cosec(2)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Cosec(Const(2.0))))
    }

    @test
    fun `parse basic alternative cosec - csc`() {
        val instructionString = "csc(2)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Cosec(Const(2.0))))
    }

    @test
    fun `parse cosec of var`() {
        val instructionString = "cosec(x)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Cosec(Var("x"))))
    }

    @test
    fun `parse complex cosec`() {
        val instructionString = "cosec(x + 1)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Cosec(Binop(Plus, Var("x"), Const(1.0)))))
    }

    @test
    fun `parse basic cotan`() {
        val instructionString = "cotan(2)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Cotan(Const(2.0))))
    }

    @test
    fun `parse basic alternative cotan - cot`() {
        val instructionString = "cot(2)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Cotan(Const(2.0))))
    }

    @test
    fun `parse cotan of var`() {
        val instructionString = "cotan(x)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Cotan(Var("x"))))
    }

    @test
    fun `parse complex cotan`() {
        val instructionString = "cotan(x + 1)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Cotan(Binop(Plus, Var("x"), Const(1.0)))))
    }

    @test
    fun `parse basic arcsec`() {
        val instructionString = "arcsec(2)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Asec(Const(2.0))))
    }

    @test
    fun `parse basic alternative arcsec - asec`() {
        val instructionString = "asec(2)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Asec(Const(2.0))))
    }

    @test
    fun `parse arcsec of var`() {
        val instructionString = "arcsec(x)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Asec(Var("x"))))
    }

    @test
    fun `parse complex arcsec`() {
        val instructionString = "arcsec(x + 1)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Asec(Binop(Plus, Var("x"), Const(1.0)))))
    }

    @test
    fun `parse basic arccosec`() {
        val instructionString = "arccosec(2)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Acosec(Const(2.0))))
    }

    @test
    fun `parse 1st alternative arccosec - arccsc`() {
        val instructionString = "arccsc(2)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Acosec(Const(2.0))))
    }

    @test
    fun `parse 2nd alternative arccosec - acsc`() {
        val instructionString = "acsc(2)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Acosec(Const(2.0))))
    }

    @test
    fun `parse arccosec of var`() {
        val instructionString = "arccosec(x)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Acosec(Var("x"))))
    }

    @test
    fun `parse complex arccosec`() {
        val instructionString = "arccosec(x + 1)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Acosec(Binop(Plus, Var("x"), Const(1.0)))))
    }

    /*@test
    fun `parse basic arccotan`() {
        val instructionString = "arccotan(2)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Acotan(Const(2.0))))
    }

    @test
    fun `parse arccotan of var`() {
        val instructionString = "arccotan(x)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Acotan(Var("x"))))
    }

    @test
    fun `parse complex arccotan`() {
        val instructionString = "arccotan(x + 1)"
        val parser = KalculusParser()
        val ast: Option<FKalculusAST> = parser.parse(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Acotan(Binop(Plus, Var("x"), Const(1.0)))))
    }*/
}