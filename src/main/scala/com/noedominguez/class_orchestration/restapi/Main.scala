package com.noedominguez.class_orchestration.restapi

import com.noedominguez.class_orchestration.restapi.services.{AuthService, TeamsService, UsersService, ExplorationsService, ExplorationObjectsService}
import akka.actor.ActorSystem
import akka.event.{Logging, LoggingAdapter}
import akka.http.scaladsl.Http
import akka.stream.ActorMaterializer
import com.noedominguez.class_orchestration.restapi.http.HttpService
import com.noedominguez.class_orchestration.restapi.utils.{DatabaseService, FlywayService}
import com.noedominguez.class_orchestration.restapi.utils.Config

import scala.concurrent.ExecutionContext

object Main extends App with Config {
  implicit val actorSystem = ActorSystem()
  implicit val executor: ExecutionContext = actorSystem.dispatcher
  implicit val log: LoggingAdapter = Logging(actorSystem, getClass)
  implicit val materializer: ActorMaterializer = ActorMaterializer()

  val flywayService = new FlywayService(jdbcUrl, dbUser, dbPassword)
  flywayService.migrateDatabaseSchema

  val databaseService = new DatabaseService(jdbcUrl, dbUser, dbPassword)

  val usersService = new UsersService(databaseService)
  val authService = new AuthService(databaseService)(usersService)
  val teamsService= new TeamsService(databaseService)
  val explorationsService= new ExplorationsService(databaseService)
  val explorationObjectsService= new ExplorationObjectsService(databaseService)

  val httpService = new HttpService(usersService, authService, teamsService, explorationsService,explorationObjectsService)

  Http().bindAndHandle(httpService.routes, httpHost, httpPort)
}
