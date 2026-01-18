package com.telusko.ecom_proj.filters;

import jakarta.servlet.*;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.Part;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Collection;
import java.util.Enumeration;

@Component
public class DetailedRequestLoggingFilter implements Filter {

    @Override
    public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
            throws IOException, ServletException {

        HttpServletRequest httpRequest = (HttpServletRequest) request;

        System.out.println("\n╔═══════════════════════════════════════════════╗");
        System.out.println("║         INCOMING HTTP REQUEST                 ║");
        System.out.println("╚═══════════════════════════════════════════════╝");

        System.out.println("┌─ Request Info ────────────────────────────────");
        System.out.println("│ Method:        " + httpRequest.getMethod());
        System.out.println("│ URI:           " + httpRequest.getRequestURI());
        System.out.println("│ Query String:  " + httpRequest.getQueryString());
        System.out.println("│ Content-Type:  " + httpRequest.getContentType());
        System.out.println("│ Content-Length: " + httpRequest.getContentLength());
        System.out.println("│ Remote Addr:   " + httpRequest.getRemoteAddr());
        System.out.println("└───────────────────────────────────────────────");

        System.out.println("\n┌─ Headers ─────────────────────────────────────");
        Enumeration<String> headerNames = httpRequest.getHeaderNames();
        while (headerNames.hasMoreElements()) {
            String headerName = headerNames.nextElement();
            String headerValue = httpRequest.getHeader(headerName);
            System.out.println("│ " + headerName + ": " + headerValue);
        }
        System.out.println("└───────────────────────────────────────────────");

        // Log multipart data if present
        if (httpRequest.getContentType() != null &&
                httpRequest.getContentType().startsWith("multipart/form-data")) {
            try {
                System.out.println("\n┌─ Multipart Parts ─────────────────────────────");
                Collection<Part> parts = httpRequest.getParts();
                for (Part part : parts) {
                    System.out.println("│ Part Name:         " + part.getName());
                    System.out.println("│ Content-Type:      " + part.getContentType());
                    System.out.println("│ Size:              " + part.getSize() + " bytes");
                    System.out.println("│ Submitted FileName: " + part.getSubmittedFileName());
                    System.out.println("│ ─────────────────────────────────────────────");
                }
                System.out.println("└───────────────────────────────────────────────");
            } catch (Exception e) {
                System.out.println("│ Error reading multipart: " + e.getMessage());
                System.out.println("└───────────────────────────────────────────────");
            }
        }

        System.out.println("\n┌─ Parameters ──────────────────────────────────");
        httpRequest.getParameterMap().forEach((key, value) -> {
            System.out.println("│ " + key + ": " + String.join(", ", value));
        });
        System.out.println("└───────────────────────────────────────────────\n");

        // Continue the chain
        chain.doFilter(request, response);
    }
}