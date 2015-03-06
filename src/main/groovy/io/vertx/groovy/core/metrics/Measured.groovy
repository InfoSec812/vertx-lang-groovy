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

package io.vertx.groovy.core.metrics;
import groovy.transform.CompileStatic
import io.vertx.lang.groovy.InternalHelper
/**
 * @author <a href="mailto:nscavell@redhat.com">Nick Scavelli</a>
*/
@CompileStatic
public interface Measured {
  public Object getDelegate();
  String metricBaseName();

  static final java.util.function.Function<io.vertx.core.metrics.Measured, Measured> FACTORY = io.vertx.lang.groovy.Factories.createFactory() {
    io.vertx.core.metrics.Measured arg -> new MeasuredImpl(arg);
  };
}

@CompileStatic
class MeasuredImpl implements Measured {
  final def io.vertx.core.metrics.Measured delegate;
  public MeasuredImpl(io.vertx.core.metrics.Measured delegate) {
    this.delegate = delegate;
  }
  public Object getDelegate() {
    return delegate;
  }
  /**
   * The metric base name
   * @return the metric base name
   */
  public String metricBaseName() {
    def ret = ((io.vertx.core.metrics.Measured) this.delegate).metricBaseName();
    return ret;
  }
}
