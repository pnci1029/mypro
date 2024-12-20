package hello.external;

import lombok.extern.slf4j.Slf4j;

@Slf4j
public class CommandLineV1 {
    public static void main(String[] args) {
        /**
         * edit configuration -> program argument -> dataA dataB
         * url=devdb username=dev_user password=dev_pw
         * //띄어쓰기 기준으로 구분됨
         */
        for (String arg : args) {
            log.info("args:{}", arg);
        }
    }
}
