package com.rockthejvm

object FunctionalProgramming extends App {

    // Must See : https://stackoverflow.com/questions/2529184/difference-between-method-and-function-in-scala
    // Methods defined by def and function literals defined by => are functions.

    class Person(name:String) {
      def apply(age:Int) = println(s"My age is $age")
    }
    /*
    Presence of an apply method allows instance of a class to be invoked as function
     */
    val bob = new Person("bob")
    bob.apply(43)
    bob(43) // invoking object bob as a function.

    // --FunctionX--
    val simpleIncrementer = new Function1[Int,Int] {
      override def apply(v1: Int): Int = v1 + 1
    }
    simpleIncrementer.apply(23)
    // or
    simpleIncrementer(23)
    /*
    Function1[Int.Int] is a trait with input and output type int.
    simpleIncrementer is an instance or an object of Function1 trait
    and simpleIncrementer object can be invoked as a function as it
    has an apply method.
    All scala functions are instances of FunctionX[] traits,
    where FunctionX = Function1,Function2,...,Function22 (22 is the max)
     */

    // --Function2--
    // Function2 trait takes two argument.
    // As shown below two arguments are String
    // and the last argument is return type which is also String
    val stringConcat = new Function2[String,String,String]{
      override def apply(v1: String, v2: String): String = v1 + v2
    }
    stringConcat("Hello"," World") // Hello World

    // --Syntactic Sugar1--
    val doubler:Function1[Int,Int] = (x:Int) => 2 * x
    doubler(4) //8
    /*
    The above shorthand notation is equivalent to:
        new Function1[Int,Int]{
           override def apply(x:Int):Int = 2 * x
        }
     */

    // --Syntactic Sugar2--
    // Int => Int is equivalent to Function1[Int,Int]
    val doublerNew: Int => Int = (x:Int) => 2 * x

    // --Higher Order Functions--
    //Methods/Functions that take functions as args or returns function as result
    val aMappedList:List[Int] = List(1,2,3).map(x => x+1)
    println(aMappedList) // List(2, 3, 4)
    //map(x => x+1) is a HOF as function literal "x=>x+1" is passed as an arg to map method
    val aMappedListNew = List(1,2,3).map(x => (x,2*x))
    println(aMappedListNew) // List((1,2), (2,4), (3,6))
    val aFlatMapList = List(1,2,3).flatMap(x => List(x,2*x))
    println(aFlatMapList) // List(1, 2, 2, 4, 3, 6): FlatMap help to concatenate multiple List into a single List
    /*
    Alternative syntax: using curly braces
    val aFlatMapList = List(1,2,3).flatMap {x =>
    List(x,2*x)
    }
     */
    val aFilteredList = List(1,2,3).filter(x => x <= 2)
    println(aFilteredList) // List(1,2)
    /*
    More shorter syntax:
    val aFilteredList = List(1,2,3).filter(_ <= 2) where x => x is equivalent to _
     */

    // --chaining HOF--
    // problem: all pairs between the numbers 1,2,3 and the letters 'a','b','c'
    val allPairs = List(1,2,3).flatMap(num => List('a','b','c').map(letter => s"$num-$letter"))
    println(allPairs) // List(1-a, 1-b, 1-c, 2-a, 2-b, 2-c, 3-a, 3-b, 3-c)

    // --For Comprehension--
    val allPairsAlternative = for {
      num <- List(1,2,3)
      letter <- List('a','b','c')
    } yield s"$num-$letter"
    /*
    This is equivalent to :
    List(1,2,3).flatMap(num => List('a','b','c').map(letter => s"$num-$letter"))
    Internally compiler will chain map and flatmap for the above For comprehension.
    map/flatmap chain and For Comprehension is totally identical.
     */

    // --Methods with multiple Parameter(currying)--
    def add(a: Int) = (b: Int) => a + b;
    println(add(20)(19)) // output 39

    // --HOF Currying--
    def myLoop(start: Int, end: Int)(callback:Int => Unit) ={
      for (i <- Range(start, end)){
        callback(i)
      }
    }
    myLoop(5, 10)(x => println(s"x has value ${x}"))
    // can also be written with curly brace as : myLoop(5, 10){x => println(s"x has value ${x}")}
    /*
    Output:
    x has value 5
    x has value 6
    x has value 7
    x has value 8
    x has value 9
     */

    // --Collections--

    // --List--
    val aList = List(1,2,3)
    val firstElement = aList.head
    val rest = aList.tail
    val aPrependedList = 0::aList // List(0,1,2,3) -- :: operator is applicable to a List
    val anExtendedList = 0 +: aList :+ 4 // List(0,1,2,3,4) // +: prepends (just as above) and :+ appends

    // --Sequences--
    val aSequence:Seq[Int] = Seq(1,2,3)
    /*
      Seq is a trait containing apply factory method
      Seq(1,2,3) == Seq.apply(1,2,3)
     */
    val SeqFirstElement = aSequence(1) // equivalent to aSequence.apply(1) returns the elem at index 1: output = 2

    // --Vectors--
    /*
    1. A Fast sequence implementation
    2. Useful for large data
     */
    val aVector = Vector(1,2,3,4,5)

    // --Sets--
    // Collection with no duplicates
    val aSet = Set(1,2,3,2,3,4) // output: set(1,2,3,4)
    val setHas5 = aSet.contains(5) // check if 5 is in aSet, output: false
    val anAddedSet = aSet + 5 // output: set(1,2,3,4,5)
    val aRemoveSet = aSet - 3 // output: set(1,2,4)

    // --Range--
    val aRange = 1 to 100
    val twoByTwo = aRange.map(x => 2*x).toList // output: List(2,4,6,8,...,200)

    // --Tuples--
    // kinda same as python tuples
    val aTuple = ("hello","World",22)

    // --Map--
    // same as hashmap/dictionary
    val aPhoneBook: Map[String,Int] = Map(
      ("Daniel",1234),
      ("Jane" -> 5678) // same as ("Jane",5678)
    )
}



