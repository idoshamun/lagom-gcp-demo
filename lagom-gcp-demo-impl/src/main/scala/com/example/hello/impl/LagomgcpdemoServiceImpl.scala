package com.example.hello.impl

import com.example.hello.api.LagomgcpdemoService
import com.google.cloud.MonitoredResource
import com.google.cloud.logging.Logging.WriteOption
import com.google.cloud.logging.Payload.StringPayload
import com.google.cloud.logging.{LogEntry, Logging, LoggingOptions}
import com.lightbend.lagom.scaladsl.api.ServiceCall

import scala.collection.JavaConverters._
import scala.concurrent.Future

/**
  * Implementation of the LagomgcpdemoService.
  */
class LagomgcpdemoServiceImpl() extends LagomgcpdemoService {

  val logging: Logging = LoggingOptions.getDefaultInstance.getService

  override def hello() = ServiceCall { _ =>
    val entry = LogEntry.newBuilder(StringPayload.of("Hello world!")).build
    logging.write(List(entry).asJava, WriteOption.logName("lagom"),
      WriteOption.resource(MonitoredResource.newBuilder("global").build()))

    Future.successful("Hello World")
  }
}
