# Stuff API

Spring Boot API for managing todos. The service exposes its API at `/api/todos` and
its Railway health check at `/actuator/health`.

## Deploying to Railway

1. Create a Railway project and add a PostgreSQL service.
2. Add this repository as a service. Railway detects `railway.json`, builds the
   executable JAR with the Gradle wrapper, and starts it on Railway's `PORT`.
3. In the application service's variables, add the following references to the
   PostgreSQL service (replace `Postgres` with that service's Railway name):

   ```text
   SPRING_DATASOURCE_URL=jdbc:postgresql://${{Postgres.PGHOST}}:${{Postgres.PGPORT}}/${{Postgres.PGDATABASE}}
   SPRING_DATASOURCE_USERNAME=${{Postgres.PGUSER}}
   SPRING_DATASOURCE_PASSWORD=${{Postgres.PGPASSWORD}}
   ```

Railway injects `PORT` automatically. Do not commit database credentials; use
Railway variables locally or in production. The configured health check is
`GET /actuator/health`.

## Local development

Set either the `SPRING_DATASOURCE_*` variables or the standard Railway PostgreSQL
variables (`PGHOST`, `PGPORT`, `PGDATABASE`, `PGUSER`, and `PGPASSWORD`), then run:

```bash
./gradlew bootRun
```
