package com.noedominguez.class_orchestration.restapi.services

import com.noedominguez.class_orchestration.restapi.models.db.ExplorationEventEntityTable
import com.noedominguez.class_orchestration.restapi.models.{ExplorationEventEntity, ExplorationEventEntityUpdate}
import com.noedominguez.class_orchestration.restapi.utils.DatabaseService

import scala.concurrent.{ExecutionContext, Future}

class ExplorationEventsService(val databaseService: DatabaseService)(implicit executionContext: ExecutionContext) extends ExplorationEventEntityTable  {

  import databaseService._
  import databaseService.driver.api._

  def getExplorationEvents(): Future[Seq[ExplorationEventEntity]] = db.run(explorationEvents.sortBy(_.id.asc.nullsFirst).result)

  def getExplorationEventById(id: Long): Future[Option[ExplorationEventEntity]] = db.run(explorationEvents.filter(_.id === id).result.headOption)

  def createExplorationEvent(explorationEvent: ExplorationEventEntity): Future[ExplorationEventEntity] = db.run(explorationEvents returning explorationEvents += explorationEvent)

  def updateExplorationEvent(id: Long, explorationEventUpdate: ExplorationEventEntityUpdate): Future[Option[ExplorationEventEntity]] = getExplorationEventById(id).flatMap {
    case Some(explorationEvent) =>
      val updatedExploration = explorationEventUpdate.merge(explorationEvent)
      db.run(explorationEvents.filter(_.id === id).update(updatedExploration).map(_ => Some(updatedExploration)) )
    case None => Future.successful(None)
  }

  def deleteExplorationEvent(id: Long): Future[Int] = db.run(explorationEvents.filter(_.id === id).delete)

}
