package services

import java.util.UUID

import graphql.Models.PaymentMethod

class PaymentMethodService {

  val dummySingle = PaymentMethod(UUID.randomUUID().toString, "credit_card", "Tony","Stark", 123)

  val dummyList = Seq(
    dummySingle,
    PaymentMethod(UUID.randomUUID().toString, "visa", "Tony","Stark", 123),
    PaymentMethod(UUID.randomUUID().toString, "mastercard", "Tony","Stark", 123),
    PaymentMethod(UUID.randomUUID().toString, "amex", "Tony","Stark", 123),
    PaymentMethod(UUID.randomUUID().toString, "credit_card", "Tony","Stark", 123)
  )
  def get() = {
    dummySingle
  }
  def getDummyList() = {
    dummyList
  }
  def get(guid: String) ={
    PaymentMethod(guid = guid, "credit_card", "Tony", "Stark", 123)
  }
}
