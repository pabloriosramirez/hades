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

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;

import net.objecthunter.exp4j.Expression;
import net.objecthunter.exp4j.ExpressionBuilder;

@RestController
public class RatioController {

	@PostMapping(value = "/v1/rest/ratios")
	public void calculateRatios(@RequestBody JsonNode payload) throws IOException {

		JsonNode values = payload.get("dataintegration").get("values");
		JsonNode ratiosCollections = payload.get("riskRatios").get("configuration").get("ratiosCollection");
		
		List<JsonNode> listaValues = new ArrayList<JsonNode>();
		if (values.isArray()) {
			for (int i = 0; i < values.size(); i++) {
				listaValues.add(values.get(i));
			}
		}
		
		for (int i = 0; i < listaValues.size(); i++) {
			System.out.println(listaValues.get(i));
		}		
		
		if(ratiosCollections.isArray()) {
			
			for (final JsonNode objNode : ratiosCollections) {
				System.out.println(objNode);
				Set<String> setVariablesEnOperacion = new HashSet<String>();  
				Pattern p = Pattern.compile(Pattern.quote("{") + "(.*?)" + Pattern.quote("}"));
				Matcher m = p.matcher(objNode.get("operation").asText());
				
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
	
				System.out.println(objNode.get("operation").asText());
		     	Expression e = new ExpressionBuilder(objNode.get("operation").asText())
		    	        .variables(setVariablesEnOperacion)
		    	        .build()
		    	        .setVariables(mapVariablesValor);
		    	double result = e.evaluate();
	    	
		    	System.out.println("RESULTADO: " + result);
		        
		    }
		}
	}

}
