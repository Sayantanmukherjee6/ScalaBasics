package com.example

import java.util.Date

import com.example.TypeClass.{Equal, User}

object TypeClassImplicit extends App {

  case class User(name:String,age:Int,email:String)

  // Type Class : It can take any type of parameter
  trait HTMLSerializer[T]{
    def serialize(value:T):String
  }

  // Companion object of HTMLSerializer type class
  object HTMLSerializer {
    def serialize[T](value:T)(implicit serializer: HTMLSerializer[T]): String =
      serializer.serialize(value)
  }

  // IntSerializer instance of Type class HTMLSerializer
  implicit object IntSerializer extends HTMLSerializer[Int] {
    override def serialize(value: Int): String = s"<div style: color=blue> $value </div>"
  }

  // UserSerializer instance of Type class HTMLSerializer
  implicit object UserSerializer extends HTMLSerializer[User]{
    override def serialize(user: User): String = s"<div> ${user.name} (${user.age} yo) <a href=${user.email}/> </div>"
  }

  /*
   Instead of calling IntSerializer directly if we want to use IntSerializer
   (without implicit definition) from Type Class companion object then we need to do this :

   object IntSerializer extends HTMLSerializer[Int] {
    override def serialize(value: Int): String = s"<div style: color=blue> $value </div>"
   }

    println(HTMLSerializer.serialize(42)(IntSerializer))

   But using implicit on IntSerializer object would make the method call more compact
  */

  println(HTMLSerializer.serialize(42))

  /**
   * What addition of implicit does:
   * HTMLSerializer.serialize(42) - compiler will figure 42 as Int
   * Compiler figures out that it needs "implicit" serializer as an HTMLSerializer of type Int
   ** in this parameter: implicit serializer: HTMLSerializer[T]
   * And it finds an implicit object of IntSerializer which is an extension of HTMLSerializer
   */

  // Same will happen for UserSerializer using implicit
  val john = User("John",33,"john@gmail.com")
  println(HTMLSerializer.serialize(john))

  // -----------Equal type class with implicit----------------

  trait Equal[T]{
    def check(a:T, b:T):Boolean
  }

  object Equal {
    def isEqual[T](a:T, b:T)(implicit equalizer:Equal[T]):Boolean =
      equalizer.check(a,b)
  }

  implicit object NameEquality extends Equal[User]{
    override def check(a: User, b: User): Boolean = a.name == b.name
  }

  val anotherJohn = User("John",43,"john@yahoo.com")
  println(Equal.isEqual(john,anotherJohn))
}
