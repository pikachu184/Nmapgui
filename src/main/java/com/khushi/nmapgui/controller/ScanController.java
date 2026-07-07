package com.khushi.nmapgui.controller;

import com.khushi.nmapgui.service.NmapService;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class ScanController {

    private final NmapService nmapService;

    public ScanController(NmapService nmapService) {
        this.nmapService = nmapService;
    }

    @GetMapping("/scan")
    public String scan(
            @RequestParam String target,
            @RequestParam String scanType
    ) {
        return nmapService.scan(target, scanType);
    }
}