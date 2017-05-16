package com.noedominguez.class_orchestration.restapi.models

//import java.util.UUID

case class TeamEntity(id: Long,
                      name: Option[String] = None,
                      rights: Option[String] = None,
                      objectId: Option[Long] = None,
                      explorationId: Option[Long] = None) {
}

case class TeamEntityUpdate(name: Option[String] = None,
                            rights: Option[String] = None,
                            objectId: Option[Long] = None,
                            explorationId: Option[Long] = None) {

  def merge(team: TeamEntity): TeamEntity = {
    TeamEntity(team.id,
      name.orElse(team.name),
      rights.orElse(team.rights),
      objectId.orElse(team.objectId),
      explorationId.orElse(team.objectId)
    )
  }
}

