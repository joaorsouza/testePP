package br.com.joaorsouzaz.testeDesenvolvedorPP.rest;

import java.io.IOException;
import java.security.Key;
import java.util.List;

import javax.annotation.Priority;
import javax.ws.rs.Priorities;
import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerRequestFilter;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.HttpHeaders;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.ext.Provider;

import io.jsonwebtoken.Jwts;

@Provider
@Priority(Priorities.AUTHENTICATION)
public class JWTFilter implements ContainerRequestFilter {

	@Context
	private UriInfo uriInfo;

	@Override
	public void filter(ContainerRequestContext requestContext) throws IOException {
		String authorizationHeader = requestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

		if (authorizationHeader != null && authorizationHeader.contains("Bearer ")) {
			String token = authorizationHeader.substring("Bearer".length()).trim();

			if (tokenValido(token, JWTAuth.generateKey())) {

				return;
			}
		} else if (acessoConsultaGeracao(requestContext)) {

			return;
		}
		throw new IOException("Não foi possivel validar este Token");
	}

	private boolean acessoConsultaGeracao(ContainerRequestContext requestContext) {
		if (requestContext.getUriInfo().getAbsolutePath().toString().contains("createnormal") || 
				requestContext.getUriInfo().getAbsolutePath().toString().contains("createpref") ||
					requestContext.getUriInfo().getAbsolutePath().toString().contains("logar")) {
			return true;
		} else if (requestContext.getUriInfo().getAbsolutePath().toString().contains("createpref")){
			return true;
		}
		else {
			return false;
		}

	}

	private boolean tokenValido(String token, Key key) {
		try {
			Jwts.parser().setSigningKey(key).parseClaimsJws(token);
			return true;
		} catch (Exception e) {
			return false;
		}
	}

}
