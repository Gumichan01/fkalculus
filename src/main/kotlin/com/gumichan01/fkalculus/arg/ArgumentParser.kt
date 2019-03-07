package com.gumichan01.fkalculus

import com.xenomachina.argparser.ArgParser
import com.xenomachina.argparser.default

class ArgumentParser {

    fun parse(args: Array<String>): Arguments {
        return ArgParser(args).parseInto(::ParsedArgs).run { Arguments(verbose, source, destination) }
    }
}

class ParsedArgs(kparser: ArgParser) {

    val verbose by kparser.flagging("-v", "--verbose", help = "enable verbose mode of instructions").default(false)
    val source by kparser.storing("-f", "--file", help = "source file to convert").default("")
    val destination by kparser.storing("-o", "--output", help = "destination file to convert").default("")
}
