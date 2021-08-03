package com.example

object ProblemSet5 extends App {

  /**
   * Duplicate the elements of a list n number of times
   * scala> duplicateN(3, List('a, 'b, 'c, 'c, 'd))
   * res0: List[Symbol] = List('a, 'a, 'a, 'b, 'b, 'b, 'c, 'c, 'c, 'c, 'c, 'c, 'd, 'd, 'd)
   */

  println("Problem 1================")
  def duplicateN[T](n:Int,l:List[T]):List[T] = l.flatMap(x => List.fill(n)(x))
  println(duplicateN(3, List('a, 'b, 'c, 'c, 'd)))

  /**
   *  Drop every Nth element from a list.
   * scala> drop(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
   * res0: List[Symbol] = List('a, 'b, 'd, 'e, 'g, 'h, 'j, 'k)
   */

  println("Problem 2================")
  def drop[T](n:Int,l:List[T]):List[T] = l.filter(x => x!=l(n-1))
  println(drop(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))

  /**
   * Split a list into two parts.
   * scala> split(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
   * res0: (List[Symbol], List[Symbol]) = (List('a, 'b, 'c),List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
   */

  println("Problem 3================")
  println(List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k).splitAt(3))

  /**
   * Extract a slice from a list and last element.
   * scala> sliceAndLast(3, 7, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
   * res0: (List[Symbol], Symbol) = List(List('d, 'e, 'f, 'g),'k)
   */

  println("Problem 4================")
  def sliceAndLast[T](firstIndex:Int,lastIndex:Int,l:List[T]):(List[T],T) = {
    val slice = l.slice(firstIndex,lastIndex)
    val last = l.last
    (slice,last)
  }
  println(sliceAndLast(3, 7, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))

  /**
   * Rotate a list N places to the left.
   * scala> rotate(3, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
   * res0: List[Symbol] = List('d, 'e, 'f, 'g, 'h, 'i, 'j, 'k, 'a, 'b, 'c)
   *
   * scala> rotateLeft(-2, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k))
   * res1: List[Symbol] = List('j, 'k, 'a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i)
   */

  println("Problem 5================")
  def rotateLeft[T](n:Int,l:List[T]):List[Any] = n match {
    case i if i > 0 => {
      val (a,b) = l.splitAt(i)
      b ::: a
    }
    case i if i < 0 => {
      val (a,b) = l.splitAt(l.length + i)
      b ::: a
    }
  }
  println(rotateLeft(-2, List('a, 'b, 'c, 'd, 'e, 'f, 'g, 'h, 'i, 'j, 'k)))

  /**
   * Sorting a list of lists according to length of sublists.
   * scala> lsort(List(List('a, 'b, 'c), List('d, 'e), List('f, 'g, 'h), List('d, 'e), List('i, 'j, 'k, 'l), List('m, 'n), List('o)))
   * res0: List[List[Symbol]] = List(List('o), List('d, 'e), List('d, 'e), List('m, 'n), List('a, 'b, 'c), List('f, 'g, 'h), List('i, 'j, 'k, 'l))
   */

  println("Problem 6================")
  println(List(List('a, 'b, 'c), List('d, 'e), List('f, 'g, 'h), List('d, 'e), List('i, 'j, 'k, 'l), List('m, 'n), List('o)).sortBy(x => x.length))

}
