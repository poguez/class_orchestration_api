package com.arisanet

import akka.http.scaladsl.testkit.ScalatestRouteTest
import de.heikoseeberger.akkahttpcirce.CirceSupport
import com.noedominguez.class_orchestration.restapi.http.HttpService
import com.noedominguez.class_orchestration.restapi.models.UserEntity
import com.noedominguez.class_orchestration.restapi.services.{AuthService, ExplorationsService, ExplorationEventsService, TeamsService, UsersService, ExplorationObjectsService}
import com.noedominguez.class_orchestration.restapi.utils.DatabaseService
import com.arisanet.utils.InMemoryPostgresStorage._
import org.scalatest._

import scala.concurrent.duration._
import scala.concurrent.{Await, Future}
import scala.util.Random

trait BaseServiceTest extends WordSpec with Matchers with ScalatestRouteTest with CirceSupport {

  dbProcess.getProcessId

  private val databaseService = new DatabaseService(jdbcUrl, dbUser, dbPassword)

  val usersService = new UsersService(databaseService)
  val authService = new AuthService(databaseService)(usersService)
  val teamsService = new TeamsService(databaseService)
  val explorationsService = new ExplorationsService(databaseService)
  val explorationEventsService = new ExplorationEventsService(databaseService)
  val explorationObjectsService = new ExplorationObjectsService(databaseService)
  val httpService = new HttpService(usersService, authService, teamsService, explorationsService, explorationEventsService, explorationObjectsService)

  def provisionUsersList(size: Int): Seq[UserEntity] = {
    val savedUsers = (1 to size).map { _ =>
      UserEntity(Some(Random.nextLong()),
                  Random.nextString(10),
                  Random.nextString(10),
                  Random.nextBoolean(),
                  Some(0L))
    }.map(usersService.createUser)

    Await.result(Future.sequence(savedUsers), 10.seconds)
  }

  def provisionTokensForUsers(usersList: Seq[UserEntity]) = {
    val savedTokens = usersList.map(authService.createToken)
    Await.result(Future.sequence(savedTokens), 10.seconds)
  }

}
