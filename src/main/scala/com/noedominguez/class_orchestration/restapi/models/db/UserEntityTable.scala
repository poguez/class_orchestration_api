package com.noedominguez.class_orchestration.restapi.models.db

import com.noedominguez.class_orchestration.restapi.models.UserEntity
import com.noedominguez.class_orchestration.restapi.utils.DatabaseService

trait UserEntityTable {

  protected val databaseService: DatabaseService
  import databaseService.driver.api._

  class Users(tag: Tag) extends Table[UserEntity](tag, "users") {
    def id = column[Option[Long]]("id", O.PrimaryKey, O.AutoInc)
    def name = column[String]("name")
    def password = column[String]("password")
    def isAdmin = column[Boolean]("isAdmin")
    def teamId = column[Option[Long]]("teamId")

    def * = (id, name, password, isAdmin, teamId) <> ((UserEntity.apply _).tupled, UserEntity.unapply)
  }

  protected val users = TableQuery[Users]

}
