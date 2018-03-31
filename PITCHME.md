# The Expression Problem and Scala Typeclasses 

---

### Philip Wadler

![Philip Wadler](https://c2.staticflickr.com/6/5672/21794046360_8dbb25e869_b.jpg)

---

### 12 November 1998

Definition
> The goal is to define a datatype by cases, where one can add new cases to the
> datatype and new functions over the datatype, without recompiling
> existing code, and while retaining static type safety (e.g., no
> casts).

---

### Example Problem

|           | Logistic Regressor | DecisionTree Regressor|   |   |
|-----------|--------------------|-----------------------|---|---|
| fit()      |                    |                       |   |   |
| predict() |                    |                       |   |   |
|           |                    |                       |   |   |

---?code=code/src/main/java/interfaces/Algo.java&lang=java

### Solution Criteria

1. Recompilation (Repacking of Existing Code)
2. Static Typesafety
3. Minimal Duplication and Modification

---

### Extensibility in Two Directions

[Chart of extensibility]

---

### Other Language Solutions

- Visitor Pattern - OOP
- Open Classes/Monkey Patching - Ruby
- Multimethods - Clojure

---

### Visitor Pattern

- Example
- Problems (Verbosity, Accidental Complexity)

---

### Open Classes/Monkey Patching - Ruby

- Example
- Problems (TERRIFYINGLY AWESOME)

---

### Basic Scala Machinery

- Implicit Resolution
- Context Bounds

---

### What is a typeclass?

- the trait, the concrete instances and the implicits are the pattern
- Pattern vs Language Feature 

---

### How it tries to solve the expression problem

---

### How it fails to resolve the expression problem

---

### More advanced solutions

- Final Tagless (iOS Talk)
- Interpreter Pattern and the Free Monad (Next time)

---

### References and Additional Resources

[Finally Solving the Expression Problem (Swift)](https://www.youtube.com/watch?v=EsanJ7_U89A)

