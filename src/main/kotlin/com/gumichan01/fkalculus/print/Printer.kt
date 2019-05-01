package com.gumichan01.fkalculus.print

import com.gumichan01.fkalculus.ast.*

class Printer {

    fun print(result: ResultValue) {
        when (result) {
            is HelpText -> println(result.text)
            is IdentifierValue -> result.run {
                println("$identifier = " + stringOf(value))
            }
        }
    }

    private fun stringOf(expression: Expression): String {
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
            is Sqrt -> "√(" + stringOf(expression.expr) + ")"
            is Expo -> "e(" + stringOf(expression.expr) + ")"
            is Ln -> "ln(" + stringOf(expression.expr) + ")"
            is Log10 -> "lg(" + stringOf(expression.expr) + ")"
            is Log2 -> "lb(" + stringOf(expression.expr) + ")"
            else -> TODO("Print $expression not implemented")
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
}