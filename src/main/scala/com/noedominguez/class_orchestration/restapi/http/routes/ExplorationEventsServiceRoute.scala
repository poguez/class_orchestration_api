package com.noedominguez.class_orchestration.restapi.http.routes

import akka.http.scaladsl.model.StatusCodes
import akka.http.scaladsl.server.Directives._
import com.noedominguez.class_orchestration.restapi.models.{ExplorationEventEntity, ExplorationEventEntityUpdate}
import com.noedominguez.class_orchestration.restapi.services.ExplorationEventsService
import de.heikoseeberger.akkahttpcirce.FailFastCirceSupport
import io.circe.generic.auto._
import io.circe.syntax._

import scala.concurrent.ExecutionContext

class ExplorationEventsServiceRoute(explorationEventsService: ExplorationEventsService)(implicit executionContext: ExecutionContext)
  extends FailFastCirceSupport {
  import StatusCodes._
  import explorationEventsService._

  val route = pathPrefix("exploration-event") {
    pathEndOrSingleSlash {
      get {
        complete(getExplorationEvents().map(_.asJson))
      }
    } ~
    pathPrefix("new") {
      pathEndOrSingleSlash {
        post{
          entity(as[ExplorationEventEntity]){
            newExplorationEvent =>
              complete(createExplorationEvent(newExplorationEvent).map(_.asJson))
          }
        }~
        put{
          entity(as[ExplorationEventEntity]){
            newExplorationEvent =>
              complete(createExplorationEvent(newExplorationEvent).map(_.asJson))
          }
        }
      }
    } ~
    pathPrefix(IntNumber) { id =>
      pathEndOrSingleSlash {
        get {
          complete(getExplorationEventById(id).map(_.asJson))
        } ~
        post {
          entity(as[ExplorationEventEntityUpdate]) { explorationEventUpdate =>
            complete(updateExplorationEvent(id, explorationEventUpdate).map(_.asJson))
          }
        } ~
        put {
          entity(as[ExplorationEventEntityUpdate]) { explorationEventUpdate =>
            complete(updateExplorationEvent(id, explorationEventUpdate).map(_.asJson))
          }
        } ~
        delete {
          onSuccess(deleteExplorationEvent(id)) { ignored =>
            complete(NoContent)
          }
        }
      }
    }
  }
}
