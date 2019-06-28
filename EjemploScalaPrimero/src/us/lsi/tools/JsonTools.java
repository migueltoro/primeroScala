package us.lsi.tools;

import java.io.File;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URL;
import java.net.URLEncoder;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;



/**
 * Clase de utilicad con métodos para ayudar a serializar y deserilizar tipos en Jackson.
 * @author Toñi Reina, Fermín Cruz
 *
 */
public class JsonTools {
	
	private static final ObjectMapper MAPPER = new ObjectMapper();

	/**
	 * @param obj Objeto que se va a serializar
	 * @return Cadena con el objeto o serializado en formato JSON
	 */
	public static String toJSON(Object obj) {
		String res = null;
		try {
			res = MAPPER.writerWithDefaultPrettyPrinter()
					    .writeValueAsString(obj);
		} catch (JsonProcessingException e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * @param obj Objeto que se va a serializar
	 * @param filename Nombre del fichero en el que se va a guardar el objeto serializado
	 * @post Crea un fichero con nombre filename, que contiene la serialización JSON del objeto obj    
	 */
	public static void toJSONFile(Object obj, String filename) {
		try {
			MAPPER.writeValue(new File(filename), obj);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	
	/**
	 * @param jsonAsString Cadena en formato JSON a partir de la que se quiere crear un objeto.
	 * @param tipo Referencia a la clase que se debe usar para crear el objeto.
	 * @return Objeto de tipo T creado a partir de la cadena jsonAsString.
	 *  
	 * */
	public static <T> T fromJSON(String jsonAsString, Class<T> tipo) {
		T res = null;
		try {
			res =  MAPPER.readValue(jsonAsString, tipo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}

	/**
	 * @param fileName Nombre y ruta del fichero JSON a partir del que se quiere crear un objeto.
	 * @param tipo Referencia a la clase que se debe usar para crear el objeto.
	 * @return Objeto de tipo T creado a partir de la cadena jsonAsString.
	 */
	public static <T> T fromJSONFile(String fileName, Class<T> tipo) {
		T res = null;
		try {
			res =  MAPPER.readValue(new File(fileName), tipo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}


	/**
	 * @param url URL del recurso a partir del que se quiere crear un objeto.
	 * @param tipo Referencia a la clase que se debe usar para crear el objeto.
	 * @return Objeto de tipo T creado a partir de la cadena jsonAsString.
	 */
	public static <T> T fromJSON_URL(String url, Class<T> tipo) {
		T res = null;
		try {
			res =  MAPPER.readValue(new URL(url), tipo);
		} catch (IOException e) {
			System.err.println(e.getMessage());
//			e.printStackTrace();
		}
		return res;
	}


	private static String codificaURL(String urlString, String[] params, String[] values) {
		Preconditions.checkArgument(params.length == values.length,"Los arrays de parámetros y valores no tienen el mismo número de elementos.");
		String res = "";
		try {
			String urlStringParams = urlString + "?";
			for (int i = 0; i < params.length; i++) {
				urlStringParams += params[i] + "=" + URLEncoder.encode(values[i], "UTF-8");
				if (i < params.length - 1) {
					urlStringParams += "&";
				}
			}
			res = urlStringParams;
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	/**
	 * @param tipo Referencia a la clase que se debe usar para crear el objeto.
	 * @return Objeto de tipo T creado a partir de la cadena jsonAsString.
	 */
	public static <T> T fromJSON_URL(String urlBase,  String[] params, String[] values, Class<T> tipo) {
		String url = codificaURL(urlBase, params, values);
		return fromJSON_URL(url, tipo);
	}
	/**
	 * @param jsonAsString Cadena en formato JSON a partir de la que se quiere crear un objeto.
	 * @param tipo Objeto  de tipo TypeReference que representa una instancia de una clase genérica.
	 * @return Un objeto de tipo T creado a partir de la cadena jsonAsString.
	 * Esta llamada se debe usar cuando el objeto T que se quiere crear es instancia de una clase que tiene tipos genéricos.
	 * 
	 * Un ejemplo de llamada a este método es:
	 *  <code>Page &lt;TMDBMovie&gt; pagina = UtilesJSon.fromJSON(cadenaJSON, new TypeReference&lt;Page&lt;TMDBMovie&gt;&gt;())</code>;
	 * En este caso, el objeto a crear es de tipo Page &lt;TMDBMovie&gt;, que es una instancia del tipo genéricoPage &lt;T&gt;.  
	 */
	public static <T> T fromJSON(String jsonAsString, TypeReference<T> tipo) {
		T res = null;
		try {
			res =  MAPPER.readValue(jsonAsString, tipo);
		} catch (IOException e) {			
			e.printStackTrace();
		}
		return res;
	}
	
	/**
	 * @param fileName Nombre y ruta del fichero JSON a partir del que se quiere crear un objeto.
	 * @param tipo Objeto  de tipo TypeReference que representa una instancia de una clase genérica.
	 * @return Un objeto de tipo T creado a partir de la cadena jsonAsString.
	 * Esta llamada se debe usar cuando el objeto T que se quiere crear es instancia de una clase que tiene tipos genéricos.
	 * 
	 * Un ejemplo de llamada a este método es:
	 *  <code>Page &lt;TMDBMovie&gt; pagina = UtilesJSon.fromJSON(ficheroJSON, new TypeReference&lt;Page&lt;TMDBMovie&gt;&gt;())</code>;
	 * En este caso, el objeto a crear es de tipo Page &lt;TMDBMovie&gt;, que es una instancia del tipo genéricoPage &lt;T&gt;.  
	 */
	public static <T> T fromJSONFile(String fileName, TypeReference<T> tipo) {
		T res = null;
		try {
			res =  MAPPER.readValue(new File(fileName), tipo);
		} catch (IOException e) {
			e.printStackTrace();
		}
		return res;
	}
	
	/**
	 * @param url  URL del recurso a partir del que se quiere crear un objeto.
	 * @param tipo Objeto  de tipo TypeReference que representa una instancia de una clase genérica.
	 * @return Un objeto de tipo T creado a partir de la cadena jsonAsString.
	 * Esta llamada se debe usar cuando el objeto T que se quiere crear es instancia de una clase que tiene tipos genéricos.
	 * 
	 * Un ejemplo de llamada a este método es:
	 *  <code>Page &lt;TMDBMovie&gt; pagina = UtilesJSon.fromJSON(urlJSON, new TypeReference&lt;Page&lt;TMDBMovie&gt;&gt;())</code>;
	 * En este caso, el objeto a crear es de tipo Page &lt;TMDBMovie&gt;, que es una instancia del tipo genéricoPage &lt;T&gt;.  
	 */
	public static <T> T fromJSON_URL(String url, TypeReference<T> tipo) {
		T res = null;
		try {
			res =  MAPPER.readValue(new URL(url), tipo);
		} catch (IOException e) {
			System.err.println(e.getMessage());
//			e.printStackTrace();
		}
		return res;
	}


	public static <T> T fromJSON_URL(String urlBase,  String[] params, String[] values, TypeReference<T> tipo) {
		String url = codificaURL(urlBase, params, values);
		return fromJSON_URL(url, tipo);

	}
}

