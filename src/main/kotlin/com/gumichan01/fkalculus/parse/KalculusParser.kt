package com.gumichan01.fkalculus.parse

import com.github.h0tk3y.betterParse.combinators.or
import com.github.h0tk3y.betterParse.combinators.use
import com.github.h0tk3y.betterParse.grammar.Grammar
import com.github.h0tk3y.betterParse.grammar.parseToEnd
import com.gumichan01.fkalculus.ast.*
import com.gumichan01.fkalculus.util.None
import com.gumichan01.fkalculus.util.Option
import com.gumichan01.fkalculus.util.Some

class KalculusParser {

    val fkalculusGrammar = object : Grammar<FKalculusAST>() {

        // Tokens
        val pi by token("Pi|pi|\u03C0")
        val e by token("e")
        val identifier by token("v[0-9]+")
        val integer by token("[-+]?[0-9]+")

        // Rules
        val piRule by pi use { Pi }
        val eRule by e use { Exp1 }
        val identifierRule by identifier use { Identifier(text) }
        val integerRule by integer use { Const(text.toDouble()) }

        override val rootParser by piRule or eRule or identifierRule or integerRule
    }

    fun parse(text: String): Option<FKalculusAST> {
        return try {
            Some(fkalculusGrammar.parseToEnd(text))
        } catch (e: Exception) {
            None
        }
    }
}