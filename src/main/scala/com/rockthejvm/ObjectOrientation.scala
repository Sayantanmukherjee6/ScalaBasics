package com.rockthejvm

import java.security.KeyStore.TrustedCertificateEntry

import com.rockthejvm.FunctionalProgramming.Action

object ObjectOrientation extends App {

    // --class and instance--
    class Animal{
      // define fields
      val age:Int = 0
      // define methods
      def eat():Unit = println("I am eating")
    }

    val anAnimal = new Animal

    // --inheritance--
    class Dog extends Animal

    // --constructor definition--
    class DogWithArgs(name:String) extends Animal
    val aDog = new DogWithArgs("Lassie")
    // constructor arguments are not fields, so
    // aDog.name will give an error. To fix this issue we can use val with name
    // val with constructor argument will treat the args as member of the class
    class DogWithArgsNew(val name:String) extends Animal
    val aDogNew = new DogWithArgsNew("Lassie")
    aDogNew.name

    // --subtype polymorphism--
    val aDeclaredAnimal:Animal = new DogWithArgsNew("Hachi")
    // eat() will use Animal class eat() method unless overridden.
    // During compile time primary class's method will be used
    // overridden methods is called during runtime
    aDeclaredAnimal.eat()

    // --abstract class--
    abstract class WalkingAnimal{
      val hasLegs = true
      def walk():Unit
    }

    // --interface--
    trait Carnivore{
      def eat(animal:Animal):Unit
    }
    trait Philosopher{
      def ?!(thought:String):Unit // valid method name
    }

    // --multi Interface(Trait) Inheritance--
    // also known as "mixing"
    // but single class inheritance (same as java)
    class Crocodile extends Animal with Carnivore with Philosopher {
      override def eat(animal: Animal): Unit = println("I am eating")
      override def ?!(thought: String): Unit = println(s"I was thinking $thought")
    }

    // --infix notation--
    val aCrocodile =  new Crocodile
    aCrocodile.eat(aDog)
    // or
    aCrocodile eat aDog // infix notation = object method argument, only available for methods with one arg
    aCrocodile ?! "What if we could eat more?"

    // --operator--
    // operators in scala are actually methods
    val basicMath = 1+2 // here + is a method belonging to int type
    val basicMathNew = 1.+(2) // equivalent to above

    // --anonymous class--
    val dinosaur = new Carnivore {
      override def eat(animal: Animal): Unit = println("i am a dinosaur, so I can eat pretty much anything")
    }
    /*
     compiler initializes an anonymous class in background and assigns that to dinosaur value
     as shown below:

     class Carnivore_Anonymous_SomeID extends Carnivore{
      override def eat(animal: Animal): Unit = println("i am a dinosaur, so I can eat pretty much anything")
     }
     val dinosaur = Carnivore_Anonymous_SomeID
     */

    // Example 2
    trait Action{
      def act(arg:Int): Int
    }
    val anAction:Action = (x:Int) => x+1
    /*
    The above statement is similar to:
        val anAction:Action = new Action {
            override def act(x:Int): Int = x+1
        }
     */

    // --singleton object--
    object MySingleton{
      val mySpecialValue = 2345
      def mySpecialMethod():Int = 5678
      def apply(x:Int):Int = x+1 // special method available for both class and object types
    }
    // calling above singleton type
    MySingleton.mySpecialMethod()
    // apply method can be called as
    MySingleton.apply(34)
    MySingleton(34) // same as above

    // --apply method example--
    val aList:List[Int] = List(1,2,3)  // is equivalent to List.apply(1,2,3)

    // --class/traits and object of same names--
    // objects belonging to same class name are known as companions
    object Animal{
      // companions can access each other's private field/methods
      // singleton object Animal and instances of Animal(new Animal) are different things
      val canLiveIndefinitely = false
    }
    // Fields of the companions can be accessed in same way as MySingleton
    val something = Animal.canLiveIndefinitely // fields/methods on object is same as static fields/methods in java

    // --case classes--
    /*
    On defining a case class compiler automatically generates
    1. sensible equals
    2. hashcode
    3. serialization
    4. companion with apply
     */
    case class Person(name:String, age:Int)
    val bob = new Person("Bob", 54)
    // or
    val bob_new = Person("Bob",54) // just like companion
    /*
    The above statement is equivalent to
     val bob_new = Person.apply("Bob",54)
     */

    // --exceptions--
    try{
      val x:String = null
      x.length // This will give an error if not handled using try catch
    } catch {
      case e: Exception => println("length not initialised")
    } finally {
      println("this block will run irrespective of any issue")
    }

    // --generics--
     abstract class MyList[T]{
      def head: T
      def tail: MyList[T]
    }
}
