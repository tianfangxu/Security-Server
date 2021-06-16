package com.mot.receiver;

import com.mot.common.enums.ReceiverMessageEnums;
import com.mot.common.utils.SpringContextHolder;
import org.springframework.context.ApplicationContext;

import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author tianfx
 * @date 2021/6/9 11:01 上午
 */
public class ReceiverMessageFactory {

    public static List<ReceiverMessageHolder> getHolders(){
        ApplicationContext context = SpringContextHolder.getApplicationContext();
        ReceiverMessageEnums[] receiverMessageEnums = ReceiverMessageEnums.values();
        Arrays.sort(receiverMessageEnums, Comparator.comparingInt(ReceiverMessageEnums::getCode));
        return Arrays.stream(receiverMessageEnums).map(r->context.getBean(r.getHolder())).collect(Collectors.toList());
    }

}
