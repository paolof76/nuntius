package com.six.psd2

import java.io.File
import java.net.URI
import java.time.LocalDateTime
import java.util.Optional

import com.six.psd2.Psd2Lib.token
import com.six.psd2.Psd2Trait.Token
import org.apache.http.client.methods.{CloseableHttpResponse, HttpGet, HttpPost}
import org.apache.http.client.protocol.HttpClientContext
import org.apache.http.entity.StringEntity
import org.apache.http.entity.mime.MultipartEntityBuilder
import org.apache.http.entity.mime.content.FileBody
import org.apache.http.util.EntityUtils
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager
import org.apache.http.impl.client.{CloseableHttpClient, HttpClients}
import org.json4s._
import org.json4s.native.JsonMethods.parse

import scala.util.Try

/*
todo: password config out of code
todo: fixed URL over config file-..
 */
object Psd2Connector extends Psd2Trait {

  case class FundsAvailable(answer: Boolean, date: LocalDateTime, reqId: String)


  val url: String = if (conf.hasPath("url")) conf.getString("url") else "https://psd2-api.openbankproject.com/"
  val obpVer: String = if (conf.hasPath("obp-version")) conf.getString("obp-version") else "obp/v3.1.0/"
  val port: Int = if (conf.hasPath("port")) conf.getInt("port") else 8080
  val user: String = if (conf.hasPath("username")) conf.getString("username") else "nuntius"
  val pass: String = if (conf.hasPath("password")) conf.getString("password") else "@Nuntius1234"
  val obpKey: String = if (conf.hasPath("obp-key")) conf.getString("obpKey") else "o153kts4lby2cej3z4cm5d4lrlai0k5xrh4ewk03"
  val bank: String = if (conf.hasPath("bank")) conf.getString("bank") else "psd201-bank-x--uk"
  val viewId: String = if (conf.hasPath("view-id")) conf.getString("view-id") else "_test"



  val authUrl: String  = if (conf.hasPath("auth-url")) conf.getString("auth-url") else "my/logins/direct"
  val accessUrl: String = if (conf.hasPath("access-url")) conf.getString("access-url") else "/oauth/token"


  implicit val formats = DefaultFormats


  val hostUrl = url

  lazy val httpClient = {
    val connManager = new PoolingHttpClientConnectionManager()
    HttpClients.custom().setConnectionManager(connManager).build()
  }


  def queryAnyGet(url: String, token: String): String = {

    val httpclient: CloseableHttpClient = HttpClients.createDefault()
    val httpGet = new HttpGet(hostUrl + obpVer + url)
    httpGet.setHeader("Content-Type","application/json")
    httpGet.addHeader("Authorization", s"""DirectLogin token="$token"""")

    val res = httpclient.execute(httpGet)
    val resp = res.getEntity
    val result = EntityUtils.toString(resp)

    if (res != null) res.close()
    result
  }

  def queryAnyPost(url: String, token: String): String = {

    val httpclient: CloseableHttpClient = HttpClients.createDefault()
    val httpPost = new HttpPost(hostUrl + obpVer + url)
    httpPost.setHeader("Content-Type","application/json")
    httpPost.addHeader("Authorization", s"""DirectLogin token="$token"""")

    val res = httpclient.execute(httpPost)
    val resp = res.getEntity
    val result = EntityUtils.toString(resp)

    if (res != null) res.close()
    result
  }

  def queryAnyPut(url: String, token: String, body: String): String = {

    val httpclient: CloseableHttpClient = HttpClients.createDefault()
    val httpPut = new HttpPost(hostUrl + obpVer + url)
    httpPut.setHeader("Content-Type","application/json")
    httpPut.addHeader("Authorization", s"""DirectLogin token="$token"""")

    if (body.nonEmpty) {
      httpPut.setEntity(new StringEntity(body))
    }
    println(httpPut.getURI)
    val res = httpclient.execute(httpPut)
    val resp = res.getEntity
    val result = EntityUtils.toString(resp)

    if (res != null) res.close()
    result
  }

  def getToken(): String = {
    val key = doAuth(user, pass, obpKey)
    if (key.contains("token")) {
      val parsedKey = (parse(key) \ "token").extractOrElse("")

      parsedKey
    }
    else ""
  }

  def doAuth(user: String, pass: String, obpKey: String): String = {
    val uri = new URI(url + authUrl)
    val httpclient: CloseableHttpClient = HttpClients.createDefault()

    val authString = s"""DirectLogin username="${user}",   password="${pass}",  consumer_key="${obpKey}""""

    val httpPost = new HttpPost(uri)

    httpPost.setHeader("Content-type", "application/json")
    httpPost.setHeader("Authorization", authString)

    val response: CloseableHttpResponse = httpClient.execute(httpPost)

    val entity = response.getEntity
    val result = EntityUtils.toString(entity)

    if (response != null) response.close()

    result
  }

  def availFunds(token: String, bank: String, account: String, viewId: String, amount: String, currency: String): Optional[FundsAvailable] = {

    val availFundsUrl = s"banks/$bank/accounts/$account/$viewId/funds-available?amount=$amount&currency=$currency"

    val resp = queryAnyGet(availFundsUrl, token)

    val json = parse(resp)
    Optional.of[FundsAvailable](FundsAvailable(toBool((json \ "answer").extractOrElse("no")),
      LocalDateTime.parse((json \ "date").extractOrElse(LocalDateTime.now().toString)
        .filter(_!='Z')),
      (json \ "available_funds_request_id").extractOrElse("none")))
  }

  def createAccount(token: String, accountNo: String, bank: String, amount: String, currency: String) = {
    val createAccountUrl = s"banks/${bank}/accounts/${accountNo}"
    val userId = getUserId(token)

    val body = s"""
    {
      "user_id": "$userId",
      "label": "Label",
      "type": "CURRENT",
      "balance": {
        "currency": "$currency",
        "amount": "0"
      },
      "branch_id": "1234",
      "account_routing": {
        "scheme": "OBP",
        "address": "UK123456"
      }
    }"""

    println(body)
    val resp = queryAnyPut(createAccountUrl, token, body)
    println(resp)


  }

  def generateNextAccountNo(token: String, bank: String) = {

  }

  def getUserId(token: String): String = {
    val userUrl = "users/current"

    val resp = queryAnyGet(userUrl, token)

    println(resp)
    val json = parse(resp)
    (json \ "user_id").extractOrElse("")

  }

}



