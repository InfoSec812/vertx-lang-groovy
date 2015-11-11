/*
 * Copyright 2014 Red Hat, Inc.
 *
 * Red Hat licenses this file to you under the Apache License, version 2.0
 * (the "License"); you may not use this file except in compliance with the
 * License.  You may obtain a copy of the License at:
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.  See the
 * License for the specific language governing permissions and limitations
 * under the License.
 */

package io.vertx.groovy.core.http;
import groovy.transform.CompileStatic
import io.vertx.lang.groovy.InternalHelper
import io.vertx.core.json.JsonObject
import io.vertx.groovy.core.metrics.Measured
import io.vertx.core.http.HttpMethod
import io.vertx.groovy.core.MultiMap
import io.vertx.core.http.WebsocketVersion
import io.vertx.core.Handler
/**
 * An asynchronous HTTP client.
 * <p>
 * It allows you to make requests to HTTP servers, and a single client can make requests to any server.
 * <p>
 * It also allows you to open WebSockets to servers.
 * <p>
 * The client can also pool HTTP connections.
 * <p>
 * For pooling to occur, keep-alive must be true on the <a href="../../../../../../../cheatsheet/HttpClientOptions.html">HttpClientOptions</a> (default is true).
 * In this case connections will be pooled and re-used if there are pending HTTP requests waiting to get a connection,
 * otherwise they will be closed.
 * <p>
 * This gives the benefits of keep alive when the client is loaded but means we don't keep connections hanging around
 * unnecessarily when there would be no benefits anyway.
 * <p>
 * The client also supports pipe-lining of requests. Pipe-lining means another request is sent on the same connection
 * before the response from the preceeding one has returned. Pipe-lining is not appropriate for all requests.
 * <p>
 * To enable pipe-lining, it must be enabled on the <a href="../../../../../../../cheatsheet/HttpClientOptions.html">HttpClientOptions</a> (default is false).
 * <p>
 * When pipe-lining is enabled the connection will be automatically closed when all in-flight responses have returned
 * and there are no outstanding pending requests to write.
 * <p>
 * The client is designed to be reused between requests.
*/
@CompileStatic
public class HttpClient implements Measured {
  private final def io.vertx.core.http.HttpClient delegate;
  public HttpClient(Object delegate) {
    this.delegate = (io.vertx.core.http.HttpClient) delegate;
  }
  public Object getDelegate() {
    return delegate;
  }
  /**
   * Whether the metrics are enabled for this measured object
   * @return true if the metrics are enabled
   */
  public boolean isMetricsEnabled() {
    def ret = ((io.vertx.core.metrics.Measured) this.delegate).isMetricsEnabled();
    return ret;
  }
  /**
   * Create an HTTP request to send to the server at the specified host and port.
   * @param method the HTTP method
   * @param port the port
   * @param host the host
   * @param requestURI the relative URI
   * @return an HTTP client request object
   */
  public HttpClientRequest request(HttpMethod method, int port, String host, String requestURI) {
    def ret= InternalHelper.safeCreate(this.delegate.request(method, port, host, requestURI), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Create an HTTP request to send to the server at the specified host and default port.
   * @param method the HTTP method
   * @param host the host
   * @param requestURI the relative URI
   * @return an HTTP client request object
   */
  public HttpClientRequest request(HttpMethod method, String host, String requestURI) {
    def ret= InternalHelper.safeCreate(this.delegate.request(method, host, requestURI), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Create an HTTP request to send to the server at the specified host and port, specifying a response handler to receive
   * the response
   * @param method the HTTP method
   * @param port the port
   * @param host the host
   * @param requestURI the relative URI
   * @param responseHandler the response handler
   * @return an HTTP client request object
   */
  public HttpClientRequest request(HttpMethod method, int port, String host, String requestURI, Handler<HttpClientResponse> responseHandler) {
    def ret= InternalHelper.safeCreate(this.delegate.request(method, port, host, requestURI, new Handler<io.vertx.core.http.HttpClientResponse>() {
      public void handle(io.vertx.core.http.HttpClientResponse event) {
        responseHandler.handle(new io.vertx.groovy.core.http.HttpClientResponse(event));
      }
    }), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Create an HTTP request to send to the server at the specified host and default port, specifying a response handler to receive
   * the response
   * @param method the HTTP method
   * @param host the host
   * @param requestURI the relative URI
   * @param responseHandler the response handler
   * @return an HTTP client request object
   */
  public HttpClientRequest request(HttpMethod method, String host, String requestURI, Handler<HttpClientResponse> responseHandler) {
    def ret= InternalHelper.safeCreate(this.delegate.request(method, host, requestURI, new Handler<io.vertx.core.http.HttpClientResponse>() {
      public void handle(io.vertx.core.http.HttpClientResponse event) {
        responseHandler.handle(new io.vertx.groovy.core.http.HttpClientResponse(event));
      }
    }), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Create an HTTP request to send to the server at the default host and port.
   * @param method the HTTP method
   * @param requestURI the relative URI
   * @return an HTTP client request object
   */
  public HttpClientRequest request(HttpMethod method, String requestURI) {
    def ret= InternalHelper.safeCreate(this.delegate.request(method, requestURI), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Create an HTTP request to send to the server at the default host and port, specifying a response handler to receive
   * the response
   * @param method the HTTP method
   * @param requestURI the relative URI
   * @param responseHandler the response handler
   * @return an HTTP client request object
   */
  public HttpClientRequest request(HttpMethod method, String requestURI, Handler<HttpClientResponse> responseHandler) {
    def ret= InternalHelper.safeCreate(this.delegate.request(method, requestURI, new Handler<io.vertx.core.http.HttpClientResponse>() {
      public void handle(io.vertx.core.http.HttpClientResponse event) {
        responseHandler.handle(new io.vertx.groovy.core.http.HttpClientResponse(event));
      }
    }), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Create an HTTP request to send to the server using an absolute URI
   * @param method the HTTP method
   * @param absoluteURI the absolute URI
   * @return an HTTP client request object
   */
  public HttpClientRequest requestAbs(HttpMethod method, String absoluteURI) {
    def ret= InternalHelper.safeCreate(this.delegate.requestAbs(method, absoluteURI), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Create an HTTP request to send to the server using an absolute URI, specifying a response handler to receive
   * the response
   * @param method the HTTP method
   * @param absoluteURI the absolute URI
   * @param responseHandler the response handler
   * @return an HTTP client request object
   */
  public HttpClientRequest requestAbs(HttpMethod method, String absoluteURI, Handler<HttpClientResponse> responseHandler) {
    def ret= InternalHelper.safeCreate(this.delegate.requestAbs(method, absoluteURI, new Handler<io.vertx.core.http.HttpClientResponse>() {
      public void handle(io.vertx.core.http.HttpClientResponse event) {
        responseHandler.handle(new io.vertx.groovy.core.http.HttpClientResponse(event));
      }
    }), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Create an HTTP GET request to send to the server at the specified host and port.
   * @param port the port
   * @param host the host
   * @param requestURI the relative URI
   * @return an HTTP client request object
   */
  public HttpClientRequest get(int port, String host, String requestURI) {
    def ret= InternalHelper.safeCreate(this.delegate.get(port, host, requestURI), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Create an HTTP GET request to send to the server at the specified host and default port.
   * @param host the host
   * @param requestURI the relative URI
   * @return an HTTP client request object
   */
  public HttpClientRequest get(String host, String requestURI) {
    def ret= InternalHelper.safeCreate(this.delegate.get(host, requestURI), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Create an HTTP GET request to send to the server at the specified host and port, specifying a response handler to receive
   * the response
   * @param port the port
   * @param host the host
   * @param requestURI the relative URI
   * @param responseHandler the response handler
   * @return an HTTP client request object
   */
  public HttpClientRequest get(int port, String host, String requestURI, Handler<HttpClientResponse> responseHandler) {
    def ret= InternalHelper.safeCreate(this.delegate.get(port, host, requestURI, new Handler<io.vertx.core.http.HttpClientResponse>() {
      public void handle(io.vertx.core.http.HttpClientResponse event) {
        responseHandler.handle(new io.vertx.groovy.core.http.HttpClientResponse(event));
      }
    }), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Create an HTTP GET request to send to the server at the specified host and default port, specifying a response handler to receive
   * the response
   * @param host the host
   * @param requestURI the relative URI
   * @param responseHandler the response handler
   * @return an HTTP client request object
   */
  public HttpClientRequest get(String host, String requestURI, Handler<HttpClientResponse> responseHandler) {
    def ret= InternalHelper.safeCreate(this.delegate.get(host, requestURI, new Handler<io.vertx.core.http.HttpClientResponse>() {
      public void handle(io.vertx.core.http.HttpClientResponse event) {
        responseHandler.handle(new io.vertx.groovy.core.http.HttpClientResponse(event));
      }
    }), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Create an HTTP GET request to send to the server at the default host and port.
   * @param requestURI the relative URI
   * @return an HTTP client request object
   */
  public HttpClientRequest get(String requestURI) {
    def ret= InternalHelper.safeCreate(this.delegate.get(requestURI), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Create an HTTP GET request to send to the server at the default host and port, specifying a response handler to receive
   * the response
   * @param requestURI the relative URI
   * @param responseHandler the response handler
   * @return an HTTP client request object
   */
  public HttpClientRequest get(String requestURI, Handler<HttpClientResponse> responseHandler) {
    def ret= InternalHelper.safeCreate(this.delegate.get(requestURI, new Handler<io.vertx.core.http.HttpClientResponse>() {
      public void handle(io.vertx.core.http.HttpClientResponse event) {
        responseHandler.handle(new io.vertx.groovy.core.http.HttpClientResponse(event));
      }
    }), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Create an HTTP GET request to send to the server using an absolute URI
   * @param absoluteURI the absolute URI
   * @return an HTTP client request object
   */
  public HttpClientRequest getAbs(String absoluteURI) {
    def ret= InternalHelper.safeCreate(this.delegate.getAbs(absoluteURI), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Create an HTTP GET request to send to the server using an absolute URI, specifying a response handler to receive
   * the response
   * @param absoluteURI the absolute URI
   * @param responseHandler the response handler
   * @return an HTTP client request object
   */
  public HttpClientRequest getAbs(String absoluteURI, Handler<HttpClientResponse> responseHandler) {
    def ret= InternalHelper.safeCreate(this.delegate.getAbs(absoluteURI, new Handler<io.vertx.core.http.HttpClientResponse>() {
      public void handle(io.vertx.core.http.HttpClientResponse event) {
        responseHandler.handle(new io.vertx.groovy.core.http.HttpClientResponse(event));
      }
    }), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Sends an HTTP GET request to the server at the specified host and port, specifying a response handler to receive
   * the response
   * @param port the port
   * @param host the host
   * @param requestURI the relative URI
   * @param responseHandler the response handler
   * @return a reference to this, so the API can be used fluently
   */
  public HttpClient getNow(int port, String host, String requestURI, Handler<HttpClientResponse> responseHandler) {
    this.delegate.getNow(port, host, requestURI, new Handler<io.vertx.core.http.HttpClientResponse>() {
      public void handle(io.vertx.core.http.HttpClientResponse event) {
        responseHandler.handle(new io.vertx.groovy.core.http.HttpClientResponse(event));
      }
    });
    return this;
  }
  /**
   * Sends an HTTP GET request to the server at the specified host and default port, specifying a response handler to receive
   * the response
   * @param host the host
   * @param requestURI the relative URI
   * @param responseHandler the response handler
   * @return a reference to this, so the API can be used fluently
   */
  public HttpClient getNow(String host, String requestURI, Handler<HttpClientResponse> responseHandler) {
    this.delegate.getNow(host, requestURI, new Handler<io.vertx.core.http.HttpClientResponse>() {
      public void handle(io.vertx.core.http.HttpClientResponse event) {
        responseHandler.handle(new io.vertx.groovy.core.http.HttpClientResponse(event));
      }
    });
    return this;
  }
  /**
   * Sends an HTTP GET request  to the server at the default host and port, specifying a response handler to receive
   * the response
   * @param requestURI the relative URI
   * @param responseHandler the response handler
   * @return a reference to this, so the API can be used fluently
   */
  public HttpClient getNow(String requestURI, Handler<HttpClientResponse> responseHandler) {
    this.delegate.getNow(requestURI, new Handler<io.vertx.core.http.HttpClientResponse>() {
      public void handle(io.vertx.core.http.HttpClientResponse event) {
        responseHandler.handle(new io.vertx.groovy.core.http.HttpClientResponse(event));
      }
    });
    return this;
  }
  /**
   * Create an HTTP POST request to send to the server at the specified host and port.
   * @param port the port
   * @param host the host
   * @param requestURI the relative URI
   * @return an HTTP client request object
   */
  public HttpClientRequest post(int port, String host, String requestURI) {
    def ret= InternalHelper.safeCreate(this.delegate.post(port, host, requestURI), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Create an HTTP POST request to send to the server at the specified host and default port.
   * @param host the host
   * @param requestURI the relative URI
   * @return an HTTP client request object
   */
  public HttpClientRequest post(String host, String requestURI) {
    def ret= InternalHelper.safeCreate(this.delegate.post(host, requestURI), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Create an HTTP POST request to send to the server at the specified host and port, specifying a response handler to receive
   * the response
   * @param port the port
   * @param host the host
   * @param requestURI the relative URI
   * @param responseHandler the response handler
   * @return an HTTP client request object
   */
  public HttpClientRequest post(int port, String host, String requestURI, Handler<HttpClientResponse> responseHandler) {
    def ret= InternalHelper.safeCreate(this.delegate.post(port, host, requestURI, new Handler<io.vertx.core.http.HttpClientResponse>() {
      public void handle(io.vertx.core.http.HttpClientResponse event) {
        responseHandler.handle(new io.vertx.groovy.core.http.HttpClientResponse(event));
      }
    }), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Create an HTTP POST request to send to the server at the specified host and default port, specifying a response handler to receive
   * the response
   * @param host the host
   * @param requestURI the relative URI
   * @param responseHandler the response handler
   * @return an HTTP client request object
   */
  public HttpClientRequest post(String host, String requestURI, Handler<HttpClientResponse> responseHandler) {
    def ret= InternalHelper.safeCreate(this.delegate.post(host, requestURI, new Handler<io.vertx.core.http.HttpClientResponse>() {
      public void handle(io.vertx.core.http.HttpClientResponse event) {
        responseHandler.handle(new io.vertx.groovy.core.http.HttpClientResponse(event));
      }
    }), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Create an HTTP POST request to send to the server at the default host and port.
   * @param requestURI the relative URI
   * @return an HTTP client request object
   */
  public HttpClientRequest post(String requestURI) {
    def ret= InternalHelper.safeCreate(this.delegate.post(requestURI), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Create an HTTP POST request to send to the server at the default host and port, specifying a response handler to receive
   * the response
   * @param requestURI the relative URI
   * @param responseHandler the response handler
   * @return an HTTP client request object
   */
  public HttpClientRequest post(String requestURI, Handler<HttpClientResponse> responseHandler) {
    def ret= InternalHelper.safeCreate(this.delegate.post(requestURI, new Handler<io.vertx.core.http.HttpClientResponse>() {
      public void handle(io.vertx.core.http.HttpClientResponse event) {
        responseHandler.handle(new io.vertx.groovy.core.http.HttpClientResponse(event));
      }
    }), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Create an HTTP POST request to send to the server using an absolute URI
   * @param absoluteURI the absolute URI
   * @return an HTTP client request object
   */
  public HttpClientRequest postAbs(String absoluteURI) {
    def ret= InternalHelper.safeCreate(this.delegate.postAbs(absoluteURI), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Create an HTTP POST request to send to the server using an absolute URI, specifying a response handler to receive
   * the response
   * @param absoluteURI the absolute URI
   * @param responseHandler the response handler
   * @return an HTTP client request object
   */
  public HttpClientRequest postAbs(String absoluteURI, Handler<HttpClientResponse> responseHandler) {
    def ret= InternalHelper.safeCreate(this.delegate.postAbs(absoluteURI, new Handler<io.vertx.core.http.HttpClientResponse>() {
      public void handle(io.vertx.core.http.HttpClientResponse event) {
        responseHandler.handle(new io.vertx.groovy.core.http.HttpClientResponse(event));
      }
    }), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Create an HTTP HEAD request to send to the server at the specified host and port.
   * @param port the port
   * @param host the host
   * @param requestURI the relative URI
   * @return an HTTP client request object
   */
  public HttpClientRequest head(int port, String host, String requestURI) {
    def ret= InternalHelper.safeCreate(this.delegate.head(port, host, requestURI), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Create an HTTP HEAD request to send to the server at the specified host and default port.
   * @param host the host
   * @param requestURI the relative URI
   * @return an HTTP client request object
   */
  public HttpClientRequest head(String host, String requestURI) {
    def ret= InternalHelper.safeCreate(this.delegate.head(host, requestURI), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Create an HTTP HEAD request to send to the server at the specified host and port, specifying a response handler to receive
   * the response
   * @param port the port
   * @param host the host
   * @param requestURI the relative URI
   * @param responseHandler the response handler
   * @return an HTTP client request object
   */
  public HttpClientRequest head(int port, String host, String requestURI, Handler<HttpClientResponse> responseHandler) {
    def ret= InternalHelper.safeCreate(this.delegate.head(port, host, requestURI, new Handler<io.vertx.core.http.HttpClientResponse>() {
      public void handle(io.vertx.core.http.HttpClientResponse event) {
        responseHandler.handle(new io.vertx.groovy.core.http.HttpClientResponse(event));
      }
    }), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Create an HTTP HEAD request to send to the server at the specified host and default port, specifying a response handler to receive
   * the response
   * @param host the host
   * @param requestURI the relative URI
   * @param responseHandler the response handler
   * @return an HTTP client request object
   */
  public HttpClientRequest head(String host, String requestURI, Handler<HttpClientResponse> responseHandler) {
    def ret= InternalHelper.safeCreate(this.delegate.head(host, requestURI, new Handler<io.vertx.core.http.HttpClientResponse>() {
      public void handle(io.vertx.core.http.HttpClientResponse event) {
        responseHandler.handle(new io.vertx.groovy.core.http.HttpClientResponse(event));
      }
    }), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Create an HTTP HEAD request to send to the server at the default host and port.
   * @param requestURI the relative URI
   * @return an HTTP client request object
   */
  public HttpClientRequest head(String requestURI) {
    def ret= InternalHelper.safeCreate(this.delegate.head(requestURI), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Create an HTTP HEAD request to send to the server at the default host and port, specifying a response handler to receive
   * the response
   * @param requestURI the relative URI
   * @param responseHandler the response handler
   * @return an HTTP client request object
   */
  public HttpClientRequest head(String requestURI, Handler<HttpClientResponse> responseHandler) {
    def ret= InternalHelper.safeCreate(this.delegate.head(requestURI, new Handler<io.vertx.core.http.HttpClientResponse>() {
      public void handle(io.vertx.core.http.HttpClientResponse event) {
        responseHandler.handle(new io.vertx.groovy.core.http.HttpClientResponse(event));
      }
    }), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Create an HTTP HEAD request to send to the server using an absolute URI
   * @param absoluteURI the absolute URI
   * @return an HTTP client request object
   */
  public HttpClientRequest headAbs(String absoluteURI) {
    def ret= InternalHelper.safeCreate(this.delegate.headAbs(absoluteURI), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Create an HTTP HEAD request to send to the server using an absolute URI, specifying a response handler to receive
   * the response
   * @param absoluteURI the absolute URI
   * @param responseHandler the response handler
   * @return an HTTP client request object
   */
  public HttpClientRequest headAbs(String absoluteURI, Handler<HttpClientResponse> responseHandler) {
    def ret= InternalHelper.safeCreate(this.delegate.headAbs(absoluteURI, new Handler<io.vertx.core.http.HttpClientResponse>() {
      public void handle(io.vertx.core.http.HttpClientResponse event) {
        responseHandler.handle(new io.vertx.groovy.core.http.HttpClientResponse(event));
      }
    }), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Sends an HTTP HEAD request to the server at the specified host and port, specifying a response handler to receive
   * the response
   * @param port the port
   * @param host the host
   * @param requestURI the relative URI
   * @param responseHandler the response handler
   * @return a reference to this, so the API can be used fluently
   */
  public HttpClient headNow(int port, String host, String requestURI, Handler<HttpClientResponse> responseHandler) {
    this.delegate.headNow(port, host, requestURI, new Handler<io.vertx.core.http.HttpClientResponse>() {
      public void handle(io.vertx.core.http.HttpClientResponse event) {
        responseHandler.handle(new io.vertx.groovy.core.http.HttpClientResponse(event));
      }
    });
    return this;
  }
  /**
   * Sends an HTTP HEAD request to the server at the specified host and default port, specifying a response handler to receive
   * the response
   * @param host the host
   * @param requestURI the relative URI
   * @param responseHandler the response handler
   * @return a reference to this, so the API can be used fluently
   */
  public HttpClient headNow(String host, String requestURI, Handler<HttpClientResponse> responseHandler) {
    this.delegate.headNow(host, requestURI, new Handler<io.vertx.core.http.HttpClientResponse>() {
      public void handle(io.vertx.core.http.HttpClientResponse event) {
        responseHandler.handle(new io.vertx.groovy.core.http.HttpClientResponse(event));
      }
    });
    return this;
  }
  /**
   * Sends an HTTP HEAD request  to the server at the default host and port, specifying a response handler to receive
   * the response
   * @param requestURI the relative URI
   * @param responseHandler the response handler
   * @return a reference to this, so the API can be used fluently
   */
  public HttpClient headNow(String requestURI, Handler<HttpClientResponse> responseHandler) {
    this.delegate.headNow(requestURI, new Handler<io.vertx.core.http.HttpClientResponse>() {
      public void handle(io.vertx.core.http.HttpClientResponse event) {
        responseHandler.handle(new io.vertx.groovy.core.http.HttpClientResponse(event));
      }
    });
    return this;
  }
  /**
   * Create an HTTP OPTIONS request to send to the server at the specified host and port.
   * @param port the port
   * @param host the host
   * @param requestURI the relative URI
   * @return an HTTP client request object
   */
  public HttpClientRequest options(int port, String host, String requestURI) {
    def ret= InternalHelper.safeCreate(this.delegate.options(port, host, requestURI), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Create an HTTP OPTIONS request to send to the server at the specified host and default port.
   * @param host the host
   * @param requestURI the relative URI
   * @return an HTTP client request object
   */
  public HttpClientRequest options(String host, String requestURI) {
    def ret= InternalHelper.safeCreate(this.delegate.options(host, requestURI), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Create an HTTP OPTIONS request to send to the server at the specified host and port, specifying a response handler to receive
   * the response
   * @param port the port
   * @param host the host
   * @param requestURI the relative URI
   * @param responseHandler the response handler
   * @return an HTTP client request object
   */
  public HttpClientRequest options(int port, String host, String requestURI, Handler<HttpClientResponse> responseHandler) {
    def ret= InternalHelper.safeCreate(this.delegate.options(port, host, requestURI, new Handler<io.vertx.core.http.HttpClientResponse>() {
      public void handle(io.vertx.core.http.HttpClientResponse event) {
        responseHandler.handle(new io.vertx.groovy.core.http.HttpClientResponse(event));
      }
    }), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Create an HTTP OPTIONS request to send to the server at the specified host and default port, specifying a response handler to receive
   * the response
   * @param host the host
   * @param requestURI the relative URI
   * @param responseHandler the response handler
   * @return an HTTP client request object
   */
  public HttpClientRequest options(String host, String requestURI, Handler<HttpClientResponse> responseHandler) {
    def ret= InternalHelper.safeCreate(this.delegate.options(host, requestURI, new Handler<io.vertx.core.http.HttpClientResponse>() {
      public void handle(io.vertx.core.http.HttpClientResponse event) {
        responseHandler.handle(new io.vertx.groovy.core.http.HttpClientResponse(event));
      }
    }), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Create an HTTP OPTIONS request to send to the server at the default host and port.
   * @param requestURI the relative URI
   * @return an HTTP client request object
   */
  public HttpClientRequest options(String requestURI) {
    def ret= InternalHelper.safeCreate(this.delegate.options(requestURI), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Create an HTTP OPTIONS request to send to the server at the default host and port, specifying a response handler to receive
   * the response
   * @param requestURI the relative URI
   * @param responseHandler the response handler
   * @return an HTTP client request object
   */
  public HttpClientRequest options(String requestURI, Handler<HttpClientResponse> responseHandler) {
    def ret= InternalHelper.safeCreate(this.delegate.options(requestURI, new Handler<io.vertx.core.http.HttpClientResponse>() {
      public void handle(io.vertx.core.http.HttpClientResponse event) {
        responseHandler.handle(new io.vertx.groovy.core.http.HttpClientResponse(event));
      }
    }), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Create an HTTP OPTIONS request to send to the server using an absolute URI
   * @param absoluteURI the absolute URI
   * @return an HTTP client request object
   */
  public HttpClientRequest optionsAbs(String absoluteURI) {
    def ret= InternalHelper.safeCreate(this.delegate.optionsAbs(absoluteURI), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Create an HTTP OPTIONS request to send to the server using an absolute URI, specifying a response handler to receive
   * the response
   * @param absoluteURI the absolute URI
   * @param responseHandler the response handler
   * @return an HTTP client request object
   */
  public HttpClientRequest optionsAbs(String absoluteURI, Handler<HttpClientResponse> responseHandler) {
    def ret= InternalHelper.safeCreate(this.delegate.optionsAbs(absoluteURI, new Handler<io.vertx.core.http.HttpClientResponse>() {
      public void handle(io.vertx.core.http.HttpClientResponse event) {
        responseHandler.handle(new io.vertx.groovy.core.http.HttpClientResponse(event));
      }
    }), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Sends an HTTP OPTIONS request to the server at the specified host and port, specifying a response handler to receive
   * the response
   * @param port the port
   * @param host the host
   * @param requestURI the relative URI
   * @param responseHandler the response handler
   * @return a reference to this, so the API can be used fluently
   */
  public HttpClient optionsNow(int port, String host, String requestURI, Handler<HttpClientResponse> responseHandler) {
    this.delegate.optionsNow(port, host, requestURI, new Handler<io.vertx.core.http.HttpClientResponse>() {
      public void handle(io.vertx.core.http.HttpClientResponse event) {
        responseHandler.handle(new io.vertx.groovy.core.http.HttpClientResponse(event));
      }
    });
    return this;
  }
  /**
   * Sends an HTTP OPTIONS request to the server at the specified host and default port, specifying a response handler to receive
   * the response
   * @param host the host
   * @param requestURI the relative URI
   * @param responseHandler the response handler
   * @return a reference to this, so the API can be used fluently
   */
  public HttpClient optionsNow(String host, String requestURI, Handler<HttpClientResponse> responseHandler) {
    this.delegate.optionsNow(host, requestURI, new Handler<io.vertx.core.http.HttpClientResponse>() {
      public void handle(io.vertx.core.http.HttpClientResponse event) {
        responseHandler.handle(new io.vertx.groovy.core.http.HttpClientResponse(event));
      }
    });
    return this;
  }
  /**
   * Sends an HTTP OPTIONS request  to the server at the default host and port, specifying a response handler to receive
   * the response
   * @param requestURI the relative URI
   * @param responseHandler the response handler
   * @return a reference to this, so the API can be used fluently
   */
  public HttpClient optionsNow(String requestURI, Handler<HttpClientResponse> responseHandler) {
    this.delegate.optionsNow(requestURI, new Handler<io.vertx.core.http.HttpClientResponse>() {
      public void handle(io.vertx.core.http.HttpClientResponse event) {
        responseHandler.handle(new io.vertx.groovy.core.http.HttpClientResponse(event));
      }
    });
    return this;
  }
  /**
   * Create an HTTP PUT request to send to the server at the specified host and port.
   * @param port the port
   * @param host the host
   * @param requestURI the relative URI
   * @return an HTTP client request object
   */
  public HttpClientRequest put(int port, String host, String requestURI) {
    def ret= InternalHelper.safeCreate(this.delegate.put(port, host, requestURI), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Create an HTTP PUT request to send to the server at the specified host and default port.
   * @param host the host
   * @param requestURI the relative URI
   * @return an HTTP client request object
   */
  public HttpClientRequest put(String host, String requestURI) {
    def ret= InternalHelper.safeCreate(this.delegate.put(host, requestURI), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Create an HTTP PUT request to send to the server at the specified host and port, specifying a response handler to receive
   * the response
   * @param port the port
   * @param host the host
   * @param requestURI the relative URI
   * @param responseHandler the response handler
   * @return an HTTP client request object
   */
  public HttpClientRequest put(int port, String host, String requestURI, Handler<HttpClientResponse> responseHandler) {
    def ret= InternalHelper.safeCreate(this.delegate.put(port, host, requestURI, new Handler<io.vertx.core.http.HttpClientResponse>() {
      public void handle(io.vertx.core.http.HttpClientResponse event) {
        responseHandler.handle(new io.vertx.groovy.core.http.HttpClientResponse(event));
      }
    }), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Create an HTTP PUT request to send to the server at the specified host and default port, specifying a response handler to receive
   * the response
   * @param host the host
   * @param requestURI the relative URI
   * @param responseHandler the response handler
   * @return an HTTP client request object
   */
  public HttpClientRequest put(String host, String requestURI, Handler<HttpClientResponse> responseHandler) {
    def ret= InternalHelper.safeCreate(this.delegate.put(host, requestURI, new Handler<io.vertx.core.http.HttpClientResponse>() {
      public void handle(io.vertx.core.http.HttpClientResponse event) {
        responseHandler.handle(new io.vertx.groovy.core.http.HttpClientResponse(event));
      }
    }), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Create an HTTP PUT request to send to the server at the default host and port.
   * @param requestURI the relative URI
   * @return an HTTP client request object
   */
  public HttpClientRequest put(String requestURI) {
    def ret= InternalHelper.safeCreate(this.delegate.put(requestURI), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Create an HTTP PUT request to send to the server at the default host and port, specifying a response handler to receive
   * the response
   * @param requestURI the relative URI
   * @param responseHandler the response handler
   * @return an HTTP client request object
   */
  public HttpClientRequest put(String requestURI, Handler<HttpClientResponse> responseHandler) {
    def ret= InternalHelper.safeCreate(this.delegate.put(requestURI, new Handler<io.vertx.core.http.HttpClientResponse>() {
      public void handle(io.vertx.core.http.HttpClientResponse event) {
        responseHandler.handle(new io.vertx.groovy.core.http.HttpClientResponse(event));
      }
    }), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Create an HTTP PUT request to send to the server using an absolute URI
   * @param absoluteURI the absolute URI
   * @return an HTTP client request object
   */
  public HttpClientRequest putAbs(String absoluteURI) {
    def ret= InternalHelper.safeCreate(this.delegate.putAbs(absoluteURI), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Create an HTTP PUT request to send to the server using an absolute URI, specifying a response handler to receive
   * the response
   * @param absoluteURI the absolute URI
   * @param responseHandler the response handler
   * @return an HTTP client request object
   */
  public HttpClientRequest putAbs(String absoluteURI, Handler<HttpClientResponse> responseHandler) {
    def ret= InternalHelper.safeCreate(this.delegate.putAbs(absoluteURI, new Handler<io.vertx.core.http.HttpClientResponse>() {
      public void handle(io.vertx.core.http.HttpClientResponse event) {
        responseHandler.handle(new io.vertx.groovy.core.http.HttpClientResponse(event));
      }
    }), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Create an HTTP DELETE request to send to the server at the specified host and port.
   * @param port the port
   * @param host the host
   * @param requestURI the relative URI
   * @return an HTTP client request object
   */
  public HttpClientRequest delete(int port, String host, String requestURI) {
    def ret= InternalHelper.safeCreate(this.delegate.delete(port, host, requestURI), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Create an HTTP DELETE request to send to the server at the specified host and default port.
   * @param host the host
   * @param requestURI the relative URI
   * @return an HTTP client request object
   */
  public HttpClientRequest delete(String host, String requestURI) {
    def ret= InternalHelper.safeCreate(this.delegate.delete(host, requestURI), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Create an HTTP DELETE request to send to the server at the specified host and port, specifying a response handler to receive
   * the response
   * @param port the port
   * @param host the host
   * @param requestURI the relative URI
   * @param responseHandler the response handler
   * @return an HTTP client request object
   */
  public HttpClientRequest delete(int port, String host, String requestURI, Handler<HttpClientResponse> responseHandler) {
    def ret= InternalHelper.safeCreate(this.delegate.delete(port, host, requestURI, new Handler<io.vertx.core.http.HttpClientResponse>() {
      public void handle(io.vertx.core.http.HttpClientResponse event) {
        responseHandler.handle(new io.vertx.groovy.core.http.HttpClientResponse(event));
      }
    }), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Create an HTTP DELETE request to send to the server at the specified host and default port, specifying a response handler to receive
   * the response
   * @param host the host
   * @param requestURI the relative URI
   * @param responseHandler the response handler
   * @return an HTTP client request object
   */
  public HttpClientRequest delete(String host, String requestURI, Handler<HttpClientResponse> responseHandler) {
    def ret= InternalHelper.safeCreate(this.delegate.delete(host, requestURI, new Handler<io.vertx.core.http.HttpClientResponse>() {
      public void handle(io.vertx.core.http.HttpClientResponse event) {
        responseHandler.handle(new io.vertx.groovy.core.http.HttpClientResponse(event));
      }
    }), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Create an HTTP DELETE request to send to the server at the default host and port.
   * @param requestURI the relative URI
   * @return an HTTP client request object
   */
  public HttpClientRequest delete(String requestURI) {
    def ret= InternalHelper.safeCreate(this.delegate.delete(requestURI), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Create an HTTP DELETE request to send to the server at the default host and port, specifying a response handler to receive
   * the response
   * @param requestURI the relative URI
   * @param responseHandler the response handler
   * @return an HTTP client request object
   */
  public HttpClientRequest delete(String requestURI, Handler<HttpClientResponse> responseHandler) {
    def ret= InternalHelper.safeCreate(this.delegate.delete(requestURI, new Handler<io.vertx.core.http.HttpClientResponse>() {
      public void handle(io.vertx.core.http.HttpClientResponse event) {
        responseHandler.handle(new io.vertx.groovy.core.http.HttpClientResponse(event));
      }
    }), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Create an HTTP DELETE request to send to the server using an absolute URI
   * @param absoluteURI the absolute URI
   * @return an HTTP client request object
   */
  public HttpClientRequest deleteAbs(String absoluteURI) {
    def ret= InternalHelper.safeCreate(this.delegate.deleteAbs(absoluteURI), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Create an HTTP DELETE request to send to the server using an absolute URI, specifying a response handler to receive
   * the response
   * @param absoluteURI the absolute URI
   * @param responseHandler the response handler
   * @return an HTTP client request object
   */
  public HttpClientRequest deleteAbs(String absoluteURI, Handler<HttpClientResponse> responseHandler) {
    def ret= InternalHelper.safeCreate(this.delegate.deleteAbs(absoluteURI, new Handler<io.vertx.core.http.HttpClientResponse>() {
      public void handle(io.vertx.core.http.HttpClientResponse event) {
        responseHandler.handle(new io.vertx.groovy.core.http.HttpClientResponse(event));
      }
    }), io.vertx.groovy.core.http.HttpClientRequest.class);
    return ret;
  }
  /**
   * Connect a WebSocket to the specified port, host and relative request URI
   * @param port the port
   * @param host the host
   * @param requestURI the relative URI
   * @param wsConnect handler that will be called with the websocket when connected
   * @return a reference to this, so the API can be used fluently
   */
  public HttpClient websocket(int port, String host, String requestURI, Handler<WebSocket> wsConnect) {
    this.delegate.websocket(port, host, requestURI, new Handler<io.vertx.core.http.WebSocket>() {
      public void handle(io.vertx.core.http.WebSocket event) {
        wsConnect.handle(new io.vertx.groovy.core.http.WebSocket(event));
      }
    });
    return this;
  }
  /**
   * Connect a WebSocket to the specified port, host and relative request URI
   * @param port the port
   * @param host the host
   * @param requestURI the relative URI
   * @param wsConnect handler that will be called with the websocket when connected
   * @param failureHandler handler that will be called if websocekt connection fails
   * @return a reference to this, so the API can be used fluently
   */
  public HttpClient websocket(int port, String host, String requestURI, Handler<WebSocket> wsConnect, Handler<Throwable> failureHandler) {
    this.delegate.websocket(port, host, requestURI, new Handler<io.vertx.core.http.WebSocket>() {
      public void handle(io.vertx.core.http.WebSocket event) {
        wsConnect.handle(new io.vertx.groovy.core.http.WebSocket(event));
      }
    }, failureHandler);
    return this;
  }
  /**
   * Connect a WebSocket to the host and relative request URI and default port
   * @param host the host
   * @param requestURI the relative URI
   * @param wsConnect handler that will be called with the websocket when connected
   * @return a reference to this, so the API can be used fluently
   */
  public HttpClient websocket(String host, String requestURI, Handler<WebSocket> wsConnect) {
    this.delegate.websocket(host, requestURI, new Handler<io.vertx.core.http.WebSocket>() {
      public void handle(io.vertx.core.http.WebSocket event) {
        wsConnect.handle(new io.vertx.groovy.core.http.WebSocket(event));
      }
    });
    return this;
  }
  /**
   * Connect a WebSocket to the host and relative request URI and default port
   * @param host the host
   * @param requestURI the relative URI
   * @param wsConnect handler that will be called with the websocket when connected
   * @param failureHandler handler that will be called if websocekt connection fails
   * @return a reference to this, so the API can be used fluently
   */
  public HttpClient websocket(String host, String requestURI, Handler<WebSocket> wsConnect, Handler<Throwable> failureHandler) {
    this.delegate.websocket(host, requestURI, new Handler<io.vertx.core.http.WebSocket>() {
      public void handle(io.vertx.core.http.WebSocket event) {
        wsConnect.handle(new io.vertx.groovy.core.http.WebSocket(event));
      }
    }, failureHandler);
    return this;
  }
  /**
   * Connect a WebSocket to the specified port, host and relative request URI, and with the specified headers
   * @param port the port
   * @param host the host
   * @param requestURI the relative URI
   * @param headers the headers
   * @param wsConnect handler that will be called with the websocket when connected
   * @return a reference to this, so the API can be used fluently
   */
  public HttpClient websocket(int port, String host, String requestURI, MultiMap headers, Handler<WebSocket> wsConnect) {
    this.delegate.websocket(port, host, requestURI, (io.vertx.core.MultiMap)headers.getDelegate(), new Handler<io.vertx.core.http.WebSocket>() {
      public void handle(io.vertx.core.http.WebSocket event) {
        wsConnect.handle(new io.vertx.groovy.core.http.WebSocket(event));
      }
    });
    return this;
  }
  /**
   * Connect a WebSocket to the specified port, host and relative request URI, and with the specified headers
   * @param port the port
   * @param host the host
   * @param requestURI the relative URI
   * @param headers the headers
   * @param wsConnect handler that will be called with the websocket when connected
   * @param failureHandler handler that will be called if websocekt connection fails
   * @return a reference to this, so the API can be used fluently
   */
  public HttpClient websocket(int port, String host, String requestURI, MultiMap headers, Handler<WebSocket> wsConnect, Handler<Throwable> failureHandler) {
    this.delegate.websocket(port, host, requestURI, (io.vertx.core.MultiMap)headers.getDelegate(), new Handler<io.vertx.core.http.WebSocket>() {
      public void handle(io.vertx.core.http.WebSocket event) {
        wsConnect.handle(new io.vertx.groovy.core.http.WebSocket(event));
      }
    }, failureHandler);
    return this;
  }
  /**
   * Connect a WebSocket to the specified host,relative request UR, and default port and with the specified headers
   * @param host the host
   * @param requestURI the relative URI
   * @param headers the headers
   * @param wsConnect handler that will be called with the websocket when connected
   * @return a reference to this, so the API can be used fluently
   */
  public HttpClient websocket(String host, String requestURI, MultiMap headers, Handler<WebSocket> wsConnect) {
    this.delegate.websocket(host, requestURI, (io.vertx.core.MultiMap)headers.getDelegate(), new Handler<io.vertx.core.http.WebSocket>() {
      public void handle(io.vertx.core.http.WebSocket event) {
        wsConnect.handle(new io.vertx.groovy.core.http.WebSocket(event));
      }
    });
    return this;
  }
  /**
   * Connect a WebSocket to the specified host,relative request UR, and default port and with the specified headers
   * @param host the host
   * @param requestURI the relative URI
   * @param headers the headers
   * @param wsConnect handler that will be called with the websocket when connected
   * @param failureHandler handler that will be called if websocekt connection fails
   * @return a reference to this, so the API can be used fluently
   */
  public HttpClient websocket(String host, String requestURI, MultiMap headers, Handler<WebSocket> wsConnect, Handler<Throwable> failureHandler) {
    this.delegate.websocket(host, requestURI, (io.vertx.core.MultiMap)headers.getDelegate(), new Handler<io.vertx.core.http.WebSocket>() {
      public void handle(io.vertx.core.http.WebSocket event) {
        wsConnect.handle(new io.vertx.groovy.core.http.WebSocket(event));
      }
    }, failureHandler);
    return this;
  }
  /**
   * Connect a WebSocket to the specified port, host and relative request URI, with the specified headers and using
   * the specified version of WebSockets
   * @param port the port
   * @param host the host
   * @param requestURI the relative URI
   * @param headers the headers
   * @param version the websocket version
   * @param wsConnect handler that will be called with the websocket when connected
   * @return a reference to this, so the API can be used fluently
   */
  public HttpClient websocket(int port, String host, String requestURI, MultiMap headers, WebsocketVersion version, Handler<WebSocket> wsConnect) {
    this.delegate.websocket(port, host, requestURI, (io.vertx.core.MultiMap)headers.getDelegate(), version, new Handler<io.vertx.core.http.WebSocket>() {
      public void handle(io.vertx.core.http.WebSocket event) {
        wsConnect.handle(new io.vertx.groovy.core.http.WebSocket(event));
      }
    });
    return this;
  }
  /**
   * Connect a WebSocket to the specified port, host and relative request URI, with the specified headers and using
   * the specified version of WebSockets
   * @param port the port
   * @param host the host
   * @param requestURI the relative URI
   * @param headers the headers
   * @param version the websocket version
   * @param wsConnect handler that will be called with the websocket when connected
   * @param failureHandler handler that will be called if websocekt connection fails
   * @return a reference to this, so the API can be used fluently
   */
  public HttpClient websocket(int port, String host, String requestURI, MultiMap headers, WebsocketVersion version, Handler<WebSocket> wsConnect, Handler<Throwable> failureHandler) {
    this.delegate.websocket(port, host, requestURI, (io.vertx.core.MultiMap)headers.getDelegate(), version, new Handler<io.vertx.core.http.WebSocket>() {
      public void handle(io.vertx.core.http.WebSocket event) {
        wsConnect.handle(new io.vertx.groovy.core.http.WebSocket(event));
      }
    }, failureHandler);
    return this;
  }
  /**
   * Connect a WebSocket to the specified host, relative request URI and default port with the specified headers and using
   * the specified version of WebSockets
   * @param host the host
   * @param requestURI the relative URI
   * @param headers the headers
   * @param version the websocket version
   * @param wsConnect handler that will be called with the websocket when connected
   * @return a reference to this, so the API can be used fluently
   */
  public HttpClient websocket(String host, String requestURI, MultiMap headers, WebsocketVersion version, Handler<WebSocket> wsConnect) {
    this.delegate.websocket(host, requestURI, (io.vertx.core.MultiMap)headers.getDelegate(), version, new Handler<io.vertx.core.http.WebSocket>() {
      public void handle(io.vertx.core.http.WebSocket event) {
        wsConnect.handle(new io.vertx.groovy.core.http.WebSocket(event));
      }
    });
    return this;
  }
  /**
   * Connect a WebSocket to the specified host, relative request URI and default port with the specified headers and using
   * the specified version of WebSockets
   * @param host the host
   * @param requestURI the relative URI
   * @param headers the headers
   * @param version the websocket version
   * @param wsConnect handler that will be called with the websocket when connected
   * @param failureHandler handler that will be called if websocekt connection fails
   * @return a reference to this, so the API can be used fluently
   */
  public HttpClient websocket(String host, String requestURI, MultiMap headers, WebsocketVersion version, Handler<WebSocket> wsConnect, Handler<Throwable> failureHandler) {
    this.delegate.websocket(host, requestURI, (io.vertx.core.MultiMap)headers.getDelegate(), version, new Handler<io.vertx.core.http.WebSocket>() {
      public void handle(io.vertx.core.http.WebSocket event) {
        wsConnect.handle(new io.vertx.groovy.core.http.WebSocket(event));
      }
    }, failureHandler);
    return this;
  }
  /**
   * Connect a WebSocket to the specified port, host and relative request URI, with the specified headers, using
   * the specified version of WebSockets, and the specified websocket sub protocols
   * @param port the port
   * @param host the host
   * @param requestURI the relative URI
   * @param headers the headers
   * @param version the websocket version
   * @param subProtocols the subprotocols to use
   * @param wsConnect handler that will be called with the websocket when connected
   * @return a reference to this, so the API can be used fluently
   */
  public HttpClient websocket(int port, String host, String requestURI, MultiMap headers, WebsocketVersion version, String subProtocols, Handler<WebSocket> wsConnect) {
    this.delegate.websocket(port, host, requestURI, (io.vertx.core.MultiMap)headers.getDelegate(), version, subProtocols, new Handler<io.vertx.core.http.WebSocket>() {
      public void handle(io.vertx.core.http.WebSocket event) {
        wsConnect.handle(new io.vertx.groovy.core.http.WebSocket(event));
      }
    });
    return this;
  }
  /**
   * Connect a WebSocket to the specified port, host and relative request URI, with the specified headers, using
   * the specified version of WebSockets, and the specified websocket sub protocols
   * @param port the port
   * @param host the host
   * @param requestURI the relative URI
   * @param headers the headers
   * @param version the websocket version
   * @param subProtocols the subprotocols to use
   * @param wsConnect handler that will be called with the websocket when connected
   * @param failureHandler handler that will be called if websocekt connection fails
   * @return a reference to this, so the API can be used fluently
   */
  public HttpClient websocket(int port, String host, String requestURI, MultiMap headers, WebsocketVersion version, String subProtocols, Handler<WebSocket> wsConnect, Handler<Throwable> failureHandler) {
    this.delegate.websocket(port, host, requestURI, (io.vertx.core.MultiMap)headers.getDelegate(), version, subProtocols, new Handler<io.vertx.core.http.WebSocket>() {
      public void handle(io.vertx.core.http.WebSocket event) {
        wsConnect.handle(new io.vertx.groovy.core.http.WebSocket(event));
      }
    }, failureHandler);
    return this;
  }
  /**
   * Connect a WebSocket to the specified host, relative request URI and default port, with the specified headers, using
   * the specified version of WebSockets, and the specified websocket sub protocols
   * @param host the host
   * @param requestURI the relative URI
   * @param headers the headers
   * @param version the websocket version
   * @param subProtocols the subprotocols to use
   * @param wsConnect handler that will be called with the websocket when connected
   * @return a reference to this, so the API can be used fluently
   */
  public HttpClient websocket(String host, String requestURI, MultiMap headers, WebsocketVersion version, String subProtocols, Handler<WebSocket> wsConnect) {
    this.delegate.websocket(host, requestURI, (io.vertx.core.MultiMap)headers.getDelegate(), version, subProtocols, new Handler<io.vertx.core.http.WebSocket>() {
      public void handle(io.vertx.core.http.WebSocket event) {
        wsConnect.handle(new io.vertx.groovy.core.http.WebSocket(event));
      }
    });
    return this;
  }
  /**
   * Connect a WebSocket to the specified host, relative request URI and default port, with the specified headers, using
   * the specified version of WebSockets, and the specified websocket sub protocols
   * @param host the host
   * @param requestURI the relative URI
   * @param headers the headers
   * @param version the websocket version
   * @param subProtocols the subprotocols to use
   * @param wsConnect handler that will be called with the websocket when connected
   * @param failureHandler handler that will be called if websocekt connection fails
   * @return a reference to this, so the API can be used fluently
   */
  public HttpClient websocket(String host, String requestURI, MultiMap headers, WebsocketVersion version, String subProtocols, Handler<WebSocket> wsConnect, Handler<Throwable> failureHandler) {
    this.delegate.websocket(host, requestURI, (io.vertx.core.MultiMap)headers.getDelegate(), version, subProtocols, new Handler<io.vertx.core.http.WebSocket>() {
      public void handle(io.vertx.core.http.WebSocket event) {
        wsConnect.handle(new io.vertx.groovy.core.http.WebSocket(event));
      }
    }, failureHandler);
    return this;
  }
  /**
   * Connect a WebSocket at the relative request URI using the default host and port
   * @param requestURI the relative URI
   * @param wsConnect handler that will be called with the websocket when connected
   * @return a reference to this, so the API can be used fluently
   */
  public HttpClient websocket(String requestURI, Handler<WebSocket> wsConnect) {
    this.delegate.websocket(requestURI, new Handler<io.vertx.core.http.WebSocket>() {
      public void handle(io.vertx.core.http.WebSocket event) {
        wsConnect.handle(new io.vertx.groovy.core.http.WebSocket(event));
      }
    });
    return this;
  }
  /**
   * Connect a WebSocket at the relative request URI using the default host and port
   * @param requestURI the relative URI
   * @param wsConnect handler that will be called with the websocket when connected
   * @param failureHandler handler that will be called if websocekt connection fails
   * @return a reference to this, so the API can be used fluently
   */
  public HttpClient websocket(String requestURI, Handler<WebSocket> wsConnect, Handler<Throwable> failureHandler) {
    this.delegate.websocket(requestURI, new Handler<io.vertx.core.http.WebSocket>() {
      public void handle(io.vertx.core.http.WebSocket event) {
        wsConnect.handle(new io.vertx.groovy.core.http.WebSocket(event));
      }
    }, failureHandler);
    return this;
  }
  /**
   * Connect a WebSocket at the relative request URI using the default host and port and the specified headers
   * @param requestURI the relative URI
   * @param headers the headers
   * @param wsConnect handler that will be called with the websocket when connected
   * @return a reference to this, so the API can be used fluently
   */
  public HttpClient websocket(String requestURI, MultiMap headers, Handler<WebSocket> wsConnect) {
    this.delegate.websocket(requestURI, (io.vertx.core.MultiMap)headers.getDelegate(), new Handler<io.vertx.core.http.WebSocket>() {
      public void handle(io.vertx.core.http.WebSocket event) {
        wsConnect.handle(new io.vertx.groovy.core.http.WebSocket(event));
      }
    });
    return this;
  }
  /**
   * Connect a WebSocket at the relative request URI using the default host and port and the specified headers
   * @param requestURI the relative URI
   * @param headers the headers
   * @param wsConnect handler that will be called with the websocket when connected
   * @param failureHandler handler that will be called if websocekt connection fails
   * @return a reference to this, so the API can be used fluently
   */
  public HttpClient websocket(String requestURI, MultiMap headers, Handler<WebSocket> wsConnect, Handler<Throwable> failureHandler) {
    this.delegate.websocket(requestURI, (io.vertx.core.MultiMap)headers.getDelegate(), new Handler<io.vertx.core.http.WebSocket>() {
      public void handle(io.vertx.core.http.WebSocket event) {
        wsConnect.handle(new io.vertx.groovy.core.http.WebSocket(event));
      }
    }, failureHandler);
    return this;
  }
  /**
   * Connect a WebSocket at the relative request URI using the default host and port, the specified headers and the
   * specified version of WebSockets
   * @param requestURI the relative URI
   * @param headers the headers
   * @param version the websocket version
   * @param wsConnect handler that will be called with the websocket when connected
   * @return a reference to this, so the API can be used fluently
   */
  public HttpClient websocket(String requestURI, MultiMap headers, WebsocketVersion version, Handler<WebSocket> wsConnect) {
    this.delegate.websocket(requestURI, (io.vertx.core.MultiMap)headers.getDelegate(), version, new Handler<io.vertx.core.http.WebSocket>() {
      public void handle(io.vertx.core.http.WebSocket event) {
        wsConnect.handle(new io.vertx.groovy.core.http.WebSocket(event));
      }
    });
    return this;
  }
  /**
   * Connect a WebSocket at the relative request URI using the default host and port, the specified headers and the
   * specified version of WebSockets
   * @param requestURI the relative URI
   * @param headers the headers
   * @param version the websocket version
   * @param wsConnect handler that will be called with the websocket when connected
   * @param failureHandler handler that will be called if websocekt connection fails
   * @return a reference to this, so the API can be used fluently
   */
  public HttpClient websocket(String requestURI, MultiMap headers, WebsocketVersion version, Handler<WebSocket> wsConnect, Handler<Throwable> failureHandler) {
    this.delegate.websocket(requestURI, (io.vertx.core.MultiMap)headers.getDelegate(), version, new Handler<io.vertx.core.http.WebSocket>() {
      public void handle(io.vertx.core.http.WebSocket event) {
        wsConnect.handle(new io.vertx.groovy.core.http.WebSocket(event));
      }
    }, failureHandler);
    return this;
  }
  /**
   * Connect a WebSocket at the relative request URI using the default host and port, the specified headers, the
   * specified version of WebSockets and the specified sub protocols
   * @param requestURI the relative URI
   * @param headers the headers
   * @param version the websocket version
   * @param subProtocols the subprotocols
   * @param wsConnect handler that will be called with the websocket when connected
   * @return a reference to this, so the API can be used fluently
   */
  public HttpClient websocket(String requestURI, MultiMap headers, WebsocketVersion version, String subProtocols, Handler<WebSocket> wsConnect) {
    this.delegate.websocket(requestURI, (io.vertx.core.MultiMap)headers.getDelegate(), version, subProtocols, new Handler<io.vertx.core.http.WebSocket>() {
      public void handle(io.vertx.core.http.WebSocket event) {
        wsConnect.handle(new io.vertx.groovy.core.http.WebSocket(event));
      }
    });
    return this;
  }
  /**
   * Connect a WebSocket at the relative request URI using the default host and port, the specified headers, the
   * specified version of WebSockets and the specified sub protocols
   * @param requestURI the relative URI
   * @param headers the headers
   * @param version the websocket version
   * @param subProtocols the subprotocols
   * @param wsConnect handler that will be called with the websocket when connected
   * @param failureHandler handler that will be called if websocekt connection fails
   * @return a reference to this, so the API can be used fluently
   */
  public HttpClient websocket(String requestURI, MultiMap headers, WebsocketVersion version, String subProtocols, Handler<WebSocket> wsConnect, Handler<Throwable> failureHandler) {
    this.delegate.websocket(requestURI, (io.vertx.core.MultiMap)headers.getDelegate(), version, subProtocols, new Handler<io.vertx.core.http.WebSocket>() {
      public void handle(io.vertx.core.http.WebSocket event) {
        wsConnect.handle(new io.vertx.groovy.core.http.WebSocket(event));
      }
    }, failureHandler);
    return this;
  }
  /**
   * Create a WebSocket stream to the specified port, host and relative request URI
   * @param port the port
   * @param host the host
   * @param requestURI the relative URI
   * @return a reference to this, so the API can be used fluently
   */
  public WebSocketStream websocketStream(int port, String host, String requestURI) {
    def ret= InternalHelper.safeCreate(this.delegate.websocketStream(port, host, requestURI), io.vertx.groovy.core.http.WebSocketStream.class);
    return ret;
  }
  /**
   * Create a WebSocket stream to the specified host, relative request URI and default port
   * @param host the host
   * @param requestURI the relative URI
   * @return a reference to this, so the API can be used fluently
   */
  public WebSocketStream websocketStream(String host, String requestURI) {
    def ret= InternalHelper.safeCreate(this.delegate.websocketStream(host, requestURI), io.vertx.groovy.core.http.WebSocketStream.class);
    return ret;
  }
  /**
   * Create a WebSocket stream to the specified port, host and relative request URI, and with the specified headers
   * @param port the port
   * @param host the host
   * @param requestURI the relative URI
   * @param headers the headers
   * @return a reference to this, so the API can be used fluently
   */
  public WebSocketStream websocketStream(int port, String host, String requestURI, MultiMap headers) {
    def ret= InternalHelper.safeCreate(this.delegate.websocketStream(port, host, requestURI, (io.vertx.core.MultiMap)headers.getDelegate()), io.vertx.groovy.core.http.WebSocketStream.class);
    return ret;
  }
  /**
   * Create a WebSocket stream to the specified host, relative request URI and default port and with the specified headers
   * @param host the host
   * @param requestURI the relative URI
   * @param headers the headers
   * @return a reference to this, so the API can be used fluently
   */
  public WebSocketStream websocketStream(String host, String requestURI, MultiMap headers) {
    def ret= InternalHelper.safeCreate(this.delegate.websocketStream(host, requestURI, (io.vertx.core.MultiMap)headers.getDelegate()), io.vertx.groovy.core.http.WebSocketStream.class);
    return ret;
  }
  /**
   * Create a WebSocket stream to the specified port, host and relative request URI, with the specified headers and using
   * the specified version of WebSockets
   * @param port the port
   * @param host the host
   * @param requestURI the relative URI
   * @param headers the headers
   * @param version the websocket version
   * @return a reference to this, so the API can be used fluently
   */
  public WebSocketStream websocketStream(int port, String host, String requestURI, MultiMap headers, WebsocketVersion version) {
    def ret= InternalHelper.safeCreate(this.delegate.websocketStream(port, host, requestURI, (io.vertx.core.MultiMap)headers.getDelegate(), version), io.vertx.groovy.core.http.WebSocketStream.class);
    return ret;
  }
  /**
   * Create a WebSocket stream to the specified host, relative request URI and default port and with the specified headers and using
   * the specified version of WebSockets
   * @param host the host
   * @param requestURI the relative URI
   * @param headers the headers
   * @param version the websocket version
   * @return a reference to this, so the API can be used fluently
   */
  public WebSocketStream websocketStream(String host, String requestURI, MultiMap headers, WebsocketVersion version) {
    def ret= InternalHelper.safeCreate(this.delegate.websocketStream(host, requestURI, (io.vertx.core.MultiMap)headers.getDelegate(), version), io.vertx.groovy.core.http.WebSocketStream.class);
    return ret;
  }
  /**
   * Create a WebSocket stream to the specified port, host and relative request URI, with the specified headers, using
   * the specified version of WebSockets, and the specified websocket sub protocols
   * @param port the port
   * @param host the host
   * @param requestURI the relative URI
   * @param headers the headers
   * @param version the websocket version
   * @param subProtocols the subprotocols to use
   * @return a reference to this, so the API can be used fluently
   */
  public WebSocketStream websocketStream(int port, String host, String requestURI, MultiMap headers, WebsocketVersion version, String subProtocols) {
    def ret= InternalHelper.safeCreate(this.delegate.websocketStream(port, host, requestURI, (io.vertx.core.MultiMap)headers.getDelegate(), version, subProtocols), io.vertx.groovy.core.http.WebSocketStream.class);
    return ret;
  }
  /**
   * Create a WebSocket stream to the specified host, relative request URI and default port, with the specified headers, using
   * the specified version of WebSockets, and the specified websocket sub protocols
   * @param host the host
   * @param requestURI the relative URI
   * @param headers the headers
   * @param version the websocket version
   * @param subProtocols the subprotocols to use
   * @return a reference to this, so the API can be used fluently
   */
  public WebSocketStream websocketStream(String host, String requestURI, MultiMap headers, WebsocketVersion version, String subProtocols) {
    def ret= InternalHelper.safeCreate(this.delegate.websocketStream(host, requestURI, (io.vertx.core.MultiMap)headers.getDelegate(), version, subProtocols), io.vertx.groovy.core.http.WebSocketStream.class);
    return ret;
  }
  /**
   * Create a WebSocket stream at the relative request URI using the default host and port and the specified headers
   * @param requestURI the relative URI
   * @return a reference to this, so the API can be used fluently
   */
  public WebSocketStream websocketStream(String requestURI) {
    def ret= InternalHelper.safeCreate(this.delegate.websocketStream(requestURI), io.vertx.groovy.core.http.WebSocketStream.class);
    return ret;
  }
  /**
   * Create a WebSocket stream at the relative request URI using the default host and port and the specified headers
   * @param requestURI the relative URI
   * @param headers the headers
   * @return a reference to this, so the API can be used fluently
   */
  public WebSocketStream websocketStream(String requestURI, MultiMap headers) {
    def ret= InternalHelper.safeCreate(this.delegate.websocketStream(requestURI, (io.vertx.core.MultiMap)headers.getDelegate()), io.vertx.groovy.core.http.WebSocketStream.class);
    return ret;
  }
  /**
   * Create a WebSocket stream at the relative request URI using the default host and port, the specified headers and the
   * specified version of WebSockets
   * @param requestURI the relative URI
   * @param headers the headers
   * @param version the websocket version
   * @return a reference to this, so the API can be used fluently
   */
  public WebSocketStream websocketStream(String requestURI, MultiMap headers, WebsocketVersion version) {
    def ret= InternalHelper.safeCreate(this.delegate.websocketStream(requestURI, (io.vertx.core.MultiMap)headers.getDelegate(), version), io.vertx.groovy.core.http.WebSocketStream.class);
    return ret;
  }
  /**
   * Create a WebSocket stream at the relative request URI using the default host and port, the specified headers, the
   * specified version of WebSockets and the specified sub protocols
   * @param requestURI the relative URI
   * @param headers the headers
   * @param version the websocket version
   * @param subProtocols the subprotocols
   * @return a reference to this, so the API can be used fluently
   */
  public WebSocketStream websocketStream(String requestURI, MultiMap headers, WebsocketVersion version, String subProtocols) {
    def ret= InternalHelper.safeCreate(this.delegate.websocketStream(requestURI, (io.vertx.core.MultiMap)headers.getDelegate(), version, subProtocols), io.vertx.groovy.core.http.WebSocketStream.class);
    return ret;
  }
  /**
   * Close the client. Closing will close down any pooled connections.
   * Clients should always be closed after use.
   */
  public void close() {
    this.delegate.close();
  }
}
