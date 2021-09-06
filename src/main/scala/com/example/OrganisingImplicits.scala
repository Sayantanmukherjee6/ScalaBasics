package com.example

object OrganisingImplicits extends App {

  println(List(1,4,5,3,2).sorted) // output : List(1,2,3,4,5)

  /*
   If we want to change the sorting order then we need to access the implicit
   parameter of sorted method
   sorted method implementation : def sorted[B >: A](implicit ord: scala.Ordering[B])
   In the second parameter of sorted method there is an implicit value which we can use to
   sort it in a different way. Lets see
   */

  implicit val reverseOrdering:Ordering[Int] = Ordering.fromLessThan(_>_)
  println(List(1,4,5,3,2).sorted) // output : List(5, 4, 3, 2, 1)

  /*
  In the above code implicit val reverseOrdering got passed in the sorted method,
  due to which we obtained a reverse list
   */

  //--------------- Exercise ---------------
  /*
  Provided a Person class and a List of persons. Sort :
    a. Person by name
    b. Person by age

  Assume ordering of Person by name is commonly used compared to person by age.
  Given below is the definition of Person class and persons list.

  case class Person(name:String,age:Int)
  val persons = List(
    Person("Steve", 30),
    Person("John", 66),
    Person("Amy",22)
  )

  if we try persons.sorted we will get an error , since unlike List[Int] persons contains
  List[Person]. So we need to create implicit ordering for Person
   */

  case class Person(name:String,age:Int)
  val persons = List(
    Person("Steve", 30),
    Person("John", 66),
    Person("Amy",22)
  )
  object Person {
    implicit val orderByName:Ordering[Person] = Ordering.fromLessThan((a,b) => a.name.compareTo(b.name) <0)
  }
  object ageOrdering{
    implicit val orderByAge:Ordering[Person] = Ordering.fromLessThan((a,b) => a.age < b.age)
  }

  println(persons.sorted) // By default it will use companion objects implicit definition

  import ageOrdering._
  println(persons.sorted) // To use age ordering we need to import it

}
