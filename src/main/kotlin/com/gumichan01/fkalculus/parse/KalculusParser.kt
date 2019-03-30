package com.gumichan01.fkalculus.parse

import com.github.h0tk3y.betterParse.combinators.*
import com.github.h0tk3y.betterParse.grammar.Grammar
import com.github.h0tk3y.betterParse.grammar.parseToEnd
import com.github.h0tk3y.betterParse.grammar.parser
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
        val integer by token("-?[0-9]+")
        val lowercaseLetter by token("[a-z]")
        val plus by token("\\+")
        val whitespace by token("\\s+", ignore = true)

        /** Rules */
        val piParser by pi use { Pi }
        val eParser by e use { Exp1 }
        val identifierParser by identifier use { Identifier(text) }
        val integerParser by integer use { Const(text.toDouble()) }
        val variableParser by lowercaseLetter use { Var(text) }
        val termexpr by piParser or eParser or identifierParser or integerParser or variableParser

        val plusParser by leftAssociative(termexpr, plus) { left, _, right -> Binop(Plus, left, right) }

        val expr by plusParser
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