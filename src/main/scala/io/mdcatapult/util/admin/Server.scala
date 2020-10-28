package io.mdcatapult.util.admin

import java.net.InetSocketAddress

import com.sun.net.httpserver.HttpServer
import com.typesafe.config.Config
import io.prometheus.client.CollectorRegistry
import io.prometheus.client.exporter.HTTPServer

object Server {
  def apply(config: Config, checkHealth: () => Boolean) = new Server(config, checkHealth)
  def apply(config: Config) = new Server(config, () => true)
}

class Server(config: Config, checkHealth: () => Boolean) {
  private var server: HTTPServer = _

  def start(): Unit = {
    val port = config.getInt("admin.port")
    val addr = new InetSocketAddress(port)
    val srv = HttpServer.create(addr, 3)
    val healthCheckHandler = new HTTPHealthCheckHandler(checkHealth)
    srv.createContext("/health", healthCheckHandler)
    val reg = CollectorRegistry.defaultRegistry
    server = new HTTPServer(srv, reg, false)
  }

  def stop(): Unit = {
    server.stop()
  }
}