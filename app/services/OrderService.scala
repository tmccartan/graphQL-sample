package services

import graphql.Models.Order

class OrderService {

  def get() = {
    Order(guid = "")
  }
  def get(guid: String) ={
    Order(guid = guid)
  }
}
