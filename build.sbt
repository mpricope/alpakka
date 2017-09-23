lazy val alpakka = project
  .in(file("."))
  .enablePlugins(PublishUnidoc)
  .aggregate(
    amqp,
    awslambda,
    azureStorageQueue,
    cassandra,
    csv,
    dynamodb,
    elasticsearch,
    files,
    ftp,
    geode,
    googleCloudPubSub,
    hbase,
    ironmq,
    jms,
    kinesis,
    mqtt,
    s3,
    simpleCodecs,
    slick,
    sns,
    sqs,
    sse,
    eip,
    xml
  )
  .settings(
    onLoadMessage :=
      """
        |** Welcome to the sbt build definition for Alpakka! **
        |
        |Useful sbt tasks:
        |
        |  docs/local:paradox - builds documentation with locally
        |    linked Scala API docs, which can be found at
        |    docs/target/paradox/site/local
        |
        |  test - runs all the tests for all of the connectors.
        |   Make sure to run `docker-compose up` first.
        |
        |  mqtt/testOnly *.MqttSourceSpec - runs a single test
      """.stripMargin
  )

lazy val amqp = project
  .enablePlugins(AutomateHeaderPlugin)
  .settings(
    name := "akka-stream-alpakka-amqp",
    Dependencies.Amqp
  )

lazy val awslambda = project
  .enablePlugins(AutomateHeaderPlugin)
  .settings(
    name := "akka-stream-alpakka-awslambda",
    Dependencies.AwsLambda,
    // For mockito https://github.com/akka/alpakka/issues/390
    parallelExecution in Test := false
  )

lazy val azureStorageQueue = project
  .in(file("azure-storage-queue"))
  .enablePlugins(AutomateHeaderPlugin)
  .settings(
    name := "akka-stream-alpakka-azure-storage-queue",
    Dependencies.AzureStorageQueue
  )

lazy val cassandra = project
  .enablePlugins(AutomateHeaderPlugin)
  .settings(
    name := "akka-stream-alpakka-cassandra",
    Dependencies.Cassandra
  )

lazy val csv = project
  .enablePlugins(AutomateHeaderPlugin)
  .settings(
    name := "akka-stream-alpakka-csv",
    Dependencies.Csv
  )

lazy val dynamodb = project
  .enablePlugins(AutomateHeaderPlugin)
  .settings(
    name := "akka-stream-alpakka-dynamodb",
    Dependencies.DynamoDB
  )

lazy val elasticsearch = project
  .enablePlugins(AutomateHeaderPlugin)
  .settings(
    name := "akka-stream-alpakka-elasticsearch",
    Dependencies.Elasticsearch
  )

lazy val files = project // The name file is taken by `sbt.file`!
  .in(file("file"))
  .enablePlugins(AutomateHeaderPlugin)
  .settings(
    name := "akka-stream-alpakka-file",
    Dependencies.File
  )

lazy val ftp = project
  .enablePlugins(AutomateHeaderPlugin)
  .settings(
    name := "akka-stream-alpakka-ftp",
    Dependencies.Ftp,
    parallelExecution in Test := false,
    fork in Test := true,
    // To avoid potential blocking in machines with low entropy (default is `/dev/random`)
    javaOptions in Test += "-Djava.security.egd=file:/dev/./urandom"
  )

lazy val geode = project
  .enablePlugins(AutomateHeaderPlugin)
  .settings(
    name := "akka-stream-alpakka-geode",
    Dependencies.Geode,
    fork in Test := true,
    parallelExecution in Test := false
  )

lazy val googleCloudPubSub = project
  .in(file("google-cloud-pub-sub"))
  .enablePlugins(AutomateHeaderPlugin)
  .settings(
    name := "akka-stream-alpakka-google-cloud-pub-sub",
    Dependencies.GooglePubSub,
    fork in Test := true,
    envVars in Test := Map("PUBSUB_EMULATOR_HOST" -> "localhost:8538"),
    // For mockito https://github.com/akka/alpakka/issues/390
    parallelExecution in Test := false
  )

lazy val hbase = project
  .enablePlugins(AutomateHeaderPlugin)
  .settings(
    name := "akka-stream-alpakka-hbase",
    Dependencies.HBase,
    fork in Test := true
  )

lazy val ironmq = project
  .enablePlugins(AutomateHeaderPlugin)
  .settings(
    name := "akka-stream-alpakka-ironmq",
    Dependencies.IronMq
  )

lazy val jms = project
  .enablePlugins(AutomateHeaderPlugin)
  .settings(
    name := "akka-stream-alpakka-jms",
    Dependencies.Jms,
    parallelExecution in Test := false
  )

