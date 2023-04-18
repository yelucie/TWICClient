package com.servlets;

import java.io.IOException;

import com.services.VilleBlo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class ListeVilles extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private VilleBlo villeBlo;

	public ListeVilles() {
		super();
		villeBlo = new VilleBlo();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		int size = villeBlo.getPageSize() + 1;
		int page = request.getParameter("page") != null ? Integer.parseInt(request.getParameter("page")) : 1;

		request.setAttribute("size", size);
		
		// Avoir la température
		//request.setAttribute("villes", villeBlo.getVillesByPage(page - 1, true));
		
		// Sans la température
		request.setAttribute("villes", villeBlo.getVillesByPage(page - 1, false));
		this.getServletContext().getRequestDispatcher("/WEB-INF/listeVilles.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}