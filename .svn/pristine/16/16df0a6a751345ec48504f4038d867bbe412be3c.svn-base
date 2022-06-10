package egovframework.jtLunch.main.qrcode.Controller;

import java.io.PrintWriter;

import javax.inject.Inject;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import egovframework.jtLunch.main.qrcode.DTO.QrCodeDTO;
import egovframework.jtLunch.main.qrcode.Service.QrCodeService;
import egovframework.jtLunch.main.reservation.DTO.ReservationDTO;


@Controller
public class QrCodeController {
	
	@Inject
	QrCodeService qrcodeService;
	
	@RequestMapping(value="/user/qrcodeMain", method=RequestMethod.GET)
	public String qrcodeMain(Model model, HttpServletResponse response) throws Exception{
		response.setCharacterEncoding("UTF-8");
		response.setContentType("text/html; charset=utf-8");
		PrintWriter out=response.getWriter();
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String id = authentication.getName();
		QrCodeDTO vo = qrcodeService.selectCheck(id);
		
		try {
			
			if(vo.getId() != null) {
				out.println("<script>alert('이미 확인하셨습니다.'); location.href='/user/menuplanner';</script>");
				out.flush();
				return "/main/menuplanner/menuPlanner";
			}
		
		} catch(Exception e) {
			System.out.println(e.getMessage());
	}
		return "/user/qrscan";
		
	}

	@RequestMapping(value="/user/qrcode", method=RequestMethod.GET)
	public String qrcodeScan(Model model) throws Exception{
		
		Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
		String id = authentication.getName();
		ReservationDTO dto = qrcodeService.selectRes(id);
			
			if(dto != null) 
			{
				if(dto.getRes_check() == 0) 
				{
					qrcodeService.updateRes(id);
					qrcodeService.insertRes(id);
				}
			} 
			else if(dto == null)
			{
				qrcodeService.insertnoRes(id);
				qrcodeService.insertnoResate(id);
			}
		
		return "redirect:/user/menuplanner";
	}

}
