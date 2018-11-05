// build.sc
import mill._, scalalib._

object $name$ extends ScalaModule{
  def scalaVersion = "$scala_version$"
  def ivyDeps = Agg(
    ivy"org.scalatest::scalatest:3.0.5",
    ivy"log4j:log4j:1.2.17",
    ivy"org.apache.spark::spark-sql:$spark_version$",
    ivy"org.apache.spark::spark-hive:$spark_version$",
    ivy"org.apache.spark::spark-yarn:$spark_version$"
  )
}