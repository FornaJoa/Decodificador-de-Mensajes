

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * The test class DecodificadorMensajesTest.
 *
 * @author  (your name)
 * @version (a version number or a date)
 */
public class DecodificadorMensajesTest
{
    /**
     * Default constructor for test class DecodificadorMensajesTest
     */
    public DecodificadorMensajesTest()
    {
    }

    /**
     * Sets up the test fixture.
     *
     * Called before every test case method.
     */
    @BeforeEach
    public void setUp()
    {
    }

    /**
     * Tears down the test fixture.
     *
     * Called after every test case method.
     */
    @AfterEach
    public void tearDown()
    {
    }

    @Test
    public void test()
    {
        Mensaje mensaje2 = new Mensaje();
        mensaje2.agregarLinea("}}}}");
        CodificadorMensajes codifica1 = new CodificadorMensajes(mensaje2);
        codifica1.codificarMensaje();
        int[] codigo = codifica1.obtenerCodigoEncripcion();
        Mensaje mensaje3 = codifica1.obtenerMensajeCodificado();
        DecodificadorMensajes decodifi1 = new DecodificadorMensajes(mensaje3, codigo);
        decodifi1.decodificarMensaje();
        Mensaje mensaje4 = decodifi1.obtenerMensajeDecodificado();        
        assertTrue(mensaje2.equals(decodifi1.obtenerMensajeDecodificado()));
    }
}

