package hello.container;

import jakarta.servlet.ServletContainerInitializer;
import jakarta.servlet.ServletContext;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.HandlesTypes;

import java.util.Set;

@HandlesTypes(AppInit.class)
public class MyContainerInitV2 implements ServletContainerInitializer {

    @Override
    public void onStartup(Set<Class<?>> c, ServletContext ctx) throws ServletException {

        System.out.println("MyContainerInitV2.onStartup");
        System.out.println("c = " + c);
        System.out.println("ctx = " + ctx);

        /**
            c =>  class hello.controller.AppInitV1Servlet
            @HandlesTypes의 인터페이스 구현체를 가지고옴
         */
        for (Class<?> appInitClass : c) {
            try {

                //  new AppInitV1Servlet() 와 같은 코드
                AppInit appInit = (AppInit)appInitClass.getDeclaredConstructor().newInstance();
                appInit.onStartup(ctx);

            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }
}
