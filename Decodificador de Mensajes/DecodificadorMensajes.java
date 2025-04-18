/**
 * Clase DecodificadorMensajes: representa una componente capaz de descifrar
 * un mensaje en formato texto, dado el mensaje y el código usado para la
 * encripción. El mensaje a descifrar/decodificar debe ser un objeto
 * de tipo Mensaje (básicamente, una lista de strings, donde cada string
 * representa una línea). Se asume que el mensaje es ASCII, es decir, todos
 * los caracteres del mensaje tienen códigos en el rango [0, 127].
 *
 * La codificación/decodificación utiliza una variante de Cifrado Cesar, en
 * el cual el desplazamiento se basa en una código de encripción múltiple.
 * Véase Cifrado de Vigenère para más detalles.
 *
 * @autor N. Aguirre
 * @versión 0.1
 */
public class DecodificadorMensajes {
    /**
     * Mensaje codificado
     */
    private Mensaje mensajeADecodificar;
   
    /**
     * Código a utilizar
     */
    private int[] codigoEncripcion;
   
    /**
     * Mensaje decodificado
     */
    private Mensaje mensajeDecodificado;

    /**
     * Constructor de la clase DecodificadorMensajes.
     * Inicializa el mensaje a desencriptar/decodificar con el parámetro pasado,
     * junto con el código de desencripción.
     * Precondición: tanto el mensaje msg como el código codigo no pueden ser nulos
     * @param msg es el mensaje a desencriptar.
     * @param codigo es el código de desencripción.
     */
    public DecodificadorMensajes(Mensaje msg, int[] codigo) {
        if (msg == null)
            throw new IllegalArgumentException("Mensaje nulo");
        if (codigo == null)
            throw new IllegalArgumentException("Código inválido.");
        mensajeADecodificar = msg;
        codigoEncripcion = codigo;
        mensajeDecodificado = null;
    }

    /**
     * Desencripta el mensaje. El mensaje no debe estar desencriptado.
     * Precondición: El mensaje aún no fue descifrado (i.e., el campo
     * mensajeDecodificado es null).
     */
    
    public void decodificarMensaje() {
        if (mensajeDecodificado != null)
            throw new IllegalStateException("El mensaje ya fue decodificado");
        mensajeDecodificado = new Mensaje();
        int j = 0;
        while (j < mensajeADecodificar.cantLineas()) {
            String mensajeListo = desencriptarCadena(mensajeADecodificar.obtenerLinea(j), codigoEncripcion);
            mensajeDecodificado.agregarLinea(mensajeListo);
            j++;
        }
    }
    
    /**
     * Retorna el mensaje ya decodificado/descifrado.
     * Precondición: el mensaje debe haber sido decodificado previamente (i.e.,
     * se debe haber llamado a decodificarMensaje()).
     * Postcondicion: se retorna el mensaje descifrado/decodificado.
     * @return el mensaje descifrado.
     */
    public Mensaje obtenerMensajeDecodificado() {
        if (mensajeDecodificado == null)
            throw new IllegalStateException("Mensaje aún no decodificado");
        return mensajeDecodificado;
    }
   
    /**
     * Desencripta una cadena, dado un código numérico. Se usan los dígitos del código
     * para reemplazar cada caracter de la cadena por el caracter correspondiente a
     * "trasladar" el mismo el número de lugares que indica el código, en sentido inverso
     * al de encripción (es decir, se resta el código al caracter). El código tiene
     * múltiples valores: se usa el primero para el primer caracter, el segundo para el
     * segundo, y así sucesivamente. Si se agota el código, se vuelve al comienzo del mismo,
     * hasta desencriptar toda la cadena.
     * Precondición: tanto str como codigo no deben ser nulos.
     * @param str es la cadena a desencriptar
     * @param codigo es el código a utilizar para la desencripción
     */
    private String desencriptarCadena(String str, int[] codigo) {
        if (str == null) 
            throw new IllegalArgumentException("Cadena nula");
        if (codigo == null) 
            throw new IllegalArgumentException("Código inválido");
        String result = "";
        int indiceCodigo = 0;
        int curr = 0;
        for (int i = 0; i < str.length(); i++) {
             curr = str.charAt(i);
            for (int j = 0; j <codigo[indiceCodigo]; j++) 
            {  
                curr = (int) curr - 1;
                if(curr < 0)
                {
                    curr = 127;            
                }
            }
            //char currEncriptado = (char) ((curr - codigo[indiceCodigo]) % 128);
            result = result + (char) curr;
            indiceCodigo = (indiceCodigo + 1) % (codigo.length);
        }
        return result;
    }
}
