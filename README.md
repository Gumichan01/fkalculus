# FKalculus #

FKalculus is a simple formal mathematical calculus program made in Kotlin.

**Disclaimer**: This program is still in development and is unstable.
Please do not use it in production.

## Features ##

- Basic calculation expressions - WIP
- Variable substitution - TODO
- Simplify expressions - TODO
- Calculate derivative of a function - TODO
- Primitive integral of a function - TODO
- Resolve simple equations - TODO
- Conversion to Latex?


## Build ##

You need a Kotlin compiler on your machine. You just need to clone this project
and execute the following comand.

```
mvn package
```

For now, the generated package(`fkalculus-0.0.1-SNAPSHOT-standalone.jar`) is 
in the `target` directory.

```
java -jar target/fkalculus-0.0.1-SNAPSHOT-standalone.jar
```


## Dependencies ##

FKalculus uses [JUnit 5](https://github.com/junit-team/junit5/),
[kotlin-argparser](https://github.com/xenomachina/kotlin-argparser) 
and [better-parse](https://github.com/h0tk3y/better-parse).

## License ##

This software is Licensed uner CeCILL v2.1.
