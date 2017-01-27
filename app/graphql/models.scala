package graphql

import services.{AddressService, PaymentMethodService, SessionService}

object Models {

  case class Session(
    guid: String,
    user : User,
    paymentMethods: Seq[PaymentMethod],
    paymentMethod: PaymentMethod,
    addresses: Seq[Address],
    address: Address,
    order: Order
  )
  case class PaymentMethod(
    guid: String,
    pm_type: String,
    firstName : String,
    lastName: String,
    cvn :Int
  )
  case class Address(
    guid: String
  )
  case class Order(
    guid: String
  )
  case class User(
    guid: String
  )

  case class SessionRepo (
    sessionService: SessionService
  )

}
