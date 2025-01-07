package org.llamagas.servicelayer.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class CorsController {

    @RequestMapping(value = "/**", method = RequestMethod.OPTIONS)
    public void handleOptions() {
        // Este método asegura que las solicitudes OPTIONS sean procesadas correctamente.
    }
}
