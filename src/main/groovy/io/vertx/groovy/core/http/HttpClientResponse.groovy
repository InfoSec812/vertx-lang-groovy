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
import java.util.List
import io.vertx.groovy.core.buffer.Buffer
import io.vertx.groovy.core.streams.ReadStream
import io.vertx.groovy.core.MultiMap
import io.vertx.core.Handler
import io.vertx.groovy.core.net.NetSocket
/**
 * Represents a client-side HTTP response.
 * <p>
 * Vert.x provides you with one of these via the handler that was provided when creating the {@link io.vertx.groovy.core.http.HttpClientRequest}
 * or that was set on the {@link io.vertx.groovy.core.http.HttpClientRequest} instance.
 * <p>
 * It implements {@link io.vertx.groovy.core.streams.ReadStream} so it can be used with
 * {@link io.vertx.groovy.core.streams.Pump} to pump data with flow control.
*/
@CompileStatic
public class HttpClientResponse implements ReadStream<Buffer> {
  final def io.vertx.core.http.HttpClientResponse delegate;
  public HttpClientResponse(io.vertx.core.http.HttpClientResponse delegate) {
    this.delegate = delegate;
  }
  public Object getDelegate() {
    return delegate;
  }
  public HttpClientResponse resume() {
    this.delegate.resume();
    return this;
  }
  public HttpClientResponse exceptionHandler(Handler<Throwable> handler) {
    this.delegate.exceptionHandler(handler);
    return this;
  }
  public HttpClientResponse handler(Handler<Buffer> handler) {
    this.delegate.handler(new Handler<io.vertx.core.buffer.Buffer>() {
      public void handle(io.vertx.core.buffer.Buffer event) {
        handler.handle(new io.vertx.groovy.core.buffer.Buffer(event));
      }
    });
    return this;
  }
  public HttpClientResponse pause() {
    this.delegate.pause();
    return this;
  }
  public HttpClientResponse endHandler(Handler<Void> endHandler) {
    this.delegate.endHandler(endHandler);
    return this;
  }
  /**
   * @return the status code of the response
   * @return 
   */
  public int statusCode() {
    def ret = this.delegate.statusCode();
    return ret;
  }
  /**
   * @return the status message of the response
   * @return 
   */
  public String statusMessage() {
    def ret = this.delegate.statusMessage();
    return ret;
  }
  /**
   * @return the headers
   * @return 
   */
  public MultiMap headers() {
    if (cached_0 != null) {
      return cached_0;
    }
    def ret= new io.vertx.groovy.core.MultiMap(this.delegate.headers());
    cached_0 = ret;
    return ret;
  }
  /**
   * Return the first header value with the specified name
   * @param headerName the header name
   * @return the header value
   */
  public String getHeader(String headerName) {
    def ret = this.delegate.getHeader(headerName);
    return ret;
  }
  /**
   * Return the first trailer value with the specified name
   * @param trailerName the trailer name
   * @return the trailer value
   */
  public String getTrailer(String trailerName) {
    def ret = this.delegate.getTrailer(trailerName);
    return ret;
  }
  /**
   * @return the trailers
   * @return 
   */
  public MultiMap trailers() {
    if (cached_1 != null) {
      return cached_1;
    }
    def ret= new io.vertx.groovy.core.MultiMap(this.delegate.trailers());
    cached_1 = ret;
    return ret;
  }
  /**
   * @return the Set-Cookie headers (including trailers)
   * @return 
   */
  public List<String> cookies() {
    if (cached_2 != null) {
      return cached_2;
    }
    def ret = this.delegate.cookies();
    cached_2 = ret;
    return ret;
  }
  /**
   * Convenience method for receiving the entire request body in one piece.
   * <p>
   * This saves you having to manually set a dataHandler and an endHandler and append the chunks of the body until
   * the whole body received. Don't use this if your request body is large - you could potentially run out of RAM.
   * @param bodyHandler This handler will be called after all the body has been received
   * @return 
   */
  public HttpClientResponse bodyHandler(Handler<Buffer> bodyHandler) {
    this.delegate.bodyHandler(new Handler<io.vertx.core.buffer.Buffer>() {
      public void handle(io.vertx.core.buffer.Buffer event) {
        bodyHandler.handle(new io.vertx.groovy.core.buffer.Buffer(event));
      }
    });
    return this;
  }
  /**
   * Get a net socket for the underlying connection of this request.
   * <p>
   * USE THIS WITH CAUTION! Writing to the socket directly if you don't know what you're doing can easily break the HTTP protocol
   * <p>
   * One valid use-case for calling this is to receive the {@link io.vertx.groovy.core.net.NetSocket} after a HTTP CONNECT was issued to the
   * remote peer and it responded with a status code of 200.
   * @return the net socket
   */
  public NetSocket netSocket() {
    if (cached_3 != null) {
      return cached_3;
    }
    def ret= new io.vertx.groovy.core.net.NetSocket(this.delegate.netSocket());
    cached_3 = ret;
    return ret;
  }
  private MultiMap cached_0;
  private MultiMap cached_1;
  private java.util.List<java.lang.String> cached_2;
  private NetSocket cached_3;
}
