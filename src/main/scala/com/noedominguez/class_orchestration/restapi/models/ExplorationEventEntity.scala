package com.noedominguez.class_orchestration.restapi.models

case class ExplorationEventEntity(id: Option[Long],
                                        name: Option[String] = None,
                                        description: Option[String] = None) {
}

case class ExplorationEventEntityUpdate(name: Option[String] = None,
                                        description: Option[String] = None) {

  def merge(explorationEvent: ExplorationEventEntity): ExplorationEventEntity = {
      ExplorationEventEntity(explorationEvent.id,
      name.orElse(explorationEvent.name),
      description.orElse(explorationEvent.description)
    )
  }
}

