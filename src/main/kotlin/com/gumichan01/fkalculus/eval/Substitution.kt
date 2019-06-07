package com.gumichan01.fkalculus.eval

import com.gumichan01.fkalculus.ast.*
import com.gumichan01.fkalculus.print.Printer
import com.gumichan01.fkalculus.util.SimpleKlogger

class Substitution(private val env: Environment, private val verbose: Boolean) {

    fun subst(substitution: Subst): Expression {
        return substitution.run {
            substitute(expr, variable, expr1)
        }
    }

    private fun substitute(expr: Expression, variable: String, expr1: Expression): Expression {
        val textExpression = Printer().stringOf(expr)
        val replacementExpression = Printer().stringOf(expr1)
        printStep("replace every occurrences of $variable in $textExpression with $replacementExpression")
        return when (expr) {
            is Pi, is Exp1, is Const -> expr
            is Identifier -> {
                printStep("replace ${expr.name} by its value")
                substitute(env.find(expr.name), variable, expr1)
            }
            is Var -> {
                printStep("replace ${expr.variable} by its value")
                if (expr.variable == variable) expr1 else expr
            }
            is Binop -> {
                val e1 = substitute(expr.expr1, variable, expr1)
                val e2 = substitute(expr.expr2, variable, expr1)
                Binop(expr.operator, e1, e2)
            }
            is Sqrt -> Sqrt(substitute(expr.expr, variable, expr1))
            is Expo -> Expo(substitute(expr.expr, variable, expr1))
            is Ln -> Ln(substitute(expr.expr, variable, expr1))
            is Log10 -> Log10(substitute(expr.expr, variable, expr1))
            is Log2 -> Log2(substitute(expr.expr, variable, expr1))
            is Cos -> Cos(substitute(expr.expr, variable, expr1))
            is Sin -> Sin(substitute(expr.expr, variable, expr1))
            is Tan -> Tan(substitute(expr.expr, variable, expr1))
            is Acos -> Acos(substitute(expr.expr, variable, expr1))
            is Asin -> Asin(substitute(expr.expr, variable, expr1))
            is Atan -> Atan(substitute(expr.expr, variable, expr1))
            is Sec -> Sec(substitute(expr.expr, variable, expr1))
            is Cosec -> Cosec(substitute(expr.expr, variable, expr1))
            is Cotan -> Cotan(substitute(expr.expr, variable, expr1))
            is Asec -> Asec(substitute(expr.expr, variable, expr1))
            is Acosec -> Acosec(substitute(expr.expr, variable, expr1))
            is Acotan -> Acotan(substitute(expr.expr, variable, expr1))
        }
    }

    private fun printStep(message: String) {
        SimpleKlogger(verbose).print("substitution step: " + message)
    }
}

