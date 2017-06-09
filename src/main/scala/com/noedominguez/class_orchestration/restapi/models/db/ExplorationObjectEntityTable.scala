package com.noedominguez.class_orchestration.restapi.models.db

import com.github.tminglei.slickpg.JsonString
import com.noedominguez.class_orchestration.restapi.models.ExplorationObjectEntity
import com.noedominguez.class_orchestration.restapi.utils.DatabaseService

trait ExplorationObjectEntityTable {

  protected val databaseService: DatabaseService
  import databaseService.driver.api._

  class ExplorationObject(tag: Tag) extends Table[ExplorationObjectEntity](tag, "explorationObjects"){
    def id = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)
    def objectState = column[Option[String]]("objectState")
    def modelName= column[Option[String]]("modelName")

    def * = (id, objectState, modelName) <> ((ExplorationObjectEntity.apply _).tupled, ExplorationObjectEntity.unapply)

  }

  protected val explorationObjects = TableQuery[ExplorationObject]

}
