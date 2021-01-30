package ir.sk.query.controllers;

import ir.sk.query.model.Query;
import ir.sk.query.services.QueryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

/**
 * Created by sad.kayvanfar
 */
@RestController
@RequestMapping(value = "v1/queries")
public class QueryServiceController {

    @Autowired
    private QueryService queryService;

    @GetMapping(value = "/retrieve")
    public Query retrieve(@RequestParam String queryString) {
        return queryService.retrieve(queryString);
    }
}
