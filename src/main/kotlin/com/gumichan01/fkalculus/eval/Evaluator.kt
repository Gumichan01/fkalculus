package com.gumichan01.fkalculus.eval

import com.gumichan01.fkalculus.ast.*
import com.gumichan01.fkalculus.util.DivisionByZeroException
import com.gumichan01.fkalculus.util.SimpleKlogger
import kotlin.math.*

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

class Evaluator(private val environment: Environment, private val verbose: Boolean) {

    fun calculate(expression: Expression): Expression {
        val logger = SimpleKlogger(verbose)

        if (expression !is Const) {
            logger.print("=> Calculate ${stringOf(expression)}")
        }

        val result = when (expression) {
            is Const, is Var -> expression
            is Pi -> Const(PI)
            is Exp1 -> Const(E)
            is Binop -> evaluateBinop(expression)
            is Sqrt -> evaluateSqrt(expression)
            is Expo -> evaluateExpo(expression)
            is Ln -> evaluateLn(expression)
            is Log10 -> evaluateLog10(expression)
            is Log2 -> evaluateLog2(expression)
            is Cos -> evaluateCosine(expression)
            is Sin -> evaluateSine(expression)
            is Tan -> evaluateTan(expression)
            is Asin -> evaluateAsin(expression)
            is Acos -> evaluateAcos(expression)
            is Atan -> evaluateAtan(expression)
            is Cosec -> evaluateCosec(expression)
            is Sec -> evaluateSec(expression)
            is Cotan -> evaluateCotan(expression)
            is Acosec -> evaluateAcosec(expression)
            is Asec -> evaluateAsec(expression)
            is Acotan -> evaluateAcotan(expression)
            is Identifier -> evaluateIdentifier(expression)
        }

        if (expression !is Const) {
            logger.print("=> ${stringOf(expression)} = ${stringOf(result)}")
        }
        return result
    }

    private fun evaluateIdentifier(identifier: Identifier): Expression {
        return calculate(environment.find(identifier.name))
    }

    private fun evaluateAcotan(arccotanFunCall: Acotan): Expression {
        return arccotanFunCall.run {
            val result = calculate(expr)
            if (result is Const) {
                Const(arccotan(result.value))
            } else {
                Acotan(result)
            }
        }
    }

    private fun evaluateAsec(arcsecantFunCall: Asec): Expression {
        return arcsecantFunCall.run {
            when (expr) {
                Const(1.0) -> Const(0.0)
                Const(-1.0) -> Pi
                else -> {
                    val result = calculate(expr)
                    if (result is Const) {
                        Const(arcsecant(result.value))
                    } else {
                        Asec(result)
                    }
                }
            }
        }
    }

    private fun evaluateAcosec(arccosecantFunCall: Acosec): Expression {
        return arccosecantFunCall.run {
            val result = calculate(expr)
            if (result is Const) {
                Const(arccosecant(result.value))
            } else {
                Acosec(result)
            }
        }
    }

    private fun arccotan(value: Double): Double = atan(1.0 / value)

    private fun arcsecant(value: Double): Double {
        val domain = -1.0..1.0
        if (value in domain) {
            throw RuntimeException("$value not in domain ]-∞, -1.0] ∪ [1.0, +∞[")
        }
        return acos(1.0 / value)
    }

    private fun arccosecant(value: Double): Double {
        if (-1.0 < value && value < 1.0) {
            throw RuntimeException("$value not in domain ]-∞, -1.0] ∪ [1.0, +∞[")
        }
        return asin(1.0 / value)
    }

    private fun evaluateCotan(cotangentFunCall: Cotan): Expression {
        return cotangentFunCall.run {
            val result = calculate(expr)
            if (result is Const) {
                Const(cotan(result.value))
            } else {
                Cotan(result)
            }
        }
    }

    private fun evaluateSec(secantFunCall: Sec): Expression {
        return secantFunCall.run {
            val result = calculate(expr)
            if (result is Const) {
                Const(secant(result.value))
            } else {
                Sec(result)
            }
        }
    }

    private fun evaluateCosec(cosecantFunCall: Cosec): Expression {
        return cosecantFunCall.run {
            val result = calculate(expr)
            if (result is Const) {
                Const(cosecant(result.value))
            } else {
                Cosec(result)
            }
        }
    }

    private fun cosecant(value: Double): Double {
        val sinValue = sin(value)
        checkNotZero(sinValue)
        return 1.0 / sinValue
    }

    private fun secant(value: Double): Double {
        val cosValue = cos(value)
        checkNotZero(cosValue)
        return 1.0 / cosValue
    }

    private fun cotan(value: Double): Double {
        val tanValue = tan(value)
        checkNotZero(tanValue)
        return 1.0 / tanValue
    }

    private fun checkNotZero(value: Double) {
        val epsilon = 10e-15
        if (abs(value - 0.0) < epsilon) {
            throw DivisionByZeroException()
        }
    }

