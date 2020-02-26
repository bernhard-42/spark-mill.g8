import mill._, scalalib._
import mill.modules.Assembly

object $name$ extends ScalaModule { outer =>
  def scalaVersion = "$scala_version$"
  def scalacOptions =
    Seq("-encoding", "utf-8", "-explaintypes", "-feature", "-deprecation")

  def sparkIvyDeps =
    Agg(
      ivy"org.apache.spark::spark-sql:$spark_version$"
    )

  // def ivyDeps = Agg( )

  def compileIvyDeps = sparkIvyDeps

  def assemblyRules =
    Assembly.defaultRules ++
      Seq(
        "scala/.*",
        "org.slf4j.*",
        "org.apache.log4j.*"
      ).map(Assembly.Rule.ExcludePattern.apply)
      
  object standalone extends ScalaModule {
    def mainClass = T { Some("$package$.$name$") }
    def scalaVersion = outer.scalaVersion
    def moduleDeps = Seq(outer)
    def ivyDeps = outer.sparkIvyDeps
  }
}
