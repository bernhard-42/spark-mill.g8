// build.sc
import mill._, scalalib._

object $name$ extends ScalaModule {
  def scalaVersion = "$scala_version$"
  def ivyDeps = Agg(
    ivy"net.liftweb::lift-json:3.3.0",
    ivy"ch.qos.logback:logback-classic:1.2.3",
    ivy"com.typesafe.scala-logging::scala-logging:3.9.0"
  )
  def scalacOptions = Seq("-encoding", "utf-8", "-explaintypes", "-feature", "-deprecation")
}