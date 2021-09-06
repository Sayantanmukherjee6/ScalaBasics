package com.example

import java.util.Date

object TypeClass extends App {

  case class User(name:String,age:Int,email:String)

  // Type Class : It can take any type of parameter
  trait HTMLSerializer[T]{
    def serialize(value:T):String
  }

  // UserSerializer instance of Type class HTMLSerializer
  object UserSerializer extends HTMLSerializer[User]{
    override def serialize(user: User): String = s"<div> ${user.name} (${user.age} yo) <a href=${user.email}/> </div>"
  }

  // UserNameSerializer instance of Type class HTMLSerializer
  object UserNameSerializer extends HTMLSerializer[User]{
    override def serialize(user: User): String = s"<div> ${user.name} </div>"
  }

  // DateSerializer instance of Type class HTMLSerializer
  object DateSerializer extends HTMLSerializer[Date] {
    override def serialize(date: Date): String = s"<div> ${date.toString}/> </div>"
  }

  val john = User("John",33,"john@gmail.com")
  println(UserSerializer.serialize(john))
  println(UserNameSerializer.serialize(john))


  // ----------- Example 2 -----------------

  // Equal type class with NameEquality and NameEmailEquality instances

  trait Equal[T]{
    def apply(a:T, b:T):Boolean
  }

  object NameEquality extends Equal[User]{
    override def apply(a: User, b: User): Boolean = a.name == b.name
  }

  object NameEmailEquality extends Equal[User]{
    override def apply(a: User, b: User): Boolean = a.name == b.name && a.email == b.email
  }

}
