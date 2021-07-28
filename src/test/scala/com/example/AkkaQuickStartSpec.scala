package com.example

import akka.actor.testkit.typed.scaladsl.ScalaTestWithActorTestKit
import com.example.Notifier.Notification
import com.example.Shipper.Shipment
import org.scalatest.wordspec.AnyWordSpecLike

class AkkaQuickStartSpec extends ScalaTestWithActorTestKit with AnyWordSpecLike{

  "A Shipper" must {
    "notify to notifier" in {
      val replyProbe = createTestProbe[Notification]()
      val underTest = spawn(Shipper())
      underTest ! Shipment(0, "Jacket", 1, replyProbe.ref)
      replyProbe.expectMessage(Notification(0,true))
    }
  }

}
