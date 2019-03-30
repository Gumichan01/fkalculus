package com.gumichan01.fkalculus.parse

import com.github.h0tk3y.betterParse.combinators.use
import com.github.h0tk3y.betterParse.grammar.Grammar
import com.github.h0tk3y.betterParse.grammar.parseToEnd
import com.gumichan01.fkalculus.ast.FKalculusAST
import com.gumichan01.fkalculus.ast.Pi
import com.gumichan01.fkalculus.util.None
import com.gumichan01.fkalculus.util.Option
import com.gumichan01.fkalculus.util.Some

class KalculusParser {

    val fkalculusGrammar = object : Grammar<FKalculusAST>() {

        // Tokens
        val pi by token("Pi|pi")

        // Rules
        val piRule by pi use { Pi }

        override val rootParser by piRule
    }

    fun parse(text: String): Option<FKalculusAST> {
        return try {
            Some(fkalculusGrammar.parseToEnd(text))
        } catch (e: Exception) {
            None
        }
    }
}