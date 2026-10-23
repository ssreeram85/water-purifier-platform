package com.waterpurifier.customer.api;
import java.util.Map;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/customer")
public class ServiceInfoController {
 @GetMapping("/info") public Map<String,String> info(){ return Map.of("service","customer-service","status","ready-for-business-modules"); }
}
