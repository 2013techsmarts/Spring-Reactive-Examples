package org.smarttechie
import io.gatling.core.Predef._
import io.gatling.http.Predef._
import scala.concurrent.duration._

class ReactiveRESTEndPointPrefSimulation extends Simulation {
	
	val httpProtocol = http
					   .baseUrl("http://localhost:8081")
	val restEndPoint = "/product"

	val restEndpointScenario = scenario("Posts_Pref_Simulation")
								.exec(http("request_1")
								.post(restEndPoint)
								.body(StringBody("""{ "id": 1 , "title":"sample title"}""")).asJson
)
	setUp( restEndpointScenario.inject( // 1
    rampUsers(5000) during (30 seconds)// 2
  ).protocols(httpProtocol))
}