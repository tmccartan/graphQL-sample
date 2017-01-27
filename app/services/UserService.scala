package services

import java.util.UUID

import graphql.Models.User

class UserService {

  def get() = {
    User(UUID.randomUUID().toString)
  }
  def get(guid: String) ={
    User(guid = guid)
  }
}
