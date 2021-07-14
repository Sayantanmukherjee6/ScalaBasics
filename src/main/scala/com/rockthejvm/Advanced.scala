package com.rockthejvm

import scala.concurrent.Future
import scala.concurrent.ExecutionContext.Implicits.global
import scala.util.{Failure, Success, Try}

object Advanced extends App {

  // --Lazy Evaluation--
  lazy val aLazyVal = 2
  lazy val lazyValueWithSideEffect = {
    println("This is a lazy code block")
    43 // return value
  }
  // Actions on top of lazyValueWithSideEffect
  // print is also a type of action
  val eagerValue = lazyValueWithSideEffect + 1
  println(eagerValue) // output: 44

  // --Pseudo-Collection: Option--
  def methodWhichCanReturnNull():String = "hello scala"
  val anOption = Option(methodWhichCanReturnNull()) // return Some("hello scala") or None
  // option : "collection" which contains at most one element: Some(value) or None

  val stringProcessing = anOption match {
    case Some(string) => "I am a valid string"
    case None => "I obtained nothing"
  }
  println(stringProcessing) // output: I am a valid string

  // Example 1
  def hello(title: String, firstName: String, lastNameOpt: Option[String])  = lastNameOpt match {
    case Some(lastName) => println(s"Hello $title. $lastName")
    case None => println(s"Hello $firstName")
  }
  hello("Master", "Witcher", None) // output: Hello Witcher
  hello("Miss", "Elizabeth", Some("Swan")) // output: Hello Miss. Swan

  // Example 2 with getOrElse
  def nameLength(name: Option[String]) = name.map(_.length).getOrElse(-1)
  println(nameLength(Some("John"))) // output: 4
  println(nameLength(None)) // output: -1 (due to getOrElse)

  // --Try--
  def methodWhichCanThrowException():String = throw new RuntimeException
  val aTry = Try(methodWhichCanThrowException())
  // A Try is a "collection" with either a value if code went well or an exception in case of failure
  val anotherStringProcessing = aTry match {
    case Success(validValue) => s"I have obtained a valid string $validValue"
    case Failure(exception) => s"I have obtained an exception $exception"
  }
  println(anotherStringProcessing) // output: I have obtained an exception java.lang.RuntimeException

  /**
   * Asynchronous Programming
   */
  val aFuture = Future {
    println("Loading...")
    Thread.sleep(1000)
    println("I have computed a value")
    67 // output : Loading
  }
  /*
  If we run the above code block, we will only see "Loading..." printed on screen.
  Whereas since the next print and evaluation (return 67) will be handled on different thread.
  To print the evaluated result we need to give a slight more delay in main thread.
   */
  Thread.sleep(2000) // prints both Loading... and I have computed a value
  // future is a "collection" which contains a value when evaluated

  // --Implicit--
  // 1. Implicit arg
  def aMethodWithImplicitArg(implicit arg:Int) = arg + 1
  implicit val myImplicitVal = 46
  println(aMethodWithImplicitArg) // output : 47
  /*
  A method with Implicit arg will search for any implicit value defined outside the scope of the method,
  and will assign it as an parameter to that implicit arg.
  So, aMethodWithImplicitArg == aMethodWithImplicitArg(myImplicitVal)
   */

  // 2. Implicit Conversion
  implicit class MyRichInteger(n:Int) {
    def isEven() = n%2==0
  }
  println(23.isEven())
  /*
  If implicit is not defined before class then we need to create an object to access the class methods.
  But if implicit is defined then we don't need to create an object. We can directly call methods.
  So under the hood:
      23.isEven() == new MyRichInteger(23).isEven()
   */
}
