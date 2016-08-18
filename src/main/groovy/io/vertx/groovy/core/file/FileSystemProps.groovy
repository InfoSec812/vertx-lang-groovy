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

package io.vertx.groovy.core.file;
import groovy.transform.CompileStatic
import io.vertx.lang.groovy.InternalHelper
import io.vertx.core.json.JsonObject
/**
 * Represents properties of the file system.
*/
@CompileStatic
public class FileSystemProps {
  private final def io.vertx.core.file.FileSystemProps delegate;
  public FileSystemProps(Object delegate) {
    this.delegate = (io.vertx.core.file.FileSystemProps) delegate;
  }
  public Object getDelegate() {
    return delegate;
  }
  /**
   * @return The total space on the file system, in bytes
   */
  public long totalSpace() {
    def ret = delegate.totalSpace();
    return ret;
  }
  /**
   * @return The total un-allocated space on the file system, in bytes
   */
  public long unallocatedSpace() {
    def ret = delegate.unallocatedSpace();
    return ret;
  }
  /**
   * @return The total usable space on the file system, in bytes
   */
  public long usableSpace() {
    def ret = delegate.usableSpace();
    return ret;
  }
}
