package org.lib.minor1.controller;

import org.lib.minor1.exception.TxnServiceException;
import org.lib.minor1.models.Student;
import org.lib.minor1.request.CreateReturnTxnRequest;
import org.lib.minor1.request.CreateTxnRequest;
import org.lib.minor1.response.GenericResponse;
import org.lib.minor1.response.TxnSettlementResponse;
import org.lib.minor1.service.TxnService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/txn")
public class TxnController {

    @Autowired
    private TxnService txnService;


    @PostMapping("/create")
    public ResponseEntity<GenericResponse<String>> create(@RequestBody @Valid CreateTxnRequest createTxnRequest) throws TxnServiceException {

        String txnid= txnService.create(createTxnRequest);
        GenericResponse genericResponse = GenericResponse.builder().
                error(null).
                data(txnid).
                status(HttpStatus.OK).build();
        return new ResponseEntity<>(genericResponse, HttpStatus.OK);
    }

    @PostMapping("/return")
    public TxnSettlementResponse returnBook(@RequestBody CreateReturnTxnRequest createReturnTxnRequest) throws TxnServiceException {
        return txnService.returnBook(createReturnTxnRequest);
    }

}
