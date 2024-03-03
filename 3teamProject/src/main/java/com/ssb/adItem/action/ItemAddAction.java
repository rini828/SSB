package com.ssb.adItem.action;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.oreilly.servlet.MultipartRequest;
import com.ssb.adItem.db.ItemDAO;
import com.ssb.adItem.db.ItemDTO;
import com.ssb.board.action.ReviewFileNamePolicy;
import com.ssb.category.db.CategoryDAO;
import com.ssb.rental.db.RentalDAO;
import com.ssb.rental.db.RentalDTO;
import com.ssb.util.Action;
import com.ssb.util.ActionForward;

public class ItemAddAction implements Action {
	  @Override
	    public ActionForward execute(HttpServletRequest request, HttpServletResponse response) throws Exception {
		  	
		  	// 파일 저장 경로
		  	String realPath = request.getRealPath("/upload");
		  	// 첨부파일의 크기 설정 (5MB)
			int maxSize = 5*1024*1024;
					
			// 첨부파일 업로드 => 객체 생성
			MultipartRequest multi = new MultipartRequest(
														request,
														realPath,
														maxSize,
														"UTF-8"												
														);
			
	        // 기존 카테고리 정보
	        int categoryCode = Integer.parseInt(multi.getParameter("category_code"));
	        String categorySport = multi.getParameter("category_sport");
	        String categoryMajor = multi.getParameter("category_major");
	        String categorySub = multi.getParameter("category_sub");
	        String categoryBrand = multi.getParameter("category_brand");

	        // 기존의 카테고리 ID 찾기 -> 새로 등록하는 상품에 부여함
	        int categoryId = new CategoryDAO().findOrCreateCategory(categorySport, categoryMajor, categorySub, categoryBrand, categoryCode);

	        ItemDTO dto = new ItemDTO();
	        RentalDTO rdto = new RentalDTO();

	        // 판매 / 렌탈 구분
	        if (categoryCode == 1) { // 판매인 경우
	            setCommonFields(dto, multi, categoryId);
	            setItemFields(dto, multi);

	            new ItemDAO().addItem(dto); // ItemDAO 객체 생성 - 상품 등록 메서드 호출
	        } else if (categoryCode == 2) { // 렌탈인 경우
	            setCommonFields(rdto, multi, categoryId);
	            setRentalFields(rdto, multi);

	            new RentalDAO().addRental(rdto); // RentalDAO 객체 생성 - 렌탈 등록 메서드 호출
	
	            
	        }

	        // 페이지 이동 준비
	        ActionForward forward = new ActionForward();
	        forward.setPath("./ItemMgt.it");
	        forward.setRedirect(true);

	        return forward;
	    }
    
    

    private void setCommonFields(Object dto, MultipartRequest multi, int categoryId) {
        if (dto instanceof ItemDTO) {
            ItemDTO itemDTO = (ItemDTO) dto;
            itemDTO.setCategory_id(categoryId);
            itemDTO.setCategory_code(Integer.parseInt(multi.getParameter("category_code")));
            itemDTO.setCategory_sport(multi.getParameter("category_sport"));
            itemDTO.setCategory_major(multi.getParameter("category_major"));
            itemDTO.setCategory_sub(multi.getParameter("category_sub"));
            itemDTO.setCategory_brand(multi.getParameter("category_brand"));
        } else if (dto instanceof RentalDTO) {
            RentalDTO rentalDTO = (RentalDTO) dto;
            rentalDTO.setCategory_id(categoryId);
            rentalDTO.setCategory_code(Integer.parseInt(multi.getParameter("category_code")));
            rentalDTO.setCategory_sport(multi.getParameter("category_sport"));
            rentalDTO.setCategory_major(multi.getParameter("category_major"));
            rentalDTO.setCategory_sub(multi.getParameter("category_sub"));
            rentalDTO.setCategory_brand(multi.getParameter("category_brand"));
        }
    }


    private void setItemFields(ItemDTO dto, MultipartRequest multi) {
        dto.setItem_name(multi.getParameter("item_name"));
        dto.setItem_price(Integer.parseInt(multi.getParameter("item_price")));
        dto.setItem_img_main(multi.getFilesystemName("item_img_main"));
        dto.setItem_img_sub(multi.getFilesystemName("item_img_sub"));
        dto.setItem_img_logo(multi.getFilesystemName("item_img_logo"));
        dto.setOptions_name(multi.getParameter("options_name"));
        dto.setOptions_value(multi.getParameter("options_value"));
        dto.setOptions_quantity(Integer.parseInt(multi.getParameter("options_quantity")));
    }

    private void setRentalFields(RentalDTO rdto, MultipartRequest multi) {
    	rdto.setRental_item_name(multi.getParameter("rental_item_name"));
    	rdto.setRental_item_price(Integer.parseInt(multi.getParameter("rental_item_price")));
    	rdto.setRental_days(Integer.parseInt(multi.getParameter("rental_days")));
    	rdto.setRental_img_main(multi.getFilesystemName("rental_img_main"));
    	rdto.setRental_img_sub(multi.getFilesystemName("rental_img_sub"));
    	rdto.setRental_img_logo(multi.getFilesystemName("rental_img_logo"));
    	rdto.setRental_opt_name(multi.getParameter("rental_opt_name"));
    	rdto.setRental_opt_value(multi.getParameter("rental_opt_value"));
    	rdto.setRental_opt_quantity(Integer.parseInt(multi.getParameter("rental_opt_quantity")));

    	}	
    }


