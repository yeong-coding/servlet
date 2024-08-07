package hello.servlet.basic.web.frontcontroller.v5;

import hello.servlet.basic.web.frontcontroller.ModelView;
import hello.servlet.basic.web.frontcontroller.v3.ControllerV3;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

public class ControllerV3HandlerAdapter implements MyHandlerAdapter{
    @Override
    public boolean supports(Object handler) {
        if(handler instanceof ControllerV3){
            return true;
        }
        return false;
    }

    @Override
    public ModelView handle(HttpServletRequest request, HttpServletResponse response, Object handler) throws ServletException, IOException {

        ControllerV3 controller=(ControllerV3) handler;
        Map<String, String> paramMap = createParamMap(request);
        ModelView mv = controller.process(paramMap);
        return mv;

    }

    private static Map<String, String> createParamMap(HttpServletRequest request) {
        Map<String, String> map=new HashMap<>();
        request.getParameterNames()
                .asIterator()
                .forEachRemaining(param->map.put(param, request.getParameter(param)));
        return map;
    }
}
