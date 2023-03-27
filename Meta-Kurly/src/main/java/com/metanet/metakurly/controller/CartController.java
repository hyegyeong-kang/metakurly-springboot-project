package com.metanet.metakurly.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.metanet.metakurly.dto.CartDTO;

import com.metanet.metakurly.service.CartService;

import lombok.AllArgsConstructor;

import com.metanet.metakurly.dto.CartDTO;
import com.metanet.metakurly.service.CartService;
import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/cart")
@AllArgsConstructor
public class CartController {

    @Autowired
    private CartService service;

    //@GetMapping("/cartList")
    //@RequestMapping("/cartList/{m_id}")
    //@RequestMapping(value="/cartList", method = {RequestMethod.GET, RequestMethod.POST})
    @GetMapping("/cartList")
    public List<CartDTO> getCartList(HttpSession session) throws Exception {
        //log.info("!!cartList!!");
       // MemberDTO member = (MemberDTO) session.getAttribute("member");
      //  Long m_id = member.getM_id();


       // List<CartDTO> cartList = service.getMyCartList(m_id);
     //   List<CartDTO> cartList = service.getMyCartList(1L);

       // model.addAttribute("list", cartList);
     //   return "cart/cart";
        System.out.println("$$$$$");
        System.out.println("#######" + service.getMyCartList(1L));
        return service.getMyCartList(1L);
    }


    @PostMapping("/cartAdd")
    //@RequestMapping(value="/cartAdd", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public boolean addCart(HttpSession session, Model model, @RequestBody Map<String, Integer> productInfo) throws Exception {
        //MemberDTO member = (MemberDTO) session.getAttribute("member");
        //Long m_id = member.getM_id();

        CartDTO cart = new CartDTO();

        Long p_id = Long.valueOf(productInfo.get("p_id"));
        int quantity = productInfo.get("quantity");

        cart.setM_id(1L);
        cart.setP_id(p_id);
        cart.setQuantity(quantity);

        if (service.checkCart(p_id, 1L) > 0) {
            service.updateCount(cart);
        } else {
            service.addCart(cart);
        }
        //return "redirect:/cart/cartList";
        return true;
    }

    @GetMapping("/cartDelete")
    public String deleteCart(@RequestParam Long p_id, HttpSession session, Model model) throws Exception {
        Long m_id = (Long) session.getAttribute("member");
        service.deleteCart(p_id, m_id);

        return "redirect:/cart/cartList" + m_id;
    }

    @GetMapping("/cartDeleteAll")
    public String deleteAllCart(HttpSession session, Model model) throws Exception{
        Long m_id = (Long) session.getAttribute("member");
        service.deleteAllCart(m_id);
        return "redirect: /cart/cartList" + m_id;
    }

    // ���� ���� �������ִ� ��
    @PostMapping("/cartUpdate")
    public String updateCart(HttpSession session, Model model, Long p_id, int quantity) {
        Long m_id = (Long) session.getAttribute("member");
        service.updateCart(p_id, m_id, quantity);
        return "redirect: /cart/cartUpdate";
    }



}