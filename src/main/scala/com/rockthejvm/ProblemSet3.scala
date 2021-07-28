package com.rockthejvm

object ProblemSet3 extends App {

  /**
   * Using pattern matching, write a function swap that receives a pair of integers and returns the
   * pair with the components swapped.
   */
  println("Problem 1================")
  val swapPair:(Int,Int) => (Int,Int) = (x:Int,y:Int) => (x,y) match {case (x,y) => (y,x)}
  println(swapPair(5,6))

  /**
   * Using pattern matching, write a function swap that swaps the first two elements of an array
   * provided its length is at least two
   */
  println("Problem 2================")
  val swapFirstTwoElemList:(List[Int]) => List[Int] = (x:List[Int]) => x match {
    case x :: y :: tail => y :: x :: tail
    case x :: Nil => x:: Nil
    case Nil => Nil
  }
  println(swapFirstTwoElemList(List(1,2,3)))

  /**
   * Write a function that computes the sum of the non-None values in a List[Option[Int]].
   */

  println("Problem 3================")
  val sumList:(List[Option[Int]]) => Int = (lst:List[Option[Int]]) => {
      lst.flatten.sum
  }
  println(sumList(List(None,Some(1),None,Some(2))))

  // PM
  // Here Collect acts like combined map + filter
  val sumListPM:(List[Option[Int]]) => Int = (lst:List[Option[Int]]) => lst.collect {
    case(Some(value)) => value
  }.sum

  println(sumListPM(List(None,Some(1),None,Some(2))))


}
