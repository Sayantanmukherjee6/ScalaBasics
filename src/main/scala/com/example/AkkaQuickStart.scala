package com.example

import akka.actor.typed.ActorSystem
import akka.actor.typed.ActorRef
import akka.actor.typed.Behavior
import akka.actor.typed.scaladsl.Behaviors
import com.example.Notifier.Notification
import com.example.OrderProcessor.Order
import com.example.Shipper.Shipment

// Notifier Actor
object Notifier {
  // Notification Mail Box
  final case class Notification(orderId: Int, shipmentSuccess:Boolean)

  def apply(): Behavior[Notification] = Behaviors.receive {
    (context,message) => context.log.info(message.toString())
    Behaviors.same
  }
}

// Shipper Actor
object Shipper {
  // Shipment Mail Box
  final case class Shipment(orderId: Int, product: String, number: Int, replyTo: ActorRef[Notification])

  def apply(): Behavior[Shipment] = Behaviors.receive {
    (context,message) =>
      context.log.info(message.toString())
      message.replyTo ! Notification(message.orderId,true) // send notification message
    Behaviors.same
  }
}

// OrderProcessor Actor (Main Actor)
object OrderProcessor {
  // Order Mail Box
  final case class Order(id: Int, product: String, number:Int)

  def apply(): Behavior[Order] = Behaviors.setup {
    // We are setting up the context, received message will be handled later
    context =>
      val shipperRef = context.spawn(Shipper(), "shipper") // spawning shipper actor
      val notifierRef = context.spawn(Notifier(),"notifier") // spawning notifier actor

      // receive message
      Behaviors.receiveMessage{
        message => context.log.info(message.toString())
        shipperRef ! Shipment(message.id,message.product,message.number, notifierRef) // send shipment message
        Behaviors.same
    }
  }
}

object AkkaQuickStart extends App {

  /*
  Logic:
    1. On getting an order "Order(0,"Jacket",2)" orderprocessor actor spawns shipper and notifier actors.
    2. Order processor actor processes the order and sends a shipment message.
    3. Based on the shipment message shipper actor processes the shipment and sends a notification with status true.
    4. Notifier actor on getting a notification message notifies the shipment status for a particular order id.
   */

  // actor system initialization
  val orderProcessor:ActorSystem[OrderProcessor.Order] = ActorSystem(OrderProcessor(),"orders")

  // send message
  orderProcessor ! Order(0,"Jacket",2)
  orderProcessor ! Order(1,"Sneakers",1)
  orderProcessor ! Order(2,"Socks",5)
  orderProcessor ! Order(3,"Umbrella",3)
}
