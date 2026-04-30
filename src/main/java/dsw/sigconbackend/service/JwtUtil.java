package dsw.sigconbackend.service;

import dsw.sigconbackend.model.Usuario;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.function.Function;

@Service
public class JwtUtil {
    @Value("${jwt.secret}")
    private String secretKey;

    @Value("3600000")   //1hora=60 minutos* 60 segundos*1000milisegundos=3600000
    private Long jwtExpiration;

    public String generateToken(Usuario usuario, List<String> modules){
        Map<String, Object> claims =new HashMap<>();
        claims.put("personaId", usuario.getPersona().getIdPersona());
        claims.put("email", usuario.getEmail());
        claims.put("names", usuario.getPersona().getNombres());
        claims.put("role", usuario.getRol()!=null ? usuario.getRol().getCodigo() :null);
        claims.put("modules", modules);

        return Jwts.builder()
                .claims(claims)
                .subject(usuario.getEmail())
                .issuedAt(new Date(System.currentTimeMillis()))
                .expiration(new Date(System.currentTimeMillis() + jwtExpiration))
                .signWith(getSigningKey())
                .compact();

    }

    public SecretKey getSigningKey(){
        byte[] keyBytes=Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractUserName(String token){
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    public boolean isTokenValid(String token, Usuario usuario){
        final String username=extractUserName(token);
        return (username.equals(usuario.getEmail())) && !isTokenExpired(token);
    }

    public boolean isTokenExpired(String token){
        return extractExpiration(token).before(new Date());

    }

    public Date extractExpiration(String token){
        return extractClaim(token,Claims::getExpiration);

    }

}
