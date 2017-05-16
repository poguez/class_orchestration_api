package com.noedominguez.class_orchestration.restapi.http

import akka.http.scaladsl.server.directives.{BasicDirectives, FutureDirectives, HeaderDirectives, RouteDirectives}
import akka.http.scaladsl.server.Directive1
import com.noedominguez.class_orchestration.restapi.models.UserEntity
import com.noedominguez.class_orchestration.restapi.services.AuthService

trait SecurityDirectives {

  import BasicDirectives._
  import HeaderDirectives._
  import RouteDirectives._
  import FutureDirectives._

  def authenticate: Directive1[UserEntity] = {
    headerValueByName("Token").flatMap { token =>
      onSuccess(authService.authenticate(token)).flatMap {
        case Some(user) => provide(user)
        case None       => reject
      }
    }
  }

  protected val authService: AuthService

}
