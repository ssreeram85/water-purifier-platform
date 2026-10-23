package com.waterpurifier.support.api;
import java.util.Map;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/support")
public class ServiceInfoController {
 @GetMapping("/info") public Map<String,String> info(){ return Map.of("service","support-service","status","ready-for-business-modules"); }
}
