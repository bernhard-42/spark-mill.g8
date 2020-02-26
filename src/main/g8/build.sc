import mill._, scalalib._
import mill.modules.Assembly

object $name$ extends ScalaModule { outer =>
  def scalaVersion = "2.12.10"
  def scalacOptions =
    Seq("-encoding", "utf-8", "-explaintypes", "-feature", "-deprecation")

  def sparkIvyDeps =
    Agg(
      ivy"org.apache.spark::spark-sql:$spark_version$"
        .exclude("org.slf4j" -> "slf4j-log4j12"),
      ivy"org.slf4j:slf4j-api:$slf4j_version$",
      ivy"org.slf4j:slf4j-log4j12:$slf4j_version$"
    )

  def appIvyDeps = Agg(
    ivy"com.lihaoyi::ujson:1.0.0"
  )

  def ivyDeps = appIvyDeps ++ sparkIvyDeps

  object remote extends ScalaModule {
    def scalaVersion = outer.scalaVersion
    def ivyDeps = outer.appIvyDeps
    def compileIvyDeps = outer.sparkIvyDeps
    def assemblyRules =
      Assembly.defaultRules ++
        Seq(
          "scala/.*",
          "org.slf4j.*",
          "org.apache.log4j.*"
        ).map(Assembly.Rule.ExcludePattern.apply)

  }
}
