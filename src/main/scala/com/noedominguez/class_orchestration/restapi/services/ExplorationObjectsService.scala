package com.noedominguez.class_orchestration.restapi.services

import com.noedominguez.class_orchestration.restapi.models.db.ExplorationObjectEntityTable
import com.noedominguez.class_orchestration.restapi.models.{ExplorationObjectEntity,ExplorationObjectEntityUpdate}
import com.noedominguez.class_orchestration.restapi.utils.DatabaseService

import scala.concurrent.{ExecutionContext, Future}

class ExplorationObjectsService(val databaseService: DatabaseService)(implicit executionContext: ExecutionContext)
  extends ExplorationObjectEntityTable {

  import databaseService._
  import databaseService.driver.api._

  def getExplorationObjects(): Future[Seq[ExplorationObjectEntity]] =
    db.run(explorationObjects.sortBy(_.id.asc.nullsFirst).result)

  def getExplorationObjectById(id: Long): Future[Option[ExplorationObjectEntity]] =
    db.run(explorationObjects.filter(_.id === id).result.headOption)

  def createExplorationObject(explorationObject: ExplorationObjectEntity): Future[ExplorationObjectEntity] =
    db.run(explorationObjects returning explorationObjects += explorationObject)

  def updateExplorationObject(id: Long, explorationObjectUpdate: ExplorationObjectEntityUpdate): Future[Option[ExplorationObjectEntity]] =
    getExplorationObjectById(id).flatMap {
      case Some(explorationObject) =>
        val updatedExplorationObject = explorationObjectUpdate.merge(explorationObject)
          db.run(explorationObjects.filter(_.id === id).
            update(updatedExplorationObject).
            map(_ => Some(updatedExplorationObject)))
      case None => Future.successful(None)
    }

  def deleteExplorationObject(id: Long): Future[Int] =
    db.run(explorationObjects.filter(_.id === id).delete)

}
