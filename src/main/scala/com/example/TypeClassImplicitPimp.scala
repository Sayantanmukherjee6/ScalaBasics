package com.example

object TypeClassImplicitPimp extends App {

  // Unlike Normal Type Class with implicit, with pimping we don't require companion object
  // of a type. Compiler implicitly figures out which type instance is required if not passed
  // explicitly

  case class User(name:String,age:Int,email:String)

  // Type Class : It can take any type of parameter
  trait HTMLSerializer[T]{
    def serialize(value:T):String
  }

  // IntSerializer instance of Type class HTMLSerializer
  implicit object IntSerializer extends HTMLSerializer[Int] {
    override def serialize(value: Int): String = s"<div style: color=blue> $value </div>"
  }

  // UserSerializer instance of Type class HTMLSerializer
  implicit object UserSerializer extends HTMLSerializer[User]{
    override def serialize(user: User): String = s"<div> ${user.name} (${user.age} yo) <a href=${user.email}/> </div>"
  }

  // UserNameSerializer instance of Type class HTMLSerializer
  object UserNameSerializer extends HTMLSerializer[User]{
    override def serialize(user: User): String = s"<div> ${user.name} </div>"
  }

  implicit class HTMLEnrichment[T](value:T) {
    def toHTML(implicit serializer: HTMLSerializer[T]):String = serializer.serialize(value)
  }

  val john = User("John",33,"john@gmail.com")
  println(john.toHTML) // same as : println(new HTMLEnrichment[User](john).toHTML(UserSerializer))
  println(john.toHTML(UserNameSerializer)) // uses normal UserNameSerializer instance explicitly
  println(23.toHTML) // uses IntSerializer instance implicitly


  // -----------Equal type class with implicit and Pimp----------------

  trait Equal[T]{
    def check(a:T, b:T):Boolean
  }

  implicit object NameEquality extends Equal[User]{
    override def check(a: User, b: User): Boolean = a.name == b.name
  }

  // This is equivalent to matching values with other
  implicit class EqualEnrichment[T](value:T) {
    def ===(other:T)(implicit equalizer: Equal[T]):Boolean = equalizer.check(value,other)
    def !==(other:T)(implicit equalizer: Equal[T]):Boolean = ! equalizer.check(value,other)
  }

  val anotherJohn = User("John",43,"johnny@aol.com")
  val anotherSomeone = User("Sam",23,"sam@yahoo.com")

  println(john === anotherJohn) // true
  println(john !== anotherJohn) // false
  println(john === anotherSomeone) // false
  println(john !== anotherSomeone) // true

}
