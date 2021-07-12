package com.rockthejvm

object PatternMatching extends App{

  /*
  Pattern match structure is actually an expression.
  Since it is an expression we can assign it to a value.
  It is equivalent to Switch expression in other languages.
  Pattern Matching will try all cases in sequence as written
   */

  val anInteger = 55
  val order = anInteger match {
    case 1 => "first"
    case 2 => "second"
    case 3 => "third"
    case _ => anInteger +"th"
  }
  println(order) // output: 55th

  // -- Matching a class structure--
  case class Person(name:String, age:Int)
  val bob = Person("Bob", 43)

  val personGreeting = bob match{
    case Person(name,age) => s"Hi my name is $name and I am $age years old"
    case _ => "Something else"
  }
  println(personGreeting) // output: Hi my name is Bob and I am 43 years old

  // --Pattern Matching with tuples--
  val aTuple = ("Bon Jovi", "Rock")
  val banDescription = aTuple match{
    case (band,genre) => s"$band belongs to the genre $genre"
    case _ => "Something Else"
  }

  // --Pattern Matching with lists--
  val aList = List(1,2,3)
  val listDescription = aList match{
    case List(_,2,_) => "List containing 2 in second position"
    case _ => "Unknown list"
  }
  println(listDescription) // output: List containing 2 in second position

  // --Pattern Matching with reverse case sequence--
  val listDescriptionRev = aList match{
    case _ => "Unknown list"
    case List(_,2,_) => "List containing 2 in second position"
  }
  println(listDescriptionRev) // output: Unknown list
  /*
  Since as described before "Pattern Matching will try all cases in sequence as written",
  so listDescriptionNew matched first with wildcard "case _" pattern
   */

  // --Pattern Matching with Methods--
  case class Point(x: Int, y: Int)

  def direction(p: Point) = p match {
    case Point(0, 0) => "origin"
    case Point(_, 0) => "horizontal"
    case Point(0, _) => "vertical"
    case _ => "diagonal"
  }
  println(direction(Point(0, 0))) // output: origin
  println(direction(Point(1, 1))) // output: diagonal

  // --Pattern Matching with string--
  case class Individual(name: String, title: String)
  def greet(i: Individual) = i match {
    case Individual(s"$firstName $lastName", title) => s"Hello $title $lastName"
    case Individual(name, title) => s"Hello $title $name"
  }
  println(greet(Individual("John Doe", "Mr"))) // output: Hello Mr Doe
  println(greet(Individual("Witcher", "Master"))) // output: Hello Master Witcher
}
