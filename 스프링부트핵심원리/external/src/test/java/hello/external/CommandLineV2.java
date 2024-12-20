package hello.external;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.DefaultApplicationArguments;

import java.util.List;
import java.util.Set;

@Slf4j
public class CommandLineV2 {
    /**
     * 기존 커맨드 라인을 key-value 형태로 받을 수 있는
     * --url=devdb --username=dev_user --password=dev_pw mode=on
     * 스프링의 표준 방식 활용
     */
    public static void main(String[] args) {
        for (String arg : args) {
            log.info("args:{}", arg);
            /**
                 -> args = url=devdb
                 -> args = username=dev_user
                 -> args = password=dev_pw
                 -> args = mode=on
             */

        }
        ApplicationArguments appArgs = new DefaultApplicationArguments(args);

        log.info("SourceArgs = {}", List.of(appArgs.getSourceArgs())); // SourceArgs = [url=devdb, username=dev_user, password=dev_pw, mode=on]
        log.info("NonOptionArgs = {}", appArgs.getNonOptionArgs()); // NonOptionArgs = [mode=on]
        log.info("OptionNames = {}", appArgs.getOptionNames()); // OptionNames = [password, url, username]

    }
}
