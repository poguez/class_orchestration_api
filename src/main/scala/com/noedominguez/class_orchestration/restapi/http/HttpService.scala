package com.noedominguez.class_orchestration.restapi.http

import akka.http.scaladsl.server.Directives._
import com.noedominguez.class_orchestration.restapi.http.routes.{AuthServiceRoute, ExplorationsServiceRoute, ExplorationObjectsServiceRoute, ExplorationEventsServiceRoute, TeamsServiceRoute, UsersServiceRoute}
import com.noedominguez.class_orchestration.restapi.services.{AuthService, ExplorationsService, ExplorationObjectsService, TeamsService, UsersService, ExplorationEventsService}
import com.noedominguez.class_orchestration.restapi.utils.CorsSupport

import scala.concurrent.ExecutionContext

class HttpService(usersService: UsersService,
                  authService: AuthService,
                  teamService: TeamsService,
                  explorationsService: ExplorationsService,
                  explorationEventsService: ExplorationEventsService,
                  explorationObjectsService: ExplorationObjectsService
                 )(implicit executionContext: ExecutionContext) extends CorsSupport {

  val usersRouter = new UsersServiceRoute(authService, usersService)
  val authRouter = new AuthServiceRoute(authService)
  val teamRouter = new TeamsServiceRoute(teamService)
  val explorationRouter = new ExplorationsServiceRoute(explorationsService)
  val explorationEventRouter = new ExplorationEventsServiceRoute(explorationEventsService)
  val explorationObjectRouter = new ExplorationObjectsServiceRoute(explorationObjectsService)

  val routes =
    pathPrefix("v1") {
      corsHandler {
        usersRouter.route ~
        teamRouter.route ~
        authRouter.route ~
        explorationRouter.route ~
        explorationEventRouter.route ~
        explorationObjectRouter.route
      }
    }

}
