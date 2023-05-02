package com.example.pro.config;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WebSocketTest {

    private static final String WS_ENDPOINT = "/websocket";
    private static final String MESSAGE = "hello";

    private BlockingQueue<String> messageQueue = new LinkedBlockingQueue<>();

    @Autowired
    private TestRestTemplate restTemplate;

    @Autowired
    private WebSocketStompClient webSocketStompClient;

    private WebSocketTestHandler webSocketTestHandler;

    @LocalServerPort
    private int port;

    @BeforeAll
    public void setUp() throws Exception {
        webSocketStompClient.setMessageConverter(new StringMessageConverter());
        webSocketTestHandler = new WebSocketTestHandler(messageQueue);
    }

    @Test
    public void testWebSocket() throws Exception {
        WebSocketHttpHeaders headers = new WebSocketHttpHeaders();
        headers.setSecWebSocketProtocol("v10.stomp");

        webSocketStompClient.connect(getWebSocketUrl(), headers, webSocketTestHandler);

        Thread.sleep(500L);

        assertEquals("Received: " + MESSAGE, messageQueue.poll(1, TimeUnit.SECONDS));
    }

    private String getWebSocketUrl() {
        return "ws://localhost:" + port + WS_ENDPOINT;
    }

    private static class WebSocketTestHandler extends AbstractWebSocketHandler {
        private final BlockingQueue<String> messageQueue;

        public WebSocketTestHandler(BlockingQueue<String> messageQueue) {
            this.messageQueue = messageQueue;
        }

        @Override
        public void afterConnectionEstablished(WebSocketSession session) throws Exception {
            session.sendMessage(new TextMessage(MESSAGE));
        }

        @Override
        protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
            messageQueue.add("Received: " + message.getPayload());
        }
    }
}