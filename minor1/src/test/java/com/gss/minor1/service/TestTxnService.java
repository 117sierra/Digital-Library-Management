package com.gss.minor1.service;

import com.gss.minor1.Repository.TxnRepository;

import com.gss.minor1.exception.Txnserviceexception;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.*;
import org.mockito.junit.MockitoJUnitRunner;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.util.ReflectionTestUtils;

import java.util.HashMap;
import java.util.Map;

import static org.mockito.ArgumentMatchers.any;   // manual import idk why

@SpringBootTest
@RunWith(MockitoJUnitRunner.class)
public class TestTxnService {
    @Mock
    private Studentservice studentservice;
    @Mock
    private BookService bookService;
    @Mock
    private TxnRepository txnRepository;
    @InjectMocks
    private TxnService txnService;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(new TxnService());
        ReflectionTestUtils.setField(txnService,"validays","14");
    }
    @Test(expected = Txnserviceexception.class)
    public void testfindstudentwithnullcheck() throws Txnserviceexception {
        Mockito.when(studentservice.findstudents(any(),any(),any())).thenReturn(null);
        txnService.findstudent("8686868686");
    }
   

}
