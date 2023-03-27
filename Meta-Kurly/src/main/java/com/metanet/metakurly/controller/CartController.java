package com.metanet.metakurly.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.metanet.metakurly.dto.CartDTO;

import com.metanet.metakurly.service.CartService;

import lombok.AllArgsConstructor;

import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("/members")
@AllArgsConstructor
public class CartController {

    @Autowired
    private CartService service;

    @GetMapping("{m_id}/cart")
    public List<CartDTO> getCartList(@PathVariable Long m_id, HttpSession session) throws Exception {
       // MemberDTO member = (MemberDTO) session.getAttribute("member");
       //  Long m_id = member.getM_id();

       // List<CartDTO> cartList = service.getMyCartList(m_id);
        return service.getMyCartList(41L);
    }


    @PostMapping("{m_id}/cart")
    //@RequestMapping(value="/cartAdd", method = {RequestMethod.GET, RequestMethod.POST})
    @ResponseBody
    public boolean addCart(HttpSession session, @PathVariable Long m_id, @RequestBody Map<String, Integer> productInfo) throws Exception {
        //MemberDTO member = (MemberDTO) session.getAttribute("member");
        //Long m_id = member.getM_id();

        CartDTO cart = new CartDTO();

        Long p_id = Long.valueOf(productInfo.get("p_id"));
        int quantity = productInfo.get("quantity");

        cart.setM_id(41L);
        cart.setP_id(p_id);
        cart.setQuantity(quantity);

        if (service.checkCart(p_id, 41L) > 0) {
            service.updateCount(cart);
        } else {
            service.addCart(cart);
        }
        //return "redirect:/cart/cartList";
        return true;
    }

    @DeleteMapping("{m_id}/cart/{p_id}")
    public String deleteCart(@PathVariable Long p_id, @PathVariable Long m_id, HttpSession session) throws Exception {
       // Long m_id = (Long) session.getAttribute("member");
        service.deleteCart(p_id, 1L);

        return "redirect:/cart/cartList" + 1L;
    }

    @GetMapping("/cartDeleteAll")
    public String deleteAllCart(HttpSession session, Model model) throws Exception{
        Long m_id = (Long) session.getAttribute("member");
        service.deleteAllCart(m_id);
       // return "redirect: /cart/cartList" + m_id;
        return "true";
    }

    @PatchMapping("{m_id}/cart")
    public String updateCart(HttpSession session, @PathVariable Long m_id ,@RequestBody Map<String, Integer> productInfo) {
      //  Long m_id = (Long) session.getAttribute("member");
        Long p_id = Long.valueOf(productInfo.get("p_id"));
        int quantity = productInfo.get("quantity");
        service.updateCart(p_id, 1L, quantity);
        return "redirect: /cart/cartUpdate";
    }
}