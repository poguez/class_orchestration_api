package com.noedominguez.class_orchestration.restapi.models.db

import com.noedominguez.class_orchestration.restapi.models.ExplorationEventEntity
import com.noedominguez.class_orchestration.restapi.utils.DatabaseService

trait ExplorationEventEntityTable {

  protected val databaseService: DatabaseService
  import databaseService.driver.api._

  class ExplorationEvent(tag: Tag) extends Table[ExplorationEventEntity](tag, "explorationEvents"){
    def id = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)
    def name = column[Option[String]]("name")
    def description = column[Option[String]]("description")

    def * = (id, name, description) <> ((ExplorationEventEntity.apply _).tupled, ExplorationEventEntity.unapply)

  }

  protected val explorationEvents = TableQuery[ExplorationEvent]

}
