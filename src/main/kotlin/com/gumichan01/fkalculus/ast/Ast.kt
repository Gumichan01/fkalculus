package com.gumichan01.fkalculus.ast

// Abstract Syntax Tree (AST) of commands and expressions

sealed class FKalculusAST
object Help: FKalculusAST()
sealed class Instruction : FKalculusAST()
data class Eval(val expr: Expression) : Instruction()
data class Simpl(val expr: Expression) : Instruction()
data class Subst(val expr: Expression, val variable: String, val expr1: Expression) : Instruction()
data class Solve(val expr: Expression, val variable: String) : Instruction()
data class Derive(val expr: Expression, val variable: String) : Instruction()
data class Integ(val expr: Expression, val variable: String, val min: Expression, val max: Expression) : Instruction()

sealed class Expression : FKalculusAST()
object Pi : Expression()
object Exp1 : Expression()
data class Const(val value: Double) : Expression()
data class Var(val variable: String) : Expression()
data class Binop(val operator: Operator, val expr1: Expression, val expr2: Expression) : Expression()
data class Sqrt(val expr: Expression) : Expression()
data class Expo(val expr: Expression) : Expression()
data class Ln(val expr: Expression) : Expression()     /* Natural Logarithm */
data class Log10(val expr: Expression) : Expression()
data class Log2(val expr: Expression) : Expression()
data class Sin(val expr: Expression) : Expression()
data class Cos(val expr: Expression) : Expression()
data class Tan(val expr: Expression) : Expression()
data class Asin(val expr: Expression) : Expression()
data class Acos(val expr: Expression) : Expression()
data class Atan(val expr: Expression) : Expression()
data class Sec(val expr: Expression) : Expression()
data class Cosec(val expr: Expression) : Expression()
data class Cotan(val expr: Expression) : Expression()
data class Asec(val expr: Expression) : Expression()
data class Acosec(val expr: Expression) : Expression()
data class Acotan(val expr: Expression) : Expression()

sealed class Operator
object Plus : Operator()
object Minus : Operator()
object Mult : Operator()
object Div : Operator()
object Pow : Operator()
