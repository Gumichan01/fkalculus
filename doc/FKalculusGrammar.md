
# FKalculus Grammar (WIP) #

This file describes the FKalculus language.

## Tokens ##

```
lowercase_alpha ≡ [a-z] \ {e}
integer         ≡ [0-9]+
double          ≡ (integer).(integer)
number          ≡ integer | double
newline         ≡ ('\r' | '\n' | '\r\n')

/* Tokens */

// Basic tokens
NL      : newline
COMMAND : cmd
LPAREN  : '('
RPAREN  : ')'
SPACE   : "\\s+"
PLUS    : '+'
MINUS   : '-'
STAR    : '*'
SLASH   : '/'
CIRCUM  : '^'

// Keywords
EVAL    : eval
SUBST   : subst
SIMPL   : simpl
SOLVE   : solve
DERIVE  : derive
INTEG   : integ
PI      : "Pi" | "pi" | 'π'     // TODO unicode character for Pi
EXP     : 'e'
SQRT    : "sqrt" | "√"          // TODO unicode character for square root
EXPON   : exp
LOGN    : "ln"
LOG10   : "lg" | "log10"
LOG2    : "lb" | "log2"
COS     : "cos"
SIN     : "sin"
TAN     : "tan"
ACOS    : "acos"
ASIN    : "asin"
ATAN    : "atan"
SEC     : "sec"
COSEC   : "cosec"
COTAN   : "cotan"
ASEC    : "sec"
ACOSEC  : "cosec"
ACOTAN  : "cotan"
```

## Grammar ##

```
instruction ::= command
    command ::= EVAL(expr)
              | SUBST(expr, lowercase_alpha, expr)  
              | SIMPL(expr)
              | SOLVE(expr, lowercase_alpha)
              | DERIVE(expr, lowercase_alpha)
              | INTEG(expr, lowercase_alpha, number, number)

       expr ::= PI | EXP
              | (expr)
              | expr PLUS expr
              | expr MINUS expr
              | expr MULT expr
              | expr DIV expr
              | expr POW expr
              | SQRT(expr)
              | LOGN(expr)
              | LOG10(expr)
              | LOG2(expr)
              | COS(expr)
              | SINE(expr)
              | TAN(expr)
              | ACOS(expr)
              | ASINE(expr)
              | ATAN(expr)
              | SEC(expr)
              | COSEC(expr)
              | COTAN(expr)
              | ASEC(expr)
              | ACOSEC(expr)
              | ACOTAN(expr)
```

<a rel="license" href="http://creativecommons.org/licenses/by-sa/4.0/">
    <img alt="Creative Commons License" style="border-width:0" src="https://i.creativecommons.org/l/by-sa/4.0/88x31.png" />
</a><br/>This work is licensed under a
<a rel="license" href="http://creativecommons.org/licenses/by-sa/4.0/">
Creative Commons Attribution-ShareAlike 4.0 International License
</a>.
