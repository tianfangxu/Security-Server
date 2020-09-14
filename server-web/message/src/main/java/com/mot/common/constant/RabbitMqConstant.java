package com.mot.common.constant;

import com.mot.config.properties.GlobalSettingConfig;

public class RabbitMqConstant {

    public static final String exchangeName = "messageExchange";

    public static final String routerKeyPrefix = "message.rabbit";

    public static String routerKey = routerKeyPrefix + "." + GlobalSettingConfig.getPropValue("server_name");
}
