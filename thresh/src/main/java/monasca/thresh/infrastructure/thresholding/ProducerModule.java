/*
 * Copyright (c) 2014 Hewlett-Packard Development Company, L.P.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or
 * implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package monasca.thresh.infrastructure.thresholding;

import monasca.common.configuration.KafkaProducerConfiguration;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;

public class ProducerModule extends AbstractModule {
  private KafkaProducerConfiguration config;
  private AlarmEventForwarder alarmEventForwarder;

  @Override
  protected void configure() {}

  public ProducerModule(KafkaProducerConfiguration config) {
    this.config = config;
  }

  public ProducerModule(AlarmEventForwarder alarmEventForwarder) {
    this.alarmEventForwarder = alarmEventForwarder;
  }

  @Provides
  AlarmEventForwarder alarmEventForwarder() {
    return alarmEventForwarder == null ? new KafkaAlarmEventForwarder(config) : alarmEventForwarder;
  }
}
