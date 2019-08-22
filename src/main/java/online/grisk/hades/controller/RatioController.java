package online.grisk.hades.controller;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

@RestController
public class RatioController {

	@PostMapping(value = "/api/hades/ratios")
	public ResponseEntity<?> calculateRatios(@RequestBody JsonNode payload) throws IOException {

		JsonNode values = payload.get("dataintegration").get("values");
		JsonNode ratiosCollections = payload.get("riskRatios").get("configuration").get("riskRatiosRatiosCollection");
		
		ObjectMapper mapper = new ObjectMapper();
		
		Map<String, Object> jsonMap = mapper.convertValue(payload, Map.class);
		
		List<Map<String, Object>> nuevosDatos = new ArrayList<Map<String,Object>>();
		
		List<JsonNode> listaValues = new ArrayList<JsonNode>();
		if (values.isArray()) {
			for (int i = 0; i < values.size(); i++) {
				listaValues.add(values.get(i));
			}
		}
		
		if(ratiosCollections.isArray()) {
			
			for (final JsonNode objNode : ratiosCollections) {
				Set<String> setVariablesEnOperacion = new HashSet<String>();  
				Pattern p = Pattern.compile(Pattern.quote("{") + "(.*?)" + Pattern.quote("}"));
				Matcher m = p.matcher(objNode.get("expression").asText());
				
				while (m.find()) {
				  setVariablesEnOperacion.add(m.group(1));
				}
				
				Map<String, Double> mapVariablesValor = new HashMap<String, Double>();
				
				setVariablesEnOperacion.forEach((String var) -> {
					List<JsonNode> res = listaValues.stream().filter(a -> a.get("code").asText().equalsIgnoreCase(var)).collect(Collectors.toList());
					if(!res.isEmpty()) {
						mapVariablesValor.put(var.toUpperCase(), Double.parseDouble(res.get(0).get("value").asText()));
					} else {
						//return 500 variable no encontrada
					}
				});
	
		     	Expression exp = new ExpressionBuilder(objNode.get("expression").asText()).variables(setVariablesEnOperacion).build() .setVariables(mapVariablesValor);
		     	
		    	Map<String, Object> jsonResult = new HashMap<String, Object>();
		    	jsonResult.put("idRiskRatioRatio", objNode.get("idRiskRatioRatio").asLong());
		    	String operacion = objNode.get("expression").asText().replace("{", "").replace("}", "");
		    	for (Map.Entry<String, Double> entry : mapVariablesValor.entrySet()) {
		    		operacion = operacion.replace(entry.getKey(), entry.getValue().toString());
		    	}
		    	jsonResult.put("expression", operacion);
		    	try {
		    		jsonResult.put("result", exp.evaluate());
				} catch (Exception e) {
					jsonResult.put("result", 0);
				}
		    	
		    	jsonResult.put("titule", objNode.get("titule").asText());
		    	jsonResult.put("color", objNode.get("color").asText());
		    	jsonResult.put("postResult", objNode.get("postResult").asText());
		    	jsonResult.put("orderDisplay", objNode.get("orderDisplay").asInt());
		    	
		    	nuevosDatos.add(jsonResult); 
		    }
		}
//		jsonMap.put("ratioResponse", nuevosDatos);
		((Map)jsonMap.get("riskRatios")).put("values", nuevosDatos);
		return new ResponseEntity<Map<String, Object>>(jsonMap, HttpStatus.OK);
	}

}
