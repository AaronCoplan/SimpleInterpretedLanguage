# SimpleInterpretedLanguage

Compile: `javac src/main/java/com/aaroncoplan/**/*.java`

Run: `java -cp ./src/main/java com.aaroncoplan.Application <filepath> <phasenumber (optional)>`

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