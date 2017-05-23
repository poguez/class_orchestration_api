package com.noedominguez.class_orchestration.restapi.services

import com.noedominguez.class_orchestration.restapi.models.db.ExplorationEntityTable
import com.noedominguez.class_orchestration.restapi.models.{ExplorationEntity, ExplorationEntityUpdate}
import com.noedominguez.class_orchestration.restapi.utils.DatabaseService

import scala.concurrent.{ExecutionContext, Future}

class ExplorationsService(val databaseService: DatabaseService)(implicit executionContext: ExecutionContext) extends ExplorationEntityTable  {

  import databaseService._
  import databaseService.driver.api._

  def getExplorations(): Future[Seq[ExplorationEntity]] = db.run(explorations.result)

  def getExplorationById(id: Long): Future[Option[ExplorationEntity]] = db.run(explorations.filter(_.id === id).result.headOption)

  def createExploration(exploration: ExplorationEntity): Future[ExplorationEntity] = db.run(explorations returning explorations += exploration)

  def updateExploration(id: Long, explorationUpdate: ExplorationEntityUpdate): Future[Option[ExplorationEntity]] = getExplorationById(id).flatMap {
    case Some(exploration) =>
      val updatedExploration = explorationUpdate.merge(exploration)
      db.run(explorations.filter(_.id === id).update(updatedExploration).map(_ => Some(updatedExploration)) )
    case None => Future.successful(None)
  }

  def deleteExploration(id: Long): Future[Int] = db.run(explorations.filter(_.id === id).delete)

}
