package com.noedominguez.class_orchestration.restapi.models

case class TeamEntity(id: Option[Long],
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

