package com.noedominguez.class_orchestration.restapi.http.routes

import akka.http.scaladsl.server.Directives._
import com.noedominguez.class_orchestration.restapi.services.TeamsService
import de.heikoseeberger.akkahttpcirce.{CirceSupport, FailFastCirceSupport}
import io.circe.generic.auto._
import io.circe.syntax._

import scala.concurrent.ExecutionContext




class TeamsServiceRoute(teamsService: TeamsService)(implicit executionContext: ExecutionContext)
  extends FailFastCirceSupport {

  import teamsService._

  val route = pathPrefix("teams") {
    pathEndOrSingleSlash {
      get {
        complete(getTeams().map(_.asJson))
      }
    }
  }
}
