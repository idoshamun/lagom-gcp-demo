package com.example.hello.api

import akka.{Done, NotUsed}
import com.lightbend.lagom.scaladsl.api.broker.Topic
import com.lightbend.lagom.scaladsl.api.broker.kafka.{KafkaProperties, PartitionKeyStrategy}
import com.lightbend.lagom.scaladsl.api.{Service, ServiceCall}
import play.api.libs.json.{Format, Json}

object LagomgcpdemoService {
  val TOPIC_NAME = "greetings"
}

/**
  * The lagom-gcp-demo service interface.
  * <p>
  * This describes everything that Lagom needs to know about how to serve and
  * consume the LagomgcpdemoService.
  */
trait LagomgcpdemoService extends Service {

  /**
    * Example: curl http://localhost:9000/api/hello
    */
  def hello(): ServiceCall[NotUsed, String]

  override final def descriptor = {
    import Service._
    // @formatter:off
    named("lagom-gcp-demo")
      .withCalls(
        pathCall("/api/hello", hello _)
      )
      .withAutoAcl(true)
    // @formatter:on
  }
}