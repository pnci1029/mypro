package com.example.jooq1;

import org.jooq.DSLContext;
import org.jooq.generated.tables.JServers;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class Jooq1ApplicationTests {

    @Autowired
    DSLContext dslContext;

    @Test
    void contextLoads() {
        dslContext.selectFrom(JAc.SERVERS)
                .limit(10)
                .fetch();
    }

}
