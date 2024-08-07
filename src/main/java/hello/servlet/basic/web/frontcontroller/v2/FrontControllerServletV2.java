package hello.servlet.basic.web.frontcontroller.v2;

import hello.servlet.basic.web.frontcontroller.MyView;
import hello.servlet.basic.web.frontcontroller.v2.controller.MemberFormControllerV2;
import hello.servlet.basic.web.frontcontroller.v2.controller.MemberListControllerV2;
import hello.servlet.basic.web.frontcontroller.v2.controller.MemberSaveControllerV2;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "frontControllerServletV2", urlPatterns = "/front-controller/v2/*")
public class FrontControllerServletV2 extends HttpServlet {
    Map<String, ControllerV2> controllers=new HashMap<>();

    public FrontControllerServletV2(){
        controllers.put("/front-controller/v2/members/new-form", new MemberFormControllerV2());
        controllers.put("/front-controller/v2/members/save", new MemberSaveControllerV2());
        controllers.put("/front-controller/v2/members", new MemberListControllerV2());
    }

    @Override
    protected void service(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        String url= request.getRequestURI();
        System.out.println("url = " + url);

        ControllerV2 controller=controllers.get(url);

        if(controller==null){
            response.setStatus(HttpServletResponse.SC_NOT_FOUND);
            return;
        }

        MyView view = controller.process(request, response);
        view.render(request, response);
    }
}
