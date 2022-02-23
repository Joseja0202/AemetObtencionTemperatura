package es.fpDual2022.aemetTemperatura.utilidades;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import es.fpDual2022.aemetTemperatura.modelo.Datos;

@Service
public class UtilidadesMunicipio {

	public Datos getDatos(Long codMun) {

		Datos datos = new Datos();

		HttpClient client = HttpClients.createDefault();
		HttpGet peticion = new HttpGet(
				"https://opendata.aemet.es/opendata/api/prediccion/especifica/municipio/diaria/" + codMun);
		peticion.addHeader("api_key",
				"eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJqZ29tZXpqaW1lbmV6QGVjaS5zYWZhbmV0LmVzIiwianRpIjoiZTY4NDkxNTctN2ZhZi00MmZlLWIwMjEtMzRmN2Q5ZTdhYzI1IiwiaXNzIjoiQUVNRVQiLCJpYXQiOjE2NDU0MzAwNzEsInVzZXJJZCI6ImU2ODQ5MTU3LTdmYWYtNDJmZS1iMDIxLTM0ZjdkOWU3YWMyNSIsInJvbGUiOiIifQ.Jlt_3FhjyiW8-MUGv6CbBQrwjPOgIYWVziz7tv66aos");

		HttpResponse httpResponse;
		try {
			httpResponse = client.execute(peticion);
			String JSONString = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");
			JSONObject jsonObject = new JSONObject(JSONString);

			String urlDatos = jsonObject.getString("datos");

			HttpGet peticionDatos = new HttpGet(urlDatos);

			httpResponse = client.execute(peticionDatos);

			String JSONStringDatos = EntityUtils.toString(httpResponse.getEntity(), "UTF-8");

			JSONArray jsonArrayDatos = new JSONArray(JSONStringDatos);

			String ArrayDatos = jsonArrayDatos.getJSONObject(0).toString();

			JSONObject jsonObjectDatos = new JSONObject(ArrayDatos);

			// Prediccion

			JSONObject prediccion = jsonObjectDatos.getJSONObject("prediccion");

			// Dia

			JSONArray dia = prediccion.getJSONArray("dia");

			// Paso intermedio de dia a temperatura

			String dia_temperatura = dia.getJSONObject(0).toString();

			// Temperatura

			JSONObject jsonObjectDatosDia = new JSONObject(dia_temperatura);

			JSONObject temperatura = jsonObjectDatosDia.getJSONObject("temperatura");

			// Minima
			Integer temperaturaMaxima = temperatura.getInt("maxima");

			// Maxima

			Integer temperaturaMinima = temperatura.getInt("minima");

			datos.setTemperaturaMaxima(temperaturaMaxima);
			datos.setTemperaturaMinima(temperaturaMinima);

			return datos;

		} catch (ClientProtocolException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;

	}

}
