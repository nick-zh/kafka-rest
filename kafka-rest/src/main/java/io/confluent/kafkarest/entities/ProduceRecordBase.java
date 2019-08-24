/*
 * Copyright 2018 Confluent Inc.
 *
 * Licensed under the Confluent Community License (the "License"); you may not use
 * this file except in compliance with the License.  You may obtain a copy of the
 * License at
 *
 * http://www.confluent.io/confluent-community-license
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS, WITHOUT
 * WARRANTIES OF ANY KIND, either express or implied.  See the License for the
 * specific language governing permissions and limitations under the License.
 */

package io.confluent.kafkarest.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;

public abstract class ProduceRecordBase<K, V, H> implements ProduceRecord<K, V, H> {

  protected K key;
  protected V value;
  protected H headers;

  public ProduceRecordBase(@JsonProperty K key, @JsonProperty V value, @JsonProperty H headers) {
    this.key = key;
    this.value = value;
    this.headers = headers;
  }

  @JsonIgnore
  public K getKey() {
    return key;
  }

  public void setKey(K key) {
    this.key = key;
  }

  @JsonIgnore
  public V getValue() {
    return value;
  }

  public void setValue(V value) {
    this.value = value;
  }

  @JsonIgnore
  public H getHeaders() {
    return headers;
  }

  public void setHeaders(H headers) {
    this.headers = headers;
  }

  @Override
  public Integer partition() {
    return null;
  }

  /**
   * Return a JSON-serializable version of the key. This does not need to handle schemas.
   */
  @JsonProperty("key")
  public Object getJsonKey() {
    return key;
  }

  /**
   * Return a JSON-serializable version of the value. This does not need to handle schemas.
   */
  @JsonProperty("value")
  public Object getJsonValue() {
    return value;
  }

  /**
   * Return a JSON-serializable version of the headers. This does not need to handle schemas.
   */
  @JsonProperty("headers")
  public Object getJsonHeaders() {
    return headers;
  }
}