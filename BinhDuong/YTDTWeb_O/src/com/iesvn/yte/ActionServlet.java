package com.iesvn.yte;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.richfaces.json.XML;


public class ActionServlet extends HttpServlet {
   
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException, ClassNotFoundException, InstantiationException, IllegalAccessException, Exception {
        //System.out.println("---Start process request---");
        request.setCharacterEncoding("UTF-8");
        String actionType = request.getParameter("urlAction");
        String myActionType = "com.iesvn.yte.dieutri.ajax." + actionType;
        
        String xmlData = request.getParameter("xmlData");
        //System.out.println(xmlData);
        
        Action action = (Action) Class.forName(myActionType).newInstance();
        
        String myResult = action.performAction(xmlData);
        
        //System.out.println(myResult);
        
        //response.setContentType("text/xml;charset=UTF-8");
        //response.getWriter().print(myResult);
        if(actionType.equals("GetCLSAction") || actionType.equals("GetCLSActionNT") || actionType.equals("GetCLSBangGiaCDHA")){
        	response.setContentType("text/xml;charset=UTF-8");
            response.getWriter().print(myResult);
        }else{
	        response.setContentType("application/x-json;charset=UTF-8");        
	        response.getWriter().print(XML.toJSONObject(myResult));
        }
        
        //System.out.println("---End process request---");
    } 

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    } 

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
    throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
        } catch (Exception ex) {
            Logger.getLogger(ActionServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}


