package com.example

import java.util.Date

object JsonSerializationImplicitExample extends App {

  /*
  Users, posts, feeds
  Serialize to JSON
   */

  case class User(name: String, age: Int, email: String)
  case class Post(content: String, createdAt: Date)
  case class Feed(user: User, posts: List[Post])

  trait JSONValue {
    def stringify: String
  }
  case class JSONString(value:String) extends JSONValue {
    def stringify: String = "\"" + value + "\""
  }
  case class JSONNumber(value:Int) extends JSONValue {
    def stringify: String = value.toString
  }
  case class JSONArray(value:List[JSONValue]) extends JSONValue {
    def stringify: String = value.map(_.stringify).mkString("[",",","]")
  }
  case class JSONObject(value: Map[String,JSONValue]) extends JSONValue {
    def stringify: String = value.map {
      case (key,value) => "\"" + key + "\":" + value.stringify
    }.mkString("{",",","}")
  }

  // Type class
  trait JSONConverter[T] {
    def convert(value:T) 
  }

  // Enrichment class for pimping
  implicit class JSONOps[T](value:T) {
    def toJson(implicit converter: JSONConverter[T]):JSONValue = converter.convert(value)
  }

  implicit object StringConverter extends JSONConverter[String] {
    override def convert(value: String): JSONValue = JSONString(value)
  }
  implicit object NumberConverter extends JSONConverter[Int] {
    override def convert(value: Int): JSONValue = JSONNumber(value)
  }
  implicit object UserConverter extends JSONConverter[User] {
    override def convert(user: User): JSONValue = JSONObject(Map(
      "name" -> JSONString(user.name),
      "age" -> JSONNumber(user.age),
      "email" -> JSONString(user.email)
    ))
  }
  implicit object PostConverter extends JSONConverter[Post] {
    override def convert(post: Post): JSONValue = JSONObject(Map(
      "content" -> JSONString(post.content),
      "created" -> JSONString(post.createdAt.toString)
    ))
  }
  implicit object FeedConverter extends JSONConverter[Feed] {
    override def convert(feed: Feed): JSONValue = JSONObject(Map(
      "user" -> feed.user.toJson,
      "posts" -> JSONArray(feed.posts.map(_.toJson))
    ))
  }

  val now = new Date(System.currentTimeMillis())
  val john = User("John",33,"john@gmail.com")
  val feed = Feed(john, List(
    Post("hello",now),
    Post("world",now)
  ))
  println(feed.toJson.stringify)
}
