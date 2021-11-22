package backend.hyperion.adra.servicio;

import backend.hyperion.adra.entity.Usuario;

public interface UsuarioService {
	public Usuario findByUsername(String username);

}
