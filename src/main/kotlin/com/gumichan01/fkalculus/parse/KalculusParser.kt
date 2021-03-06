package com.gumichan01.fkalculus.parse

import com.github.h0tk3y.betterParse.combinators.*
import com.github.h0tk3y.betterParse.grammar.Grammar
import com.github.h0tk3y.betterParse.grammar.parseToEnd
import com.github.h0tk3y.betterParse.grammar.parser
import com.github.h0tk3y.betterParse.lexer.TokenMatch
import com.github.h0tk3y.betterParse.parser.Parser
import com.github.h0tk3y.betterParse.utils.Tuple3
import com.gumichan01.fkalculus.ast.*
import com.gumichan01.fkalculus.util.None
import com.gumichan01.fkalculus.util.Option
import com.gumichan01.fkalculus.util.SimpleKlogger
import com.gumichan01.fkalculus.util.Some

/**
Copyright, Luxon JEAN-PIERRE (2019)

luxon.jean.pierre@gmail.com

This software is a computer program whose purpose is to [describe
functionalities and technical features of your software].

This software is governed by the CeCILL license under French law and
abiding by the rules of distribution of free software.  You can  use,
modify and/ or redistribute the software under the terms of the CeCILL
license as circulated by CEA, CNRS and INRIA at the following URL
"http://www.cecill.info".

As a counterpart to the access to the source code and  rights to copy,
modify and redistribute granted by the license, users are provided only
with a limited warranty  and the software's author,  the holder of the
economic rights,  and the successive licensors  have only  limited
liability.

In this respect, the user's attention is drawn to the risks associated
with loading,  using,  modifying and/or developing or reproducing the
software by the user in light of its specific status of free software,
that may mean  that it is complicated to manipulate,  and  that  also
therefore means  that it is reserved for developers  and  experienced
professionals having in-depth computer knowledge. Users are therefore
encouraged to load and test the software's suitability as regards their
requirements in conditions enabling the security of their systems and/or
data to be ensured and,  more generally, to use and operate it in the
same conditions as regards security.

The fact that you are presently reading this means that you have had
knowledge of the CeCILL license and that you accept its terms.
 */

class KalculusParser(private val verbose: Boolean) {

    private val fkalculusGrammar = object : Grammar<FKalculusAST>() {

        /** Tokens */
        // Keywords
        val help by token("help")
        val subst by token("subst")
        val simpl by token("simpl")
        val pi by token("Pi|pi|\u03C0")
        val sqrt by token("sqrt|√")
        val expo by token("exp")
        val ln by token("ln")
        val log10 by token("log10|lg")
        val log2 by token("log2|lb")
        val arcsecant by token("arcsec|asec")
        val arccosecant by token("arccosec|arccsc|acsc")
        val arccotangent by token("arccotan|arccot|acotan")
        val arcsin by token("arcsin|asin")
        val arccos by token("arccos|acos")
        val arctan by token("arctan|atan")
        val secant by token("sec")
        val cosecant by token("cosec|csc")
        val cotangent by token("cotan|cot")
        val sine by token("sin")
        val cosine by token("cos")
        val tangent by token("tan")
        val e by token("e")

        // Basic tokens
        val lparen by token("\\(")
        val rparen by token("\\)")
        val comma by token(",")
        val identifier by token("v[0-9]+")
        val positiveInteger by token("[0-9]+")
        val lowercaseLetter by token("[a-z]")
        val whitespace by token("\\s+", ignore = true)

        // Math tokens
        val pow by token("\\^")
        val mult by token("\\*")
        val div by token("/")
        val minus by token("-")
        val plus by token("\\+")

        /** Rules */
        val helpParser by help use { Help }
        val piParser by pi use { Pi }
        val eParser by e use { Exp1 }
        val identifierParser by identifier use { Identifier(text) }
        val positiveIntegerParser by positiveInteger use { Const(text.toDouble()) }
        val negativeIntegerParser by skip(minus) and positiveInteger use { Const(-text.toDouble()) }
        val variableParser by lowercaseLetter use { Var(text) }
        val integerParser by positiveIntegerParser or negativeIntegerParser
        val simpleExpr by piParser or eParser or identifierParser or integerParser or variableParser

        val logarithmFun by ln or log10 or log2
        val arcusFun by arcsin or arccos or arctan or arcsecant or arccosecant or arccotangent
        val trigoFun by sine or cosine or tangent or secant or cosecant or cotangent
        val mathFun by sqrt or expo or logarithmFun or trigoFun or arcusFun
        val highPriorityexpressionRule by skip(lparen) and parser { expr } and skip(rparen)
        val term: Parser<Expression> by simpleExpr or highPriorityexpressionRule
        val funCall: Parser<Expression> by mathFun and highPriorityexpressionRule use { produceFunCall(t1, t2) }
        val termOrSqrt by term or funCall

        val powParser by leftAssociative(termOrSqrt, pow) { left, op, right -> produceBinop(op, left, right) }
        val multParser by leftAssociative(powParser, mult) { left, op, right -> produceBinop(op, left, right) }
        val divParser by leftAssociative(multParser, div) { left, op, right -> produceBinop(op, left, right) }
        val sumDiffParser by leftAssociative(divParser, plus or minus) { left, op, right -> produceBinop(op, left, right) }
        val arithmmeticParser by sumDiffParser

        val substParameter by arithmmeticParser and skip(comma) and lowercaseLetter and skip(comma) and arithmmeticParser
        val substParser by subst and skip(lparen) and substParameter and skip(rparen) use { produceSubstFunCall(t2) }
        val simplParser by simpl and skip(lparen) and arithmmeticParser and skip(rparen) use { Simpl(t2) }
        val commandParser by helpParser or substParser or simplParser

        private fun produceSubstFunCall(substParam: Tuple3<Expression, TokenMatch, Expression>): Command {
            return Subst(substParam.t1, substParam.t2.text, substParam.t3)
        }

        private fun produceFunCall(function: TokenMatch, argument: Expression): Expression {
            return when (function.type) {
                sqrt -> Sqrt(argument)
                expo -> Expo(argument)
                ln -> Ln(argument)
                log10 -> Log10(argument)
                log2 -> Log2(argument)
                sine -> Sin(argument)
                cosine -> Cos(argument)
                tangent -> Tan(argument)
                arcsin -> Asin(argument)
                arccos -> Acos(argument)
                arctan -> Atan(argument)
                secant -> Sec(argument)
                cosecant -> Cosec(argument)
                cotangent -> Cotan(argument)
                arcsecant -> Asec(argument)
                arccosecant -> Acosec(argument)
                arccotangent -> Acotan(argument)
                else -> throw RuntimeException("Invalid function : $function")
            }
        }

        private fun produceBinop(op: TokenMatch, left: Expression, right: Expression): Binop {
            return Binop(produceOperator(op), left, right)
        }

        private fun produceOperator(op: TokenMatch): Operator {
            return when (op.type) {
                plus -> Plus
                minus -> Minus
                mult -> Mult
                div -> Div
                pow -> Pow
                else -> throw RuntimeException("Invalid operator: $op")
            }
        }

        val expr by arithmmeticParser
        override val rootParser by commandParser or expr
    }

    fun parse(text: String): Option<FKalculusAST> {
        return try {
            Some(fkalculusGrammar.parseToEnd(text))
        } catch (e: Exception) {
            println(e.message)
            SimpleKlogger(verbose).print(e)
            None
        }
    }
}