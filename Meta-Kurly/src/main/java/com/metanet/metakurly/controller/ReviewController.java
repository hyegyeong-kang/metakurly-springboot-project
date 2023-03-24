package com.metanet.metakurly.controller;

import com.metanet.metakurly.dto.ProductDTO;
import com.metanet.metakurly.dto.ReviewDTO;
import com.metanet.metakurly.service.ReviewService;
import lombok.AllArgsConstructor;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.util.List;

@RestController
@RequestMapping("/products")
@AllArgsConstructor
public class ReviewController {
    private ReviewService service;
//    private MemberService mService;


    @GetMapping("/{p_id}/reviews")
    public String showProductReview(@PathVariable("p_id") Long p_id, Model model) {


        List<ProductDTO> productReview = service.getProductReviewList(p_id);
        model.addAttribute("product", productReview);
        return "reviews/review";
    }

    @GetMapping("/{p_id}/reviews/{r_id}")
    public String showReviewDetail(@PathVariable("p_id") Long p_id, @PathVariable("r_id") Long r_id ,Model model, HttpSession session) {

        List<ProductDTO> productReview = service.getProductReviewList(p_id);
        model.addAttribute("product", productReview);

        return "reviews/reviewDetail";
    }

    @GetMapping("/reviews")
    public String showCreateReview(ReviewDTO review, Model model) {
        return "reviews/reviewForm";
    }

//    @PostMapping("/reviews")
//    public String createReview(HttpSession session, ReviewDTO review, Model model, HttpServletRequest request) {
//        MemberDTO member = (MemberDTO) session.getAttribute("member");
//        review.setM_id(member.getM_id());
//        review.setContents(request.getParameter("contents"));
//        review.setP_id(2L);
//
//        log.info("요기>!!!!!!");
//
//
//        service.registerReview(review);
//
//        log.info("register : " + review);
//
//
//        return "redirect:/";
//    }

//	@GetMapping("/reviews")
//	public String showCreateReview() {
//		return "reviews/reviewForm";
//	}
//
//	@PostMapping("/reviews")
//	public String createReview(ReviewDTO review, RedirectAttributes rttr) {
//		log.info("register : " + review);
//
//		service.registerReview(review);
//
//		rttr.addFlashAttribute("result", review.getR_id());
//		return "redirect:/";
//	}

    @GetMapping("/reviews/review/{r_id}")
    public String showModifyReview() {
        return "";
    }

    @PostMapping("/reviews/review/{r_id}")
    public String modifyReview(ReviewDTO review, RedirectAttributes rttr) {

        if(service.updateReview(review)) {
            rttr.addFlashAttribute("result", "success");
        }
        return "redirect:/";
    }

    @PostMapping("/reviews/{r_id}")
    public String deleteReview() {
        return "";
    }
}
