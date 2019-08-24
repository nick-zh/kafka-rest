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

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class JsonProduceRecord extends ProduceRecordBase<Object, Object, Object> {

  @JsonCreator
  public JsonProduceRecord(
      @JsonProperty("key") Object key,
      @JsonProperty("value") Object value,
      @JsonProperty("headers") Object headers
  ) {
    super(key, value, headers);
  }

  public JsonProduceRecord(Object key, Object value) {
    this(key, value, null);
  }

  public JsonProduceRecord(Object value) {
    this(null, value, null);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    JsonProduceRecord that = (JsonProduceRecord) o;

    return key != null ? key.equals(that.key) : that.key == null
            && !(value != null ? !value.equals(that.value) : that.value != null)
            && !(headers != null ? !headers.equals(that.headers) : that.headers != null) ;

  }

  @Override
  public int hashCode() {
    int result = key != null ? key.hashCode() : 0;
    result = 31 * result + (value != null ? value.hashCode() : 0);
    result = result + (headers != null ? headers.hashCode() : 0);
    return result;
  }
}
