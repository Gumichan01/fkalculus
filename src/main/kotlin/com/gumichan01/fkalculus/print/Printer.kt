package com.gumichan01.fkalculus.print

import com.gumichan01.fkalculus.ast.*

class Printer {

    fun print(result: ResultValue): Unit {
        when (result) {
            is HelpText -> println(result.text)
            is IdentifierValue -> result.run {
                println("$identifier = " + stringOf(value))
            }
        }
    }

    fun print(expression: Expression): Unit {
        println(stringOf(expression))
    }

    private fun stringOf(expression: Expression): String {
        return when (expression) {
            is Pi -> "π"
            is Exp1 -> "e"
            is Const -> expression.value.toString()
            is Var -> expression.variable
            is Identifier -> expression.name
            // TODO Deal with binop operations inside operations
            is Binop -> stringOf(expression.expr1) + " " + stringOf(expression.operator) + " " + stringOf(expression.expr2)
            is Sqrt -> "√(" + stringOf(expression.expr) + ")"
            is Expo -> "e(" + stringOf(expression.expr) + ")"
            is Ln -> "ln(" + stringOf(expression.expr) + ")"
            is Log10 -> "lg(" + stringOf(expression.expr) + ")"
            is Log2 -> "lb(" + stringOf(expression.expr) + ")"
            else -> TODO("Print $expression not implemented")
        }
    }

    private fun stringOf(operator: Operator): String {
        return when (operator) {
            is Plus -> "+"
            is Minus -> "-"
            is Mult -> "*"
            is Div -> "/"
            is Pow -> "^"
        }
    }
}