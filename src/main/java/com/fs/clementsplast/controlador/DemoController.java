package com.fs.clementsplast.controlador;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController //Permite recibir y realizar peticiones del tipo Rest
// http://localhost:8080/clements-plast/  utilizar RequestMapping a nivel de clase hace que en todas las URL de la aplicacion tengamos que agregar primero este path
@RequestMapping("clements-plast")  //Nombre general de la aplicacion (ContextPath)
public class DemoController {

    @GetMapping(value = "/demo")
    public String login() {
        return "Welcome from secure endpoint";
    }
}
