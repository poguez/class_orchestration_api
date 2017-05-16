package com.noedominguez.class_orchestration.restapi.utils

import com.github.tminglei.slickpg._
import com.github.tminglei.slickpg.PgDateSupportJoda
import com.zaxxer.hikari.{HikariConfig, HikariDataSource}

trait MyPostgresDriver extends ExPostgresProfile with PgDateSupportJoda{
  def pgjson = "jsonb"

  override val api = MyAPI

  object MyAPI extends API with DateTimeImplicits
}

object MyPostgresDriver extends MyPostgresDriver

class DatabaseService(jdbcUrl: String, dbUser: String, dbPassword: String) {
  private val hikariConfig = new HikariConfig()
  hikariConfig.setJdbcUrl(jdbcUrl)
  hikariConfig.setUsername(dbUser)
  hikariConfig.setPassword(dbPassword)

  private val dataSource = new HikariDataSource(hikariConfig)

  val driver = MyPostgresDriver
  import driver.api._
  val db = Database.forDataSource(dataSource, Some(10))
  db.createSession()
}
