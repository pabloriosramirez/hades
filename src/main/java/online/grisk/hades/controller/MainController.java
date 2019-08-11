package online.grisk.hades.controller;

import online.grisk.hades.integration.gateway.GatewayService;
import online.grisk.hades.util.MathOperator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.support.MessageBuilder;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@RestController
public class MainController {
    @Autowired
    GatewayService gatewayService;

    @PostMapping(value = "/v1/rest/api/consumer")
    public void report(/*@RequestBody Map payload*/) {
//    	
//    	
//    	Expression e = new ExpressionBuilder("3 * sin(var1) - 2 / (var2 - 2)")
//    	        .variables("var1", "var2")
//    	        .build()
//    	        .setVariable("var1", 2.3)
//    	        .setVariable("var2", 3.14);
//    	double result = e.evaluate();
//    	
//    	System.out.println("resultado: " + result);
    	
//    	Expression e2 = new ExpressionBuilder("(4 * 2) / 5.4 + (3 - 2)").build();
//    	
//    	System.out.println("res2 :" + e2.evaluate());
    	
    	
//        HashMap<String, Object> response = new HashMap<>();
//        response.put("uuid", UUID.randomUUID());
//        response.put("timestamp", new Date());
//        response.put("request", payload);
//        return gatewayService.process(MessageBuilder.withPayload(response).build());
    	
    	System.out.println("RES 1>=2: " + MathOperator.gratherEqualThan("1>=2"));
    	System.out.println("RES 2>=2: " + MathOperator.gratherEqualThan("2>=2"));
    	System.out.println("RES 3>=2: " + MathOperator.gratherEqualThan("3>=2"));

    	System.out.println("RES 1>2: " + MathOperator.gratherThan("1>2"));
    	System.out.println("RES 2>2: " + MathOperator.gratherThan("2>2"));
    	System.out.println("RES 3>2: " + MathOperator.gratherThan("3>2"));
    	
    	System.out.println("RES 1<2: " + MathOperator.lessThan("1<2"));
    	System.out.println("RES 2<2: " + MathOperator.lessThan("2<2"));
    	System.out.println("RES 3<2: " + MathOperator.lessThan("3<2"));
    	
    	System.out.println("RES 1<=2: " + MathOperator.lessEqualThan("1<=2"));
    	System.out.println("RES 2<=2: " + MathOperator.lessEqualThan("2<=2"));
    	System.out.println("RES 3<=2: " + MathOperator.lessEqualThan("3<=2"));
    	
     	System.out.println("RES 1==2: " + MathOperator.equalThan("1==2"));
    	System.out.println("RES 2==2: " + MathOperator.equalThan("2==2"));
    	System.out.println("RES 3==2: " + MathOperator.equalThan("3==2"));
    	
     	System.out.println("RES 1!=2: " + MathOperator.notEqualThan("1!=2"));
    	System.out.println("RES 2!=2: " + MathOperator.notEqualThan("2!=2"));
    	System.out.println("RES 3!=2: " + MathOperator.notEqualThan("3!=2"));
    }
}
