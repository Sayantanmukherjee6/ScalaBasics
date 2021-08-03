package com.example

object Basics extends App {

    // defining a value
    val meaningOfLife: Int = 42

    // boolean
    val aBoolean = false

    // string and string operations
    val aString = "I am learning scala"
    val aComposedString = "I" + "am" + "learning" + "scala"
    val anInterpolatedString = s"The meaning of life is $meaningOfLife"

    // expressions = structures that can be reduced to a value
    val anExpression = 2+3

    // if -expression
    val ifExpression = if (meaningOfLife>43) 56 else 999
    val chainIfExpression:Int =
      if (meaningOfLife>43) 56
      else if (meaningOfLife<0) -2
      else if (meaningOfLife > 999) 78
      else 0

    // code block
    val aCodeBlock = {
      // definitions
      val aLocalValue = 67
      // return value of the entire block is the last expression
      aLocalValue + 3
    }

    // method
    def myMethod(x:Int, y:String): String = y + " " + x
    def myMethodWithinCodeBlock(x:Int, y:String): String = {
      y + " " + x
    }

    // recursive methods
    def factorial(n:Int):Int = {
      if (n<=1) 1
      else n * factorial(n-1)
    }

    // Unit Type or Void
    println("I am learning scala")
    def myUnitReturningMethod(): Unit = {
      println("I am learning scala")
    }
    val theUnit = ()


}
