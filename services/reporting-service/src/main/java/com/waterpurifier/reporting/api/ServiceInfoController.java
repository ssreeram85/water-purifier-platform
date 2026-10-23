package com.waterpurifier.reporting.api;
import java.util.Map;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/reporting")
public class ServiceInfoController {
 @GetMapping("/info") public Map<String,String> info(){ return Map.of("service","reporting-service","status","ready-for-business-modules"); }
}
