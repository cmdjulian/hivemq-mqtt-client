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
 */

package org.mqttbee.mqtt.mqtt3.exceptions;

import io.reactivex.functions.Function;
import org.jetbrains.annotations.NotNull;
import org.mqttbee.api.mqtt.mqtt3.exceptions.Mqtt3MessageException;
import org.mqttbee.api.mqtt.mqtt3.message.Mqtt3Message;
import org.mqttbee.api.mqtt.mqtt5.exceptions.Mqtt5MessageException;
import org.mqttbee.api.mqtt.mqtt5.message.Mqtt5Message;
import org.mqttbee.mqtt.message.connect.MqttConnect;
import org.mqttbee.mqtt.message.connect.connack.MqttConnAck;
import org.mqttbee.mqtt.message.connect.connack.mqtt3.Mqtt3ConnAckView;
import org.mqttbee.mqtt.message.connect.mqtt3.Mqtt3ConnectView;
import org.mqttbee.mqtt.message.disconnect.MqttDisconnect;
import org.mqttbee.mqtt.message.disconnect.mqtt3.Mqtt3DisconnectView;
import org.mqttbee.mqtt.message.ping.MqttPingReq;
import org.mqttbee.mqtt.message.ping.MqttPingResp;
import org.mqttbee.mqtt.message.ping.mqtt3.Mqtt3PingReqView;
import org.mqttbee.mqtt.message.ping.mqtt3.Mqtt3PingRespView;
import org.mqttbee.mqtt.message.publish.MqttPublish;
import org.mqttbee.mqtt.message.publish.mqtt3.Mqtt3PublishView;
import org.mqttbee.mqtt.message.publish.puback.MqttPubAck;
import org.mqttbee.mqtt.message.publish.puback.mqtt3.Mqtt3PubAckView;
import org.mqttbee.mqtt.message.publish.pubcomp.MqttPubComp;
import org.mqttbee.mqtt.message.publish.pubcomp.mqtt3.Mqtt3PubCompView;
import org.mqttbee.mqtt.message.publish.pubrec.MqttPubRec;
import org.mqttbee.mqtt.message.publish.pubrec.mqtt3.Mqtt3PubRecView;
import org.mqttbee.mqtt.message.publish.pubrel.MqttPubRel;
import org.mqttbee.mqtt.message.publish.pubrel.mqtt3.Mqtt3PubRelView;
import org.mqttbee.mqtt.message.subscribe.MqttSubscribe;
import org.mqttbee.mqtt.message.subscribe.mqtt3.Mqtt3SubscribeView;
import org.mqttbee.mqtt.message.subscribe.suback.MqttSubAck;
import org.mqttbee.mqtt.message.subscribe.suback.mqtt3.Mqtt3SubAckView;
import org.mqttbee.mqtt.message.unsubscribe.MqttUnsubscribe;
import org.mqttbee.mqtt.message.unsubscribe.mqtt3.Mqtt3UnsubscribeView;
import org.mqttbee.mqtt.message.unsubscribe.unsuback.MqttUnsubAck;
import org.mqttbee.mqtt.message.unsubscribe.unsuback.mqtt3.Mqtt3UnsubAckView;

/**
 * @author David Katz
 * @author Silvio Giebl
 */
public class Mqtt3ExceptionFactory {

    public static @NotNull Function<Throwable, Throwable> MAPPER = Mqtt3ExceptionFactory::map;
    public static @NotNull java.util.function.Function<Throwable, Throwable> MAPPER_JAVA = Mqtt3ExceptionFactory::map;

    public static @NotNull Throwable map(final @NotNull Throwable throwable) {
        if (throwable instanceof Mqtt5MessageException) {
            return map((Mqtt5MessageException) throwable);
        }
        return throwable;
    }

    public static @NotNull Mqtt3MessageException map(final @NotNull Mqtt5MessageException mqtt5MessageException) {
        return new Mqtt3MessageException(viewOf(mqtt5MessageException.getMqttMessage()),
                mqtt5MessageException.getMessage(), mqtt5MessageException.getCause());
    }

    private static @NotNull Mqtt3Message viewOf(final @NotNull Mqtt5Message mqtt5Message) {
        if (mqtt5Message instanceof MqttConnect) {
            return Mqtt3ConnectView.of((MqttConnect) mqtt5Message);
        } else if (mqtt5Message instanceof MqttConnAck) {
            return Mqtt3ConnAckView.of((MqttConnAck) mqtt5Message);
        } else if (mqtt5Message instanceof MqttPublish) {
            return Mqtt3PublishView.of((MqttPublish) mqtt5Message);
        } else if (mqtt5Message instanceof MqttPubAck) {
            return Mqtt3PubAckView.INSTANCE;
        } else if (mqtt5Message instanceof MqttPubRec) {
            return Mqtt3PubRecView.INSTANCE;
        } else if (mqtt5Message instanceof MqttPubRel) {
            return Mqtt3PubRelView.INSTANCE;
        } else if (mqtt5Message instanceof MqttPubComp) {
            return Mqtt3PubCompView.INSTANCE;
        } else if (mqtt5Message instanceof MqttSubscribe) {
            return Mqtt3SubscribeView.of((MqttSubscribe) mqtt5Message);
        } else if (mqtt5Message instanceof MqttSubAck) {
            return Mqtt3SubAckView.of((MqttSubAck) mqtt5Message);
        } else if (mqtt5Message instanceof MqttUnsubscribe) {
            return Mqtt3UnsubscribeView.of((MqttUnsubscribe) mqtt5Message);
        } else if (mqtt5Message instanceof MqttUnsubAck) {
            return Mqtt3UnsubAckView.INSTANCE;
        } else if (mqtt5Message instanceof MqttPingReq) {
            return Mqtt3PingReqView.INSTANCE;
        } else if (mqtt5Message instanceof MqttPingResp) {
            return Mqtt3PingRespView.INSTANCE;
        } else if (mqtt5Message instanceof MqttDisconnect) {
            return Mqtt3DisconnectView.INSTANCE;
        }
        throw new IllegalStateException();
    }
}
