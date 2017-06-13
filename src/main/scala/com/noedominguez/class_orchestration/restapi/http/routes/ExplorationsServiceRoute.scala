package com.noedominguez.class_orchestration.restapi.http.routes

import akka.http.scaladsl.server.Directives._
import akka.http.scaladsl.model.StatusCodes
import com.noedominguez.class_orchestration.restapi.services.ExplorationsService
import com.noedominguez.class_orchestration.restapi.models.{ExplorationEntity, ExplorationEntityUpdate}
import de.heikoseeberger.akkahttpcirce.{CirceSupport, FailFastCirceSupport}

import io.circe.generic.auto._
import io.circe.syntax._

import scala.concurrent.ExecutionContext

class ExplorationsServiceRoute(explorationsService: ExplorationsService)(implicit executionContext: ExecutionContext)
  extends FailFastCirceSupport {
  import StatusCodes._
  import explorationsService._

  val route = pathPrefix("exploration") {
    pathEndOrSingleSlash {
      get {
        complete(getExplorations().map(_.asJson))
      }
    } ~
    pathPrefix("new") {
      pathEndOrSingleSlash {
        post{
          entity(as[ExplorationEntity]){
            newUser =>
              complete(createExploration(newUser).map(_.asJson))
          }
        }~
        post{
          entity(as[ExplorationEntity]){
            newUser =>
              complete(createExploration(newUser).map(_.asJson))
          }
        }
      }
    } ~
    pathPrefix(IntNumber) { id =>
      pathEndOrSingleSlash {
        get {
          complete(getExplorationById(id).map(_.asJson))
        } ~
        post {
          entity(as[ExplorationEntityUpdate]) { userUpdate =>
            complete(updateExploration(id, userUpdate).map(_.asJson))
          }
        } ~
        delete {
          onSuccess(deleteExploration(id)) { ignored =>
            complete(NoContent)
          }
        }
      }
    }
  }
}
