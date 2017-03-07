package services

import java.util.UUID

import graphql.Models.PaymentMethod

class PaymentMethodService {

  val dummySingle = PaymentMethod(UUID.randomUUID().toString, "credit_card", "Bugs","Bunny", 123)

  val dummyList = Seq(
    dummySingle,
    PaymentMethod(UUID.randomUUID().toString, "visa", "Bugs","Bunny", 123),
    PaymentMethod(UUID.randomUUID().toString, "mastercard", "Bugs","Bunny", 123),
    PaymentMethod(UUID.randomUUID().toString, "amex", "Bugs","Bunny", 123),
    PaymentMethod(UUID.randomUUID().toString, "credit_card", "Bugs","Bunny", 123)
  )
  def get() = {
    dummySingle
  }
  def getDummyList() = {
    dummyList
  }
  def get(guid: String) ={
    PaymentMethod(guid = guid, "credit_card", "Bugs", "Bunny", 123)
  }
}
