ThisBuild / version := "0.1.0-SNAPSHOT"

ThisBuild / scalaVersion := "3.3.1"

lazy val root = (project in file("."))
  .settings(
    name := "advent-of-code-2023",
    libraryDependencies ++= Seq(
      "com.github.haifengl" %% "smile-scala" % "3.0.2",
      "org.typelevel"       %% "cats-core"   % "2.10.0"
    )
  )
