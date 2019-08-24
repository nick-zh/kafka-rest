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

import java.io.IOException;
import java.util.Arrays;

import io.confluent.rest.validation.ConstraintViolations;

public class BinaryProduceRecord extends ProduceRecordBase<byte[], byte[], byte[]> {

  @JsonCreator
  public BinaryProduceRecord(@JsonProperty("key") String key, @JsonProperty("value") String value, @JsonProperty("headers") String headers)
      throws IOException {
    super(null, null, null);
    try {
      this.key = (key != null) ? EntityUtils.parseBase64Binary(key) : null;
    } catch (IllegalArgumentException e) {
      throw ConstraintViolations.simpleException("Record key contains invalid base64 encoding");
    }
    try {
      this.value = (value != null) ? EntityUtils.parseBase64Binary(value) : null;
    } catch (IllegalArgumentException e) {
      throw ConstraintViolations.simpleException("Record value contains invalid base64 encoding");
    }
    try {
      this.headers = (value != headers) ? EntityUtils.parseBase64Binary(headers) : null;
    } catch (IllegalArgumentException e) {
      throw ConstraintViolations.simpleException("Record headers contains invalid base64 encoding");
    }
  }

  public BinaryProduceRecord(byte[] key, byte[] value, byte[] headers) {
    super(key, value, headers);
  }

  public BinaryProduceRecord(byte[] key, byte[] value) {
    super(key, value, null);
  }


  public BinaryProduceRecord(byte[] unencodedValue) {
    this(null, unencodedValue, null);
  }

  @Override
  @JsonProperty("key")
  public String getJsonKey() {
    return (key == null ? null : EntityUtils.encodeBase64Binary(key));
  }

  @Override
  @JsonProperty("value")
  public String getJsonValue() {
    return (value == null ? null : EntityUtils.encodeBase64Binary(value));
  }

  @Override
  @JsonProperty("headers")
  public String getJsonHeaders() {
    return (headers == null ? null : EntityUtils.encodeBase64Binary(headers));
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }

    BinaryProduceRecord that = (BinaryProduceRecord) o;

    if (!Arrays.equals(key, that.key)) {
      return false;
    }
    if (!Arrays.equals(value, that.value)) {
      return false;
    }
    if (!Arrays.equals(headers, that.headers)) {
      return false;
    }

    return true;
  }

  @Override
  public int hashCode() {
    int result = key != null ? Arrays.hashCode(key) : 0;
    result = 31 * result + (value != null ? Arrays.hashCode(value) : 0);
    result = result + (headers != null ? Arrays.hashCode(headers) : 0);
    return result;
  }
}
