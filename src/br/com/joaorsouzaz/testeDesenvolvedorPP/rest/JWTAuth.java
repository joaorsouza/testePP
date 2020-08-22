package br.com.joaorsouzaz.testeDesenvolvedorPP.rest;

import java.security.Key;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.Date;

import javax.crypto.spec.SecretKeySpec;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.Response;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;

@Path("login")
public class JWTAuth {

	@POST
	@Path("logar")
	public Response autenticar() {

		String token = gerarToken();

		return Response.ok().header("Authorization", "Bearer " + token).build();
	}

	static Key generateKey() {
		String keyString = "ZWQyYmVmYjExNDk5NDg5ZTI1NzBjYjA1M2Y3NzRiOGVkOTNlODllZGRhYjNmNzg4NjdhMmE1ZjMyYzU4ODQ1ZQ=="; // ”joao”
																														// codificado em
																														// SHA-256
																														// e
																														// EncodeBase64;
		Key key = new SecretKeySpec(keyString.getBytes(), 0, keyString.getBytes().length, "HmacSHA256");
		return key;
	}

	private String gerarToken() {

		String jwtToken = Jwts.builder().signWith(SignatureAlgorithm.HS256, generateKey()).setHeaderParam("typ", "JWT")
				.setSubject("gerente").setIssuer("teste").setIssuedAt(new Date())
				.setExpiration(toDate(LocalDateTime.now().plusMinutes(60L)))

				.compact();
		return jwtToken;
	}

	private Date toDate(LocalDateTime localDateTime) {
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

}
