package com.rockthejvm

object ProblemSet1 extends App {

  /**
   * The signum of a number is 1 if the number is positive, –1 if it is negative, and
   * 0 if it is zero. Write a function that computes this value.
   */

  println("Problem 1================")
  // PM
  def signum(n:Int) = n match {
    case n if n>0 => 1
    case n if n<0 => -1
    case _ => 0
  }
  println(signum(-100))

  // shorter approach without PM
  def signum2(n:Int):Int = n compare 0
  println(signum2(100))

  /**
   * Write a Scala equivalent for the Java loop
   * for (int i = 10; i >= 0; i--) System.out.println(i);
   */

  println("Problem 2================")
  // Style 1:
  for (i <- (10 to 1 by -1)) { println(i) }

  // Style 2:
  for (i <- (1 to 10).reverse) { println(i) }

  /**
   * Write a procedure countdown(n: Int) that prints the numbers from n to 0
   */

  println("Problem 3================")
  def countDown(n: Int):Unit = {
    println(n)
    if (n > 0) countDown(n-1)
  }
  countDown(1)

  /**
   * Write a for loop for computing the product of the Unicode codes of all letters
   * in a string. For example, the product of the characters in "Hello" is 825152896.
   */

  println("Problem 4================")
  // With loop
  def productUnicodeFor(s:String): Int ={
    var result = 1
    for(i <- s) result*=i.toInt
    result
  }
  println(productUnicodeFor("Hello"))

  // Without loop
  def productUnicode(s:String): Int ={
    var result = 1
    s.foreach(result*=_.toInt)
    result
  }
  println(productUnicode("Hello"))

  // recursive
  def productUnicodeRec(s:String):Int ={
    if (s.length == 1) s(0).toInt
    else s.head.toInt * productUnicodeRec(s.tail)
  }
  println(productUnicodeRec("Hello"))

  // foldleft(HOF)
  val productUnicodeFold = "Hello".foldLeft(1)((acc,x) => acc*x.toInt)
  print(productUnicodeFold)
}
