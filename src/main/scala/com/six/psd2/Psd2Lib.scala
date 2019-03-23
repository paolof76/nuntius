package com.six.psd2


import java.time.LocalDateTime
import java.util.Optional

import Psd2Connector._
import com.six.psd2.Psd2Trait._
/*
todo: define case classes for every call, define call list,
what to do with properties of a call?
logon stuff

 */

object Psd2Lib extends Psd2Trait {

  val tokenValidMinutes = if (conf.hasPath("token-valid-minutes")) conf.getInt("token-valid-minutes") else 10

  var token = Token("", LocalDateTime.MIN)

  def main(args: Array[String]): Unit = {
    println(getAvailableFunds("100008", 50.0, "EUR"))
    createAccount(token.key, "1000025", bank, "300", "EUR")
    getAccountById(token.key, "100008")
  }

  private def checkToken(): Boolean = {
    if (token.datetime.isBefore(LocalDateTime.now().minusMinutes(tokenValidMinutes))) {
      val key = getToken()
      if (key.nonEmpty) {
        token = Token(key, LocalDateTime.now())
        println("got Token: " + token)
        true
      }
      else false
    }
    else true
  }


  def getAvailableFunds(account: String, amount: Double, currency: String): java.util.Optional[FundsAvailable] = {
    if (checkToken()) {
      availFunds(token.key, bank, account, viewId, amount.toString.filter(_!='.'), currency)
    }
    else Optional.empty()

  }




}
