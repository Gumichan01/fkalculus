package com.gumichan01.fkalculus.eval

import com.gumichan01.fkalculus.ast.*
import com.gumichan01.fkalculus.util.None
import com.gumichan01.fkalculus.util.Option
import com.gumichan01.fkalculus.util.Some

class Normalizer {

    private var count: Int = 0
    private var environment: List<Pair<String, Expression>> = emptyList()

    fun eval(ast: FKalculusAST): Option<ResultValue> {
        return try {
            Some(evalAST(ast))
        } catch (e: RuntimeException) {
            println("Failed to evaluate the following instruction: ${e.message}")
            None
        }
    }

    private fun evalAST(ast: FKalculusAST): ResultValue {
        if (ast is Help) {
            return HelpText(getHelpText())
        }

        val resultExpression = when (ast) {
            is Subst -> Substitution().subst(ast)
            is Expression -> Evaluator(environment).calculate(ast)
            else -> throw UnsupportedOperationException("Not implemented yet")
        }

        val freshId = freshIdentifier()
        environment = (environment + Pair(freshId, resultExpression))
        return IdentifierValue(freshId, resultExpression)
    }

    private fun freshIdentifier(): String {
        return "v" + count++
    }

    private fun getHelpText(): String {
        return """FKalculus v0.1.0-SNAPSHOT
            |
            |Type an expression in order to evaluate it
            |- Example: 2 + 3
            |
            |
            |Terms and math function:
            |
            |Pi | pi | π    Pi number
            |e              e number, exp(1) = e
            |
            |
            |List of mathematical functions with their associated keywords:
            |
            |sqrt | √                   Square root
            |exp                        Exponential function
            |ln                         Natural logarithm
            |log10 | lg                 Decimal logarithm
            |log2 | lb                  Binary logarithm
            |sin                        Sine
            |cos                        Cosine
            |tan                        Tangent
            |arcsin | asin              Arcsine
            |arccos | acos              Arccosine
            |arctan |atan               Arctangent
            |sec                        Secant
            |cosec | csc                Cosecant
            |cotan | cot                Cotangent
            |arcsec | asec              Arcsecant
            |arccosec | arccsc | acsc   Arccosecant
            |arccotan | arccot | acotan Arccotangent
            |
            |
            |
            |CTRL-C: Exit the program.
        """.trimMargin()
    }
}