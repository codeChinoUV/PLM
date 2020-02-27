package modelo.persistencia;

import modelo.dataclass.ConfiguracionConexionBD;

import java.io.IOException;

public interface ArchivoDAO {
    ConfiguracionConexionBD getConfiguracionDB() throws IOException;
}
