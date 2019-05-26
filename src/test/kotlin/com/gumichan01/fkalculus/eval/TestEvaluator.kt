package com.gumichan01.fkalculus.eval

import com.gumichan01.fkalculus.ast.*
import com.gumichan01.fkalculus.util.None
import com.gumichan01.fkalculus.util.Option
import com.gumichan01.fkalculus.util.Some
import org.junit.jupiter.api.Assertions.assertTrue
import kotlin.math.*
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

class TestEvaluator {

    @test
    fun `test init class`() {
        Evaluator()
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

        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(result is Some && result.t is IdentifierValue && (result.t as IdentifierValue).value == Var("x"))
    }

    @test
    fun `test eval pi`() {
        val ast = Pi
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(result is Some && result.t is IdentifierValue && (result.t as IdentifierValue).value == Const(PI))
    }

    @test
    fun `test eval e`() {
        val ast = Exp1
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(result is Some && result.t is IdentifierValue && (result.t as IdentifierValue).value == Const(E))
    }

    @test
    fun `test eval addition`() {
        val ast = Binop(Plus, Const(2.0), Const(2.0))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(checkConst(result))
        assertTrue(result is Some && checkConst(result) && (result.t as IdentifierValue).value == Const(4.0))

    }

    @test
    fun `test eval multiple addition`() {
        val ast = Binop(Plus, Binop(Plus, Const(2.0), Const(2.0)), Binop(Plus, Const(2.0), Const(2.0)))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(checkConst(result))
        assertTrue(result is Some && checkConst(result) && (result.t as IdentifierValue).value == Const(8.0))
    }

    @test
    fun `test eval sub`() {
        val ast = Binop(Minus, Const(2.0), Const(2.0))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(checkConst(result))
        assertTrue(result is Some && checkConst(result) && (result.t as IdentifierValue).value == Const(0.0))
    }

    @test
    fun `test eval multiple sub`() {
        val ast = Binop(Minus, Const(6.0), Binop(Minus, Const(2.0), Const(-2.0)))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(checkConst(result))
        assertTrue(result is Some && checkConst(result) && (result.t as IdentifierValue).value == Const(2.0))
    }

    @test
    fun `test eval mult`() {
        val ast = Binop(Mult, Const(2.0), Const(2.0))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(checkConst(result))
        assertTrue(result is Some && checkConst(result) && (result.t as IdentifierValue).value == Const(4.0))
    }

    @test
    fun `test eval multiple mult`() {
        val ast = Binop(Mult, Const(4.0), Binop(Mult, Const(2.0), Const(2.0)))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(checkConst(result))
        assertTrue(result is Some && checkConst(result) && (result.t as IdentifierValue).value == Const(16.0))
    }

    @test
    fun `test eval div`() {
        val ast = Binop(Div, Const(2.0), Const(2.0))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(checkConst(result))
        assertTrue(result is Some && checkConst(result) && (result.t as IdentifierValue).value == Const(1.0))
    }

    @test
    fun `test eval multiple div`() {
        val ast = Binop(Div, Const(16.0), Binop(Div, Const(4.0), Const(2.0)))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(checkConst(result))
        assertTrue(result is Some && checkConst(result) && (result.t as IdentifierValue).value == Const(8.0))
    }

