package com.noedominguez.class_orchestration.restapi.models.db

import com.noedominguez.class_orchestration.restapi.models.TeamEntity
import com.noedominguez.class_orchestration.restapi.utils.DatabaseService

trait TeamEntityTable {

  protected val databaseService: DatabaseService
  import databaseService.driver.api._

  class Teams(tag: Tag) extends Table[TeamEntity](tag, "teams"){
    def id = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)
    def name = column[Option[String]]("name")
    def rights = column[Option[String]]("rights")
    def objectId = column[Option[Long]]("objectId")
    def explorationId = column[Option[Long]]("explorationId")

    def * = (id, name, rights, objectId, explorationId) <> ((TeamEntity.apply _).tupled, TeamEntity.unapply)

  }

  protected val teams = TableQuery[Teams]

}
