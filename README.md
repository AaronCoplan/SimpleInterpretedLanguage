# SimpleInterpretedLanguage

Compile: `javac src/main/java/com/aaroncoplan/**/*.java`

Run: `java -cp ./src/main/java com.aaroncoplan.Application <filepath> <phasenumber (optional)>`

### Summary

Simple stack based language.  Each phase adds incremental functionality so those visiting the repository can see how
common programming language features are implemented in a simplified manner.  The programming language takes the following form / syntax:

`arg arg ... function`

Examples:

```
1 2 add print -> prints 3
2 4 multiply 3 add print -> prints 11
```

### Phase 1

Basic interpreter with 3 built in functions - add, multiply, and print.  Limited error checking.  
Note there is no concept of strings or doubles, only integers and functions.
Splits each line using spaces as the delimiter and pushes each item to the stack unless it is a function name.
For example, `3 4 add print` would push 3 to the stack, push 4 to the stack, recognize `add` as a function and execute it
(which would pop 3, pop 4, add them, then push the result to the stack), then recognize `print` as a function and execute it
(which would pop 7 from the stack and print it).

### Phase 2

Extends the Phase 1 interpreter by implementing the ability to deal with integers and doubles.
For example, `1 2 add` will treat both numbers as integers, and will push the result of `3` to the stack.
However, `1 2.5 add` will first try to treat both numbers as integers, fail to do so on `2.5`, and then reattempt by
treating both values as doubles.  Thus, the result pushed to the stack will be `3.5`.  The same is executed for multiplication.
This phase also adds error handling for invalid arguments.  When an argument is not a number, such as `hello 2 add`,
the interpreter will recognize this and display an error message.

### Phase 3

Extends Phase 2 by adding a very simple type system.  Now, instead of treating everything as a `String`, arguments are 
typed into `Value` objects, which contain a `type` field.  This allows us to do easy comparison of types for the add and
multiply functions in the updated phase 3 interpreter.  The major difference is that instead of determining types on popping
from the stack, we now do so before pushing.

### Phase 4

Extends Phase 3 by making use of the type system to implement the ability to create variables.  Introduces the `set` function,
which can be used to set the value of a variable.  For simplicity, variable names must begin with a dollar sign ($).
To implement this functionality, a new type has been introduced, `REFERENCE`, which tells the interpreter to lookup
the variable name in the table to obtain its value.