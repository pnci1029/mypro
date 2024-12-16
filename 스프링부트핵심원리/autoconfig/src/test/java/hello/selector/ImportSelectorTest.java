package hello.selector;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

public class ImportSelectorTest {

    @DisplayName("정적 방식의 컨피그")
    @Test
    void staticConfig() {
        // given
        AnnotationConfigApplicationContext appContext =
                new AnnotationConfigApplicationContext(StaticConfig.class);
        // when
        HelloBean bean = appContext.getBean(HelloBean.class);

        // then
        Assertions.assertThat(bean).isNotNull();
    }

    @DisplayName("")
    @Test
    void selectorConfig() {
        // given
        AnnotationConfigApplicationContext appContext =
                new AnnotationConfigApplicationContext(SelectorConfig.class);

        // when
        HelloBean bean = appContext.getBean(HelloBean.class);

        // then
        Assertions.assertThat(bean).isNotNull();
        
    }

    @Configuration
    @Import(HelloConfig.class)
    public static class StaticConfig {

    }

    @Configuration
    @Import(HelloImportSelector.class)
    public static class SelectorConfig {

    }
}
