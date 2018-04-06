package pl.sternik.jk.weekend.heloo;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;


public class HelloServlet extends HttpServlet {

    private static final Logger logger = LoggerFactory.getLogger(HelloServlet.class);
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getOutputStream().println("Hurra moj pierwszy serwlet z obsluga POST --->"
                + request.getParameter("param1"));
        logger.info("jestem w doPOST");
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getOutputStream().println("Hurra moj pierwszy serwlet");
        logger.info("jestem w doGET");

    }
    
    @Override
    public void init(ServletConfig config) throws ServletException {
        super.init(config);
        logger.info("jestem w init");
    }

    @Override
    public void destroy() {
        super.destroy();

    }
    
}
