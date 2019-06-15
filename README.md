# FKalculus #

FKalculus is a simple formal mathematical calculus program made in Kotlin.

**Disclaimer**: This program is still in development and is unstable.
Please do not use it in production.

## Features ##

- Basic calculation expressions
- Variable substitution
- Simplify expressions - TODO
- Calculate derivative of a function - TODO
- Primitive integral of a function - TODO
- Resolve simple equations - TODO


## Build ##

You need a Kotlin compiler on your machine.

## With an IDE ##

If you use an IDE (Eclipse, IntelliJ, ...) make sure you have
the Kotlin plugin installed.

## From scratch ##

Clone this repository and execute the following comands:

```
mvn clean package
./fkalculus.sh      // Launch the program
```

On Windows, an executable file is generated (`fkalculus.exe`).
You can double-click on it in order to launch it.

## Dependencies ##

FKalculus uses [JUnit 5](https://github.com/junit-team/junit5/),
[kotlin-argparser](https://github.com/xenomachina/kotlin-argparser),
[better-parse](https://github.com/h0tk3y/better-parse) and [Mockito-Kotlin](https://github.com/nhaarman/mockito-kotlin).

The Windows packaging is done with [Launch4J](http://launch4j.sourceforge.net/).

## License ##

This software is Licensed under the CeCILL v2.1 license.
