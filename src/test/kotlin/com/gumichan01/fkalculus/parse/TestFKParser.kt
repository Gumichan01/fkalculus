package com.gumichan01.fkalculus.parse

import com.gumichan01.fkalculus.ast.*
import com.gumichan01.fkalculus.util.Some
import org.junit.jupiter.api.Assertions.assertTrue
import org.junit.jupiter.api.Test as test


class TestFKParser {

    @test
    fun `parse eval Pi`() {

        val instructionString = "eval(Pi)"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Pi)
        println("========")
    }

    @test
    fun `parse eval exp1`() {

        val instructionString = "eval(e)"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Exp1)
        println("========")
    }

    @test
    fun `parse eval const`() {

        val instructionString = "eval(42)"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Const(42.0)))
        println("========")
    }

    @test
    fun `parse eval negative const`() {

        val instructionString = "eval(-64)"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Const(-64.0)))
        println("========")
    }

    @test
    fun `parse eval Var`() {

        val instructionString = "eval(x)"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Var("x")))
        println("========")
    }

    @test
    fun `parse eval number addition`() {

        val instructionString = "eval(4 + 2)"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Binop(Plus, Const(4.0), Const(2.0))))
        println("========")
    }

    @test
    fun `parse eval number + var`() {

        val instructionString = "eval(4 + x)"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Binop(Plus, Const(4.0), Var("x"))))
        println("========")
    }

    @test
    fun `parse eval number complex addition`() {

        val instructionString = "eval(4 + x + e + Pi)"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Binop(Plus, Const(4.0), Binop(Plus, Var("x"), Binop(Plus, Exp1, Pi)))))
        println("========")
    }

    @test
    fun `parse eval -`() {

        val instructionString = "eval(4 - 2)"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Binop(Minus, Const(4.0), Var("x"))))
        println("========")
    }

    @test
    fun `parse eval number - variable`() {

        val instructionString = "eval(4 - x)"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Binop(Minus, Const(4.0), Var("x"))))
        println("========")
    }

    @test
    fun `parse eval number complex sub`() {

        val instructionString = "eval(4 - x - e - Pi)"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Binop(Minus, Const(4.0), Binop(Minus, Var("x"), Binop(Minus, Exp1, Pi)))))
        println("========")
    }

    @test
    fun `parse eval number multiply`() {

        val instructionString = "eval(4 * 2)"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Binop(Mult, Const(4.0), Const(2.0))))
        println("========")
    }

    @test
    fun `parse eval number * var`() {

        val instructionString = "eval(4 * x)"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Binop(Mult, Const(4.0), Var("x"))))
        println("========")
    }

    @test
    fun `parse eval number complex multiplication`() {

        val instructionString = "eval(4 * x * e * Pi)"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Binop(Mult, Const(4.0), Binop(Mult, Var("x"), Binop(Mult, Exp1, Pi)))))
        println("========")
    }

    @test
    fun `parse eval number div`() {

        val instructionString = "eval(4 / 2)"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Binop(Div, Const(4.0), Const(2.0))))
        println("========")
    }

    @test
    fun `parse eval number / var`() {

        val instructionString = "eval(4 / x)"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Binop(Div, Const(4.0), Var("x"))))
        println("========")
    }

    @test
    fun `parse eval number complex div`() {

        val instructionString = "eval(4 / x / e / Pi)"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Binop(Mult, Const(4.0), Binop(Mult, Var("x"), Binop(Mult, Exp1, Pi)))))
        println("========")
    }

    @test
    fun `parse eval number pow`() {

        val instructionString = "eval(4^2)"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Binop(Pow, Const(4.0), Const(2.0))))
        println("========")
    }

    @test
    fun `parse eval number pow var`() {

        val instructionString = "eval(4^x)"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Binop(Pow, Const(4.0), Var("x"))))
        println("========")
    }

    @test
    fun `parse eval number complex pow`() {

        val instructionString = "eval(4^x^e^Pi)" // ((4^x)^e)^π
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Binop(Pow, Const(4.0), Binop(Pow, Var("x"), Binop(Pow, Exp1, Pi)))))
        println("========")
    }

    @test
    fun `parse eval priority op + *`() {

        val instructionString = "eval(2*x + 1)"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Binop(Plus, Binop(Mult, Const(2.0), Var("x")), Const(1.0))))
        println("========")
    }

    @test
    fun `parse eval priority op - *`() {

        val instructionString = "eval(2*x - 1)"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Binop(Minus, Binop(Mult, Const(2.0), Var("x")), Const(1.0))))
        println("========")
    }

    @test
    fun `parse eval priority op div *`() {

        val instructionString = "eval(4 * 2 / 3)"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Binop(Div, Binop(Mult, Const(4.0), Const(2.0)), Const(3.0))))
        println("========")
    }

    @test
    fun `parse eval priority op div * (2)`() {

        val instructionString = "eval(2 / 3 * 4)"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Binop(Div, Const(3.0), Binop(Mult, Const(4.0), Const(2.0)))))
        println("========")
    }

    @test
    fun `parse eval priority op div * (3)`() {

        val instructionString = "eval(4 * (2 / 3))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Binop(Mult, Const(4.0), Binop(Div, Const(2.0), Const(3.0)))))
        println("========")
    }

    @test
    fun `parse eval priority op div * (4)`() {

        val instructionString = "eval((2 / 3) * 4)"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Binop(Mult,Binop(Div, Const(2.0), Const(3.0)), Const(4.0))))
        println("========")
    }

    @test
    fun `parse eval priority op ^ +`() {

        val instructionString = "eval(4^2 + 3))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Binop(Plus, Binop(Pow, Const(4.0), Const(2.0)), Const(3.0))))
        println("========")
    }

    @test
    fun `parse eval priority op ^ + (2)`() {

        val instructionString = "eval(4^(2 / 3))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Binop(Pow, Const(4.0), Binop(Plus, Const(2.0), Const(3.0)))))
        println("========")
    }

    @test
    fun `parse eval priority op ^ + (3)`() {

        val instructionString = "eval(3 + 4^2))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Binop(Plus, Const(3.0), Binop(Pow, Const(4.0), Const(2.0)))))
        println("========")
    }

    @test
    fun `parse eval priority op ^ + (4)`() {

        val instructionString = "eval((3 + 4)^2))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Binop(Pow, Binop(Pow, Const(4.0), Const(2.0)), Const(3.0))))
        println("========")
    }

    @test
    fun `parse eval basic sqrt`() {

        val instructionString = "eval(sqrt(2))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Sqrt(Const(2.0))))
        println("========")
    }

    @test
    fun `parse eval sqrt of var`() {

        val instructionString = "eval(sqrt(x))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Sqrt(Var("x"))))
        println("========")
    }

    @test
    fun `parse eval complex sqrt`() {

        val instructionString = "eval(sqrt(x + 1))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Sqrt(Binop(Plus, Var("x"), Const(1.0)))))
        println("========")
    }

    @test
    fun `parse eval basic expo`() {

        val instructionString = "eval(exp(2))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Expo(Const(2.0))))
        println("========")
    }

    @test
    fun `parse eval expo of var`() {

        val instructionString = "eval(exp(x))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Expo(Var("x"))))
        println("========")
    }

    @test
    fun `parse eval complex expo`() {

        val instructionString = "eval(exp(x + 1))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Expo(Binop(Plus, Var("x"), Const(1.0)))))
        println("========")
    }

    @test
    fun `parse eval basic ln`() {

        val instructionString = "eval(ln(2))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Ln(Const(2.0))))
        println("========")
    }

    @test
    fun `parse eval ln of var`() {

        val instructionString = "eval(ln(x))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Ln(Var("x"))))
        println("========")
    }

    @test
    fun `parse eval complex ln`() {

        val instructionString = "eval(ln(x + 1))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Ln(Binop(Plus, Var("x"), Const(1.0)))))
        println("========")
    }

    @test
    fun `parse eval basic log10`() {

        val instructionString = "eval(log10(2))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Log10(Const(2.0))))
        println("========")
    }

    @test
    fun `parse eval basic normalized notation, lg = log10`() {

        val instructionString = "eval(lg(2))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Log10(Const(2.0))))
        println("========")
    }

    @test
    fun `parse eval log10 of var`() {

        val instructionString = "eval(log10(x))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Log10(Var("x"))))
        println("========")
    }

    @test
    fun `parse eval complex log10`() {

        val instructionString = "eval(log10(x + 1))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Log10(Binop(Plus, Var("x"), Const(1.0)))))
        println("========")
    }

    @test
    fun `parse eval basic log2`() {

        val instructionString = "eval(log2(2))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Log2(Const(2.0))))
        println("========")
    }

    @test
    fun `parse eval basic normalized notation, lb = log2`() {

        val instructionString = "eval(lb(2))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Log2(Const(2.0))))
        println("========")
    }

    @test
    fun `parse eval log2 of var`() {

        val instructionString = "eval(log2(x))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Log2(Var("x"))))
        println("========")
    }

    @test
    fun `parse eval complex log2`() {

        val instructionString = "eval(log2(x + 1))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Log2(Binop(Plus, Var("x"), Const(1.0)))))
        println("========")
    }

    @test
    fun `parse eval basic Sin`() {

        val instructionString = "eval(sin(2))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Sin(Const(2.0))))
        println("========")
    }

    @test
    fun `parse eval Sin of var`() {

        val instructionString = "eval(sin(x))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Sin(Var("x"))))
        println("========")
    }

    @test
    fun `parse eval complex Sin`() {

        val instructionString = "eval(sin(x + 1))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Sin(Binop(Plus, Var("x"), Const(1.0)))))
        println("========")
    }

    @test
    fun `parse eval basic Cos`() {

        val instructionString = "eval(cos(2))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Cos(Const(2.0))))
        println("========")
    }

    @test
    fun `parse eval Cos of var`() {

        val instructionString = "eval(cos(x))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Cos(Var("x"))))
        println("========")
    }

    @test
    fun `parse eval complex Cos`() {

        val instructionString = "eval(cos(x + 1))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Cos(Binop(Plus, Var("x"), Const(1.0)))))
        println("========")
    }

    @test
    fun `parse eval basic Tan`() {

        val instructionString = "eval(tan(2))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Tan(Const(2.0))))
        println("========")
    }

    @test
    fun `parse eval Tan of var`() {

        val instructionString = "eval(tan(x))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Tan(Var("x"))))
        println("========")
    }

    @test
    fun `parse eval complex Tan`() {

        val instructionString = "eval(tan(x + 1))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Tan(Binop(Plus, Var("x"), Const(1.0)))))
        println("========")
    }

    @test
    fun `parse eval basic arcsin`() {

        val instructionString = "eval(arcsin(2))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Asin(Const(2.0))))
        println("========")
    }

    @test
    fun `parse eval arcsin of var`() {

        val instructionString = "eval(arcsin(x))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Asin(Var("x"))))
        println("========")
    }

    @test
    fun `parse eval complex arcsin`() {

        val instructionString = "eval(arcsin(x + 1))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Asin(Binop(Plus, Var("x"), Const(1.0)))))
        println("========")
    }

    @test
    fun `parse eval basic arccos`() {

        val instructionString = "eval(arccos(2))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Acos(Const(2.0))))
        println("========")
    }

    @test
    fun `parse eval arccos of var`() {

        val instructionString = "eval(arccos(x))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Acos(Var("x"))))
        println("========")
    }

    @test
    fun `parse eval complex arccos`() {

        val instructionString = "eval(arcsin(x + 1))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Acos(Binop(Plus, Var("x"), Const(1.0)))))
        println("========")
    }

    @test
    fun `parse eval basic arctan`() {

        val instructionString = "eval(arctan(2))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Atan(Const(2.0))))
        println("========")
    }

    @test
    fun `parse eval arctan of var`() {

        val instructionString = "eval(arctan(x))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Atan(Var("x"))))
        println("========")
    }

    @test
    fun `parse eval complex arctan`() {

        val instructionString = "eval(arctan(x + 1))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Atan(Binop(Plus, Var("x"), Const(1.0)))))
        println("========")
    }

    @test
    fun `parse eval basic sec`() {

        val instructionString = "eval(sec(2))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Sec(Const(2.0))))
        println("========")
    }

    @test
    fun `parse eval sec of var`() {

        val instructionString = "eval(sec(x))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Sec(Var("x"))))
        println("========")
    }

    @test
    fun `parse eval complex sec`() {

        val instructionString = "eval(sec(x + 1))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Sec(Binop(Plus, Var("x"), Const(1.0)))))
        println("========")
    }

    @test
    fun `parse eval basic cosec`() {

        val instructionString = "eval(cosec(2))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Cosec(Const(2.0))))
        println("========")
    }

    @test
    fun `parse eval cosec of var`() {

        val instructionString = "eval(cosec(x))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Cosec(Var("x"))))
        println("========")
    }

    @test
    fun `parse eval complex cosec`() {

        val instructionString = "eval(cosec(x + 1))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Cosec(Binop(Plus, Var("x"), Const(1.0)))))
        println("========")
    }

    @test
    fun `parse eval basic cotan`() {

        val instructionString = "eval(cotan(2))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Cotan(Const(2.0))))
        println("========")
    }

    @test
    fun `parse eval cotan of var`() {

        val instructionString = "eval(cotan(x))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Cotan(Var("x"))))
        println("========")
    }

    @test
    fun `parse eval complex cotan`() {

        val instructionString = "eval(cotan(x + 1))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Cotan(Binop(Plus, Var("x"), Const(1.0)))))
        println("========")
    }

    @test
    fun `parse eval basic arcsec`() {

        val instructionString = "eval(arcsec(2))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Asec(Const(2.0))))
        println("========")
    }

    @test
    fun `parse eval arcsec of var`() {

        val instructionString = "eval(arcsec(x))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Asec(Var("x"))))
        println("========")
    }

    @test
    fun `parse eval complex arcsec`() {

        val instructionString = "eval(arcsec(x + 1))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Asec(Binop(Plus, Var("x"), Const(1.0)))))
        println("========")
    }

    @test
    fun `parse eval basic arccosec`() {

        val instructionString = "eval(arccosec(2))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Acosec(Const(2.0))))
        println("========")
    }

    @test
    fun `parse eval arccosec of var`() {

        val instructionString = "eval(arccosec(x))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Acosec(Var("x"))))
        println("========")
    }

    @test
    fun `parse eval complex arccosec`() {

        val instructionString = "eval(arccosec(x + 1))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Acosec(Binop(Plus, Var("x"), Const(1.0)))))
        println("========")
    }

    @test
    fun `parse eval basic arccotan`() {

        val instructionString = "eval(arccotan(2))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Acotan(Const(2.0))))
        println("========")
    }

    @test
    fun `parse eval arccotan of var`() {

        val instructionString = "eval(arccotan(x))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Acotan(Var("x"))))
        println("========")
    }

    @test
    fun `parse eval complex arccotan`() {

        val instructionString = "eval(arccotan(x + 1))"
        val parser: FKParser = FKParser()

        val ast: Some<FKalculusAST> = parser.parse(instructionString)
        println(instructionString)

        assertTrue(ast is Some)
        assertTrue(ast.t == Eval(Acotan(Binop(Plus, Var("x"), Const(1.0)))))
        println("========")
    }
}