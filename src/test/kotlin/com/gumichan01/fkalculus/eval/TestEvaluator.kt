package com.gumichan01.fkalculus.eval

import com.gumichan01.fkalculus.ast.*
import com.gumichan01.fkalculus.util.None
import com.gumichan01.fkalculus.util.Option
import com.gumichan01.fkalculus.util.Some
import org.junit.jupiter.api.Assertions.assertTrue
import kotlin.math.*
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
        assertTrue(result is Some && result.t is IdentifierValue && (result.t as IdentifierValue).value == Const(PI))
        println("========")
    }

    @test
    fun `test eval e`() {

        val ast = Exp1
        val result: Option<ResultValue> = Evaluator().eval(ast)
        println(result)
        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(result is Some && result.t is IdentifierValue && (result.t as IdentifierValue).value == Const(E))
        println("========")
    }

    @test
    fun `test eval addition`() {

        val ast = Binop(Plus, Const(2.0), Const(2.0))
        val result: Option<ResultValue> = Evaluator().eval(ast)
        println(result)
        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(checkConst(result))
        assertTrue(result is Some && checkConst(result) && (result.t as IdentifierValue).value == Const(4.0))
        println("========")
    }

    @test
    fun `test eval multiple addition`() {

        val ast = Binop(Plus, Binop(Plus, Const(2.0), Const(2.0)), Binop(Plus, Const(2.0), Const(2.0)))
        val result: Option<ResultValue> = Evaluator().eval(ast)
        println(result)
        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(checkConst(result))
        assertTrue(result is Some && checkConst(result) && (result.t as IdentifierValue).value == Const(8.0))
        println("========")
    }

    @test
    fun `test eval sub`() {

        val ast = Binop(Minus, Const(2.0), Const(2.0))
        val result: Option<ResultValue> = Evaluator().eval(ast)
        println(result)
        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(checkConst(result))
        assertTrue(result is Some && checkConst(result) && (result.t as IdentifierValue).value == Const(0.0))
        println("========")
    }

    @test
    fun `test eval multiple sub`() {

        val ast = Binop(Minus, Const(6.0), Binop(Minus, Const(2.0), Const(-2.0)))
        val result: Option<ResultValue> = Evaluator().eval(ast)
        println(result)
        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(checkConst(result))
        assertTrue(result is Some && checkConst(result) && (result.t as IdentifierValue).value == Const(2.0))
        println("========")
    }

    @test
    fun `test eval mult`() {

        val ast = Binop(Mult, Const(2.0), Const(2.0))
        val result: Option<ResultValue> = Evaluator().eval(ast)
        println(result)
        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(checkConst(result))
        assertTrue(result is Some && checkConst(result) && (result.t as IdentifierValue).value == Const(4.0))
        println("========")
    }

    @test
    fun `test eval multiple mult`() {

        val ast = Binop(Mult, Const(4.0), Binop(Mult, Const(2.0), Const(2.0)))
        val result: Option<ResultValue> = Evaluator().eval(ast)
        println(result)
        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(checkConst(result))
        assertTrue(result is Some && checkConst(result) && (result.t as IdentifierValue).value == Const(16.0))
        println("========")
    }

    @test
    fun `test eval div`() {

        val ast = Binop(Div, Const(2.0), Const(2.0))
        val result: Option<ResultValue> = Evaluator().eval(ast)
        println(result)
        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(checkConst(result))
        assertTrue(result is Some && checkConst(result) && (result.t as IdentifierValue).value == Const(1.0))
        println("========")
    }

    @test
    fun `test eval multiple div`() {

        val ast = Binop(Div, Const(16.0), Binop(Div, Const(4.0), Const(2.0)))
        val result: Option<ResultValue> = Evaluator().eval(ast)
        println(result)
        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(checkConst(result))
        assertTrue(result is Some && checkConst(result) && (result.t as IdentifierValue).value == Const(8.0))
        println("========")
    }

    @test
    fun `test eval invalid div`() {
        val ast = Binop(Div, Const(5.0), Const(0.0))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is None)
        println("========")
    }

    @test
    fun `test eval pow`() {

        val ast = Binop(Pow, Const(2.0), Const(5.0))
        val result: Option<ResultValue> = Evaluator().eval(ast)
        println(result)
        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(checkConst(result))
        assertTrue(result is Some && checkConst(result) && (result.t as IdentifierValue).value == Const(32.0))
        println("========")
    }

    @test
    fun `test eval pow complex`() {

        val ast = Binop(Pow, Const(2.0), Binop(Plus, Const(2.0), Const(3.0)))
        val result: Option<ResultValue> = Evaluator().eval(ast)
        println(result)
        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(checkConst(result))
        assertTrue(result is Some && checkConst(result) && (result.t as IdentifierValue).value == Const(32.0))
        println("========")
    }

    @test
    fun `test eval 0^0`() {

        val ast = Binop(Pow, Const(0.0), Const(0.0))
        val result: Option<ResultValue> = Evaluator().eval(ast)
        println(result)
        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(checkConst(result))
        assertTrue(result is Some && checkConst(result) && (result.t as IdentifierValue).value == Const(1.0))
        println("========")
    }


    @test
    fun `test eval (-3)^0`() {

        val ast = Binop(Pow, Const(-3.0), Const(2.0))
        val result: Option<ResultValue> = Evaluator().eval(ast)
        println(result)
        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(checkConst(result))
        assertTrue(result is Some && checkConst(result) && (result.t as IdentifierValue).value == Const(9.0))
        println("========")
    }

    @test
    fun `test eval sqrt`() {

        val ast = Sqrt(Const(4.0))
        val result: Option<ResultValue> = Evaluator().eval(ast)
        println(result)
        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(checkConst(result))
        assertTrue(result is Some && checkConst(result) && (result.t as IdentifierValue).value == Const(2.0))
        println("========")
    }

    @test
    fun `test eval complex sqrt`() {

        val ast = Sqrt(Binop(Plus, Var("x"), Const(1.0)))
        val result: Option<ResultValue> = Evaluator().eval(ast)
        println(result)
        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(result is Some && result.t is IdentifierValue
                && (result.t as IdentifierValue).value == Sqrt(Binop(Plus, Var("x"), Const(1.0))))
        println("========")
    }

    @test
    fun `test eval invalid sqrt`() {

        val ast = Sqrt(Const(-1.0))
        val result: Option<ResultValue> = Evaluator().eval(ast)
        println(result)
        assertTrue(result is None)
        println("========")
    }

    @test
    fun `test eval expo`() {

        val ast = Expo(Const(2.0))
        val result: Option<ResultValue> = Evaluator().eval(ast)
        println(result)
        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(checkConst(result))
        assertTrue(result is Some && checkConst(result) && (result.t as IdentifierValue).value == Const(exp(2.0)))
        println("========")
    }

    @test
    fun `test eval complex expo`() {

        val ast = Expo(Binop(Plus, Var("x"), Const(1.0)))
        val result: Option<ResultValue> = Evaluator().eval(ast)
        println(result)
        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(result is Some && result.t is IdentifierValue
                && (result.t as IdentifierValue).value == Expo(Binop(Plus, Var("x"), Const(1.0))))
        println("========")
    }

    @test
    fun `test eval Ln`() {

        val ast = Ln(Const(2.0))
        val result: Option<ResultValue> = Evaluator().eval(ast)
        println(result)
        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(checkConst(result))
        assertTrue(result is Some && checkConst(result) && (result.t as IdentifierValue).value == Const(ln(2.0)))
        println("========")
    }

    @test
    fun `test eval complex Ln`() {

        val ast = Ln(Binop(Plus, Var("x"), Const(1.0)))
        val result: Option<ResultValue> = Evaluator().eval(ast)
        println(result)
        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(result is Some && result.t is IdentifierValue
                && (result.t as IdentifierValue).value == Ln(Binop(Plus, Var("x"), Const(1.0))))
        println("========")
    }

    @test
    fun `test eval invalid Ln`() {

        val ast = Ln(Const(-2.0))
        val result: Option<ResultValue> = Evaluator().eval(ast)
        println(result)
        assertTrue(result is None)
        println("========")
    }

    @test
    fun `test eval Log10`() {

        val ast = Log10(Const(2.0))
        val result: Option<ResultValue> = Evaluator().eval(ast)
        println(result)
        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(checkConst(result))
        assertTrue(result is Some && checkConst(result) && (result.t as IdentifierValue).value == Const(log10(2.0)))
        println("========")
    }

    @test
    fun `test eval complex Log10`() {

        val ast = Log10(Binop(Plus, Var("x"), Const(1.0)))
        val result: Option<ResultValue> = Evaluator().eval(ast)
        println(result)
        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(result is Some && result.t is IdentifierValue
                && (result.t as IdentifierValue).value == Log10(Binop(Plus, Var("x"), Const(1.0))))
        println("========")
    }

    @test
    fun `test eval invalid Log10`() {

        val ast = Log10(Const(-2.0))
        val result: Option<ResultValue> = Evaluator().eval(ast)
        println(result)
        assertTrue(result is None)
        println("========")
    }

    @test
    fun `test eval Log2`() {

        val ast = Log2(Const(8.0))
        val result: Option<ResultValue> = Evaluator().eval(ast)
        println(result)
        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(checkConst(result))
        assertTrue(result is Some && checkConst(result) && (result.t as IdentifierValue).value == Const(3.0))
        println("========")
    }

    @test
    fun `test eval complex Log2`() {

        val ast = Log2(Binop(Plus, Var("x"), Const(1.0)))
        val result: Option<ResultValue> = Evaluator().eval(ast)
        println(result)
        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(result is Some && result.t is IdentifierValue
                && (result.t as IdentifierValue).value == Log2(Binop(Plus, Var("x"), Const(1.0))))
        println("========")
    }

    @test
    fun `test eval invalid Lb`() {

        val ast = Log2(Const(-2.0))
        val result: Option<ResultValue> = Evaluator().eval(ast)
        println(result)
        assertTrue(result is None)
        println("========")
    }

    private fun checkConst(result: Option<ResultValue>): Boolean {

        return result is Some && result.t is IdentifierValue && (result.t as IdentifierValue).value is Const
    }
}