package com.servlets;

import java.io.IOException;

import com.forms.Form;
import com.services.VilleBlo;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

public class FormVille extends HttpServlet {
	private static final long serialVersionUID = 1L;

	private VilleBlo villeBlo;

	public FormVille() {
		super();
		villeBlo = new VilleBlo();
	}

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String method = request.getQueryString();

		if ("ajouter".equals(method)) {
			showForm(request, response);
		} else if (method.contains("editer")) {
			request.setAttribute("ville", villeBlo.getVille(method.substring(7)));
			showForm(request, response);
		} else if (method.contains("supprimer")) {
			villeBlo.deleteVille(method.substring(10));
			response.sendRedirect(request.getContextPath() + "/ListeVilles");
		} else {
			response.sendRedirect(request.getContextPath() + "/ListeVilles");
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String form = request.getParameter("form");
		Form ajout = new Form();

		if ("ajouter".equals(form)) {
			request.setAttribute("resultat", ajout.getResult(request, villeBlo, form));
		} else if ("enregistrer".equals(form)) {
			request.setAttribute("resultat", ajout.getResult(request, villeBlo, form));
			request.setAttribute("ville", villeBlo.getVille(ajout.getVille().getCodeCommune()));
		}

		this.getServletContext().getRequestDispatcher("/WEB-INF/formVille.jsp").forward(request, response);
	}

	private void showForm(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		this.getServletContext().getRequestDispatcher("/WEB-INF/formVille.jsp").forward(request, response);
	}
}