package com.Ai.Courier;

import com.Ai.Courier.model.*;
import jakarta.servlet.http.HttpSession;
import jakarta.transaction.Transactional;
import org.springframework.ai.tool.annotation.Tool;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.time.LocalDate;
import java.util.*;


@Component
public class ServiceTools {

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
    private RefundObjectRepository refundObjectRepository;


    @Tool(description = "Verify if the user chatting with you is the Sender of the respective order")//c1-ok
    public boolean isLoggedInUserSender( Integer orderId){

        HttpSession session=((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getSession();
       String loggedInEmailString= session.getAttribute("loggedInEmail").toString();
        OrderObject orderObject=orderObjectRepository.findById(orderId).get();
       CustomerObject customerObject=customerObjectRepository.findByCustomerEmail(loggedInEmailString);

        return orderObject.getSendCustomerId() == customerObject.getCustomerId();
    }

    @Tool(description = "Get the current Status of an order")//c1-ok
    public String getStatus(Integer orderId){

      OrderObject order= orderObjectRepository.findById(orderId).get();
      return order.getStatus();

    }

    @Tool(description = "Get The current Date")//c1-ok
    public LocalDate getDate(){

      return LocalDate.now();
    }

    @Tool(description ="Get the date that an Order is Due" )//c1-ok
    public LocalDate orderDueDate(Integer orderId){

        OrderObject order= orderObjectRepository.findById(orderId).get();
        return order.getDueDate();

    }

    @Tool(description = "Raise a new issue to a customer Service Agent and get a reference number for that issue")//c1-ok
    public Integer raiseNewIssue( Integer  userId,  Integer orderId, String issueDescription){

      issueReferenceObject newIssue= new issueReferenceObject(userId,orderId,issueDescription);

        issueRepo.save(newIssue);

      return  newIssue.getIssueId();


    }

    @Tool(description ="Find out how much a customer must pay for an Order")//c1-ok
    public Integer getChargeDue(Integer orderId){

            return  chargeObjectRepository.getChargeDue(orderId);
    }

  @Tool(description ="Find out how much a customer has actually paid for an Order")//c1-ok
  public String getPaidAmount(Integer orderId){


      Optional<PaymentObject> paymentObjectOptional=paymentObjectRepository.findById(orderId);

      if(paymentObjectOptional.isPresent()){

          return paymentObjectOptional.get().getChargedAmount().toString();

      }
      else
           return "Payment not yet made for this order,the payment is still due";


      }




          @Tool(description="Identify the receiver of an order")//c1-ok
          public Integer  getReceiverId(Integer orderId){

            OrderObject orderObject=orderObjectRepository.findById(orderId).get();

            return orderObject.getReceiveCustomerId();


          }

   @Tool(description = "Display a list of receivers addresses when user want to change receiver")
    public List<CustomerAddressObject> showReceiversOtherAddresses(Integer ReceiverId){

          return  customerAddressObjectRepository.findReceiverAddress(ReceiverId);

   }

   @Tool(description="Change the receivers address upon users request ")
   public void updateReceiverAddress(Integer newReceiverAddressId,Integer orderId){

    orderObjectRepository.updateAddress(newReceiverAddressId,orderId);


   }

     @Tool(description ="upon changing the receiver/ receiver Address payment details must be changed" )//c1-ok
   public void updatePaymentDetails(Integer orderId,Integer additionalFees,String reasonForAddtionalFees){

        ChargeObject change=chargeObjectRepository.findByOrderId(orderId);

        Long newDistance=change.getOrderDistance()+5;
        Long newWeight= change.getOrderWeight()+5;
        ChargeObject chargeObject=new ChargeObject(orderId,newWeight,newDistance,additionalFees);
         chargeObjectRepository.save(chargeObject);
        AdditonalChargeObject additonalChargeObject=new AdditonalChargeObject(orderId,additionalFees,reasonForAddtionalFees);
         additionalChargeObjectRepository.save(additonalChargeObject);

   }
           @Tool(description="Change the receiver  upon users request ")//c1-ok
      public void updateNewReceiver(Integer OrderId,Integer NewReceiverId,Integer NewReceiversAddress){

        orderObjectRepository.updateReceiver(NewReceiverId,NewReceiversAddress,OrderId);

  }
  @Transactional
     @Tool(description = "cancel order upon users request")//c1-ok
    public void cancelOrder(Integer orderId){

        orderObjectRepository.setStatusAsCancel(orderId);
     }

     @Tool(description = "check the payment status")//c1-ok
         public String getPaymentStatus(Integer orderId){


         Optional<PaymentObject> paymentObjectOptional=paymentObjectRepository.findById(orderId);

         if(paymentObjectOptional.isPresent()){

             return paymentObjectOptional.get().getPaymentStatus();
         }

         else

             return "Payment not yet made for this order,the payment is still due";


     }


     @Tool(description = "get all the orders that the logged in user is sending")//c1-ok
    public List<OrderObject> getLoggedInUsersSendOrders(Integer userId){

            return orderObjectRepository.findOrderObjectBySendCustomerId(userId);

     }

    @Tool(description = "get all the orders that the logged in user is receiving")//c1-ok
    public List<OrderObject> getLoggedInUsersReceiverOrders(Integer userId){

        return orderObjectRepository.findOrderObjectByReceiveCustomerId(userId);
    }

    @Tool(description = "calculate the newCharge the customer must pay if the receiver address/receiver is changed after the sorting stage")
    public Long calculateNewCharge( Integer orderId,Integer additionalFeeBasedOnStage){

          Optional<ChargeObject> chargeObject=chargeObjectRepository.findById(orderId);

          long distance=chargeObject.get().getOrderDistance()+5;

            long weight=chargeObject.get().getOrderWeight()+5;

          return (distance*30)+(weight*20)+additionalFeeBasedOnStage;

    }

    @Transactional
    @Tool(description="Correct the refund amount,update the database with the correct amount")
    public void correctRefund(Integer orderId,Long  expectedAmount){


        paymentObjectRepository.updateChargedAmount(expectedAmount,orderId);

    }

//    @Tool(description = "Process the new Charge  when the user had underPaid ")//c1-ok
//    public void underpayRecovery(Long chargedAmount){
//
//        String paymentStatus="underPayRecovery";
//
//        PaymentObject paymentObject= new  PaymentObject(paymentStatus,chargedAmount);
//
//        paymentObjectRepository.save(paymentObject);
//
//    }

    @Tool(description="identify the logged in userId")
    public Integer identifyLoggedInUser(){


    HttpSession session=((ServletRequestAttributes)RequestContextHolder.currentRequestAttributes()).getRequest().getSession();

        return (Integer) session.getAttribute("loggedInUser");


    }

    @Tool(description = "Display a list of available addresses for the receiver of the given order")
    public List<CustomerAddressObject> showReceiversAddresses(Integer ReceiverId,Integer OrderId){

        Optional<OrderObject> orderObjectOptional=orderObjectRepository.findById(OrderId);



        Integer    ExistingReceiverAddressId= orderObjectOptional.get().getReceiverAddressId();

        return  customerAddressObjectRepository.showReceiversAddresses(ReceiverId,ExistingReceiverAddressId);


    }

    @Tool(description ="Find out how much a customer wil be refunded")//c1-ok
    public Integer getRefundAmount(Integer orderId){

        return (chargeObjectRepository.getChargeDue(orderId)/2);
    }



    @Tool(description ="process a refund when customer cancels an order at the 'picked up' stage")
    public String processRefund(Integer orderId,String reasonForCancellation){

        Integer chargeDue=chargeObjectRepository.getChargeDue(orderId);
        Long refundAmount= (long) (chargeDue/2);
        orderObjectRepository.setStatusAsCancel(orderId);
        RefundObject refundObject=new RefundObject(orderId,refundAmount,reasonForCancellation);
        refundObjectRepository.save(refundObject);

       return orderId+" is successfully cancelled.";

    }

    @Tool(description ="update the customer about the refund before processing when customer cancels an order at the 'picked up' stage")
    public String returnRefundInformation(Integer orderId){

        Integer chargeDue=chargeObjectRepository.getChargeDue(orderId);
        if(chargeDue==null){

            return "the payment  for this order has not yet been processed. However only 50% of the deu amount will be charged,when the order is processed before returning the parcel.";


        }

        long refundAmount= (long) (chargeDue/2);

        return "The original charge is "+ chargeDue +" only "+ refundAmount + " will be refunded.";

    }

    @Tool(description = "Process the refund  when the user had overpaid/cancels the order at the 'sorting' stage ")//c1-ok
    public void  newRefund(Integer orderId, Long refundAmount, String refundReason){

        RefundObject refundObject=new RefundObject(orderId,refundAmount,refundReason);

        refundObjectRepository.save(refundObject);

    }

    /// no need
    @Tool(description = "calculate the refund amount")//c1-ok
    public Integer setRefundAmount(Integer dueAmount){

        return dueAmount/2;

    }

}

