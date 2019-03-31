package com.gumichan01.fkalculus.parse

import com.gumichan01.fkalculus.ast.*
import com.gumichan01.fkalculus.util.Option
import com.gumichan01.fkalculus.util.Some
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test as test


class TestKalculusParser {

    @test
    fun `parse Pi`() {

        val instructionString = "Pi"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Pi) // The compiler does not understand that at this point, ast is a Some<T>
        println("========")
    }

    @test
    fun `parse pi`() {

        val instructionString = "pi"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Pi)
        println("========")
    }

    @test
    fun `parse π`() {

        val instructionString = "π"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Pi)
        println("========")
    }

    @test
    fun `parse π - alternative`() {

        val instructionString = "\u03C0"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Pi)
        println("========")
    }

    @test
    fun `parse exp1`() {

        val instructionString = "e"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Exp1)
        println("========")
    }

    @test
    fun `parse identifier 0`() {

        val instructionString = "v0"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Identifier("v0"))
        println("========")
    }

    @test
    fun `parse identifier 1`() {

        val instructionString = "v1"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Identifier("v1"))
        println("========")
    }

    @test
    fun `parse identifier 8`() {

        val instructionString = "v8"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Identifier("v8"))
        println("========")
    }

    @test
    fun `parse identifier 42`() {

        val instructionString = "v42"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Identifier("v42"))
        println("========")
    }

    @test
    fun `parse identifier 999`() {

        val instructionString = "v999"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Identifier("v999"))
        println("========")
    }

    @test
    fun `parse const`() {

        val instructionString = "42"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Const(42.0)))
        println("========")
    }

    @test
    fun `parse negative const`() {

        val instructionString = "-64"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Const(-64.0)))
        println("========")
    }

    @test
    fun `parse Var`() {

        val instructionString = "x"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Var("x")))
        println("========")
    }

    @test
    fun `parse number addition 0`() {

        val instructionString = "4+2"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Plus, Const(4.0), Const(2.0))))
        println("========")
    }

    @test
    fun `parse number addition`() {

        val instructionString = "4 + 2"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Plus, Const(4.0), Const(2.0))))
        println("========")
    }

    @test
    fun `parse number + var`() {

        val instructionString = "4 + x"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Plus, Const(4.0), Var("x"))))
        println("========")
    }

    @test
    fun `parse number complex addition`() {

        val instructionString = "4 + x + e + Pi"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Plus, Binop(Plus, Binop(Plus, Const(4.0), Var("x")), Exp1), Pi)))
        println("========")
    }

    @test
    fun `parse -`() {

        val instructionString = "4 - 2"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Minus, Const(4.0), Const(2.0))))
        println("========")
    }

    @test
    fun `parse number - variable`() {

        val instructionString = "4 - x"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Minus, Const(4.0), Var("x"))))
        println("========")
    }

    @test
    fun `parse number complex sub`() {

        val instructionString = "4 - x - e - Pi"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Minus, Binop(Minus, Binop(Minus,Const(4.0), Var("x")), Exp1), Pi)))
        println("========")
    }

    @test
    fun `parse add and sub same priority`() {

        val instructionString = "4 + 2 - 3" // (4 + 2) - 3
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Minus, Binop(Plus, Const(4.0), Const(2.0)), Const(3.0))))
        println("========")
    }

    @test
    fun `parse sub and add same priority`() {

        val instructionString = "4 - 2 + 3" // (4 - 2) + 3
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Plus, Binop(Minus, Const(4.0), Const(2.0)), Const(3.0))))
        println("========")
    }

    @test
    fun `parse add negative value`() {

        val instructionString = "4 + -2"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Plus, Const(4.0), Const(-2.0))))
        println("========")
    }

    @test
    fun `parse add negative value (again)`() {

        val instructionString = "-2 + 4"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Plus, Const(-2.0), Const(4.0))))
        println("========")
    }

    @test
    fun `parse add negative values`() {

        val instructionString = "-2 + -4"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Plus, Const(-2.0), Const(-4.0))))
        println("========")
    }

    @test
    fun `parse add negative values (tricky)`() {

        val instructionString = "-2+-4"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Plus, Const(-2.0), Const(-4.0))))
        println("========")
    }

    @test
    fun `parse sub negative value`() {

        val instructionString = "4 - -2"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Minus, Const(4.0), Const(-2.0))))
        println("========")
    }

    @test
    fun `parse sub negative value (again)`() {

        val instructionString = "-2 - 4"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Minus, Const(-2.0), Const(4.0))))
        println("========")
    }

    @test
    fun `parse sub negative value (tricky)`() {

        val instructionString = "-2 -4"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Minus, Const(-2.0), Const(4.0))))
        println("========")
    }

    /*@test
    fun `parse number multiply`() {

        val instructionString = "4 * 2"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Mult, Const(4.0), Const(2.0))))
        println("========")
    }

    @test
    fun `parse number * var`() {

        val instructionString = "4 * x"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Mult, Const(4.0), Var("x"))))
        println("========")
    }

    @test
    fun `parse number complex multiplication`() {

        val instructionString = "4 * x * e * Pi"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Mult, Const(4.0), Binop(Mult, Var("x"), Binop(Mult, Exp1, Pi)))))
        println("========")
    }

    @test
    fun `parse number div`() {

        val instructionString = "4 / 2"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Div, Const(4.0), Const(2.0))))
        println("========")
    }

    @test
    fun `parse number div var`() {

        val instructionString = "4 / x"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Div, Const(4.0), Var("x"))))
        println("========")
    }

    @test
    fun `parse number complex div`() {

        val instructionString = "4 / x / e / Pi"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Mult, Const(4.0), Binop(Mult, Var("x"), Binop(Mult, Exp1, Pi)))))
        println("========")
    }

    @test
    fun `parse number pow`() {

        val instructionString = "4^2"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Pow, Const(4.0), Const(2.0))))
        println("========")
    }

    @test
    fun `parse number pow var`() {

        val instructionString = "4^x"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Pow, Const(4.0), Var("x"))))
        println("========")
    }

    @test
    fun `parse number complex pow`() {

        val instructionString = "4^x^e^Pi" // ((4^x)^e)^π
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Pow, Const(4.0), Binop(Pow, Var("x"), Binop(Pow, Exp1, Pi)))))
        println("========")
    }

    @test
    fun `parse priority op + *`() {

        val instructionString = "2*x + 1"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Plus, Binop(Mult, Const(2.0), Var("x")), Const(1.0))))
        println("========")
    }

    @test
    fun `parse priority op - *`() {

        val instructionString = "2*x - 1"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Minus, Binop(Mult, Const(2.0), Var("x")), Const(1.0))))
        println("========")
    }

    @test
    fun `parse priority op div *`() {

        val instructionString = "4 * 2 / 3"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Div, Binop(Mult, Const(4.0), Const(2.0)), Const(3.0))))
        println("========")
    }

    @test
    fun `parse priority op div * (2)`() {

        val instructionString = "2 / 3 * 4"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Div, Const(3.0), Binop(Mult, Const(4.0), Const(2.0)))))
        println("========")
    }

    @test
    fun `parse priority op div * (3)`() {

        val instructionString = "4 * (2 / 3)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Mult, Const(4.0), Binop(Div, Const(2.0), Const(3.0)))))
        println("========")
    }

    @test
    fun `parse priority op div * (4)`() {

        val instructionString = "(2 / 3) * 4"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Mult, Binop(Div, Const(2.0), Const(3.0)), Const(4.0))))
        println("========")
    }

    @test
    fun `parse priority op ^ +`() {

        val instructionString = "4^2 + 3"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Plus, Binop(Pow, Const(4.0), Const(2.0)), Const(3.0))))
        println("========")
    }

    @test
    fun `parse priority op ^ + (2)`() {

        val instructionString = "4^(2 / 3)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Pow, Const(4.0), Binop(Plus, Const(2.0), Const(3.0)))))
        println("========")
    }

    @test
    fun `parse priority op ^ + (3)`() {

        val instructionString = "3 + 4^2"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Plus, Const(3.0), Binop(Pow, Const(4.0), Const(2.0)))))
        println("========")
    }

    @test
    fun `parse priority op ^ + (4)`() {

        val instructionString = "(3 + 4)^2"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Binop(Pow, Binop(Pow, Const(4.0), Const(2.0)), Const(3.0))))
        println("========")
    }

    @test
    fun `parse basic sqrt`() {

        val instructionString = "sqrt(2)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Sqrt(Const(2.0))))
        println("========")
    }

    @test
    fun `parse sqrt of var`() {

        val instructionString = "sqrt(x)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Sqrt(Var("x"))))
        println("========")
    }

    @test
    fun `parse complex sqrt`() {

        val instructionString = "sqrt(x + 1)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Sqrt(Binop(Plus, Var("x"), Const(1.0)))))
        println("========")
    }

    @test
    fun `parse basic expo`() {

        val instructionString = "exp(2)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Expo(Const(2.0))))
        println("========")
    }

    @test
    fun `parse expo of var`() {

        val instructionString = "exp(x)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Expo(Var("x"))))
        println("========")
    }

    @test
    fun `parse complex expo`() {

        val instructionString = "exp(x + 1)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Expo(Binop(Plus, Var("x"), Const(1.0)))))
        println("========")
    }

    @test
    fun `parse basic ln`() {

        val instructionString = "ln(2)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Ln(Const(2.0))))
        println("========")
    }

    @test
    fun `parse ln of var`() {

        val instructionString = "ln(x)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Ln(Var("x"))))
        println("========")
    }

    @test
    fun `parse complex ln`() {

        val instructionString = "ln(x + 1)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Ln(Binop(Plus, Var("x"), Const(1.0)))))
        println("========")
    }

    @test
    fun `parse basic log10`() {

        val instructionString = "log10(2)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Log10(Const(2.0))))
        println("========")
    }

    @test
    fun `parse basic normalized notation, lg = log10`() {

        val instructionString = "lg(2)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Log10(Const(2.0))))
        println("========")
    }

    @test
    fun `parse log10 of var`() {

        val instructionString = "log10(x)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Log10(Var("x"))))
        println("========")
    }

    @test
    fun `parse complex log10`() {

        val instructionString = "log10(x + 1)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Log10(Binop(Plus, Var("x"), Const(1.0)))))
        println("========")
    }

    @test
    fun `parse basic log2`() {

        val instructionString = "log2(2)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Log2(Const(2.0))))
        println("========")
    }

    @test
    fun `parse basic normalized notation, lb = log2`() {

        val instructionString = "lb(2)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Log2(Const(2.0))))
        println("========")
    }

    @test
    fun `parse log2 of var`() {

        val instructionString = "log2(x)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Log2(Var("x"))))
        println("========")
    }

    @test
    fun `parse complex log2`() {

        val instructionString = "log2(x + 1)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Log2(Binop(Plus, Var("x"), Const(1.0)))))
        println("========")
    }

    @test
    fun `parse basic Sin`() {

        val instructionString = "sin(2)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Sin(Const(2.0))))
        println("========")
    }

    @test
    fun `parse Sin of var`() {

        val instructionString = "sin(x)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Sin(Var("x"))))
        println("========")
    }

    @test
    fun `parse complex Sin`() {

        val instructionString = "sin(x + 1)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Sin(Binop(Plus, Var("x"), Const(1.0)))))
        println("========")
    }

    @test
    fun `parse basic Cos`() {

        val instructionString = "cos(2)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Cos(Const(2.0))))
        println("========")
    }

    @test
    fun `parse Cos of var`() {

        val instructionString = "cos(x)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Cos(Var("x"))))
        println("========")
    }

    @test
    fun `parse complex Cos`() {

        val instructionString = "cos(x + 1)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Cos(Binop(Plus, Var("x"), Const(1.0)))))
        println("========")
    }

    @test
    fun `parse basic Tan`() {

        val instructionString = "tan(2)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Tan(Const(2.0))))
        println("========")
    }

    @test
    fun `parse Tan of var`() {

        val instructionString = "tan(x)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Tan(Var("x"))))
        println("========")
    }

    @test
    fun `parse complex Tan`() {

        val instructionString = "tan(x + 1)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Tan(Binop(Plus, Var("x"), Const(1.0)))))
        println("========")
    }

    @test
    fun `parse basic arcsin`() {

        val instructionString = "arcsin(2)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Asin(Const(2.0))))
        println("========")
    }

    @test
    fun `parse arcsin of var`() {

        val instructionString = "arcsin(x)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Asin(Var("x"))))
        println("========")
    }

    @test
    fun `parse complex arcsin`() {

        val instructionString = "arcsin(x + 1)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Asin(Binop(Plus, Var("x"), Const(1.0)))))
        println("========")
    }

    @test
    fun `parse basic arccos`() {

        val instructionString = "arccos(2)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Acos(Const(2.0))))
        println("========")
    }

    @test
    fun `parse arccos of var`() {

        val instructionString = "arccos(x)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Acos(Var("x"))))
        println("========")
    }

    @test
    fun `parse complex arccos`() {

        val instructionString = "arcsin(x + 1)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Acos(Binop(Plus, Var("x"), Const(1.0)))))
        println("========")
    }

    @test
    fun `parse basic arctan`() {

        val instructionString = "arctan(2)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Atan(Const(2.0))))
        println("========")
    }

    @test
    fun `parse arctan of var`() {

        val instructionString = "arctan(x)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Atan(Var("x"))))
        println("========")
    }

    @test
    fun `parse complex arctan`() {

        val instructionString = "arctan(x + 1)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Atan(Binop(Plus, Var("x"), Const(1.0)))))
        println("========")
    }

    @test
    fun `parse basic sec`() {

        val instructionString = "sec(2)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Sec(Const(2.0))))
        println("========")
    }

    @test
    fun `parse sec of var`() {

        val instructionString = "sec(x)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Sec(Var("x"))))
        println("========")
    }

    @test
    fun `parse complex sec`() {

        val instructionString = "sec(x + 1)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Sec(Binop(Plus, Var("x"), Const(1.0)))))
        println("========")
    }

    @test
    fun `parse basic cosec`() {

        val instructionString = "cosec(2)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Cosec(Const(2.0))))
        println("========")
    }

    @test
    fun `parse cosec of var`() {

        val instructionString = "cosec(x)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Cosec(Var("x"))))
        println("========")
    }

    @test
    fun `parse complex cosec`() {

        val instructionString = "cosec(x + 1)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Cosec(Binop(Plus, Var("x"), Const(1.0)))))
        println("========")
    }

    @test
    fun `parse basic cotan`() {

        val instructionString = "cotan(2)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Cotan(Const(2.0))))
        println("========")
    }

    @test
    fun `parse cotan of var`() {

        val instructionString = "cotan(x)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Cotan(Var("x"))))
        println("========")
    }

    @test
    fun `parse complex cotan`() {

        val instructionString = "cotan(x + 1)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Cotan(Binop(Plus, Var("x"), Const(1.0)))))
        println("========")
    }

    @test
    fun `parse basic arcsec`() {

        val instructionString = "arcsec(2)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Asec(Const(2.0))))
        println("========")
    }

    @test
    fun `parse arcsec of var`() {

        val instructionString = "arcsec(x)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Asec(Var("x"))))
        println("========")
    }

    @test
    fun `parse complex arcsec`() {

        val instructionString = "arcsec(x + 1)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Asec(Binop(Plus, Var("x"), Const(1.0)))))
        println("========")
    }

    @test
    fun `parse basic arccosec`() {

        val instructionString = "arccosec(2)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Acosec(Const(2.0))))
        println("========")
    }

    @test
    fun `parse arccosec of var`() {

        val instructionString = "arccosec(x)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Acosec(Var("x"))))
        println("========")
    }

    @test
    fun `parse complex arccosec`() {

        val instructionString = "arccosec(x + 1)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Acosec(Binop(Plus, Var("x"), Const(1.0)))))
        println("========")
    }

    @test
    fun `parse basic arccotan`() {

        val instructionString = "arccotan(2)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Acotan(Const(2.0))))
        println("========")
    }

    @test
    fun `parse arccotan of var`() {

        val instructionString = "arccotan(x)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Acotan(Var("x"))))
        println("========")
    }

    @test
    fun `parse complex arccotan`() {

        val instructionString = "arccotan(x + 1)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == (Acotan(Binop(Plus, Var("x"), Const(1.0)))))
        println("========")
    }*/
}