/*
 * Copyright 2018 The MQTT Bee project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 *
 */

package org.mqttbee.mqtt.mqtt3.exceptions;

import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.mqttbee.internal.util.AsyncRuntimeException;
import org.mqttbee.mqtt.mqtt3.message.Mqtt3Message;

/**
 * @author David Katz
 * @author Silvio Giebl
 */
public abstract class Mqtt3MessageException extends AsyncRuntimeException {

    public Mqtt3MessageException(final @Nullable String message, final @Nullable Throwable cause) {
        super(message, cause);
    }

    public abstract @NotNull Mqtt3Message getMqttMessage();
}
