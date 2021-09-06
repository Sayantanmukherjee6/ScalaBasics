package com.example

object ImplicitIntro extends App {

  case class Person(name:String){
    def greet = s"Hi my name is $name!"
  }

  implicit def stringToPerson(str:String):Person = Person(str)

  println("Sayantan".greet)

  /**
   * println("Sayantan".greet) is equivalent to println(stringToPerson("Sayantan").greet)
   * Although greet method is not member of the String("Sayantan") class and without
   * implicit compiler won't compile this code.
   * "Sayantan" string in ("Sayantan".greet) searches for implicit class,val,methods or objects
   * that can turn "Sayantan" into something that has greet method.
   * Since Person class has greet method and also an implicit conversion from String to
   * Person (which has a greet method) so implicitly String "Sayantan" got a greet method as well.
   */

  /*
   Addition of extra implicit method for a different class with greet method will not work

              class A {
                def greet:String = "Wont work"
              }

              implicit def stringToA(str:String):A = new A

   Adding above code, greet (on line 11) wont work, since compiler found two potential
   implicits that could match, so compiler won't know which one to use.
   */

  // implicit parameter

  implicit val defaultAmount:Int = 10
  def calc(i:Int)(implicit amount:Int) = println(i+amount)
  calc(2) // calc(2) == calc(2)(defaultAmount)
  // defaultAmount got passed into calc function implicitly

}
