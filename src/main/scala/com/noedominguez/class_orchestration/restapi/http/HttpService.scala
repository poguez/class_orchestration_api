package com.noedominguez.class_orchestration.restapi.http

import akka.http.scaladsl.server.Directives._
import com.noedominguez.class_orchestration.restapi.http.routes.{AuthServiceRoute, TeamsServiceRoute, UsersServiceRoute}
import com.noedominguez.class_orchestration.restapi.services.AuthService
import com.noedominguez.class_orchestration.restapi.services.UsersService
import com.noedominguez.class_orchestration.restapi.services.TeamsService
import com.noedominguez.class_orchestration.restapi.utils.CorsSupport

import scala.concurrent.ExecutionContext

class HttpService(usersService: UsersService,
                  authService: AuthService,
                  teamService: TeamsService
                 )(implicit executionContext: ExecutionContext) extends CorsSupport {

  val usersRouter = new UsersServiceRoute(authService, usersService)
  val authRouter = new AuthServiceRoute(authService)
  val teamRouter = new TeamsServiceRoute(teamService)

  val routes =
    pathPrefix("v1") {
      corsHandler {
        usersRouter.route ~
        teamRouter.route ~
        authRouter.route
      }
    }

}