    private fun evaluateAtan(arctanFunCall: Atan): Expression {
        return arctanFunCall.run {
            val result = calculate(expr)
            if (result is Const) {
                Const(atan(result.value))
            } else {
                Atan(result)
            }
        }
    }

    private fun evaluateAcos(arccosFunCall: Acos): Expression {
        return arccosFunCall.run {
            val result = calculate(expr)
            if (result is Const) {
                checkCosineArgumentOrFail(result.value)
                Const(acos(result.value))
            } else {
                Acos(result)
            }
        }
    }

    private fun evaluateAsin(arcsinFunCall: Asin): Expression {
        return arcsinFunCall.run {
            val result = calculate(expr)
            if (result is Const) {
                checkSineArgumentOrFail(result.value)
                Const(asin(result.value))
            } else {
                Asin(result)
            }
        }
    }

    private fun checkSineArgumentOrFail(value: Double) {
        val domain = -1.0..1.0
        if (value !in domain) {
            throw RuntimeException("$value not in domain [-1.0, 1.0]")
        }
    }

    private fun checkCosineArgumentOrFail(value: Double) = checkSineArgumentOrFail(value)

    private fun evaluateTan(tangentFunCall: Tan): Expression {
        return tangentFunCall.run {
            val result = calculate(expr)
            if (result is Const) {
                Const(tan(result.value))
            } else {
                Tan(result)
            }
        }
    }

    private fun evaluateSine(sineFunCall: Sin): Expression {
        return sineFunCall.run {

            // The value of Pi is an approximative value, so the result of sin(Pi) may not be 0
            if (expr is Pi) {
                Const(0.0)
            } else {
                val result = calculate(expr)
                if (result is Const) {
                    Const(sin(result.value))
                } else {
                    Sin(result)
                }
            }
        }
    }

    private fun evaluateCosine(cosineFunCall: Cos): Expression {
        return cosineFunCall.run {
            val ninetyDegreesInRadian = Binop(Div, Pi, Const(2.0))

            // The value of Pi/2 is an approximative value, so the result of sin(Pi/2) may not be 0
            if (expr == ninetyDegreesInRadian) {
                Const(0.0)
            } else {
                val result = calculate(expr)
                if (result is Const) {
                    Const(cos(result.value))
                } else {
                    Cos(result)
                }
            }
        }
    }

    private fun checkLogarithmArgumentOrFail(const: Const) {
        if (const.value < 0.0) {
            throw RuntimeException("${const.value} < 0")
        }
    }

    private fun evaluateLog2(binaryLogFunCall: Log2): Expression {
        return binaryLogFunCall.run {
            val result = calculate(expr)
            if (result is Const) {
                checkLogarithmArgumentOrFail(result)
                Const(log2(result.value))
            } else {
                Log2(result)
            }
        }
    }

    private fun evaluateLog10(decimalLogFunCall: Log10): Expression {
        return decimalLogFunCall.run {
            val result = calculate(expr)
            if (result is Const) {
                checkLogarithmArgumentOrFail(result)
                Const(log10(result.value))
            } else {
                Log10(result)
            }
        }
    }

    private fun evaluateLn(naturalLogFunCall: Ln): Expression {
        return naturalLogFunCall.run {
            val result = calculate(expr)
            if (result is Const) {
                checkLogarithmArgumentOrFail(result)
                Const(ln(result.value))
            } else {
                Ln(result)
            }
        }
    }

    private fun evaluateExpo(expFunCall: Expo): Expression {
        return expFunCall.run {
            val result = calculate(expr)
            if (result is Const) {
                Const(exp(result.value))
            } else {
                Expo(result)
            }
        }
    }

    private fun evaluateSqrt(sqrtFunCall: Sqrt): Expression {
        return sqrtFunCall.run {
            val result = calculate(this.expr)
            if (result is Const) {
                if (result.value > 0.0) {
                    Const(sqrt(result.value))
                } else {
                    throw RuntimeException("Invalid calculus: √${result.value}")
                }
            } else {
                Sqrt(result)
            }
        }
    }

    private fun evaluateBinop(binop: Binop): Expression {
        return binop.run {
            val value1 = calculate(expr1)
            val value2 = calculate(expr2)
            if (value1 is Const && value2 is Const) {
                applyBinop(operator, value1, value2)
            } else {
                Binop(operator, value1, value2)
            }
        }
    }

    private fun applyBinop(operator: Operator, const1: Const, const2: Const): Const {
        return Const(when (operator) {
            Plus -> const1.value + const2.value
            Minus -> const1.value - const2.value
            Mult -> const1.value * const2.value
            Div -> {
                if (const2.value != 0.0) {
                    const1.value / const2.value
                } else {
                    throw DivisionByZeroException()
                }
            }
            Pow -> const1.value.pow(const2.value)
        })
    }
}