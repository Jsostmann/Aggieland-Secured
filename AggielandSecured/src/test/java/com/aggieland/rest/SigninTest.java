package com.aggieland.rest;

import static org.junit.Assert.assertEquals;
import static org.mockito.Mockito.when;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.junit.Before;
import org.junit.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

public class SigninTest {

    @Mock
    HttpServletRequest request;

    @Mock
    HttpServletResponse response;

    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
    }

    String expectedResult = "jsostmann | secretPassword";

    @Test
    public void testFullName() throws IOException, ServletException {

        when(request.getParameter("username")).thenReturn("jsostmann");
        when(request.getParameter("password")).thenReturn("secretPassword");

        StringWriter sw = new StringWriter();
        PrintWriter pw = new PrintWriter(sw);

        when(response.getWriter()).thenReturn(pw);

        Signin myServlet = new Signin();

        myServlet.doGet(request, response);

        String result = sw.getBuffer().toString().trim();

        assertEquals(expectedResult,result);

    }
}
