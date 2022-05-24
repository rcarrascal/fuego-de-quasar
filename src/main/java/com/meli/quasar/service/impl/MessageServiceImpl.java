package com.meli.quasar.service.impl;

import com.meli.quasar.exception.HelpMessageException;
import com.meli.quasar.service.MessageService;
import com.meli.quasar.util.Constants;
import lombok.extern.slf4j.Slf4j;
import org.apache.logging.log4j.util.Strings;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
@Slf4j
public class MessageServiceImpl implements MessageService {
    @Override
    public String getMessage(List<List<String>> messages) {
        int numMessages = messages.size();
        if (numMessages < 3) {
            throw new HelpMessageException(Constants.EXCEPTION_MESSAGESERROR);
        }

        List<String> messageResultList = getFullMessage(messages);

        validateMessage(messageResultList);

        return messageResultList.stream().collect(Collectors.joining(Constants.BLANK));
    }

    private List<String> getFullMessage(List<List<String>> messages) {
        List<String> messageResultList = new ArrayList<>(messages.get(0).size());
        messages.get(0).stream().forEach(value -> messageResultList.add(Constants.EMPTY));
        for (int i = 0; i < messages.size(); i++) {
            for (int j = 0; j < messages.get(i).size(); j++) {
                if (Strings.isBlank(messageResultList.get(j))) {
                    messageResultList.set(j, messages.get(i).get(j));
                }
            }
        }

        return messageResultList;
    }

    private void validateMessage(List<String> messageResultList) {
        long count = messageResultList.stream().filter(message -> Constants.EMPTY.equals(message)).count();
        if (count > 0) {
            throw new HelpMessageException(Constants.EXCEPTION_MESSAGESERROR);
        }
    }
}
