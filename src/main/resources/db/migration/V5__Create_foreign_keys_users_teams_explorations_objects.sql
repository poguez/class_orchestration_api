alter table users
    add constraint fk_users_teams
    foreign key ("teamId")
    REFERENCES teams (id);

alter table teams
    add constraint fk_teams_explorations
    foreign key ("explorationId")
    REFERENCES explorations (id);

alter table teams
    add constraint fk_teams_exploration_objects
    foreign key ("objectId")
    REFERENCES "explorationObjects" (id);

alter table explorations
    add constraint fk_explorations_users
    foreign key ("teacherId")
    REFERENCES users (id);

alter table explorations
    add constraint fk_explorations_exploration_objects
    foreign key ("explorationObjectId")
    REFERENCES "explorationObjects" (id);
