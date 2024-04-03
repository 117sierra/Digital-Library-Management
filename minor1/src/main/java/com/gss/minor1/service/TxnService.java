package com.gss.minor1.service;

import com.gss.minor1.Repository.TxnRepository;
import com.gss.minor1.Request.CreateTxnRequest;
import com.gss.minor1.Request.Createreturntxnrequest;
import com.gss.minor1.exception.Txnserviceexception;
import com.gss.minor1.models.*;
import com.gss.minor1.response.Txnresponsesettlement;
import javax.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

@Service
public class TxnService {
    @Autowired
    private Studentservice studentservice;
    @Autowired
    private BookService bookService;
    @Autowired
    private TxnRepository txnRepository;
    @Value("${student.valid.days}")
    private String validays;
    @Transactional(rollbackOn = {Txnserviceexception.class})
    public String create(CreateTxnRequest createTxnRequest) throws  Txnserviceexception{

        Student student= findstudent(createTxnRequest.getStudent_contact());
        Book book1= findbook(createTxnRequest.getBookno());
        Txn txn= Txn.builder().student(student).book(book1).txnid(UUID.randomUUID().toString()).paidcost(createTxnRequest.getPaidamount()).status(TxnStatus.ISSUED).build();
        book1.setStudent(student);
        bookService.update(book1);

       return txnRepository.save(txn).getTxnid();
    }
    public Student findstudent(String contact) throws Txnserviceexception {
        List<Student> students= studentservice.findstudents(StudentFilterType.CONTACT,contact, Operationtype.EQUALS);
        if(students==null || students.isEmpty()){
            throw new Txnserviceexception("Student does not exist!!");
        }
        return students.get(0);
    }
    public Book findbook(String bookno) throws Txnserviceexception {
        List<Book> books= bookService.findbooks(Bookfiltertype.BOOK_NO,bookno,Operationtype.EQUALS);
        if(books==null || books.isEmpty()|| books.get(0).getStudent()!=null){
            throw  new Txnserviceexception("Book does not exist!!");
        }
        return books.get(0);
    }
    public Book returnfindbook(String bookno) throws Txnserviceexception {
        List<Book> books= bookService.findbooks(Bookfiltertype.BOOK_NO,bookno,Operationtype.EQUALS);
        if(books==null || books.isEmpty()){
            throw  new Txnserviceexception("Book does not exist!!");
        }
        return books.get(0);
    }
@Transactional
    public Txnresponsesettlement returnbook(Createreturntxnrequest createreturntxnrequest) throws Txnserviceexception {
        Student student1= findstudent(createreturntxnrequest.getStudent_contact());
        Book book1= returnfindbook(createreturntxnrequest.getBookno());
        Txn txn= txnRepository.findByStudent_ContactAndBook_BookNoAndStatusOrderByCreatedonDesc(student1.getContact(),book1.getBookNo(),TxnStatus.ISSUED);
        txn.setStatus(settleamount(txn)==txn.getPaidcost() ? TxnStatus.RETURNED : TxnStatus.FINED);
        txn.setPaidcost(settleamount(txn));
        txnRepository.save(txn);

        book1.setStudent(null);
        bookService.update(book1);
        return Txnresponsesettlement.builder().txnid(txn.getTxnid()).settleamount(settleamount(txn)).build();
    }
    private int settleamount(Txn txn){
        long issuetime= txn.getCreatedon().getTime();
        long returntime= System.currentTimeMillis();
        long diff = returntime-issuetime;
        int daypassed= (int) TimeUnit.DAYS.convert(diff, TimeUnit.MILLISECONDS);
        if(daypassed> Integer.valueOf(validays)){
            return txn.getPaidcost()- ((daypassed-Integer.valueOf(validays))*2);
        }
        return txn.getPaidcost();
    }

}
