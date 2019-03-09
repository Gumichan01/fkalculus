package com.gumichan01.fkalculus.ast

// Abstract Syntax Tree (AST) of expressions

sealed class Expression
object Pi : Expression()
object exp1 : Expression()
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
