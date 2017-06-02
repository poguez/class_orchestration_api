package com.noedominguez.class_orchestration.restapi.models.db

import com.noedominguez.class_orchestration.restapi.models.ExplorationEntity
import com.noedominguez.class_orchestration.restapi.utils.DatabaseService

trait ExplorationEntityTable {

  protected val databaseService: DatabaseService
  import databaseService.driver.api._

  class Exploration(tag: Tag) extends Table[ExplorationEntity](tag, "explorations"){
    def id = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)
    def teacherId = column[Option[Long]]("teacherId")
    def explorationObjectId = column[Option[Long]]("explorationObjectId")

    def * = (id, teacherId, explorationObjectId) <> ((ExplorationEntity.apply _).tupled, ExplorationEntity.unapply)

  }

  protected val explorations = TableQuery[Exploration]

}
