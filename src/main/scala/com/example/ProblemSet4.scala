package com.example

object ProblemSet4 extends App {

  /**
   * Find the last element of a list. Example
   * scala> last(List(1, 1, 2, 3, 5, 8))
   * res0: Int = 8
   */

  println("Problem 1================")
  println(List(1, 1, 2, 3, 5, 8).last)

  /**
   * Find the last but one element of a list (Second last element). Example
   * scala> penultimate(List(1, 1, 2, 3, 5, 8))
   * res0: Int = 5
   */

  println("Problem 2================")
  println(List(1, 1, 2, 3, 5, 8).reverse.tail.head)

  // another common approach using length based slicing
  println((List(1, 1, 2, 3, 5, 8)(List(1, 1, 2, 3, 5, 8).length-2)))

  /**
   * Find the nth element of a list.
   * scala> nth(2, List(1, 1, 2, 3, 5, 8))
   * res0: Int = 2
   */

  println("Problem 3================")
  val nth:(Int,List[Int]) => Int = (n:Int, l:List[Int]) => {
    l(n)
  }
  println(nth(2, List(1, 1, 2, 3, 5, 8)))

  /**
   * Flatten a nested list structure.
   * scala> flatten(List(List(1, 1), 2, List(3, List(5, 8))))
   * res0: List[Any] = List(1, 1, 2, 3, 5, 8)
   */

  println("Problem 4================")
  val flattenList:List[Any] => List[Any] = (x:List[Any]) => x flatMap {
    case (l:List[_]) => flattenList(l)
    case item => List(item)
  }
  println(flattenList(List(List(1, 1), 2, List(3, List(5, 8)))))

  /*
  Before flattening Op takes place, the individual list item or sublist item is converted to List via map and
  recursion. Each iteration steps:
    case1: List(1, 1) // 1st list item
    case2: List(1)
    case2: List(1)
    case2: List(2) // 2nd list item
    case1: List(3, List(5, 8)) // 3rd list item
    case2: List(3)
    case1: List(5, 8)
    case2: List(5)
    case2: List(8)
   */

  /**
   * Eliminate consecutive duplicates of list elements.
   * scala> compress(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
   * res0: List[String] = List('a, 'b, 'c, 'a, 'd, 'e)
   */

  println("Problem 5================")
  println(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e).distinct.map(x => x))

  /**
   * Pack duplicates of list elements into sublists.
   * scala> pack(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
   * res0: List[List[Symbol]] = List(List('a, 'a, 'a, 'a, 'a, 'a), List('b), List('c, 'c),
   *                                  List('d), List('e, 'e, 'e, 'e))
   */

  println("Problem 6================")
  println(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e).groupBy(x=>x).toList.sortBy(_._1).map(_._2))

  // using PM and Recursion
  def packGroup[T](l:List[T]):List[List[T]]= l match {
    case Nil => Nil
    case _ => {
      val (a,b) = l.partition(x => x == l.head) // where a = partition1 and b = partition2
      a :: pack(b)
    }
  }
  println(packGroup(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)))

  /**
   * Count the number of items in a list.
   * scala> pack(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
   * res0: List[(Symbol, Int)] = List((Symbol(a), 6), (Symbol(b), 1), (Symbol(c), 2),
   *                                  (Symbol(d), 1), (Symbol(e), 4))
   */

  println("Problem 7================")
  println(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e).groupBy(i=>i).mapValues(x => x.size).toList.sortBy(_._1))

  /**
   * Pack consecutive duplicates of list elements into sublists.
   * scala> pack(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
   * res0: List[List[Symbol]] = List(List('a, 'a, 'a, 'a), List('b), List('c, 'c),
   *                                  List('a, 'a), List('d), List('e, 'e, 'e, 'e))
   */

  println("Problem 8================")
  // Logic same as packGroup but using span instead of partition
  def pack[T](l:List[T]):List[List[T]]= l match {
    case Nil => Nil
    case _ => {
      val (a,b) = l.span(x => x == l.head)
      a :: pack(b)
    }
  }
  println(pack(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)))

  /**
   * Run-length encoding of a list.
   * scala> encode(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
   * res0: List[(Int, Symbol)] = List((4,'a), (1,'b), (2,'c), (2,'a), (1,'d), (4,'e))
   */

  println("Problem 9================")
  def encode[T](l:List[T]):List[(Int,T)] = {
    val unpackedList = pack(l)
    unpackedList.map(x => (x.size,x.distinct(0)))
  }

  println(encode(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)))

  /**
   * Modified run-length encoding.
   * scala> encodeModified(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e))
   * res0: List[Any] = List((4,'a), 'b, (2,'c), (2,'a), 'd, (4,'e))
   */

  println("Problem 10================")
  def encodeModified[T](l:List[T]):List[Any] = encode(l) map {
    case (1,item) => item
    case (greaterThanOne,item) => (greaterThanOne,item)
  }

  println(encodeModified(List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)))

  /**
   * Decode a run-length encoded list.
   * scala> decode(List((4, 'a), (1, 'b), (2, 'c), (2, 'a), (1, 'd), (4, 'e)))
   * res0: List[Symbol] = List('a, 'a, 'a, 'a, 'b, 'c, 'c, 'a, 'a, 'd, 'e, 'e, 'e, 'e)
   */

  println("Problem 11================")
  def decode[T](l:List[(Int,T)]):List[T] = l flatMap(x => List.fill(x._1)(x._2))
  println(decode(List((4, 'a), (1, 'b), (2, 'c), (2, 'a), (1, 'd), (4, 'e))))

}
