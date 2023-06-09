package edu.miu.shopmartbackend.controller;

import com.stripe.exception.StripeException;
import edu.miu.shopmartbackend.aspect.annotation.EmailSender;
import edu.miu.shopmartbackend.model.dto.PaymentDto;
import edu.miu.shopmartbackend.model.dto.OrderDto;
import edu.miu.shopmartbackend.service.OrderService;
import edu.miu.shopmartbackend.service.SearchService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
@RestController
@RequestMapping("/api/v1/orders")
//@CrossOrigin
public class OrderController {

    @Autowired
    OrderService orderService;

    @Autowired
    SearchService searchService;

    @ResponseStatus(HttpStatus.OK)
    @EmailSender
    @PostMapping("/create")
    OrderDto createOrder(@RequestBody PaymentDto paymentDto) throws StripeException {
        System.out.println("paymentDto------------------------ = " + paymentDto);
        return orderService.placeOrder(paymentDto);
    }

    @ResponseStatus(HttpStatus.OK)
    @EmailSender
    @PostMapping("/{orderId}/ship")
    OrderDto shipOrder(@PathVariable("orderId") long orderId){
        return orderService.shipOrder(orderId);
    }


    @ResponseStatus(HttpStatus.OK)
    @EmailSender
    @PatchMapping("/edit/{orderId}/{discount}")
    public OrderDto editOrder(@PathVariable Long orderId, @PathVariable double discount) {
        return orderService.editOrder(orderId, discount);
    }


    @ResponseStatus(HttpStatus.OK)
    @DeleteMapping("/{orderId}")
    OrderDto cancelOrder(@PathVariable long orderId){
        return orderService.cancelOrder(orderId);
    }



}
