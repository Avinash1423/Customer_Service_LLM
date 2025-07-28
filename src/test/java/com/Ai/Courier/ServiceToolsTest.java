package com.Ai.Courier;

import com.Ai.Courier.model.*;
import jakarta.persistence.EntityManager;
import jakarta.transaction.Transactional;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.mock.web.MockHttpSession;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureTestDatabase(replace=AutoConfigureTestDatabase.Replace.ANY )
class ServiceToolsTest {

    @Autowired
    OrderObjectRepository orderObjectRepository;
    @Autowired
    ChargeObjectRepository chargeObjectRepository;
    @Autowired
    PaymentObjectRepository paymentObjectRepository;
    @Autowired
    CustomerAddressObjectRepository customerAddressObjectRepository;
    @Autowired
    AdditionalChargeObjectRepository additionalChargeObjectRepository;
    @Autowired
    CustomerObjectRepository customerObjectRepository;
    @Autowired
    issueReferenceRepository issueRepo;
    @Autowired
     RefundObjectRepository refundObjectRepository;
    @Autowired
    ServiceTools serviceTools;
    @Autowired
    EntityManager entityManager;


    @BeforeEach
    void SetUpDTOs(){

        CustomerObject uselessCustomerObject=new CustomerObject();
        uselessCustomerObject.setCustomerId(2);
        uselessCustomerObject.setCustomerFirstName("Abdullah");
        uselessCustomerObject.setCustomerLastName("Farees");
        uselessCustomerObject.setCustomerPhoneNumber("789");
        uselessCustomerObject.setCustomerEmail("fake@gmail.com");
        customerObjectRepository.save(uselessCustomerObject);

        CustomerObject receiverCustomer = new CustomerObject();
        receiverCustomer.setCustomerId(3);
        receiverCustomer.setCustomerFirstName("Mohamed");
        receiverCustomer.setCustomerLastName("Ibrahim");
        receiverCustomer.setCustomerPhoneNumber("456");
        receiverCustomer.setCustomerEmail("receiver@gmail.com");
        customerObjectRepository.save(receiverCustomer);


        CustomerAddressObject senderAddress = new CustomerAddressObject();
        senderAddress.setAddressId(12);
        senderAddress.setCustomerId(2);
        senderAddress.setLine1("No. 43/100");
        senderAddress.setLine2("Happy Road");
        senderAddress.setProvince("Colombo");
        senderAddress.setDistrict("Colombo");
        senderAddress.setCity("Negombo");
        senderAddress.setPostalCode(11400);
        customerAddressObjectRepository.save(senderAddress);

        CustomerAddressObject receiverAddress = new CustomerAddressObject();
        receiverAddress.setAddressId(13);
        receiverAddress.setCustomerId(3); // can be same or different customerId
        receiverAddress.setLine1("No. 99/200");
        receiverAddress.setLine2("Palm Avenue");
        receiverAddress.setProvince("Colombo");
        receiverAddress.setDistrict("Colombo");
        receiverAddress.setCity("Moratuwa");
        receiverAddress.setPostalCode(10400);
        customerAddressObjectRepository.save(receiverAddress);

        OrderObject uselessOrderObject=new OrderObject();
        uselessOrderObject.setOrderId(1);
        uselessOrderObject.setSendCustomerId(2);
        uselessOrderObject.setSenderAddressId(12);
        uselessOrderObject.setReceiveCustomerId(3);
        uselessOrderObject.setReceiverAddressId(13);
        uselessOrderObject.setStatus("sorting");
        orderObjectRepository.save(uselessOrderObject);

        // Create and save ChargeObject for orderId 1
        ChargeObject chargeObject = new ChargeObject();
        chargeObject.setOrderId(1);
        chargeObject.setOrderWeight(5L); // 5kg
        chargeObject.setOrderDistance(15L); // 15km
       chargeObject.setAdditionalFees(0); // LKR 200
        chargeObjectRepository.save(chargeObject);
//
//// Create and save AdditonalChargeObject for orderId 1
//        AdditonalChargeObject additonalChargeObject = new AdditonalChargeObject();
//        additonalChargeObject.setOrderId(1);
//        additonalChargeObject.setAdditionalFees(200); // LKR 150
//        additonalChargeObject.setReason("Fragile item handling");
//        additionalChargeObjectRepository.save(additonalChargeObject);

    }

    @Test
    void testisLoggedInUserSender(){

        Integer orderId=1;

        MockHttpSession mockHttpSession=new MockHttpSession();
        mockHttpSession.setAttribute("loggedInEmail","fake@gmail.com");
        MockHttpServletRequest request=new MockHttpServletRequest();
        request.setSession(mockHttpSession);
        RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

         boolean result= serviceTools.isLoggedInUserSender(orderId);
        assertTrue(result);

    }

    @Test
    void testgetStatus(){

        Integer orderId=1;

       String status= serviceTools.getStatus(orderId);
       assertEquals("sorting",status);

    }


    @Test
    void testraiseNewIssue(){

        Integer  userId=2;
        Integer orderId=1;
        String issueDescription="its a useLess issue";

       Integer issueId= serviceTools.raiseNewIssue(userId,orderId,issueDescription);

         issueReferenceObject issue=  issueRepo.findById(issueId).get();

        assertEquals(issue.getIssueId(),issueId);
    }

       @Test
    void testupdatePaymentDetails(){

           Integer orderId=1;
           Integer additionalFees=100;
           String reasonForAddtionalFees="some Useless Reason";

           serviceTools.updatePaymentDetails(orderId,additionalFees,reasonForAddtionalFees);

           ChargeObject chargeObject=chargeObjectRepository.findByOrderId(1);
           AdditonalChargeObject additonalChargeObject=additionalChargeObjectRepository.findByOrderId(1);

           assertEquals(100,chargeObject.getAdditionalFees());
           assertEquals(100,additonalChargeObject.getAdditionalFees());
           assertEquals(10,chargeObject.getOrderWeight());
           assertEquals(20,chargeObject.getOrderDistance());

    }

  @Transactional
    @Test
    void testprocessRefund(){

        Integer orderId=1;
        String reasonForCancellation="customer cancels an order at the 'picked up' stage";



        ChargeObject chargeObject=chargeObjectRepository.findByOrderId(orderId);
        System.out.println("===============");
        System.out.println(chargeObject.getOrderDistance());
        System.out.println(chargeObject.getOrderWeight());
        System.out.println(chargeObject.getOrderId());
        System.out.println("===============");

        long chargeBeforeCancel=chargeObjectRepository.getChargeDue(orderId);
        long calRefundAmount=chargeBeforeCancel/2;

        serviceTools.processRefund(orderId,reasonForCancellation);

        OrderObject updatedOrderObject=orderObjectRepository.findById(orderId).get();

        RefundObject refundObject=refundObjectRepository.findByOrderId(orderId);


     entityManager.refresh(updatedOrderObject);
        assertEquals("cancelled",updatedOrderObject.getStatus());// failing

        assertEquals(calRefundAmount, refundObject.getRefundAmount());


    }


}