
# FKalculus Grammar (WIP) #

This file describes the FKalculus language.

## Tokens ##

```
lowercase_alpha ≡ [a-z]
cmd             ≡ (lowercase_alpha)+
number          ≡ [0-9]+
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
PI      : "Pi" | "pi" | 'π'       // TODO unicode charactere for Pi
EXP     : 'e'
SQRT    : "sqrt" | "√"      // TODO unicode charactere for square root
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


```

<a rel="license" href="http://creativecommons.org/licenses/by-sa/4.0/">
    <img alt="Creative Commons License" style="border-width:0" src="https://i.creativecommons.org/l/by-sa/4.0/88x31.png" />
</a><br/>This work is licensed under a
<a rel="license" href="http://creativecommons.org/licenses/by-sa/4.0/">
Creative Commons Attribution-ShareAlike 4.0 International License
</a>.
