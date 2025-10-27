package com.tricol.controller;

import com.tricol.model.Fournisseur;
import com.tricol.service.FournisseurService;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.Controller;
import com.fasterxml.jackson.databind.ObjectMapper;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.util.List;

public class FournisseurController implements Controller {

    private FournisseurService fournisseurService;
    private ObjectMapper objectMapper = new ObjectMapper();

    public void setFournisseurService(FournisseurService fournisseurService) {
        this.fournisseurService = fournisseurService;
    }

    @Override
    public ModelAndView handleRequest(HttpServletRequest request, HttpServletResponse response) throws Exception {
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");
        
        String method = request.getMethod();
        String uri = request.getRequestURI();
        String contextPath = request.getContextPath();
        String path = uri.substring(contextPath.length());
        
        // Extract ID from path if present
        String[] parts = path.split("/");
        Long id = null;
        if (parts.length > 4) {
            try {
                id = Long.parseLong(parts[4]);
            } catch (NumberFormatException e) {
                // No ID
            }
        }
        
        if ("GET".equals(method)) {
            if (id == null) {
                // GET all
                List<Fournisseur> fournisseurs = fournisseurService.findAll();
                response.getWriter().write(objectMapper.writeValueAsString(fournisseurs));
            } else {
                // GET by ID
                Fournisseur fournisseur = fournisseurService.findById(id).orElse(null);
                response.getWriter().write(objectMapper.writeValueAsString(fournisseur));
            }
        } else if ("POST".equals(method)) {
            // CREATE
            BufferedReader reader = request.getReader();
            Fournisseur fournisseur = objectMapper.readValue(reader, Fournisseur.class);
            Fournisseur saved = fournisseurService.save(fournisseur);
            response.getWriter().write(objectMapper.writeValueAsString(saved));
        } else if ("PUT".equals(method)) {
            // UPDATE
            BufferedReader reader = request.getReader();
            Fournisseur fournisseur = objectMapper.readValue(reader, Fournisseur.class);
            Fournisseur updated = fournisseurService.update(id, fournisseur);
            response.getWriter().write(objectMapper.writeValueAsString(updated));
        } else if ("DELETE".equals(method)) {
            // DELETE
            fournisseurService.deleteById(id);
            response.setStatus(HttpServletResponse.SC_OK);
        }
        
        return null;
    }
}
