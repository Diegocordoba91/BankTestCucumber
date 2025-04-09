package utilities;

import io.restassured.specification.RequestSpecification;

public class RequestProvider {

    private static final ThreadLocal<RequestSpecification> threadLocal = new ThreadLocal<>();

    public void set(RequestSpecification request) {
        threadLocal.set(request);
    }

    public static RequestSpecification get() {
        return threadLocal.get();
    }




    
}
