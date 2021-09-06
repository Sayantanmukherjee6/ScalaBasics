package com.example

object ImplicitPimp extends App {


  // Implicit class with methods
  implicit class EnrichString(val str: String) extends AnyVal{
    def asInt: Int = Integer.valueOf(str)
    def encrypt(cypherDist: Int): String = str.map(c => (c+cypherDist).asInstanceOf[Char])
  }

  println("3".asInt + 4) // Same as implicit intro
  println("abcd".encrypt(2)) // Same as implicit intro

  // Implicit methods
  implicit def stringToInt(str:String):Int = Integer.valueOf(str)
  println("6"/3) // under the hood it implicitly calls stringToInt(6)/3

  /**
   * Implicit methods are hard to debug.
   * It is better to package implicit methods within an object
   * rather than declaring them directly
   */

}
