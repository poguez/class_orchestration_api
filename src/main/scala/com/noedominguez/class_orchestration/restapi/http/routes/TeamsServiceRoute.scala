package com.noedominguez.class_orchestration.restapi.http.routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model.StatusCodes
import com.noedominguez.class_orchestration.restapi.services.TeamsService
import com.noedominguez.class_orchestration.restapi.models.{TeamEntity, TeamEntityUpdate}
import de.heikoseeberger.akkahttpcirce.{CirceSupport, FailFastCirceSupport}

import io.circe.generic.auto._
import io.circe.syntax._

import scala.concurrent.ExecutionContext




class TeamsServiceRoute(teamsService: TeamsService)(implicit executionContext: ExecutionContext)
  extends FailFastCirceSupport {
  import StatusCodes._
  import teamsService._

  val route = pathPrefix("team") {
    pathEndOrSingleSlash {
      get {
        complete(getTeams().map(_.asJson))
      }
    } ~
    pathPrefix("new") {
      pathEndOrSingleSlash {
        post{
          entity(as[TeamEntity]){
            newUser =>
              complete(createTeam(newUser).map(_.asJson))
          }
        }~
        put{
          entity(as[TeamEntity]){
            newUser =>
              complete(createTeam(newUser).map(_.asJson))
          }
        }
      }
    } ~
    pathPrefix(IntNumber) { id =>
      pathEndOrSingleSlash {
        get {
          complete(getTeamById(id).map(_.asJson))
        } ~
          post {
            entity(as[TeamEntityUpdate]) { userUpdate =>
              complete(updateTeam(id, userUpdate).map(_.asJson))
            }
          } ~
          put {
            entity(as[TeamEntityUpdate]) { userUpdate =>
              complete(updateTeam(id, userUpdate).map(_.asJson))
            }
          } ~
          delete {
            onSuccess(deleteTeam(id)) { ignored =>
              complete(NoContent)
            }
          }
      }
    }
  }
}
