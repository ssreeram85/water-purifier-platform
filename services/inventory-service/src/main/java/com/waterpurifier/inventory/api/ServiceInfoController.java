package com.waterpurifier.inventory.api;
import java.util.Map;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/inventory")
public class ServiceInfoController {
 @GetMapping("/info") public Map<String,String> info(){ return Map.of("service","inventory-service","status","ready-for-business-modules"); }
}
