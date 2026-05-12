package com.cleanroute.app.core.database

class CleanRouteDatabaseProvider(factory: DatabaseDriverFactory) {
    val database = CleanRouteDatabase(factory.createDriver())
}
