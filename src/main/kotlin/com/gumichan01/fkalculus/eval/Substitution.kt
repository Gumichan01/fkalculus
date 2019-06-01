package com.gumichan01.fkalculus.eval

import com.gumichan01.fkalculus.ast.Const
import com.gumichan01.fkalculus.ast.Expression
import com.gumichan01.fkalculus.ast.Subst
import com.gumichan01.fkalculus.ast.Var

class Substitution {
    fun subst(substitution: Subst): Expression {
        return substitution.run {
            if (expr is Var && expr.variable == variable) {
                expr1
            } else {
                expr
            }
        }
    }

}
