plugins {
  id("io.micronaut.application") version "4.5.3"
  id("com.gradleup.shadow") version "8.3.6"
}

val micronautVersion: String by project
val groupVersion: String by project
val groupId: String by project
val micronautReactor: String by project
val micronautDataProcessor: String by project
val postgresR2dbcVersion: String by project
val micronautDataR2dbc: String by project
val testContainersVersion: String by project
val slf4jVersion: String by project
val jupiterVersion: String by project
val assertJVersion: String by project
val snakeyamlVersion: String by project
val postgresR2dbcDriverVersion: String by project
val postgresJdbcDriverVersion: String by project
val dataRsqlParserVersion: String by project

version = groupId
group = groupVersion

repositories {
  mavenCentral()
}

configurations.all {
  resolutionStrategy {
    force("io.micronaut.platform:micronaut-platform:$micronautVersion")
  }
}

dependencies {
  annotationProcessor("io.micronaut:micronaut-http-validation")
  annotationProcessor("io.micronaut.serde:micronaut-serde-processor")
  annotationProcessor("io.micronaut.data:micronaut-data-processor:$micronautDataProcessor")

  implementation("io.micronaut.serde:micronaut-serde-jackson")
  implementation("io.micronaut:micronaut-management")
  implementation("org.postgresql:r2dbc-postgresql:$postgresR2dbcVersion")
  implementation("io.micronaut.data:micronaut-data-r2dbc:$micronautDataR2dbc")
  implementation("io.micronaut.data:micronaut-data-jpa")
  implementation("io.micronaut.reactor:micronaut-reactor:$micronautReactor")

  compileOnly("io.micronaut:micronaut-http-client")


  runtimeOnly("ch.qos.logback:logback-classic")
  runtimeOnly("org.yaml:snakeyaml:${snakeyamlVersion}")


  testImplementation("org.assertj:assertj-core:${assertJVersion}")
  testImplementation("org.junit.jupiter:junit-jupiter:${jupiterVersion}")
  testImplementation("io.projectreactor:reactor-test")
  testImplementation("org.testcontainers:postgresql:${testContainersVersion}")
  testImplementation("org.testcontainers:r2dbc:${testContainersVersion}")
  testImplementation("org.testcontainers:junit-jupiter:${testContainersVersion}")

  testRuntimeOnly("org.postgresql:postgresql:${postgresJdbcDriverVersion}")
}


application {
  mainClass = "com.books.authors.Application"
}
java {
  sourceCompatibility = JavaVersion.toVersion("21")
  targetCompatibility = JavaVersion.toVersion("21")
}


graalvmNative.toolchainDetection = false

micronaut {
  runtime("netty")
  testRuntime("junit5")
  processing {
    incremental(true)
    annotations("com.example.*")
  }
}




