package egovframework.jtLunch.excel;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletResponse;

import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.ss.usermodel.BorderStyle;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.util.CellRangeAddress;
import org.apache.poi.xssf.usermodel.XSSFCell;
import org.apache.poi.xssf.usermodel.XSSFFont;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import egovframework.jtLunch.admin.cmmn.DTO.FranchiseDTO;
import egovframework.jtLunch.admin.cmmn.Service.AdminFranchiseService;
import egovframework.jtLunch.admin.dashboard.DTO.NoticeDTO;
import egovframework.jtLunch.admin.dashboard.Service.NoticeService;

@Controller
public class ExcelDownloadController {
	private static Logger log = LoggerFactory.getLogger(ExcelDownloadController.class);	
	
	@Autowired
	private NoticeService noticeService;
	@Autowired
	private AdminFranchiseService franchiseService;
	//@Autowired
	//private AdminDailyEatCountService eatCountService;
	
	@GetMapping("/excel/notice")
	public void noticeExcelDownData( @RequestParam Map<String, Object> searchData,  HttpServletResponse response) throws Exception{
		String type= (String)searchData.get("type");
		List<String> header = new ArrayList<>();
		List<List<String>> data = new ArrayList<>();
		
		// 엑셀 다운로드시 페이징없이 다운로드 하기위해
		searchData.put("excel", 1);
		
		log.info("검색 조건 : " + searchData);
		try {
			header.add(0, "NO");
			header.add(1, "TITLE");
			//header.add(2, "CONTENT");
			header.add(2, "WRITER");
			header.add(3, "DATE");
			header.add(4, "VIEW");
			
			List<NoticeDTO> list = noticeService.NoticeSearch(searchData);
			
			for(NoticeDTO vo : list) {
				List<String> notice = new ArrayList<>();
				
				notice.add(Integer.toString(vo.getNoticeId()));
				notice.add(vo.getTitle());
				//notice.add(vo.getContent());
				notice.add(vo.getWriter());
				notice.add(vo.getUploadDate());
				notice.add(Integer.toString(vo.getUserCheck()));

				data.add(notice);
			}
			
			excelDownload(type, header, data, response);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}

	@GetMapping("/excel/franchise")
	public void franchiseExcelDownData( @RequestParam Map<String, Object> searchData,  HttpServletResponse response) throws Exception{
		String type= (String)searchData.get("type");
		List<String> header = new ArrayList<>();
		List<List<String>> data = new ArrayList<>();
		
		// 엑셀 다운로드시 페이징없이 다운로드 하기위해
		searchData.put("excel", 1);
		
		log.info("검색 조건 : " + searchData);
		try {
			header.add(0, "분류");
			header.add(1, "아이디");
			header.add(2, "지점명");
			header.add(3, "전화번호");
			header.add(4, "주소");
			header.add(5, "데이터 전송 주소");
			
			List<FranchiseDTO> list = franchiseService.franchiseSearch(searchData);

			for(FranchiseDTO vo : list) {
				List<String> franchise = new ArrayList<>();
				
				if(vo.getTYPE() == 0) {
					franchise.add("일반");
				} else if(vo.getTYPE() == 1) {
					franchise.add("고급");
				}
				franchise.add(vo.getID());
				franchise.add(vo.getSHOPNAME());
				franchise.add(vo.getTEL());
				franchise.add("(" + vo.getZIP_CODE() + ") " + vo.getADDRESS() + " " + vo.getDETAIL_ADDRESS());
				franchise.add(vo.getDATAURL());

				data.add(franchise);
			}
			excelDownload(type, header, data, response);
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	public void excelDownload(@RequestParam String type, @RequestParam List<String> header, @RequestParam List<List<String>> data, HttpServletResponse response) throws IOException{
		XSSFWorkbook xssfWb = null;
		XSSFSheet xssfSheet = null;
		XSSFRow xssfRow = null;
		XSSFCell xssfCell = null;
		
		try {
			// 행의 갯수
			int rowNo = 0;
			
			// XSSFWorkbook 객체 생성
			xssfWb = new XSSFWorkbook();
			// 워크시트 이름 설정
			xssfSheet = xssfWb.createSheet(type + "List");
			
			// 폰트 스타일
			XSSFFont font = xssfWb.createFont();
			// 폰트 스타일
			font.setFontName(HSSFFont.FONT_ARIAL);
			// 폰트 크기
			font.setFontHeightInPoints((short)20);
			// 진하기 설정
			font.setBold(true);
			
			// 테이블 셀 스타일
			CellStyle cellStyle = xssfWb.createCellStyle();
			// 컬럼 너비 조절
			// 컬럼 너비 자동 설정
			/*
			 * for (int i=0; i<=6; i++){ xssfSheet.autoSizeColumn(i);
			 * xssfSheet.setColumnWidth(i, (xssfSheet.getColumnWidth(i))+(short)1024); }
			 */
			// cellStyle에 font를 적용
			cellStyle.setFont(font);
			// 정렬
			//cellStyle.setAlignment(HSSFCellStyle.class);
			
			// 타이틀 생성
			// 행 객체 추가
			xssfRow = xssfSheet.createRow(rowNo++);
			// 추가한 행에 셀 객체 추가
			xssfCell = xssfRow.createCell((short)0);
			// 셀에 스타일 지정
			xssfCell.setCellStyle(cellStyle);
			
			cellStyle.setBorderRight(BorderStyle.THIN);
			cellStyle.setBorderLeft(BorderStyle.THIN);
			cellStyle.setBorderTop(BorderStyle.THIN);
			cellStyle.setBorderBottom(BorderStyle.THIN);
			
			// 데이터 입력
			if(type.equals("todayEatCount")) {
				xssfCell.setCellValue("일간 식사자 수 목록");
			} else {
				xssfCell.setCellValue(type + "List");
			}
			
			// 셀병합
			// 첫행. 마지막행. 첫열. 마지막열 병합
			xssfSheet.addMergedRegion(new CellRangeAddress(0,1,0,5));
			// 가운데 정렬 (가로기준)
			cellStyle.setAlignment(HorizontalAlignment.CENTER);
			// 배경색
			//cellStyle.setFillBackgroundColor(HSSFColorPredefined.OLIVE_GREEN.getIndex());
			//cellStyle.setFillPattern(FillPatternType.SOLID_FOREGROUND);
			// 중앙 정렬 (세로기준)
			//cellStyle.setVerticalAlignment(CellStyle.VERTICAL_CENTER);
			
			xssfSheet.createRow(rowNo++);
			// 빈행 추가
			xssfRow = xssfSheet.createRow(rowNo++);

			for(int i = 0; i < header.size(); i++) {
				xssfRow.createCell(i).setCellValue(header.get(i));
			}
			
			for(int j = 0; j < data.size(); j++) {
				List<String> dataList = data.get(j);
				
				Row row = xssfSheet.createRow(rowNo++);
				
				for(int n = 0; n < header.size(); n++) {
					row.createCell(n).setCellValue(dataList.get(n));
				}

				for (int i = 0; i <= header.size(); i++){
					xssfSheet.autoSizeColumn(i);
					xssfSheet.setColumnWidth(i, (xssfSheet.getColumnWidth(i)) + (short)2048);
				}
			}
			
			response.setContentType("ms-vnd/excel");
			response.setHeader("Content-Disposition", "attachment;filename=" + type + "List.xlsx");
			
			xssfWb.write(response.getOutputStream());
			xssfWb.close();

		} catch(Exception e) {
			e.printStackTrace();
		}
	}
}
