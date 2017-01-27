package controllers

import play.api.mvc._

class Healthcheck extends Controller{

  def get() = Action{ implicit request =>
    Ok("Play 2.5 boilerplate")
  }
}
