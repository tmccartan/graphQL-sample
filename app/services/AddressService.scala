package services

import graphql.Models.Address

class AddressService {

  def get() = {
    Address(guid = "")
  }
  def get(guid: String) ={
    Address(guid = guid)
  }
}
