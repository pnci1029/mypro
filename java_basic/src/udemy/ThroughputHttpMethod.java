package udemy;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import com.sun.net.httpserver.HttpServer;

import java.io.IOException;
import java.io.OutputStream;
import java.net.InetSocketAddress;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ThroughputHttpMethod {
    private static final String INPUT_FILE = "resources/throughput/war_and_peace.txt";
    public static final int NUMBER_OF_THREADS = 1;

    public static void main(String[] args) throws IOException {
        String text = new String(Files.readAllBytes(Paths.get(INPUT_FILE)));
        startServer(text);
    }

    public static void startServer(String text) throws IOException {
        HttpServer server = HttpServer.create(new InetSocketAddress(8000), 0);
        server.createContext("/search", new WordCountHandler(text));

        // 쓰레드풀 지정 후 서버 실행
        ExecutorService executor = Executors.newFixedThreadPool(NUMBER_OF_THREADS);
        server.setExecutor(executor);
        server.start();
    }

    private static class WordCountHandler implements HttpHandler {
        private String text;

        public WordCountHandler(String text) {
            this.text = text;
        }

        @Override
        public void handle(HttpExchange exchange) throws IOException {
            String query = exchange.getRequestURI().getQuery();
            /**
             검색 파라미터로 word=talk (talk 갯수 카운트 희망)
             입력했을 경우 스플릿하여 검색
             */
            String[] keyValue = query.split("=");
            String action = keyValue[0];
            String word = keyValue[1];

            // 오타 400에러 처리
            if (!action.equals("word")) {
                exchange.sendResponseHeaders(400, 0);
                return;
            }

            // countWord 함수 통해 검색 단어 갯수 추출
            long count = countWord(word);
            byte[] response = Long.toString(count).getBytes();
            exchange.sendResponseHeaders(200, response.length);
            OutputStream responseBody = exchange.getResponseBody();
            responseBody.write(response);
            responseBody.close();
        }

        private long countWord(String word) {
            long count = 0;
            int index = 0;
            while (index >= 0) {
                index = text.indexOf(word, index);

                if (index >= 0) {
                    count++;
                    index++;
                }
            }
            return count;
        }
    }
}
