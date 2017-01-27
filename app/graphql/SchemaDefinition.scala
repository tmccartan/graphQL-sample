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
        Some(""), resolve = _.value.pm_type.toString)
    )
  )
  val AddressType = ObjectType(
    "Address", "A address object",
    fields[Address, Address] (
      Field("guid", StringType,
        Some(""), resolve = _.value.guid.toString)
    )
  )
  val UserType = ObjectType(
    "User", "A user object",
    fields[User, User] (
      Field("guid", StringType,
        Some(""), resolve = _.value.guid.toString)
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
        Some(""), resolve = _.value.guid),
      Field("user", UserType,
        Some(""), resolve = _.value.user),
      Field("addresses", ListType(AddressType),
        Some(""), resolve = _.value.addresses),
      Field("payment_methods", ListType(PaymentMethodType),
        Some(""), resolve = _.value.paymentMethods),
      Field("order", OrderType,
        Some(""), resolve = _.value.order),
      Field("address", AddressType,
        Some(""), resolve = _.value.address),
      Field("paymentMethod", PaymentMethodType,
        Some(""), resolve = _.value.paymentMethod)
    )
  )

  val SessionGuid = Argument("guid", StringType, description = "")

  val Query = ObjectType (
    "Query", fields[SessionRepo, Unit] (
      Field("session",
        SessionType,
        arguments = SessionGuid :: Nil,
        resolve = ctx => ctx.ctx.sessionService.get(ctx arg SessionGuid)
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

  val CheckoutSchema = Schema(Query)
}



