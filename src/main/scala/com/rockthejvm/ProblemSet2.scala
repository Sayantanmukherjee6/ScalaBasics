package com.rockthejvm

object ProblemSet2 extends App {

  /**
   * Write a function values(fun: (Int) => Int, low: Int, high: Int) that
   * yields a collection of function inputs and outputs in a given range.
   * For example, values(x => x * x, -5, 5) should produce a collection of pairs
   * (-5, 25), (-4, 16), (-3,9), . . . , (5, 25).
   */

    println("Problem 1================")
    def values(f:Int => Int, low:Int, high:Int) ={
      (low to high).map(res => (res,f(res)))
    }
    println(values(x => x * x, -5, 5))

  /**
   * Largest element of an array with reduceLeft
   */
    println("Problem 2================")
    val myArray = Array(44,55,33,66,7,3,9,34,65,45,99,63,25)
    println(myArray.reduceLeft(_ max _))

  /**
   * Implement the factorial function using to and reduceLeft
   */
    println("Problem 3================")
    val fact:(Int=>Int) = (n:Int)=> {if (n<=0) 1 else (1 to n).reduceLeft(_*_) }
    println(fact(5))

  /**
   * The previous implementation needed a special case when n < 1. Show how you can avoid this
   * with foldLeft.
   */
    println("Problem 4================")
    val fact2:(Int=>Int) = (n:Int)=> {(1 to n).foldLeft(1)(_*_) }
    println(fact2(-5))

  /**
   * Write a function largest(fun: (Int) => Int, inputs: Seq[Int]) that yields
   * the largest value of a function within a given sequence of inputs. For example, largest(x
   * => 10 * x - x * x, 1 to 10) should return 25.
   **/
    println("Problem 5================")
    def largest(fun: (Int) => Int, inputs: Seq[Int]):Int ={
      inputs.map(x => fun(x)).max
    }
    println(largest(x => 10 * x - x * x, 1 to 10))

  /**
   * Modify the previous function to return the input at which the output is largest. For example,
   * largestAt(x => 10 * x - x * x, 1 to 10) should return 5.
   */
    println("Problem 6================")
    def largestAt(fun: (Int) => Int, inputs: Seq[Int]):Int ={
      val k = inputs.map(x => fun(x))
      k.indexOf(k.max)+1
    }
    println(largestAt(x => 10 * x - x * x, 1 to 10))

}
