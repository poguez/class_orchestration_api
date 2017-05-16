package com.noedominguez.class_orchestration.restapi.services

import com.noedominguez.class_orchestration.restapi.models.{TeamEntity, TeamEntityUpdate}
import com.noedominguez.class_orchestration.restapi.models.db.TeamEntityTable
import com.noedominguez.class_orchestration.restapi.utils.DatabaseService
import scala.concurrent.{ExecutionContext, Future}

class TeamsService(val databaseService: DatabaseService)(implicit executionContext: ExecutionContext) extends TeamEntityTable  {

  import databaseService._
  import databaseService.driver.api._

  def getTeams(): Future[Seq[TeamEntity]] = db.run(teams.result)

  def getTeamById(id: Long): Future[Option[TeamEntity]] = db.run(teams.filter(_.id === id).result.headOption)

  def createTeam(team: TeamEntity): Future[TeamEntity] = db.run(teams returning teams += team)

  def updateTeam(id: Long, teamUpdate: TeamEntityUpdate): Future[Option[TeamEntity]] = getTeamById(id).flatMap {
    case Some(team) =>
      val updatedTeam = teamUpdate.merge(team)
      db.run(teams.filter(_.id === id).update(updatedTeam).map(_ => Some(updatedTeam)) )
    case None => Future.successful(None)
  }

  def deleteTeam(id: Long): Future[Int] = db.run(teams.filter(_.id === id).delete)

}
