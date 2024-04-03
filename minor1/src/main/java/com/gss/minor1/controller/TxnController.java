package com.gss.minor1.controller;
import com.gss.minor1.Request.CreateTxnRequest;
import com.gss.minor1.Request.Createreturntxnrequest;
import com.gss.minor1.exception.Txnserviceexception;
import com.gss.minor1.response.Txnresponsesettlement;
import com.gss.minor1.service.TxnService;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("txn")
public class TxnController {
    @Autowired
    private TxnService txnService;


    @PostMapping("/create")
    public String create(@RequestBody @Valid CreateTxnRequest createTxnRequest) throws Txnserviceexception {

        return txnService.create(createTxnRequest);
    }
    @PostMapping("/return")
    public Txnresponsesettlement returbbook(@RequestBody Createreturntxnrequest createreturntxnrequest) throws Txnserviceexception {
        return txnService.returnbook(createreturntxnrequest);
    }
}
