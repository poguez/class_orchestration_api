package com.noedominguez.class_orchestration.restapi.models.db

import com.noedominguez.class_orchestration.restapi.models.ExplorationEntity
import com.noedominguez.class_orchestration.restapi.utils.DatabaseService

trait ExplorationEntityTable {

  protected val databaseService: DatabaseService
  import databaseService.driver.api._

  class Exploration(tag: Tag) extends Table[ExplorationEntity](tag, "explorations"){
    def id = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)
    def objectId = column[Option[Long]]("teacher_id")
    def explorationId = column[Option[Long]]("exploration_object_id")

    def * = (id, objectId, explorationId) <> ((ExplorationEntity.apply _).tupled, ExplorationEntity.unapply)

  }

  protected val explorations = TableQuery[Exploration]

}