    @test
    fun `test eval invalid div`() {
        val ast = Binop(Div, Const(5.0), Const(0.0))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is None)
    }

    @test
    fun `test eval pow`() {
        val ast = Binop(Pow, Const(2.0), Const(5.0))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(checkConst(result))
        assertTrue(result is Some && checkConst(result) && (result.t as IdentifierValue).value == Const(32.0))
    }

    @test
    fun `test eval pow complex`() {
        val ast = Binop(Pow, Const(2.0), Binop(Plus, Const(2.0), Const(3.0)))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(checkConst(result))
        assertTrue(result is Some && checkConst(result) && (result.t as IdentifierValue).value == Const(32.0))
    }

    @test
    fun `test eval 0^0`() {
        val ast = Binop(Pow, Const(0.0), Const(0.0))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(checkConst(result))
        assertTrue(result is Some && checkConst(result) && (result.t as IdentifierValue).value == Const(1.0))
    }


    @test
    fun `test eval (-3)^0`() {
        val ast = Binop(Pow, Const(-3.0), Const(2.0))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(checkConst(result))
        assertTrue(result is Some && checkConst(result) && (result.t as IdentifierValue).value == Const(9.0))
    }

    @test
    fun `test eval sqrt`() {
        val ast = Sqrt(Const(4.0))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(checkConst(result))
        assertTrue(result is Some && checkConst(result) && (result.t as IdentifierValue).value == Const(2.0))
    }

    @test
    fun `test eval complex sqrt`() {
        val ast = Sqrt(Binop(Plus, Var("x"), Const(1.0)))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(result is Some && result.t is IdentifierValue
                && (result.t as IdentifierValue).value == Sqrt(Binop(Plus, Var("x"), Const(1.0))))
    }

    @test
    fun `test eval invalid sqrt`() {
        val ast = Sqrt(Const(-1.0))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is None)
    }

    @test
    fun `test eval expo`() {
        val ast = Expo(Const(2.0))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(checkConst(result))
        assertTrue(result is Some && checkConst(result) && (result.t as IdentifierValue).value == Const(exp(2.0)))
    }

    @test
    fun `test eval complex expo`() {
        val ast = Expo(Binop(Plus, Var("x"), Const(1.0)))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(result is Some && result.t is IdentifierValue
                && (result.t as IdentifierValue).value == Expo(Binop(Plus, Var("x"), Const(1.0))))
    }

    @test
    fun `test eval Ln`() {
        val ast = Ln(Const(2.0))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(checkConst(result))
        assertTrue(result is Some && checkConst(result) && (result.t as IdentifierValue).value == Const(ln(2.0)))
    }

    @test
    fun `test eval complex Ln`() {
        val ast = Ln(Binop(Plus, Var("x"), Const(1.0)))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(result is Some && result.t is IdentifierValue
                && (result.t as IdentifierValue).value == Ln(Binop(Plus, Var("x"), Const(1.0))))
    }

    @test
    fun `test eval invalid Ln`() {
        val ast = Ln(Const(-2.0))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is None)
    }

    @test
    fun `test eval Log10`() {
        val ast = Log10(Const(2.0))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(checkConst(result))
        assertTrue(result is Some && checkConst(result) && (result.t as IdentifierValue).value == Const(log10(2.0)))
    }

    @test
    fun `test eval complex Log10`() {
        val ast = Log10(Binop(Plus, Var("x"), Const(1.0)))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(result is Some && result.t is IdentifierValue
                && (result.t as IdentifierValue).value == Log10(Binop(Plus, Var("x"), Const(1.0))))
    }

    @test
    fun `test eval invalid Log10`() {
        val ast = Log10(Const(-2.0))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is None)
    }

    @test
    fun `test eval Log2`() {
        val ast = Log2(Const(8.0))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(checkConst(result))
        assertTrue(result is Some && checkConst(result) && (result.t as IdentifierValue).value == Const(3.0))
    }

    @test
    fun `test eval complex Log2`() {
        val ast = Log2(Binop(Plus, Var("x"), Const(1.0)))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(result is Some && result.t is IdentifierValue
                && (result.t as IdentifierValue).value == Log2(Binop(Plus, Var("x"), Const(1.0))))
    }

    @test
    fun `test eval invalid Lb`() {
        val ast = Log2(Const(-2.0))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is None)
    }

    @test
    fun `test eval cosine`() {
        val ast = Cos(Const(3.14))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(checkConst(result))
        assertTrue(result is Some && checkConst(result) && (result.t as IdentifierValue).value == Const(Math.cos(3.14)))
    }

    @test
    fun `test eval complex cosine`() {
        val ast = Cos(Binop(Plus, Var("x"), Const(1.0)))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(result is Some && result.t is IdentifierValue
                && (result.t as IdentifierValue).value == Cos(Binop(Plus, Var("x"), Const(1.0))))
    }

    @test
    fun `test eval sine`() {
        val ast = Sin(Const(3.14))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(checkConst(result))
        assertTrue(result is Some && checkConst(result) && (result.t as IdentifierValue).value == Const(Math.sin(3.14)))
    }

    @test
    fun `test eval complex sine`() {
        val ast = Sin(Binop(Plus, Var("x"), Const(1.0)))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(result is Some && result.t is IdentifierValue
                && (result.t as IdentifierValue).value == Sin(Binop(Plus, Var("x"), Const(1.0))))
    }

    @test
    fun `test eval tangent`() {
        val ast = Tan(Const(3.14))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(checkConst(result))
        assertTrue(result is Some && checkConst(result) && (result.t as IdentifierValue).value == Const(Math.tan(3.14)))
    }

    @test
    fun `test eval complex tangent`() {
        val ast = Tan(Binop(Plus, Var("x"), Const(1.0)))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(result is Some && result.t is IdentifierValue
                && (result.t as IdentifierValue).value == Tan(Binop(Plus, Var("x"), Const(1.0))))
    }

    @test
    fun `test eval arcsin`() {
        val ast = Asin(Const(0.14))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(checkConst(result))
        assertTrue(result is Some && checkConst(result) && (result.t as IdentifierValue).value == Const(Math.asin(0.14)))
    }

    @test
    fun `test eval invalid arcsin of x less than -1`() {
        val ast = Asin(Const(-2.0))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is None)
    }

    @test
    fun `test eval invalid arcsin of x greater than 1`() {
        val ast = Asin(Const(2.0))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is None)
    }

    @test
    fun `test eval complex arcsin`() {
        val ast = Asin(Binop(Plus, Var("x"), Const(1.0)))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(result is Some && result.t is IdentifierValue
                && (result.t as IdentifierValue).value == Asin(Binop(Plus, Var("x"), Const(1.0))))
    }

    @test
    fun `test arcsin is inverse of sine`() {
        val ast = Asin(Sin(Const(90.0)))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(checkConst(result))
        assertTrue(result is Some && checkConst(result) && (result.t as IdentifierValue).value == Const(Math.asin(Math.sin(90.0))))
    }

    @test
    fun `test eval arccos`() {
        val ast = Acos(Const(0.14))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(checkConst(result))
        assertTrue(result is Some && checkConst(result) && (result.t as IdentifierValue).value == Const(Math.acos(0.14)))
    }

    @test
    fun `test eval complex arccos`() {
        val ast = Acos(Binop(Plus, Var("x"), Const(1.0)))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(result is Some && result.t is IdentifierValue
                && (result.t as IdentifierValue).value == Acos(Binop(Plus, Var("x"), Const(1.0))))
    }

    @test
    fun `test eval invalid arccos of x less than -1`() {
        val ast = Acos(Const(-2.0))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is None)
    }

    @test
    fun `test eval invalid arccos of x greater than 1`() {
        val ast = Acos(Const(2.0))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is None)
    }

    @test
    fun `test arccos is inverse of cos`() {
        val ast = Acos(Cos(Const(90.0)))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(checkConst(result))
        assertTrue(result is Some && checkConst(result) && (result.t as IdentifierValue).value == Const(Math.acos(Math.cos(90.0))))
    }

    @test
    fun `test eval arctan`() {
        val ast = Atan(Const(3.14))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(checkConst(result))
        assertTrue(result is Some && checkConst(result) && (result.t as IdentifierValue).value == Const(Math.atan(3.14)))
    }

    @test
    fun `test eval complex arctan`() {
        val ast = Atan(Binop(Plus, Var("x"), Const(1.0)))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(result is Some && result.t is IdentifierValue
                && (result.t as IdentifierValue).value == Atan(Binop(Plus, Var("x"), Const(1.0))))
    }

    @test
    fun `test arctan is inverse of tangent`() {
        val ast = Atan(Tan(Const(90.0)))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(checkConst(result))
        assertTrue(result is Some && checkConst(result) && (result.t as IdentifierValue).value == Const(Math.atan(Math.tan(90.0))))
    }

    @test
    fun `test eval cosec`() {
        val ast = Cosec(Const(3.14))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(checkConst(result))
        assertTrue(result is Some && checkConst(result) && (result.t as IdentifierValue).value == Const(1.0 / Math.sin(3.14)))
    }

    @test
    fun `test eval invalid cosec of Pi`() {
        val ast = Cosec(Const(Math.PI))
        val result: Option<ResultValue> = Evaluator().eval(ast)
        assertTrue(result is None)
    }

    @test
    fun `test eval complex cosec`() {
        val ast = Cosec(Binop(Plus, Var("x"), Const(1.0)))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(result is Some && result.t is IdentifierValue
                && (result.t as IdentifierValue).value == Cosec(Binop(Plus, Var("x"), Const(1.0))))
    }

    @test
    fun `test eval sec`() {
        val ast = Sec(Const(3.14))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(checkConst(result))
        assertTrue(result is Some && checkConst(result) && (result.t as IdentifierValue).value == Const(1.0 / Math.cos(3.14)))
    }

    @test
    fun `test eval invalid secant of Pi divided by 2`() {
        val ast = Sec(Const(Math.PI / 2.0))
        val result: Option<ResultValue> = Evaluator().eval(ast)
        assertTrue(result is None)
    }

    @test
    fun `test eval complex sec`() {
        val ast = Sec(Binop(Plus, Var("x"), Const(1.0)))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(result is Some && result.t is IdentifierValue
                && (result.t as IdentifierValue).value == Sec(Binop(Plus, Var("x"), Const(1.0))))
    }

    @test
    fun `test eval cotan`() {
        val ast = Cotan(Const(3.14))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(checkConst(result))
        assertTrue(result is Some && checkConst(result) && (result.t as IdentifierValue).value == Const(1.0 / Math.tan(3.14)))
    }

    @test
    fun `test eval invalid cotan of Pi`() {
        val ast = Cotan(Const(Math.PI))
        val result: Option<ResultValue> = Evaluator().eval(ast)
        assertTrue(result is None)
    }

    @test
    fun `test eval complex cotan`() {
        val ast = Cotan(Binop(Plus, Var("x"), Const(1.0)))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(result is Some && result.t is IdentifierValue
                && (result.t as IdentifierValue).value == Cotan(Binop(Plus, Var("x"), Const(1.0))))
    }

    /*@test
    fun `test eval acosec`() {
        val ast = Acosec(Const(3.14))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(checkConst(result))
        assertTrue(result is Some && checkConst(result) && (result.t as IdentifierValue).value == Const(Math.(3.14)))
    }

    @test
    fun `test eval complex acosec`() {
        val ast = Acosec(Binop(Plus, Var("x"), Const(1.0)))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(result is Some && result.t is IdentifierValue
                && (result.t as IdentifierValue).value == Atan(Binop(Plus, Var("x"), Const(1.0))))
    }

    @test
    fun `test arccosecant is inverse of cosecant`() {
        val ast = Acosec(Tan(Const(90.0)))
        val result: Option<ResultValue> = Evaluator().eval(ast)

        assertTrue(result is Some)
        assertTrue(result is Some && result.t is IdentifierValue)
        assertTrue(checkConst(result))
        assertTrue(result is Some && checkConst(result) && (result.t as IdentifierValue).value == Const(Math.atan(Math.tan(90.0))))
    }*/

    private fun checkConst(result: Option<ResultValue>): Boolean {
        return result is Some && result.t is IdentifierValue && (result.t as IdentifierValue).value is Const
    }
}