package services

import java.util.UUID

import graphql.Models.User

class UserService {

  private def dummyUser = {
    User(UUID.randomUUID().toString, "Bugs", "Bunny","bunny@gilt.com")
  }

  def get() = {
    dummyUser
  }
  def get(guid: String) ={
    dummyUser.copy(guid = guid)
  }
}
