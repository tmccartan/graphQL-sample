package controllers


import graphql.Models.SessionRepo
import graphql.SchemaDefinition
import play.api.libs.json.{Json, JsObject}
import play.api.mvc.{Action, Controller}
import sangria.execution.{ErrorWithResolver, QueryAnalysisError, Executor}
import sangria.parser.{SyntaxError, QueryParser}
import sangria.marshalling.playJson._
import sangria.renderer.SchemaRenderer
import services.SessionService

import scala.concurrent.Future
import scala.util.{Failure, Success}
import scala.concurrent.ExecutionContext.Implicits.global

class GraphQL extends Controller {

  def post() = Action.async { implicit request =>

    val text = request.body.asText.get
    val result = executeQuery(text, None, None)
    result
  }

  private def executeQuery(query: String, variables: Option[JsObject], operation: Option[String]) =

    QueryParser.parse(query) match {
      // query parsed successfully, time to execute it!
      case Success(queryAst) =>
        println(SchemaRenderer.renderSchema(SchemaDefinition.CheckoutSchema))
        println(queryAst.renderPretty)
        Executor.execute( SchemaDefinition.CheckoutSchema, queryAst, SessionRepo(new SessionService),
        operationName = operation
      ).map(result => Ok(result)).recover {
        case error: QueryAnalysisError ⇒ BadRequest(error.resolveError)
        case error: ErrorWithResolver ⇒ InternalServerError(error.resolveError)
      }


      // can't parse GraphQL query, return error
      case Failure(error: SyntaxError) ⇒
        Future.successful(BadRequest(Json.obj(
          "syntaxError" → error.getMessage,
          "locations" → Json.arr(Json.obj(
            "line" → error.originalError.position.line,
            "column" → error.originalError.position.column)))))

      case Failure(error) ⇒
        throw error
    }
}
