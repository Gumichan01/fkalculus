package com.gumichan01.fkalculus.ast

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

// Abstract Syntax Tree (AST) of commands and expressions

sealed class FKalculusAST

sealed class Command : FKalculusAST()
object Help : Command()
data class Simpl(val expr: Expression) : Command()
data class Subst(val expr: Expression, val variable: String, val expr1: Expression) : Command()
data class Solve(val expr: Expression, val variable: String) : Command()
data class Derive(val expr: Expression, val variable: String) : Command()
data class Integ(val expr: Expression, val variable: String, val min: Expression, val max: Expression) : Command()
sealed class Expression : FKalculusAST()

object Pi : Expression()
object Exp1 : Expression()
data class Const(val value: Double) : Expression()
data class Var(val variable: String) : Expression()
data class Identifier(val name: String) : Expression()
data class Binop(val operator: Operator, val expr1: Expression, val expr2: Expression) : Expression()
sealed class FunCall : Expression() {
    abstract val expr: Expression
}

data class Sqrt(override val expr: Expression) : FunCall()
data class Expo(override val expr: Expression) : FunCall()
/* Natural Logarithm */
data class Ln(override val expr: Expression) : FunCall()

/* ∀x > 0, y ∈ ℝ, log₁₀(x) = y ↔ 10ʸ = x; ISO 80000-2 notation: lg */
data class Log10(override val expr: Expression) : FunCall()

/* ∀x > 0, y ∈ ℝ, log₂(x) = y ↔ 2ʸ = x; ISO 80000-2 notation: lb */
data class Log2(override val expr: Expression) : FunCall()

data class Sin(override val expr: Expression) : FunCall()
data class Cos(override val expr: Expression) : FunCall()
data class Tan(override val expr: Expression) : FunCall()

/* Every A{cos/sine/tan} are inverse functions of cos/sin/tan */
data class Asin(override val expr: Expression) : FunCall()

data class Acos(override val expr: Expression) : FunCall()
data class Atan(override val expr: Expression) : FunCall()
data class Sec(override val expr: Expression) : FunCall()     /* Reciprocal function of Cosine */
data class Cosec(override val expr: Expression) : FunCall()   /* Reciprocal function of Sine */
data class Cotan(override val expr: Expression) : FunCall()   /* Reciprocal function of Tan */
data class Asec(override val expr: Expression) : FunCall()    /* Every A{sec/cosec/cottan} are inverse functions of sec/cosec/cotan */
data class Acosec(override val expr: Expression) : FunCall()
data class Acotan(override val expr: Expression) : FunCall()

sealed class Operator
object Plus : Operator()
object Minus : Operator()
object Mult : Operator()
object Div : Operator()
object Pow : Operator()

sealed class ResultValue
data class HelpText(val text: String) : ResultValue()
data class IdentifierValue(val identifier: String, val value: Expression) : ResultValue()


fun stringOf(expression: Expression): String {
    return when (expression) {
        is Pi -> "π"
        is Exp1 -> "e"
        is Const -> expression.value.toString()
        is Var -> expression.variable
        is Identifier -> expression.name
        is Binop -> {
            val stringExpr1 = withParenthesisOrNot(expression.expr1, stringOf(expression.expr1))
            val stringExpr2 = withParenthesisOrNot(expression.expr2, stringOf(expression.expr2))
            stringExpr1 + stringOf(expression.operator) + stringExpr2
        }
        is Sqrt -> "sqrt(" + stringOf(expression.expr) + ")"
        is Expo -> "e(" + stringOf(expression.expr) + ")"
        is Ln -> "ln(" + stringOf(expression.expr) + ")"
        is Log10 -> "lg(" + stringOf(expression.expr) + ")"
        is Log2 -> "lb(" + stringOf(expression.expr) + ")"
        is Cos -> "cos(" + stringOf(expression.expr) + ")"
        is Sin -> "sin(" + stringOf(expression.expr) + ")"
        is Tan -> "tan(" + stringOf(expression.expr) + ")"
        is Acos -> "arccos(" + stringOf(expression.expr) + ")"
        is Asin -> "arcsin(" + stringOf(expression.expr) + ")"
        is Atan -> "arctan(" + stringOf(expression.expr) + ")"
        is Sec -> "sec(" + stringOf(expression.expr) + ")"
        is Cosec -> "csc(" + stringOf(expression.expr) + ")"
        is Cotan -> "cot(" + stringOf(expression.expr) + ")"
        is Asec -> "arcsec(" + stringOf(expression.expr) + ")"
        is Acosec -> "arccsc(" + stringOf(expression.expr) + ")"
        is Acotan -> "arccot(" + stringOf(expression.expr) + ")"
    }
}

private fun Operator.isSumOrDiff(): Boolean = this is Plus || this is Minus

private fun withParenthesisOrNot(expr: Expression, stringExpr: String): String {
    return if (expr is Binop && expr.operator.isSumOrDiff()) "($stringExpr)" else stringExpr
}

private fun stringOf(operator: Operator): String {
    return when (operator) {
        is Plus -> " + "
        is Minus -> " - "
        is Mult -> " * "
        is Div -> " / "
        is Pow -> "^"
    }
}