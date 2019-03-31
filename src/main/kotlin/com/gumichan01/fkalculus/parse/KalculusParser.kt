package com.gumichan01.fkalculus.parse

import com.github.h0tk3y.betterParse.combinators.and
import com.github.h0tk3y.betterParse.combinators.leftAssociative
import com.github.h0tk3y.betterParse.combinators.or
import com.github.h0tk3y.betterParse.combinators.use
import com.github.h0tk3y.betterParse.grammar.Grammar
import com.github.h0tk3y.betterParse.grammar.parseToEnd
import com.github.h0tk3y.betterParse.lexer.TokenMatch
import com.gumichan01.fkalculus.ast.*
import com.gumichan01.fkalculus.util.None
import com.gumichan01.fkalculus.util.Option
import com.gumichan01.fkalculus.util.Some

class KalculusParser {

    val fkalculusGrammar = object : Grammar<FKalculusAST>() {

        /** Tokens */
        // Keywords
        val pi by token("Pi|pi|\u03C0")
        val e by token("e")

        // Basic tokens
        val identifier by token("v[0-9]+")
        val positiveInteger by token("[0-9]+")
        val lowercaseLetter by token("[a-z]")

        // Math tokens
        val plus by token("\\+")
        val minus by token("-")
        val whitespace by token("\\s+", ignore = true)

        /** Rules */
        val piParser by pi use { Pi }
        val eParser by e use { Exp1 }
        val identifierParser by identifier use { Identifier(text) }
        val positiveIntegerParser by positiveInteger use { Const(text.toDouble()) }
        val negativeIntegerParser by (minus and positiveInteger) use { Const(-t2.text.toDouble()) }
        val integerParser by positiveIntegerParser or negativeIntegerParser
        val variableParser by lowercaseLetter use { Var(text) }
        val termexpr by piParser or eParser or identifierParser or integerParser or variableParser

        val sumDiffParser by leftAssociative(termexpr, plus or minus) { left, op, right -> Binop(getOpAST(op), left, right) }

        private fun getOpAST(op: TokenMatch): Operator {
            return when (op.type) {
                plus -> Plus
                minus -> Minus
                else -> throw RuntimeException("Internal error in parser: invalid operator: " + op)
            }
        }

        val expr by sumDiffParser
        override val rootParser by expr
    }

    fun parse(text: String): Option<FKalculusAST> {
        return try {
            Some(fkalculusGrammar.parseToEnd(text))
        } catch (e: Exception) {
            println(e)
            e.printStackTrace()
            None
        }
    }
}