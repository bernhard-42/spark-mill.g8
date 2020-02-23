import mill._, scalalib._
import mill.modules.Assembly

object SparkApp extends ScalaModule {
  def scalaVersion = "2.12.10"
  def scalacOptions =
    Seq("-encoding", "utf-8", "-explaintypes", "-feature", "-deprecation")

  def ivySparkDeps = Agg(
    ivy"org.apache.spark::spark-sql:2.4.5"
      .exclude("org.slf4j" -> "slf4j-log4j12"),
    ivy"org.slf4j:slf4j-api:1.7.16",
    ivy"org.slf4j:slf4j-log4j12:1.7.16"
  )

  def ivyBaseDeps = Agg(
    ivy"com.lihaoyi::upickle:0.9.7"
  )

  // STANDALONE APP
  def ivyDeps = ivyBaseDeps ++ ivySparkDeps

  // REMOTE SPARK CLUSTER
  // def ivyDeps = ivyBaseDeps
  // def compileIvyDeps = ivySparkDeps
  // def assemblyRules =
  //   Assembly.defaultRules ++
  //     Seq(
  //       "scala/.*",
  //       "org.slf4j.*",
  //       "org.apache.log4j.*"
  //     ).map(Assembly.Rule.ExcludePattern.apply)
}
