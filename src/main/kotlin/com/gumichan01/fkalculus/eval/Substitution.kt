package com.gumichan01.fkalculus.eval

import com.gumichan01.fkalculus.ast.*
import com.gumichan01.fkalculus.util.SimpleKlogger

class Substitution(private val env: Environment, private val verbose: Boolean) {

    fun subst(substitution: Subst): Expression {
        return substitution.run {
            val result = substitute(expr, variable, expr1)
            printStep("the result is ${stringOf(result)}")
            result
        }
    }

    private fun substitute(expr: Expression, variable: String, expr1: Expression): Expression {
        printStep("replace every occurrences of $variable in ${stringOf(expr)} with ${stringOf(expr1)}")
        return when (expr) {
            is Pi, is Exp1, is Const -> expr
            is Identifier -> {
                val value = env.find(expr.name)
                printStep("identifier lookup { ${expr.name} := ${stringOf(value)} }")
                substitute(value, variable, expr1)
            }
            is Var -> {
                if (expr.variable == variable) {
                    printStep("apply substitution { ${expr.variable} := ${stringOf(expr1)} }")
                    expr1
                } else expr
            }
            is Binop -> {
                printStep("check ${stringOf(expr.expr1)} and ${stringOf(expr.expr2)}")
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

