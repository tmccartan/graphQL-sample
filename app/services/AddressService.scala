package services

import java.util.UUID

import graphql.Models.Address

class AddressService {

  private def dummyAddress ={
    Address(guid = UUID.randomUUID().toString, line1 = "2 Park Avenue", line2 = "4th Floor", state = "NY" , country = "USA")
  }
  def get() = {
    dummyAddress
  }
  def get(guid: String) ={
    dummyAddress.copy(guid = guid)
  }
}