lazy val kinesis = project
  .enablePlugins(AutomateHeaderPlugin)
  .settings(
    name := "akka-stream-alpakka-kinesis",
    Dependencies.Kinesis,
    // For mockito https://github.com/akka/alpakka/issues/390
    parallelExecution in Test := false
  )

lazy val mqtt = project
  .enablePlugins(AutomateHeaderPlugin)
  .settings(
    name := "akka-stream-alpakka-mqtt",
    Dependencies.Mqtt
  )

lazy val s3 = project
  .enablePlugins(AutomateHeaderPlugin)
  .settings(
    name := "akka-stream-alpakka-s3",
    Dependencies.S3
  )

lazy val simpleCodecs = project
  .in(file("simple-codecs"))
  .enablePlugins(AutomateHeaderPlugin)
  .settings(
    name := "akka-stream-alpakka-simple-codecs",
    // By default scalatest futures time out in 150 ms, dilate that to 600ms.
    // This should not impact the total test time as we don't expect to hit this
    // timeout, and indeed it doesn't appear to.
    testOptions in Test += Tests.Argument(TestFrameworks.ScalaTest, "-F", "4")
  )

lazy val slick = project
  .enablePlugins(AutomateHeaderPlugin)
  .settings(
    name := "akka-stream-alpakka-slick",
    Dependencies.Slick
  )

lazy val sns = project
  .enablePlugins(AutomateHeaderPlugin)
  .settings(
    name := "akka-stream-alpakka-sns",
    Dependencies.Sns,
    // For mockito https://github.com/akka/alpakka/issues/390
    parallelExecution in Test := false
  )

lazy val sqs = project
  .in(file("sqs"))
  .enablePlugins(AutomateHeaderPlugin)
  .settings(
    name := "akka-stream-alpakka-sqs",
    Dependencies.Sqs,
    // For mockito https://github.com/akka/alpakka/issues/390
    parallelExecution in Test := false
  )

lazy val sse = project
  .enablePlugins(AutomateHeaderPlugin)
  .settings(
    name := "akka-stream-alpakka-sse",
    Dependencies.Sse
  )

lazy val xml = project
  .enablePlugins(AutomateHeaderPlugin)
  .settings(
    name := "akka-stream-alpakka-xml",
    Dependencies.Xml
  )

lazy val eip = project
  .enablePlugins(AutomateHeaderPlugin)
  .settings(
    name := "akka-stream-alpakka-eip"
  )

val Local = config("local")
val defaultParadoxSettings: Seq[Setting[_]] = Seq(
  paradoxTheme := Some(builtinParadoxTheme("generic")),
  paradoxProperties ++= Map(
    "version" -> version.value,
    "scalaVersion" -> scalaVersion.value,
    "scalaBinaryVersion" -> scalaBinaryVersion.value,
    "akkaVersion" -> Dependencies.AkkaVersion,
    "akkaHttpVersion" -> Dependencies.AkkaHttpVersion,
    "extref.akka-docs.base_url" -> s"http://doc.akka.io/docs/akka/${Dependencies.AkkaVersion}/%s",
    "extref.akka-http-docs.base_url" -> s"http://doc.akka.io/docs/akka-http/${Dependencies.AkkaHttpVersion}/%s",
    "extref.java-api.base_url" -> "https://docs.oracle.com/javase/8/docs/api/index.html?%s.html",
    "extref.javaee-api.base_url" -> "https://docs.oracle.com/javaee/7/api/index.html?%s.html",
    "extref.paho-api.base_url" -> "https://www.eclipse.org/paho/files/javadoc/index.html?%s.html",
    "scaladoc.akka.base_url" -> s"http://doc.akka.io/api/akka/${Dependencies.AkkaVersion}",
    "scaladoc.akka.stream.alpakka.base_url" -> s"http://developer.lightbend.com/docs/api/alpakka/${version.value}"
  ),
  sourceDirectory := baseDirectory.value / "src" / "main"
)

lazy val docs = project
  .enablePlugins(ParadoxPlugin, NoPublish)
  .disablePlugins(BintrayPlugin)
  .settings(
    name := "Alpakka",
    inConfig(Compile)(defaultParadoxSettings),
    ParadoxPlugin.paradoxSettings(Local),
    inConfig(Local)(defaultParadoxSettings),
    paradoxGroups := Map("Language" -> Seq("Scala", "Java")),
    paradoxProperties in Local ++= Map(
      // point API doc links to locally generated API docs
      "scaladoc.akka.stream.alpakka.base_url" -> rebase(
        (baseDirectory in alpakka).value,
        "../../../../../"
      )((sbtunidoc.Plugin.UnidocKeys.unidoc in alpakka in Compile).value.head).get
    )
  )