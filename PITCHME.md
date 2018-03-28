# The Expression Problem and Scala Typeclasses 



---

### Philip Wadler

image of philip

---

### 12 November 1998

Definition
> The goal is to define a datatype by cases, where one can add new cases to the
> datatype and new functions over the datatype, without recompiling
> existing code, and while retaining static type safety (e.g., no
> casts).

---

### Extensibility in Two Directions

[Chart of extensibility]

---

### Other Language Solutions

- Visitor Pattern - OOP
- Open Classes/Monkey Patching - Ruby

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

-- Final Tagless (iOS Talk)
-- Interpreter Pattern and the Free Monad (Next time)

