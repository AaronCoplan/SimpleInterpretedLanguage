# SimpleInterpretedLanguage

Compile: `javac src/main/java/com/aaroncoplan/**/*.java`

Run: `java -cp ./src/main/java com.aaroncoplan.Application <filepath> <phasenumber (optional)>`

### Phase 1

Basic interpreter with 3 built in functions - add, multiply, and print.  Limited error checking.
Splits each line using spaces as the delimiter and pushes each item to the stack unless it is a function name.
For example, `3 4 add print` would push 3 to the stack, push 4 to the stack, recognize `add` as a function and execute it
(which would pop 3, pop 4, add them, then push the result to the stack), then recognize `print` as a function and execute it
(which would pop 7 from the stack and print it).