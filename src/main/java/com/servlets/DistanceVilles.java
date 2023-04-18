package com.servlets;

import java.io.IOException;

import com.forms.FormDistance;
import com.services.VilleBlo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class DistanceVilles extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private VilleBlo villeBlo;

	public DistanceVilles() {
		super();
		villeBlo = new VilleBlo();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("villes", villeBlo.getAllVilles());
		this.getServletContext().getRequestDispatcher("/WEB-INF/distanceVilles.jsp").forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		request.setAttribute("resultat", (new FormDistance()).getResult(request, villeBlo));
		doGet(request, response);
	}

}
