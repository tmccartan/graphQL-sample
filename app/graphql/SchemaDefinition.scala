package graphql

import graphql.Models._
import sangria.schema._

import scala.concurrent.ExecutionContext

object SchemaDefinition {

  implicit val ec: ExecutionContext = play.api.libs.concurrent.Execution.Implicits.defaultContext

  val PaymentMethodType = ObjectType(
    "PaymentMethod", "A payment method",
    fields[PaymentMethod, PaymentMethod] (
      Field("guid", StringType,
        Some(""), resolve = _.value.guid.toString),
      Field("type", StringType,
        Some(""), resolve = _.value.pmType.toString),
      Field("cvn" , IntType,
        Some("The card cvn"), resolve = _.value.cvn)
    )
  )
  val AddressType = ObjectType(
    "Address", "A address object",
    fields[Address, Address] (
      Field("guid", StringType,
        Some("Address guid"), resolve = _.value.guid.toString),
      Field("line_1", StringType,
        Some("Address linw 1"), resolve = _.value.line1.toString),
      Field("line_2", StringType,
        Some("Address line 2"), resolve = _.value.line2.toString),
      Field("state", StringType,
        Some("Address state"), resolve = _.value.state.toString),
      Field("country", StringType,
        Some("Address country"), resolve = _.value.country.toString)
    )
  )
  val UserType = ObjectType(
    "User", "A user object",
    fields[User, User] (
      Field("guid", StringType,
        Some(""), resolve = _.value.guid.toString),
      Field("first_name", StringType,
        Some("first name"), resolve = _.value.firstName.toString),
      Field("last_name", StringType,
        Some("last name"), resolve = _.value.lastName.toString),
      Field("email", StringType,
        Some("email"), resolve = _.value.email.toString)
    )
  )
  val OrderType = ObjectType(
    "Order", "A order object",
    fields[Order, Order] (
      Field("guid", StringType,
        Some(""), resolve = _.value.guid.toString)
    )
  )
  val SessionType = ObjectType(
    "Session", "A checkout session",
    fields[Session, Session](
      Field("guid", StringType,
        Some("The session guid"), resolve = _.value.guid),
      Field("user", UserType,
        Some("The user checking out"), resolve = _.value.user),
      Field("addresses", ListType(AddressType),
        Some("The available addresses"), resolve = _.value.addresses),
      Field("payment_methods", ListType(PaymentMethodType),
        Some("The available payment methods"), resolve = _.value.paymentMethods),
      Field("order", OrderType,
        Some("The order"), resolve = _.value.order),
      Field("selected_address", AddressType,
        Some("The selected shipping address"), resolve = _.value.address),
      Field("selected_payment_method", PaymentMethodType,
        Some("The selected payment method"), resolve = _.value.paymentMethod)
    )
  )

  val SessionGuid = Argument("guid", OptionInputType(StringType), description = "")

  val Query = ObjectType (
    "Query", fields[SessionRepo, Unit] (
      Field("session",
        SessionType,
        arguments = SessionGuid :: Nil,
        resolve = ctx => ctx.ctx.sessionService.get(ctx.argOpt(SessionGuid.name))
      ),
      Field("payment_methods",
        ListType(PaymentMethodType),
        resolve = ctx => ctx.ctx.sessionService.get().paymentMethods
      ),
      Field("addresses",
        ListType(AddressType),
        resolve = ctx => ctx.ctx.sessionService.get().addresses
      ),
      Field("address",
        AddressType,
        resolve = ctx => ctx.ctx.sessionService.get().addresses.head
      ),
      Field("payment_method",
        PaymentMethodType,
        resolve = ctx => ctx.ctx.sessionService.get().paymentMethods.head
      ),
      Field("user",
        UserType,
        resolve = ctx => ctx.ctx.sessionService.get().user
      )
    )
  )

//  case class CreateCheckoutSessionPayload(userGuid: String) extends Mutation
//
//
//  val createCheckoutMutation = ObjectType("Mutation", fields[SessionRepo, Unit](
//    Field("createSession", Query,
//      arguments = SessionGuid :: Nil,
//      resolve = ctx => UpdateCtx(ctx.ctx.sessionService.createSession())
//  )))


  //val Mutation = ObjectType("Mutation", fields[SessionRepo, Unit](createCheckoutMutation))

  val CheckoutSchema = Schema(Query)//, Some(Mutation))
}



