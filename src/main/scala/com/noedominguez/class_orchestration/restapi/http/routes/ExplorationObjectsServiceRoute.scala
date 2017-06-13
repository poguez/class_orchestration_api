package com.noedominguez.class_orchestration.restapi.http.routes

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import com.noedominguez.class_orchestration.restapi.models.{ExplorationObjectEntity, ExplorationObjectEntityUpdate}
import com.noedominguez.class_orchestration.restapi.services.ExplorationObjectsService
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport
import io.circe.generic.auto._
import io.circe.syntax._

import scala.concurrent.ExecutionContext

class ExplorationObjectsServiceRoute(explorationsService: ExplorationObjectsService)(implicit executionContext: ExecutionContext)
  extends FailFastCirceSupport {
  import StatusCodes._
  import explorationsService._

  val route = pathPrefix("exploration-object") {
    pathEndOrSingleSlash {
      get {
        complete(getExplorationObjects.map(_.asJson))
      }
    } ~
    pathPrefix("new") {
      pathEndOrSingleSlash {
        post{
          entity(as[ExplorationObjectEntity]){
            newExplorationObject =>
              complete(createExplorationObject(newExplorationObject).map(_.asJson))
          }
        }~
        post{
          entity(as[ExplorationObjectEntity]){
            newExplorationObject =>
              complete(createExplorationObject(newExplorationObject).map(_.asJson))
          }
        }
      }
    } ~
    pathPrefix(IntNumber) { id =>
      pathEndOrSingleSlash {
        get {
          complete(getExplorationObjectById(id).map(_.asJson))
        } ~
        post {
          entity(as[ExplorationObjectEntityUpdate]) { userUpdate =>
            complete(updateExplorationObject(id, userUpdate).map(_.asJson))
          }
        } ~
        delete {
          onSuccess(deleteExplorationObject(id)) { ignored =>
            complete(NoContent)
          }
        }
      }
    }
  }
}
