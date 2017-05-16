CREATE TABLE "teams" (
  "id"      BIGSERIAL PRIMARY KEY,
  "name" VARCHAR,
  "rights"   BOOLEAN,
  "objectId"   BIGINT,
  "explorationId" BIGINT
);