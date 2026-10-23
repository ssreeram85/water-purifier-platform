package com.waterpurifier.notification.api;
import java.util.Map;
import org.springframework.web.bind.annotation.*;
@RestController @RequestMapping("/api/notification")
public class ServiceInfoController {
 @GetMapping("/info") public Map<String,String> info(){ return Map.of("service","notification-service","status","ready-for-business-modules"); }
}
