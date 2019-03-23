package com.six.psd2

import java.io.{File, PrintWriter}
import java.time.{LocalDate, LocalDateTime}

import com.typesafe.config.ConfigFactory
import com.typesafe.scalalogging.LazyLogging

import scala.collection.mutable.{ListBuffer, Map}
import scala.util.{Failure, Success, Try}
import scala.io.Codec
import java.nio.charset.CodingErrorAction

object Psd2Trait {

  case class Token(key: String, datetime: LocalDateTime)
}
trait Psd2Trait extends LazyLogging {

  val conf = ConfigFactory.load()

  def toBool(a: String): Boolean = {
    if (a.toLowerCase().contains("yes", "true")) true
    else false
  }

}
