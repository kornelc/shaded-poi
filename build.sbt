import sbtassembly.AssemblyPlugin.autoImport.ShadeRule

name := "shaded-poi"

scalaVersion := "2.12.8"

scalacOptions += "-Ypartial-unification"

libraryDependencies ++= Seq(
  "org.apache.poi" % "poi-ooxml" % "5.0.0",
  "org.apache.poi" % "poi-ooxml-full" % "5.0.0"
)

assemblyMergeStrategy in assembly := {
  case PathList("module-info.class") => MergeStrategy.discard
  case PathList("org", "apache", "batik", xs @ _*) => MergeStrategy.discard
  case PathList("org", "apache", "xmlgraphics", xs @ _*) => MergeStrategy.last
  case x if x.endsWith("/module-info.class") => MergeStrategy.discard
  case x =>
    val oldStrategy = (assemblyMergeStrategy in assembly).value
    oldStrategy(x)
}

mainClass in assembly := Some("com.synkre.gains.GainsAppServer")
test in assembly := {} // skip test when running assembly

assemblyJarName in assembly := "shaded-poi.jar"

assemblyShadeRules in assembly := Seq(
  ShadeRule
    .rename("org.apache.commons.io.**" -> "myconfio.@1")
    .inAll,
  ShadeRule
    .rename("org.apache.commons.compress.**" -> "myconfcompress.@1")
    .inAll,
)
