import _root_.sbt.Keys._

lazy val root = (project in file(".")).enablePlugins(PlayScala)
name := "GraphQL Sample Project"
scalaVersion := "2.11.8"

lazy val thirdPartyDependencies: Seq[ModuleID] = Seq(
  play.sbt.PlayImport.ws withSources(),
  "org.sangria-graphql" %% "sangria" % "1.0.0-RC2",
  "org.sangria-graphql" %% "sangria-play-json" % "0.3.3"
)


libraryDependencies ++= thirdPartyDependencies