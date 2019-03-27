package com.gumichan01.fkalculus.parse

import com.gumichan01.fkalculus.ast.*
import com.gumichan01.fkalculus.util.Option
import com.gumichan01.fkalculus.util.Some
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test as test


class TestKalculusParser {

    @test
    fun `parse eval Pi`() {

        val instructionString = "Pi"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Pi) // The compiler does not understand taht at this point, ast is a Some<T>(t)
        println("========")
    }

    @test
    fun `parse eval exp1`() {

        val instructionString = "e"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Exp1)
        println("========")
    }

    @test
    fun `parse eval const`() {

        val instructionString = "42"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Const(42.0)))
        println("========")
    }

    @test
    fun `parse eval negative const`() {

        val instructionString = "-64"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Const(-64.0)))
        println("========")
    }

    @test
    fun `parse eval Var`() {

        val instructionString = "x"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Var("x")))
        println("========")
    }

    @test
    fun `parse eval number addition`() {

        val instructionString = "4 + 2"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Binop(Plus, Const(4.0), Const(2.0))))
        println("========")
    }

    @test
    fun `parse eval number + var`() {

        val instructionString = "4 + x"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Binop(Plus, Const(4.0), Var("x"))))
        println("========")
    }

    @test
    fun `parse eval number complex addition`() {

        val instructionString = "4 + x + e + Pi"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Binop(Plus, Const(4.0), Binop(Plus, Var("x"), Binop(Plus, Exp1, Pi)))))
        println("========")
    }

    @test
    fun `parse eval -`() {

        val instructionString = "4 - 2"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Binop(Minus, Const(4.0), Var("x"))))
        println("========")
    }

    @test
    fun `parse eval number - variable`() {

        val instructionString = "4 - x"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Binop(Minus, Const(4.0), Var("x"))))
        println("========")
    }

    @test
    fun `parse eval number complex sub`() {

        val instructionString = "4 - x - e - Pi"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Binop(Minus, Const(4.0), Binop(Minus, Var("x"), Binop(Minus, Exp1, Pi)))))
        println("========")
    }

    @test
    fun `parse eval number multiply`() {

        val instructionString = "4 * 2"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Binop(Mult, Const(4.0), Const(2.0))))
        println("========")
    }

    @test
    fun `parse eval number * var`() {

        val instructionString = "4 * x"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Binop(Mult, Const(4.0), Var("x"))))
        println("========")
    }

    @test
    fun `parse eval number complex multiplication`() {

        val instructionString = "4 * x * e * Pi"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Binop(Mult, Const(4.0), Binop(Mult, Var("x"), Binop(Mult, Exp1, Pi)))))
        println("========")
    }

    @test
    fun `parse eval number div`() {

        val instructionString = "4 / 2"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Binop(Div, Const(4.0), Const(2.0))))
        println("========")
    }

    @test
    fun `parse eval number div var`() {

        val instructionString = "4 / x"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Binop(Div, Const(4.0), Var("x"))))
        println("========")
    }

    @test
    fun `parse eval number complex div`() {

        val instructionString = "4 / x / e / Pi"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Binop(Mult, Const(4.0), Binop(Mult, Var("x"), Binop(Mult, Exp1, Pi)))))
        println("========")
    }

    @test
    fun `parse eval number pow`() {

        val instructionString = "4^2"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Binop(Pow, Const(4.0), Const(2.0))))
        println("========")
    }

    @test
    fun `parse eval number pow var`() {

        val instructionString = "4^x"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Binop(Pow, Const(4.0), Var("x"))))
        println("========")
    }

    @test
    fun `parse eval number complex pow`() {

        val instructionString = "4^x^e^Pi" // ((4^x)^e)^π
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Binop(Pow, Const(4.0), Binop(Pow, Var("x"), Binop(Pow, Exp1, Pi)))))
        println("========")
    }

    @test
    fun `parse eval priority op + *`() {

        val instructionString = "2*x + 1"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Binop(Plus, Binop(Mult, Const(2.0), Var("x")), Const(1.0))))
        println("========")
    }

    @test
    fun `parse eval priority op - *`() {

        val instructionString = "2*x - 1"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Binop(Minus, Binop(Mult, Const(2.0), Var("x")), Const(1.0))))
        println("========")
    }

    @test
    fun `parse eval priority op div *`() {

        val instructionString = "4 * 2 / 3"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Binop(Div, Binop(Mult, Const(4.0), Const(2.0)), Const(3.0))))
        println("========")
    }

    @test
    fun `parse eval priority op div * (2)`() {

        val instructionString = "2 / 3 * 4"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Binop(Div, Const(3.0), Binop(Mult, Const(4.0), Const(2.0)))))
        println("========")
    }

    @test
    fun `parse eval priority op div * (3)`() {

        val instructionString = "4 * (2 / 3)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Binop(Mult, Const(4.0), Binop(Div, Const(2.0), Const(3.0)))))
        println("========")
    }

    @test
    fun `parse eval priority op div * (4)`() {

        val instructionString = "(2 / 3) * 4"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Binop(Mult, Binop(Div, Const(2.0), Const(3.0)), Const(4.0))))
        println("========")
    }

    @test
    fun `parse eval priority op ^ +`() {

        val instructionString = "4^2 + 3"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Binop(Plus, Binop(Pow, Const(4.0), Const(2.0)), Const(3.0))))
        println("========")
    }

    @test
    fun `parse eval priority op ^ + (2)`() {

        val instructionString = "4^(2 / 3)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Binop(Pow, Const(4.0), Binop(Plus, Const(2.0), Const(3.0)))))
        println("========")
    }

    @test
    fun `parse eval priority op ^ + (3)`() {

        val instructionString = "3 + 4^2"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Binop(Plus, Const(3.0), Binop(Pow, Const(4.0), Const(2.0)))))
        println("========")
    }

    @test
    fun `parse eval priority op ^ + (4)`() {

        val instructionString = "(3 + 4)^2"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Binop(Pow, Binop(Pow, Const(4.0), Const(2.0)), Const(3.0))))
        println("========")
    }

    @test
    fun `parse eval basic sqrt`() {

        val instructionString = "sqrt(2)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Sqrt(Const(2.0))))
        println("========")
    }

    @test
    fun `parse eval sqrt of var`() {

        val instructionString = "sqrt(x)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Sqrt(Var("x"))))
        println("========")
    }

    @test
    fun `parse eval complex sqrt`() {

        val instructionString = "sqrt(x + 1)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Sqrt(Binop(Plus, Var("x"), Const(1.0)))))
        println("========")
    }

    @test
    fun `parse eval basic expo`() {

        val instructionString = "exp(2)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Expo(Const(2.0))))
        println("========")
    }

    @test
    fun `parse eval expo of var`() {

        val instructionString = "exp(x)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Expo(Var("x"))))
        println("========")
    }

    @test
    fun `parse eval complex expo`() {

        val instructionString = "exp(x + 1)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Expo(Binop(Plus, Var("x"), Const(1.0)))))
        println("========")
    }

    @test
    fun `parse eval basic ln`() {

        val instructionString = "ln(2)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Ln(Const(2.0))))
        println("========")
    }

    @test
    fun `parse eval ln of var`() {

        val instructionString = "ln(x)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Ln(Var("x"))))
        println("========")
    }

    @test
    fun `parse eval complex ln`() {

        val instructionString = "ln(x + 1)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Ln(Binop(Plus, Var("x"), Const(1.0)))))
        println("========")
    }

    @test
    fun `parse eval basic log10`() {

        val instructionString = "log10(2)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Log10(Const(2.0))))
        println("========")
    }

    @test
    fun `parse eval basic normalized notation, lg = log10`() {

        val instructionString = "lg(2)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Log10(Const(2.0))))
        println("========")
    }

    @test
    fun `parse eval log10 of var`() {

        val instructionString = "log10(x)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Log10(Var("x"))))
        println("========")
    }

    @test
    fun `parse eval complex log10`() {

        val instructionString = "log10(x + 1)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Log10(Binop(Plus, Var("x"), Const(1.0)))))
        println("========")
    }

    @test
    fun `parse eval basic log2`() {

        val instructionString = "log2(2)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Log2(Const(2.0))))
        println("========")
    }

    @test
    fun `parse eval basic normalized notation, lb = log2`() {

        val instructionString = "lb(2)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Log2(Const(2.0))))
        println("========")
    }

    @test
    fun `parse eval log2 of var`() {

        val instructionString = "log2(x)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Log2(Var("x"))))
        println("========")
    }

    @test
    fun `parse eval complex log2`() {

        val instructionString = "log2(x + 1)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Log2(Binop(Plus, Var("x"), Const(1.0)))))
        println("========")
    }

    @test
    fun `parse eval basic Sin`() {

        val instructionString = "sin(2)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Sin(Const(2.0))))
        println("========")
    }

    @test
    fun `parse eval Sin of var`() {

        val instructionString = "sin(x)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Sin(Var("x"))))
        println("========")
    }

    @test
    fun `parse eval complex Sin`() {

        val instructionString = "sin(x + 1)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Sin(Binop(Plus, Var("x"), Const(1.0)))))
        println("========")
    }

    @test
    fun `parse eval basic Cos`() {

        val instructionString = "cos(2)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Cos(Const(2.0))))
        println("========")
    }

    @test
    fun `parse eval Cos of var`() {

        val instructionString = "cos(x)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Cos(Var("x"))))
        println("========")
    }

    @test
    fun `parse eval complex Cos`() {

        val instructionString = "cos(x + 1)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Cos(Binop(Plus, Var("x"), Const(1.0)))))
        println("========")
    }

    @test
    fun `parse eval basic Tan`() {

        val instructionString = "tan(2)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Tan(Const(2.0))))
        println("========")
    }

    @test
    fun `parse eval Tan of var`() {

        val instructionString = "tan(x)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Tan(Var("x"))))
        println("========")
    }

    @test
    fun `parse eval complex Tan`() {

        val instructionString = "tan(x + 1)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Tan(Binop(Plus, Var("x"), Const(1.0)))))
        println("========")
    }

    @test
    fun `parse eval basic arcsin`() {

        val instructionString = "arcsin(2)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Asin(Const(2.0))))
        println("========")
    }

    @test
    fun `parse eval arcsin of var`() {

        val instructionString = "arcsin(x)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Asin(Var("x"))))
        println("========")
    }

    @test
    fun `parse eval complex arcsin`() {

        val instructionString = "arcsin(x + 1)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Asin(Binop(Plus, Var("x"), Const(1.0)))))
        println("========")
    }

    @test
    fun `parse eval basic arccos`() {

        val instructionString = "arccos(2)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Acos(Const(2.0))))
        println("========")
    }

    @test
    fun `parse eval arccos of var`() {

        val instructionString = "arccos(x)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Acos(Var("x"))))
        println("========")
    }

    @test
    fun `parse eval complex arccos`() {

        val instructionString = "arcsin(x + 1)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Acos(Binop(Plus, Var("x"), Const(1.0)))))
        println("========")
    }

    @test
    fun `parse eval basic arctan`() {

        val instructionString = "arctan(2)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Atan(Const(2.0))))
        println("========")
    }

    @test
    fun `parse eval arctan of var`() {

        val instructionString = "arctan(x)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Atan(Var("x"))))
        println("========")
    }

    @test
    fun `parse eval complex arctan`() {

        val instructionString = "arctan(x + 1)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Atan(Binop(Plus, Var("x"), Const(1.0)))))
        println("========")
    }

    @test
    fun `parse eval basic sec`() {

        val instructionString = "sec(2)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Sec(Const(2.0))))
        println("========")
    }

    @test
    fun `parse eval sec of var`() {

        val instructionString = "sec(x)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Sec(Var("x"))))
        println("========")
    }

    @test
    fun `parse eval complex sec`() {

        val instructionString = "sec(x + 1)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Sec(Binop(Plus, Var("x"), Const(1.0)))))
        println("========")
    }

    @test
    fun `parse eval basic cosec`() {

        val instructionString = "cosec(2)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Cosec(Const(2.0))))
        println("========")
    }

    @test
    fun `parse eval cosec of var`() {

        val instructionString = "cosec(x)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Cosec(Var("x"))))
        println("========")
    }

    @test
    fun `parse eval complex cosec`() {

        val instructionString = "cosec(x + 1)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Cosec(Binop(Plus, Var("x"), Const(1.0)))))
        println("========")
    }

    @test
    fun `parse eval basic cotan`() {

        val instructionString = "cotan(2)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Cotan(Const(2.0))))
        println("========")
    }

    @test
    fun `parse eval cotan of var`() {

        val instructionString = "cotan(x)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Cotan(Var("x"))))
        println("========")
    }

    @test
    fun `parse eval complex cotan`() {

        val instructionString = "cotan(x + 1)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Cotan(Binop(Plus, Var("x"), Const(1.0)))))
        println("========")
    }

    @test
    fun `parse eval basic arcsec`() {

        val instructionString = "arcsec(2)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Asec(Const(2.0))))
        println("========")
    }

    @test
    fun `parse eval arcsec of var`() {

        val instructionString = "arcsec(x)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Asec(Var("x"))))
        println("========")
    }

    @test
    fun `parse eval complex arcsec`() {

        val instructionString = "arcsec(x + 1)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Asec(Binop(Plus, Var("x"), Const(1.0)))))
        println("========")
    }

    @test
    fun `parse eval basic arccosec`() {

        val instructionString = "arccosec(2)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Acosec(Const(2.0))))
        println("========")
    }

    @test
    fun `parse eval arccosec of var`() {

        val instructionString = "arccosec(x)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Acosec(Var("x"))))
        println("========")
    }

    @test
    fun `parse eval complex arccosec`() {

        val instructionString = "arccosec(x + 1)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Acosec(Binop(Plus, Var("x"), Const(1.0)))))
        println("========")
    }

    @test
    fun `parse eval basic arccotan`() {

        val instructionString = "arccotan(2)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Acotan(Const(2.0))))
        println("========")
    }

    @test
    fun `parse eval arccotan of var`() {

        val instructionString = "arccotan(x)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Acotan(Var("x"))))
        println("========")
    }

    @test
    fun `parse eval complex arccotan`() {

        val instructionString = "arccotan(x + 1)"
        val parser = KalculusParser()

        val ast: Option<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast is Some && ast.t == Eval(Acotan(Binop(Plus, Var("x"), Const(1.0)))))
        println("========")
    }
}