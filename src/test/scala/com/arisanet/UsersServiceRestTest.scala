package com.arisanet

/**
  * Created by poguez on 5/18/17.
  */

import org.scalatest.{Matchers, WordSpec}
import akka.http.scaladsl.model._
import akka.http.scaladsl.testkit.ScalatestRouteTest
import akka.http.scaladsl.server._
import Directives._
import akka.util.ByteString
import com.noedominguez.class_orchestration.restapi.models.UserEntity

import scala.concurrent.{Await, Future}
import scala.util.Random

class UsersServiceRestTest extends WordSpec with Matchers with ScalatestRouteTest {
  val smallRoute =
    get {
      pathSingleSlash {
        complete {
          "Captain on the bridge!"
        }
      } ~
        path("users") {
          complete("[]")
        }
        path("user") {
          complete("[]")
        }
    }

  "The User service" should {
    "return no user in index users" in {
      Get("/user") ~> smallRoute ~> check {
        responseAs[String] shouldEqual "[]"
      }
    }
    "create a user from JSON" in {
      val jsonRequest = ByteString(
        s"""
           |{
           |  "id": 0,
           |  "username": "string",
           |  "password": "meow",
           |  "isAdmin": true,
           |  "teamId": 1
           |}
        """.stripMargin)

      val postRequest = HttpRequest(
        HttpMethods.POST,
        uri = "/user/new",
        entity = HttpEntity(MediaTypes.`application/json`, jsonRequest))


      postRequest ~> smallRoute ~> check {
        responseAs[String] shouldEqual s"""
                                          |{
                                          |  "id": 0,
                                          |  "username": "string",
                                          |  "password": "meow",
                                          |  "isAdmin": true,
                                          |  "teamId": 1
                                          |}
                                          """.stripMargin
      }
    }

  }
}
