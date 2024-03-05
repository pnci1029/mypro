package com.example.practicaltest.spring.spring.api.service.mail;

import com.example.practicaltest.spring.spring.client.mail.MailSendClient;
import com.example.practicaltest.spring.spring.domain.history.mail.MailSendHistory;
import com.example.practicaltest.spring.spring.domain.history.mail.MailSendHistoryRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class MailServiceTest {

    @Mock
    MailSendClient mailSendClient;

    @Mock
    MailSendHistoryRepository mailSendHistoryRepository;

    @DisplayName("메일 전송 테스트")
    @Test
    void sendMail() {
        // given
        /**
         * 아래 두 의존성을 순수 mock으로 받아서 사용하기 위함
         * private final MailSendClient mailSendClient;
         * private final MailSendHistoryRepository mailSendHistoryRepository;
         */
//        MailSendClient mailSendClient = Mockito.mock(MailSendClient.class);
//        MailSendHistoryRepository mailSendHistoryRepository = Mockito.mock(MailSendHistoryRepository.class);

        MailService mailService = new MailService(mailSendClient, mailSendHistoryRepository);

        when(mailSendClient.sendEmail(anyString(), anyString(), anyString(), anyString()))
                .thenReturn(true);
//        when(mailSendHistoryRepository.save(any(MailSendHistory.class))).thenReturn();

        // when
        boolean result = mailService.sendMail("", "", "", "");

        // then
        Mockito.verify(mailSendHistoryRepository, times(1)).save(any(MailSendHistory.class));
        assertThat(result).isTrue();
    }

}