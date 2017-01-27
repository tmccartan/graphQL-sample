package services

import java.util.UUID

import graphql.Models.Session

class SessionService {

  val userService = new UserService()
  val paymentService = new PaymentMethodService()
  val orderService = new OrderService()
  val addressService = new AddressService()

  def get() = {
    Session(UUID.randomUUID().toString,
      userService.get(),
      paymentService.getDummyList(),
      paymentService.get(),
      Seq(addressService.get()),
      addressService.get(),
      orderService.get()
      )
  }
  def get(guid: String) ={
    Session(guid,
      userService.get(),
      paymentService.getDummyList(),
      paymentService.get(),
      Seq(addressService.get()),
      addressService.get(),
      orderService.get()
    )
  }
}
