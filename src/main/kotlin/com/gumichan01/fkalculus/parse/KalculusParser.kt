package com.gumichan01.fkalculus.parse

import com.github.h0tk3y.betterParse.combinators.*
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
        val lparen by token("\\(")
        val rparen by token("\\)")
        val whitespace by token("\\s+", ignore = true)

        // Math tokens
        val plus by token("\\+")
        val minus by token("-")
        val mult by token("\\*")
        val div by token("/")
        val pow by token("\\^")

        /** Rules */
        val piParser by pi use { Pi }
        val eParser by e use { Exp1 }
        val identifierParser by identifier use { Identifier(text) }
        val positiveIntegerParser by positiveInteger use { Const(text.toDouble()) }
        val negativeIntegerParser by (skip(minus) and positiveInteger) use { Const(-text.toDouble()) }
        val variableParser by lowercaseLetter use { Var(text) }
        val integerParser by positiveIntegerParser or negativeIntegerParser
        val simpleExpr by piParser or eParser or identifierParser or integerParser or variableParser

        val powParser by leftAssociative(simpleExpr, pow) { left, _, right -> Binop(Pow, left, right) }
        val multParser by leftAssociative(powParser, mult) { left, _, right -> Binop(Mult, left, right) }
        val divParser by leftAssociative(multParser, div) { left, _, right -> Binop(Div, left, right) }
        val sumDiffParser by leftAssociative(divParser, plus or minus) { left, op, right -> Binop(produceOperator(op), left, right) }
        val arithmmeticParser by sumDiffParser

        private fun produceOperator(op: TokenMatch): Operator {
            return when (op.type) {
                plus -> Plus
                minus -> Minus
                else -> throw RuntimeException("Internal error in parser: invalid operator: " + op)
            }
        }

        val expr by arithmmeticParser
        val parenExpr by skip(lparen) and expr and skip(rparen)
        override val rootParser by expr or parenExpr
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